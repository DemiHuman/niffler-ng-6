package guru.qa.niffler.api;

import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Date;
import java.util.List;

public interface SpendApi {

  @POST("internal/spends/add")
  @ResponseStatus(HttpStatus.CREATED)
  Call<SpendJson> addSpend(@Body SpendJson spend);

  @PATCH("internal/spends/edit")
  Call<SpendJson> editSpend(@Body SpendJson spend);

  @GET("internal/spends/{id}")
  Call<SpendJson> getSpend(@Path("id") Integer id);

  @GET("internal/spends/all")
  Call<List<SpendJson>> getSpend(
          @Query("username") String username,
          @Query("filterCurrency") CurrencyValues filterCurrency,
          @Query("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
          @Query("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date to
  );

  @DELETE("internal/spends/remove")
  @ResponseStatus(HttpStatus.ACCEPTED)
  Call<ResponseStatus> deleteSpend(
          @Query("username") String username,
          @Query("ids") List<String> ids
  );

  @PATCH("internal/categories/update")
  Call<CategoryJson> updateCategory(@Body CategoryJson category);

  @GET("internal/categories/all")
  Call <List<CategoryJson>> getCategories(
          @Query("username") String username,
          @Query("excludeArchived") boolean excludeArchived
  );
}
