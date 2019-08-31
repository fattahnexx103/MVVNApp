package apps.android.fattahnexx103.mvvmapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import apps.android.fattahnexx103.mvvmapp.R;
import apps.android.fattahnexx103.mvvmapp.model.DataModel;
import apps.android.fattahnexx103.mvvmapp.view.Util;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private List<DataModel> dataModelList;

    //constructor of adapater
    public DataAdapter(List<DataModel> dataModelList){
        this.dataModelList = dataModelList;
    }

    //replaces old data with new data
    public void updateData(List<DataModel> newDataList){
        dataModelList.clear();
        dataModelList.addAll(newDataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //create a view and make a viewholder out of it
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        return new DataViewHolder(view); //view holder expects a view which we then pass to it.
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        //bind the viewholder
        holder.bind(dataModelList.get(position)); //calls the bind method of the holder

    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.card_image)
        ImageView dataImage;

        @BindView(R.id.textView_card_title)
        TextView cardTitle;

        @BindView(R.id.textView_card_description)
        TextView cardDescription;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(DataModel dataModel){
            //feed the views the data
           cardTitle.setText(dataModel.getTitle());
           cardDescription.setText(dataModel.getDescription());
           Util.loadImage(dataImage, dataModel.getPicUrl(), Util.getProgressDrawable(dataImage.getContext()));
        }
    }
}
