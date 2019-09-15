package id.co.countrypedia_v2.networkUtils;

import java.util.List;

import javax.inject.Inject;

import id.co.countrypedia_v2.di.DaggerAPIComponent;
import id.co.countrypedia_v2.model.CountryModel;
import io.reactivex.Single;

public class APICore {


    private static APICore instance;

    @Inject
    public APIService apiService;
//    public APIService connectApi = new Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//            .create(APIService.class);

    private APICore () {
        DaggerAPIComponent.create().injectData(this);
    }

    public static APICore getInstance(){
        if (instance == null) {
            instance = new APICore();
        }
        return instance;
    }

    public Single<List<CountryModel>> getDataCountries(){
        return apiService.getCountries();
    }
}
