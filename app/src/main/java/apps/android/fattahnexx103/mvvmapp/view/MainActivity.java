package apps.android.fattahnexx103.mvvmapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import apps.android.fattahnexx103.mvvmapp.R;
import apps.android.fattahnexx103.mvvmapp.adapters.DataAdapter;
import apps.android.fattahnexx103.mvvmapp.model.DataModel;
import apps.android.fattahnexx103.mvvmapp.viewmodel.DataViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.layout_swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recyclerView_data)
    RecyclerView recyclerView;

    @BindView(R.id.textView_listError)
    TextView errorText;

    @BindView(R.id.progressBar_loading)
    ProgressBar loadingProgressbar;

    private DataViewModel viewModel; //reference to view model
    private DataAdapter dataAdapter = new DataAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //instantiate view model
        viewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        viewModel.refresh();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dataAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refresh(); //refresh the viewmodel on swipe
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        observeViewModel(); //this method is responsible for observing view model
    }

    private void observeViewModel() {

        viewModel.dataList.observe(this, new Observer<List<DataModel>>() {
            @Override
            public void onChanged(List<DataModel> dataModelList) {
                if(dataModelList != null){
                    recyclerView.setVisibility(View.VISIBLE); //only if there is data we make it visible
                    dataAdapter.updateData(dataModelList); //add the data to the adapter which in turn refreshes the view
                }
            }
        });
        viewModel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                if(isError != null ){
                    if(isError){
                        loadingProgressbar.setVisibility(View.VISIBLE);
                        errorText.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                    }else{
                        loadingProgressbar.setVisibility(View.GONE);
                    }

                }
            }
        });
        viewModel.dataLoadError.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                if(isError != null ){
                    if(isError){
                        errorText.setVisibility(View.VISIBLE);
                    }
                    else{
                        errorText.setVisibility(View.GONE);
                    }
                }
            }
        });

    }
}
