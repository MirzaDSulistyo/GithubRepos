package id.garnish.android.githubrepos.api.service;

import java.util.List;

import id.garnish.android.githubrepos.api.model.GitHubRepo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Garnish.id on 12/7/2017.
 */

public interface GitHubClient {

    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> reposForUser(@Path("user") String user);
}
