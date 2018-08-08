package com.homeautogroup.meru.models;

import java.io.Serializable;

/**
 * Meru Created by kelvin on 8/7/18.
 * com.homeautogroup.meru.models
 * flyboypac@gmail.com
 * +254705419309
 */
public class Departments implements Serializable{
    private String department_description;

    private String updated_at;

    private String department_photo;

    private String department_id;

    private String created_at;

    private String department_name;

    public String getDepartment_description ()
    {
        return department_description;
    }

    public void setDepartment_description (String department_description)
    {
        this.department_description = department_description;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getDepartment_photo ()
    {
        return department_photo;
    }

    public void setDepartment_photo (String department_photo)
    {
        this.department_photo = department_photo;
    }

    public String getDepartment_id ()
    {
        return department_id;
    }

    public void setDepartment_id (String department_id)
    {
        this.department_id = department_id;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getDepartment_name ()
    {
        return department_name;
    }

    public void setDepartment_name (String department_name)
    {
        this.department_name = department_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [department_description = "+department_description+", updated_at = "+updated_at+", department_photo = "+department_photo+", department_id = "+department_id+", created_at = "+created_at+", department_name = "+department_name+"]";
    }
}
