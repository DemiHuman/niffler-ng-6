package guru.qa.niffler.jupiter;

import guru.qa.niffler.api.SpendApiClient;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpendingExtension implements BeforeEachCallback, ParameterResolver, AfterTestExecutionCallback {

  public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(SpendingExtension.class);

  private final SpendApiClient spendApiClient = new SpendApiClient();

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), Spending.class)
        .ifPresent(anno -> {
          SpendJson spend = new SpendJson(
              null,
              new Date(),
              new CategoryJson(
                  null,
                  anno.category(),
                  anno.username(),
                  false
              ),
              CurrencyValues.RUB,
              anno.amount(),
              anno.description(),
              anno.username()
          );
          context.getStore(NAMESPACE).put(
              context.getUniqueId(),
              spendApiClient.createSpend(spend)
          );
        });
  }

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return parameterContext.getParameter().getType().isAssignableFrom(SpendJson.class);
  }

  @Override
  public SpendJson resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId(), SpendJson.class);
  }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        SpendJson spend = context.getStore(SpendingExtension.NAMESPACE)
                .get(context.getUniqueId(), SpendJson.class);

        if (spend != null) {
            // создаем объект с archived = true
            List<String> isd = new ArrayList<>(){{
                add(spend.id().toString());
            }};
            spendApiClient.deleteSpend(spend.username(), isd);
        }
    }
}
