package apps.android.fattahnexx103.mvvmapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface DataApi {

    @GET("DevTides/countries/master/countriesV2.json")
    Single<List<DataModel>> getData(); //single is type of observable which ommits only one value

}
