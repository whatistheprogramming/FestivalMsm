package com.example.yuan.demofestivalmsm.bean;

/**
 * Created by yuan on 2017/11/3.
 */

public class Msg
{
    //短信的id
    private int id;
    //短信所属节日的id
    private int FestivalId;
    //短信内容
    private String content;

    public Msg(int id, int festivalId, String content)
    {
        this.id = id;
        FestivalId = festivalId;
        this.content = content;
    }

    public int getId()
    {
        return id;
    }

    public int getFestivalId()
    {
        return FestivalId;
    }

    public String getContent()
    {
        return content;
    }
}
