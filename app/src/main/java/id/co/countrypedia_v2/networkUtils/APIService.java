package id.co.countrypedia_v2.networkUtils;

import java.util.List;

import id.co.countrypedia_v2.model.CountryModel;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface APIService {

    @GET("DevTides/countries/master/countriesV2.json")
    Single<List<CountryModel>>getCountries();




}
