package com.example.android.lazyengineer.JobsAlert;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.lazyengineer.JobsAlert.jsonParsing.JobsAdapter;
import com.example.android.lazyengineer.JobsAlert.jsonParsing.Jobs;
import com.example.android.lazyengineer.JobsAlert.jsonParsing.JobsLoader;
import com.example.android.lazyengineer.R;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class JobsAlertActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Jobs>> {

    private static final String mainUrl = "https://lazyengineer.tech/leapi/jobs/viewjobs/?format=json&numbers=20";
    JobsAdapter jobsAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_alert);

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            jobsAdapter = new JobsAdapter(this, new ArrayList<Jobs>());
            listView = findViewById(R.id.jobs_listView);
            listView.setAdapter(jobsAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Jobs currentJob = jobsAdapter.getItem(position);
                    assert currentJob != null;
                    Toast.makeText(JobsAlertActivity.this, "id is " + currentJob.getId(), Toast.LENGTH_SHORT).show();
                }
            });
            LoaderManager.getInstance(this).initLoader(1, null, this).forceLoad();
        }
    }

    @NonNull
    @Override
    public Loader<List<Jobs>> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == 1) {
            try {
                return new JobsLoader(this, mainUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Jobs>> loader, List<Jobs> data) {
        if (data != null && !data.isEmpty()) {
            ProgressBar loadingProgressBar = findViewById(R.id.loading);
            loadingProgressBar.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            jobsAdapter.clear();
            jobsAdapter.addAll(data);
        }
    }


    @Override
    public void onLoaderReset(@NonNull Loader<List<Jobs>> loader) {
        loader.reset();
    }
}