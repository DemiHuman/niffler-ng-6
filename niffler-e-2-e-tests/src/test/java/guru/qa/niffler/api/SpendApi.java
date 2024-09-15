package guru.qa.niffler.api;

import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Date;
import java.util.List;

public interface SpendApi {

  @POST("internal/spends/add")
  Call<SpendJson> addSpend(@Body SpendJson spend);

  @PATCH("internal/spends/edit")
  Call<SpendJson> editSpend(@Body SpendJson spend);

  @GET("internal/spends/{id}")
  Call<SpendJson> getSpend(@Path("id") Integer id);

  @GET("internal/spends/all")
  Call<List<SpendJson>> getSpend(
          @RequestParam String username,
          @RequestParam(required = false) CurrencyValues filterCurrency,
          @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
          @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to
  );

  @DELETE("internal/spends/remove")
  void deleteSpend(
          @RequestParam String username,
          @RequestParam List<String> ids
  );

  @PATCH("internal/categories/update")
  Call<CategoryJson> updateCategory(@Body CategoryJson category);

  @GET("internal/categories/all")
  Call <List<CategoryJson>> getCategories(
          @RequestParam String username,
          @RequestParam(required = false, defaultValue = "false") boolean excludeArchived
  );
}
