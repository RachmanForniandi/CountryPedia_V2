package id.co.countrypedia_v2.networkUtils;

import java.util.List;

import id.co.countrypedia_v2.model.CountryModel;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APICore {
    private static final String BASE_URL ="https://raw.githubusercontent.com";

    private static APICore instance;

    public APIService connectApi = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(APIService.class);

    private APICore () {
    }

    public static APICore getInstance(){
        if (instance == null) {
            instance = new APICore();
        }
        return instance;
    }

    public Single<List<CountryModel>>getCountries(){
        return connectApi.getCountries();
    }
}
