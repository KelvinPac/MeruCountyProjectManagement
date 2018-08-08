package com.homeautogroup.meru.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.homeautogroup.meru.R;
import com.homeautogroup.meru.activities.SettingsActivity;
import com.homeautogroup.meru.adapters.ProjectsAdapter;
import com.homeautogroup.meru.api.APIService;
import com.homeautogroup.meru.api.APIUrl;
import com.homeautogroup.meru.models.Projects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllProjectsFragment extends Fragment {

    private Context context;
    private List<Projects> projectsList;
    private final static String TAG = AllProjectsFragment.class.getSimpleName();
    private ProgressBar progressBar;
    RecyclerView recyclerView;
    public AllProjectsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_all_projects, container, false);

        //RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        progressBar = view.findViewById(R.id.progressBar);
        projectsList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //
        fetchProjects();
        return view;
    }

    /*
     * Fetch projects retrofit*/
    private void fetchProjects() {
        showProgress();

        String url = APIUrl.getServerUrl(context);
        if (url==null){
            startActivity(new Intent(context,SettingsActivity.class));
            Objects.requireNonNull(getActivity()).finish();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<List<Projects>> call = service.getProjects();

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
                            Toast.makeText(context, "No projects here yet", Toast.LENGTH_LONG).show();
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
