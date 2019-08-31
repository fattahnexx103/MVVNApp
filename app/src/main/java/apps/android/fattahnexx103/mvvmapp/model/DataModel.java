package apps.android.fattahnexx103.mvvmapp.model;

import com.google.gson.annotations.SerializedName;

//Model Class
public class DataModel {

    @SerializedName("name")
    private String title;

    @SerializedName("capital")
    private String description;

    @SerializedName("flagPNG")
    private String picUrl;

    public DataModel(String title, String description, String picUrl) {
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPicUrl() {
        return picUrl;
    }

}
