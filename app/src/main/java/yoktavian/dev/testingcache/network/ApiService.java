package yoktavian.dev.testingcache.network;

import retrofit2.Call;
import retrofit2.http.GET;
import yoktavian.dev.testingcache.models.ActorList;

/**
 * Created by yoktavian on 11/17/17.
 */

public interface ApiService {
    @GET("http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors")
    Call<ActorList> getData();
}
