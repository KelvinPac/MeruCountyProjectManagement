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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.homeautogroup.meru.R;
import com.homeautogroup.meru.activities.DepartmentActivity;
import com.homeautogroup.meru.activities.SettingsActivity;
import com.homeautogroup.meru.api.APIUrl;
import com.homeautogroup.meru.models.Departments;
import com.homeautogroup.meru.models.Projects;

import java.util.List;

/**
 * Meru Created by kelvin on 8/7/18.
 * com.homeautogroup.meru
 * flyboypac@gmail.com
 * +254705419309
 */
public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.MyViewHolder> {

    private Context mContext;
    private List<Departments> departmentsList;




    public DepartmentAdapter(Context mContext, List<Departments> projectsLists) {
        this.mContext = mContext;
        this.departmentsList = projectsLists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       /* View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_card, parent, false);

        return new MyViewHolder(itemView);*/

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.department_card, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        //getting the product of the specified position
        final Departments departments = departmentsList.get(position);

        holder.textViewDepartment.setText(departments.getDepartment_name());
        String url = APIUrl.getServerUrl(mContext);
        if (url!=null){
            Glide.with(mContext).load(url+departments.getDepartment_photo()).into(
                    holder.imageView
            );
        }

        //holder.imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mContext, departments.getDepartment_name(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, DepartmentActivity.class);
                intent.putExtra(DepartmentActivity.DEP_EXTRA, departments);
                //intent.putExtra(ACTIVITY_START_KEY, START_KEY_FROM_INTERNAL_CLICK);
                mContext.startActivity(intent);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDepartment;
        ImageView imageView;
        View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            textViewDepartment = itemView.findViewById(R.id.textViewDepartment);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return departmentsList.size();
    }
}
