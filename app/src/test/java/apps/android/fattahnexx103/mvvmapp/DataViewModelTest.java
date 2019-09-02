package apps.android.fattahnexx103.mvvmapp;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import apps.android.fattahnexx103.mvvmapp.model.ApiService;
import apps.android.fattahnexx103.mvvmapp.model.DataModel;
import apps.android.fattahnexx103.mvvmapp.viewmodel.DataViewModel;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class DataViewModelTest {

    //we need to handle the threading for the tests

    //its a rule that any task execution is instant
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock //since we have injected this we can just say mock
    ApiService apiService;

    @InjectMocks //location of where the mock was injected aka apiservice
    DataViewModel dataViewModel = new DataViewModel();

    private Single<List<DataModel>> testSingle; //we need a single since that is the return type of the fetch data

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this); //method for initiating mocks
    }

    @Test
    public void getSuccess(){
        DataModel model = new DataModel("Title1", "desc1", "pic1");
        ArrayList<DataModel> dataModelArrayList = new ArrayList<>();
        dataModelArrayList.add(model);

        testSingle = Single.just(dataModelArrayList);
        Mockito.when(apiService.getDataList()).thenReturn(testSingle); //whenever you call getDataL

        dataViewModel.refresh();

        Assert.assertEquals(1, dataViewModel.dataList.getValue().size());
        Assert.assertEquals(false, dataViewModel.loading.getValue());
        Assert.assertEquals(false, dataViewModel.dataLoadError.getValue());
    }

    @Test
    public void getFailure(){
        testSingle = Single.error(new Throwable());

        Mockito.when(apiService.getDataList()).thenReturn(testSingle);

        dataViewModel.refresh();

        Assert.assertEquals(true, dataViewModel.dataLoadError.getValue());
        Assert.assertEquals(true, dataViewModel.loading.getValue());
    }

    //runs before every test
    @Before
    public void setupRxSchedulers(){
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(new Executor() {
                    @Override
                    public void execute(Runnable runnable) {
                        runnable.run();
                    }
                },true);
            }
        };

        RxJavaPlugins.setInitNewThreadSchedulerHandler(  scheduler -> immediate); //takes in scheduler and returns immediate
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }
}
