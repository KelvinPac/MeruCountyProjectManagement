package com.homeautogroup.meru.models;

/**
 * Meru Created by kelvin on 8/8/18.
 * com.homeautogroup.meru.models
 * flyboypac@gmail.com
 * +254705419309
 */
public class Photos {

    private String updated_at;

    private String created_at;

    private String photo_project;

    private String photo_path;

    private String photo_id;

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getPhoto_project ()
    {
        return photo_project;
    }

    public void setPhoto_project (String photo_project)
    {
        this.photo_project = photo_project;
    }

    public String getPhoto_path ()
    {
        return photo_path;
    }

    public void setPhoto_path (String photo_path)
    {
        this.photo_path = photo_path;
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
        return "ClassPojo [updated_at = "+updated_at+", created_at = "+created_at+", photo_project = "+photo_project+", photo_path = "+photo_path+", photo_id = "+photo_id+"]";
    }
}
