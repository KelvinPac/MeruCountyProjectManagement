package com.homeautogroup.meru.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.homeautogroup.meru.R;
import com.homeautogroup.meru.adapters.ProjectsAdapter;
import com.homeautogroup.meru.api.APIService;
import com.homeautogroup.meru.api.APIUrl;
import com.homeautogroup.meru.fragments.AllProjectsFragment;
import com.homeautogroup.meru.models.Departments;
import com.homeautogroup.meru.models.Projects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DepartmentActivity extends AppCompatActivity {

    private Departments department;
    public static final String DEP_EXTRA = "department_extra";
    private ImageView headerImage;
    private TextView depDescription;
    private List<Projects> projectsList;
    private final static String TAG = DepartmentActivity.class.getSimpleName();
    private ProgressBar progressBar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        ///
        headerImage = findViewById(R.id.imageViewDep);
        depDescription = findViewById(R.id.textViewDepDescription);
        Intent i = getIntent();

        department = (Departments)i.getSerializableExtra(DEP_EXTRA);
        getSupportActionBar().setTitle(department.getDepartment_name());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        depDescription.setText(department.getDepartment_description());
        String id = department.getDepartment_id();

        String url = APIUrl.getServerUrl(this);
        if (url==null){
            startActivity(new Intent(this,SettingsActivity.class));
            finish();
            return;
        }

        Glide.with(this).load(url+department.getDepartment_photo()).into(headerImage);

        progressBar = findViewById(R.id.progressBar);
        projectsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //
        fetchProjects(this,id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    /*
     * Fetch projects retrofit*/
    private void fetchProjects(final Context context,String id) {
        showProgress();

        String url = APIUrl.getServerUrl(context);
        if (url==null){
            startActivity(new Intent(context,SettingsActivity.class));
            finish();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<List<Projects>> call = service.getProjectsByDepartment(id);

        call.enqueue(new Callback<List<Projects>>() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onResponse(@NonNull Call<List<Projects>> call, @NonNull Response<List<Projects>> response) {
                hideProgress();
                /*
                 * All successful responses from crypto compare have a http code 200
                 * Check if http code is 200 then continue
                 * */
                if (response.code() == 200) {


                    try {
                        projectsList = response.body();
                        ProjectsAdapter projectsAdapter = new ProjectsAdapter(context,projectsList);
                        recyclerView.setAdapter(projectsAdapter);
                        if(projectsList.size()==0){
                            Toast.makeText(DepartmentActivity.this, "No projects here yet", Toast.LENGTH_LONG).show();
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                    }


                }else {
                    Toast.makeText(context, "Some error occurred while fetching projects", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Projects>> call, @NonNull Throwable t) {
                hideProgress();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void hideProgress(){
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private void showProgress(){
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }
}
