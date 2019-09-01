package apps.android.fattahnexx103.mvvmapp.di;

import apps.android.fattahnexx103.mvvmapp.model.ApiService;
import apps.android.fattahnexx103.mvvmapp.model.DataApi;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private final String BASE_URL = "https://raw.githubusercontent.com";

    @Provides
    public DataApi provideDataApi(){

            return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(DataApi.class);
    }

    @Provides
    public ApiService provideApiService(){
        return ApiService.getInstance();
    }

}
