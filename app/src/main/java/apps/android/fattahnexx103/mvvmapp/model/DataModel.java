package apps.android.fattahnexx103.mvvmapp.model;

//Model Class
public class DataModel {

    private String title;
    private String description;
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
