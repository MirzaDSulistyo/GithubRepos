package id.garnish.android.githubrepos.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import id.garnish.android.githubrepos.R;
import id.garnish.android.githubrepos.api.model.GitHubRepo;
import id.garnish.android.githubrepos.api.service.GitHubClient;
import id.garnish.android.githubrepos.ui.adapter.RepoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // create Retrofit builder
        // add a converter, need Gson to convert between Java objects and JSON
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        // create actual Retrofit objects
        Retrofit retrofit = builder.build();

        // do actual request
        // create instance of GitHubClient
        GitHubClient client = retrofit.create(GitHubClient.class);

        // call actual method on our client
        // which return Call<> Object
        Call<List<GitHubRepo>> call = client.reposForUser("MirzaDSulistyo");

        // since it's in MainActivity (UI Thread), it'll execute asynchronously
        // with the method : enqueue, and it expects a callback
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                // if got a response from the server
                // body is a List<GitHubRepo>
                List<GitHubRepo> repos = response.body();

                recyclerView.setAdapter(new RepoAdapter(repos));
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                // will be call if there's a network failure
                Toast.makeText(MainActivity.this, "Error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
