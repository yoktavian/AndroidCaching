package yoktavian.dev.testingcache;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Callback;
import retrofit2.Response;
import yoktavian.dev.testingcache.adapter.AdapterActor;
import yoktavian.dev.testingcache.models.ActorList;
import yoktavian.dev.testingcache.models.ActorModels;
import yoktavian.dev.testingcache.network.ApiClient;
import yoktavian.dev.testingcache.network.ApiService;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterActor adapter;
    private ArrayList<ActorModels> models;
    private static final String TAG = "=RESPONSE=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getData();
    }

    private void getData(){
        ApiService api = ApiClient.getApiService();
        retrofit2.Call<ActorList> call = api.getData();
        call.enqueue(new Callback<ActorList>() {
            @Override
            public void onResponse(retrofit2.Call<ActorList> call, Response<ActorList> response) {
                if (response.isSuccessful()){
                    getSupportActionBar().setTitle("Data From Server");
                    models = response.body().getActors();
                    write();
                    setData();
                } else {
                    Log.e(TAG, "error");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ActorList> call, Throwable t) {
                Log.e(TAG, "error "+String.valueOf(t.getMessage()));
                read();
            }
        });
    }

    private void write(){
        String json = new Gson().toJson(models);
        try {
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream
                    (new File(MainActivity.this.getCacheDir(), "") + File.separator + "cacheFile.srl"));
            out.writeObject(json);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(){
        String dat= null;
        getSupportActionBar().setTitle("Data From Cache");
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream
                    (new File(MainActivity.this.getCacheDir() + File.separator + "cacheFile.srl")));
            dat = (String) in.readObject();
            Log.d(TAG,dat);
            in.close();
            models = new Gson().fromJson(dat, new TypeToken<ArrayList<ActorModels>>() {
            }.getType());
            setData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (OptionalDataException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            getSupportActionBar().setTitle("No cache");
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setData(){
        adapter = new AdapterActor(this, models, new AdapterActor.OnItemClickListener() {
            @Override
            public void onClick(ActorModels item) {

            }
        });
        recyclerView.setAdapter(adapter);
    }
}
