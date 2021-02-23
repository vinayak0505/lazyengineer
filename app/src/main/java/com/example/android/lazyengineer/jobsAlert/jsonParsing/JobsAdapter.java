package com.example.android.lazyengineer.jobsAlert.jsonParsing;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.lazyengineer.R;
import com.example.android.lazyengineer.jobsAlert.Jobs;

import java.util.List;

public class JobsAdapter extends ArrayAdapter<Jobs> {
    public JobsAdapter(@NonNull Context context, @NonNull List<Jobs> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.jobs_listview, parent, false);

        final Jobs currentJob = getItem(position);

        TextView title = view.findViewById(R.id.jobs_title);
        assert currentJob != null;
        title.setText(currentJob.getTitle());

        TextView role = view.findViewById(R.id.jobs_role);
        role.setText(currentJob.getRole());

        TextView location = view.findViewById(R.id.jobs_location);
        location.setText(currentJob.getLocation());

        TextView salary = view.findViewById(R.id.jobs_salary);
        salary.setText(currentJob.getSalary());

        view.findViewById(R.id.jobs_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(currentJob.getApplyUrl()));
                getContext().startActivity(i);
            }
        });

        return view;
    }
}
