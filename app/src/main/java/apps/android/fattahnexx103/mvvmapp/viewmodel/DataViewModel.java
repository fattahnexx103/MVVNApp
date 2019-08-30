package apps.android.fattahnexx103.mvvmapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import apps.android.fattahnexx103.mvvmapp.model.DataModel;

public class DataViewModel extends ViewModel {

    //livedata generates values and is an observable
    //mutable livedata is livedata which can be set to something

    public MutableLiveData<List<DataModel>> dataList = new MutableLiveData<List<DataModel>>();

    public MutableLiveData<Boolean> dataLoadError = new MutableLiveData<Boolean>();

    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public void refresh(){
        fetchCountries();
    }

    private void fetchCountries(){
        DataModel dataModel = new DataModel("T1", "Description 1", "https://1");
        DataModel dataModel2 = new DataModel("T2", "Description 2", "https://2");
        DataModel dataModel3 = new DataModel("T3", "Description 3", "https://3");

        List<DataModel> dataModelsList = new ArrayList<>();
        dataModelsList.add(dataModel);
        dataModelsList.add(dataModel2);
        dataModelsList.add(dataModel3);

        //update the LiveData
        dataList.setValue(dataModelsList);
        dataLoadError.setValue(false);
        loading.setValue(false);

    }

}
