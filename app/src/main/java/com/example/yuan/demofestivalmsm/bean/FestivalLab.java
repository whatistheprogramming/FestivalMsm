package com.example.yuan.demofestivalmsm.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供节日数据
 * Created by yuan on 2017/11/3.
 */

public class FestivalLab
{
    public static FestivalLab mInstance;

    private List<Festival> mFestivals = new ArrayList<>();



    private FestivalLab()
    {
        //添加节日数据
        mFestivals.add(new Festival(1, "国庆节"));
        mFestivals.add(new Festival(2, "中秋节"));
        mFestivals.add(new Festival(3, "元旦"));
        mFestivals.add(new Festival(4, "春节"));
        mFestivals.add(new Festival(5, "端午节"));
        mFestivals.add(new Festival(6, "七夕节"));
        mFestivals.add(new Festival(7, "圣诞节"));
        mFestivals.add(new Festival(8, "儿童节"));
    }

    //返回节日数据
    public List<Festival> getFestivals()
    {
        //防止引用有可能外部会修改，这里返回副本
        return new ArrayList<>(mFestivals);
    }


    //通过节日id获取数据
    public Festival getFestivalById(int id)
    {
        for (Festival festival : mFestivals)
        {
            if (festival.getId() == id)
            {
                return festival;
            }
        }
        return null;
    }




    public static FestivalLab getInstance()
    {
        if (mInstance == null) //多线程的时候，为了提升效率，没必要每次都同步；
        {
            synchronized (FestivalLab.class) //让线程互斥的进入；注意if语句；
            {
                if (mInstance == null)
                {
                    mInstance = new FestivalLab();
                }
            }
        }
        return mInstance;
    }
}
