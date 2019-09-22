package id.co.countrypedia_v2.di;

import dagger.Module;
import dagger.Provides;
import id.co.countrypedia_v2.networkUtils.APICore;
import id.co.countrypedia_v2.networkUtils.APIService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class APIModule {
    private static final String BASE_URL ="https://raw.githubusercontent.com";

    @Provides
    public APIService provideApiService(){
        return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(APIService.class);
        //    public APIService connectApi = new Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//            .create(APIService.class);
    }

    @Provides
    public APICore provideApiCore(){
        return APICore.getInstance();
    }
}
