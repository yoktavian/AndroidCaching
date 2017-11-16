package yoktavian.dev.testingcache.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by yoktavian on 11/16/17.
 */

public class ActorList {
    @SerializedName("actors")
    @Expose
    private ArrayList<ActorModels> actors = new ArrayList<>();

    public ArrayList<ActorModels> getActors(){
        return actors;
    }

    public void setActors(ArrayList<ActorModels> actors){
        this.actors = actors;
    }
}
