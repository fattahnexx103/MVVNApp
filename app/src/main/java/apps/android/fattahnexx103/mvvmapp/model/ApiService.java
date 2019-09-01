package apps.android.fattahnexx103.mvvmapp.model;

import java.util.List;

import javax.inject.Inject;

import apps.android.fattahnexx103.mvvmapp.di.DaggerApiComponent;
import io.reactivex.Single;

public class ApiService {


    private static ApiService instance;

    @Inject
    public DataApi api;

//    private DataApi api = new Retrofit.Builder()
//            .baseUrl(API_ENDPOINT)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//            .create(DataApi.class);

    private ApiService(){
        DaggerApiComponent.create().injectApiService(this);
    }

    public static ApiService getInstance(){
        if(instance == null){
            instance = new ApiService();
        }
        return instance;
    }

    public Single<List<DataModel>> getDataList(){
        return api.getData();
    }
}
