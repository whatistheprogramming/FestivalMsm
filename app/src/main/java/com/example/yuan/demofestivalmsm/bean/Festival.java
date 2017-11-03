package com.example.yuan.demofestivalmsm.bean;

import java.util.Date;

/**
 * Created by yuan on 2017/11/3.
 */

public class Festival
{
    //节日主键
    private int id;
    //节日名称
    private String name;
    //节日描述
    private String desc;
    //节日日期
    private Date date;

    public Festival(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
