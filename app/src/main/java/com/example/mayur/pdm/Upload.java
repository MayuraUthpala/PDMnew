package com.example.mayur.pdm;

import com.google.firebase.database.Exclude;

/**
 * Created by Dulip on 5/17/2018.
 */

public class Upload {
    private String name;
    private String url;
    private String mkey;

    public Upload()
    {

    }

    public Upload(String mname, String murl)
    {
        if (mname.trim().equals(""))
        {
            mname = "no name";
        }

        name = mname;
        url = murl;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String mname)
    {
        name=mname;
    }

    public String getUrl()
    {
        return url;
    }
    public void setUrl(String murl)
    {
        url = murl;
    }

    @Exclude
    public String getkey()
    {
        return mkey;
    }
    @Exclude
    public void setkey(String key)
    {
        mkey=key;
    }
}
