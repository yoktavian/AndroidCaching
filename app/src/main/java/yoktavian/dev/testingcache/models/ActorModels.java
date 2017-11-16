package yoktavian.dev.testingcache.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yoktavian on 11/16/17.
 */

public class ActorModels {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("image")
    @Expose
    private String img_url;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDob() {
        return dob;
    }

    public String getCountry() {
        return country;
    }

    public String getImg_url() {
        return img_url;
    }
}
