package com.homeautogroup.meru.api;

import com.homeautogroup.meru.models.Departments;
import com.homeautogroup.meru.models.Project;
import com.homeautogroup.meru.models.Projects;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface APIService {


   /* //getting messages
    @GET("messages/{id}")
    Call<Messages> getMessages(@Path("id") int id);*/

    @GET("api/projects")
    Call<List<Projects>> getProjects();


    @GET("api/departments")
    Call<List<Departments>> getDepartments();

    //get projects by department
    @GET("api/departments/{id}/projects")
    Call<List<Projects>> getProjectsByDepartment(@Path("id") String id);

    //get single project by ID
    @GET("api/projects/{id}")
    Call<List<Project>> getSingleProjects(@Path("id") String id);

}
