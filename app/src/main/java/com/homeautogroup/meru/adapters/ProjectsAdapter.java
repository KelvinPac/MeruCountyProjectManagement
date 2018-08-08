package com.homeautogroup.meru.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.homeautogroup.meru.R;
import com.homeautogroup.meru.activities.ProjectViewActivity;
import com.homeautogroup.meru.api.APIUrl;
import com.homeautogroup.meru.models.Projects;

import java.util.List;

/**
 * Meru Created by kelvin on 8/7/18.
 * com.homeautogroup.meru
 * flyboypac@gmail.com
 * +254705419309
 */
public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Projects> projectsList;




    public ProjectsAdapter(Context mContext, List<Projects> projectsLists) {
        this.mContext = mContext;
        this.projectsList = projectsLists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       /* View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_card, parent, false);

        return new MyViewHolder(itemView);*/

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.project_card, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        //getting the product of the specified position
        final Projects project = projectsList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(project.getProject_name());
        holder.textViewShortDesc.setText(project.getProject_description());
        String completion = project.getCompletion();
        if (completion.equals("100")){
            holder.textViewRating.setText("Completed");
        }else {
            holder.textViewRating.setText(completion+"%"+" complete");
        }
        holder.textViewPrice.setText("Ksh: "+project.getBudget());

        String url = APIUrl.getServerUrl(mContext);
        if (url!=null){
            Glide.with(mContext).load(url+project.getPhoto_path()).into(
                    holder.imageView
            );
        }
        //holder.imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ProjectViewActivity.class);
                intent.putExtra(ProjectViewActivity.PROJECT_EXTRA, project);
                mContext.startActivity(intent);
            }
        });

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;
        View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return projectsList.size();
    }
}
