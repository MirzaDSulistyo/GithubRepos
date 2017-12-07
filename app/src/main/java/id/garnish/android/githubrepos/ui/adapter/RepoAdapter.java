package id.garnish.android.githubrepos.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.garnish.android.githubrepos.R;
import id.garnish.android.githubrepos.api.model.GitHubRepo;

/**
 * Created by Garnish.id4 on 12/7/2017.
 */

public class RepoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<GitHubRepo> gitHubRepos;

    public RepoAdapter(List<GitHubRepo> gitHubRepos) {
        this.gitHubRepos = gitHubRepos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ReposViewHolder viewHolder;

        View view = inflater.inflate(R.layout.content_repos, parent, false);
        viewHolder = new ReposViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setViewHolder((ReposViewHolder)holder, position);
    }

    @Override
    public int getItemCount() {
        if (gitHubRepos != null) {
            return gitHubRepos.size();
        }
        return 0;
    }

    private void setViewHolder(ReposViewHolder viewHolder, int position) {
        GitHubRepo repo = gitHubRepos.get(position);

        viewHolder.name.setText(repo.getName());
    }

    private class ReposViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public ReposViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.tv_repos_name);
        }
    }
}
