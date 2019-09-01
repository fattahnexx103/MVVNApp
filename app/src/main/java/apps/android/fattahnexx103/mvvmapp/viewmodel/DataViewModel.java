package apps.android.fattahnexx103.mvvmapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import apps.android.fattahnexx103.mvvmapp.di.DaggerApiComponent;
import apps.android.fattahnexx103.mvvmapp.model.ApiService;
import apps.android.fattahnexx103.mvvmapp.model.DataModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DataViewModel extends ViewModel {

    //livedata generates values and is an observable
    //mutable livedata is livedata which can be set to something

    public MutableLiveData<List<DataModel>> dataList = new MutableLiveData<List<DataModel>>();

    public MutableLiveData<Boolean> dataLoadError = new MutableLiveData<Boolean>();

    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    @Inject
    public ApiService service;

    private CompositeDisposable disposable = new CompositeDisposable(); //this is to handle data during interruptions like closing the app

    public DataViewModel(){
        super();
        DaggerApiComponent.create().inject(this);
    }

    public void refresh(){
        fetchCountries();
    }

    private void fetchCountries(){
        loading.setValue(true);
        disposable.add(
                service.getDataList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DataModel>>() {
                    @Override
                    public void onSuccess(List<DataModel> dataModelList) {
                        dataList.setValue(dataModelList);
                        dataLoadError.setValue(false);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        dataLoadError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();
                    }
                })
        );

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear(); //remove the data from disposable
    }
}
