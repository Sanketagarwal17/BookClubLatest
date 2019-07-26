package com.example.android.bookclublatest.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref
{

    private static final String PREF_NAME = "welcome";
    private static final String MOBILE = "mobile";
    private static final String ADMISSION = "admission";
    private static final String EMAIL = "email";
    private static final String ACCESS_LEVEL = "level";
    private static final String USERNAME = "username";
    private static final String IMAGE_URL = "image_url";


    int Private_mode=0;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public SharedPref(Context context)
    {
        this.context=context;
        pref=context.getSharedPreferences(PREF_NAME,Private_mode);
        editor=pref.edit();
    }

    public String getMobile()
    {
        return pref.getString(MOBILE,"");
    }
    public void setMobile(String mobile)
    {
        editor.putString(MOBILE,mobile);
        editor.commit();
    }

    public String getEmail()
    {
        return pref.getString(EMAIL,"");
    }
    public void setEmail(String email)
    {
        editor.putString(EMAIL,email);
        editor.commit();
    }

    public String getAdmission()
    {
        return pref.getString(ADMISSION,"");
    }
    public void setAdmission(String admission)
    {
        editor.putString(ADMISSION,admission);
        editor.commit();
    }
    public String getUsername()
    {
        return pref.getString(USERNAME,"");
    }
    public void setUsername(String username)
    {
        editor.putString(USERNAME,username);
        editor.commit();
    }
    public String getAccessLevel()
    {
        return pref.getString(ACCESS_LEVEL,"");
    }
    public void setAccessLevel(String level)
    {
        editor.putString(ACCESS_LEVEL,level);
        editor.commit();
    }
    public String getImageUrl()
    {
        return pref.getString(IMAGE_URL,"");
    }
    public void setImageUrl(String url)
    {
        editor.putString(IMAGE_URL,url);
        editor.commit();
    }
}
