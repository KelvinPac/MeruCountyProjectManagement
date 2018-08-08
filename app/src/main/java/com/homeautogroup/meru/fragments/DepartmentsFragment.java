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
import com.homeautogroup.meru.adapters.DepartmentAdapter;
import com.homeautogroup.meru.api.APIService;
import com.homeautogroup.meru.api.APIUrl;
import com.homeautogroup.meru.models.Departments;

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
public class DepartmentsFragment extends Fragment {

    private View view;
    private Context context;
    private List<Departments> departmentsList;
    private final static String TAG = DepartmentsFragment.class.getSimpleName();
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public DepartmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = inflater.getContext();
        view =  inflater.inflate(R.layout.fragment_departments, container, false);

        progressBar = view.findViewById(R.id.progressBarDepList);
        departmentsList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerViewDepList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //
        fetchDepartments();

        return view;
    }

    /*
     * Fetch projects retrofit*/
    private void fetchDepartments() {
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

        Call<List<Departments>> call = service.getDepartments();

        call.enqueue(new Callback<List<Departments>>() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onResponse(@NonNull Call<List<Departments>> call, @NonNull Response<List<Departments>> response) {
                hideProgress();
                /*
                 * All successful responses from crypto compare have a http code 200
                 * Check if http code is 200 then continue
                 * */
                if (response.code() == 200) {
                    try {
                        Log.e(TAG, response.body().toString());

                        // List<Projects> pro = response.body();
                        departmentsList = response.body();
                        DepartmentAdapter departmentAdapter = new DepartmentAdapter(context,departmentsList);
                        recyclerView.setAdapter(departmentAdapter);
                        if(departmentsList.size()==0){
                            Toast.makeText(context, "No Departments here yet", Toast.LENGTH_LONG).show();
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, "Some error occurred while fetching departments", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Departments>> call, @NonNull Throwable t) {
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
