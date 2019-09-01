package apps.android.fattahnexx103.mvvmapp.di;

import apps.android.fattahnexx103.mvvmapp.model.ApiService;
import apps.android.fattahnexx103.mvvmapp.viewmodel.DataViewModel;
import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void injectApiService(ApiService service); //inject at the ApiService

    void inject(DataViewModel dataViewModel); //inject into that class
}
