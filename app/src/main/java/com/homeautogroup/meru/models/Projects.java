package com.homeautogroup.meru.models;

import java.io.Serializable;

/**
 * Meru Created by kelvin on 8/7/18.
 * com.homeautogroup.meru.models
 * flyboypac@gmail.com
 * +254705419309
 */
public class Projects implements Serializable {
    private String budget;

    private String project_name;

    private String project_constituency;

    private String contractor;

    private String project_category;

    private String photo_path;

    private String completion;

    private String project_description;

    private String project_ward;

    private String updated_at;

    private String added_by;

    private String project_id;

    private String photo_project;

    private String due_date;

    private String created_at;

    private String photo_id;

    public String getBudget ()
    {
        return budget;
    }

    public void setBudget (String budget)
    {
        this.budget = budget;
    }

    public String getProject_name ()
    {
        return project_name;
    }

    public void setProject_name (String project_name)
    {
        this.project_name = project_name;
    }

    public String getProject_constituency ()
    {
        return project_constituency;
    }

    public void setProject_constituency (String project_constituency)
    {
        this.project_constituency = project_constituency;
    }

    public String getContractor ()
    {
        return contractor;
    }

    public void setContractor (String contractor)
    {
        this.contractor = contractor;
    }

    public String getProject_category ()
    {
        return project_category;
    }

    public void setProject_category (String project_category)
    {
        this.project_category = project_category;
    }

    public String getPhoto_path ()
    {
        return photo_path;
    }

    public void setPhoto_path (String photo_path)
    {
        this.photo_path = photo_path;
    }

    public String getCompletion ()
    {
        return completion;
    }

    public void setCompletion (String completion)
    {
        this.completion = completion;
    }

    public String getProject_description ()
    {
        return project_description;
    }

    public void setProject_description (String project_description)
    {
        this.project_description = project_description;
    }

    public String getProject_ward ()
    {
        return project_ward;
    }

    public void setProject_ward (String project_ward)
    {
        this.project_ward = project_ward;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getAdded_by ()
    {
        return added_by;
    }

    public void setAdded_by (String added_by)
    {
        this.added_by = added_by;
    }

    public String getProject_id ()
    {
        return project_id;
    }

    public void setProject_id (String project_id)
    {
        this.project_id = project_id;
    }

    public String getPhoto_project ()
    {
        return photo_project;
    }

    public void setPhoto_project (String photo_project)
    {
        this.photo_project = photo_project;
    }

    public String getDue_date ()
    {
        return due_date;
    }

    public void setDue_date (String due_date)
    {
        this.due_date = due_date;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getPhoto_id ()
    {
        return photo_id;
    }

    public void setPhoto_id (String photo_id)
    {
        this.photo_id = photo_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [budget = "+budget+", project_name = "+project_name+", project_constituency = "+project_constituency+", contractor = "+contractor+", project_category = "+project_category+", photo_path = "+photo_path+", completion = "+completion+", project_description = "+project_description+", project_ward = "+project_ward+", updated_at = "+updated_at+", added_by = "+added_by+", project_id = "+project_id+", photo_project = "+photo_project+", due_date = "+due_date+", created_at = "+created_at+", photo_id = "+photo_id+"]";
    }
}
