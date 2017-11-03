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

    //节日集合
    private List<Festival> mFestivals = new ArrayList<>();

    //短信集合
    private List<Msg> mMsgs = new ArrayList<>();


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

        mMsgs.add(new Msg(1, 1, "不是每朵白云，都会带来真情；不是每个拥抱，都会面带微笑；不是每次思念，都能立刻兑现；不是每个朋友，都在身边守候；不是每个日子，都逢良辰吉时。国庆节到了，愿你节日快乐！"));
        mMsgs.add(new Msg(2, 1, "国庆节到了，我派短信宝宝投入你的怀抱，替我送去一个甜甜的微笑，一个深深的拥抱，一份浓浓的思念，一份纯纯的祝福！愿这份祝福似一缕清风，温润你的心田；如一丝阳光，灿烂你的微笑。祝国庆快乐。"));
        mMsgs.add(new Msg(3, 1, "美丽的花朵纯纯的你，要让烦恼烧道而行；弯弯的月亮柔情的你，要让幸福缠绕着你；明媚的阳光活泼的你，要让健康常伴着你。祝你国庆节快乐！"));
        mMsgs.add(new Msg(4, 1, "五星红旗迎风飘扬，问候声音多么嘹亮。祝福我那亲爱的朋友，从今拥有幸福健康。越过烦恼高山，走进快乐平原，跨上成功的巅峰。国庆快乐！"));
        mMsgs.add(new Msg(5, 1, "是不是国庆不重要，有问候就好；是不是长假不重要，有祝福就好；秋天的风带来金色的祝愿：愿正在看短信的你幸福长伴，快乐不断！国庆快乐！"));
        mMsgs.add(new Msg(6, 1, "金秋十月国庆至，送你十一种祝福：快乐、开心、平安、健康、甜蜜、美丽、魅力、成功、顺利、如意、幸福，世间所有的美好都送给你，愿你事业顺利，家庭幸福，爱情甜蜜，友情永伴，平安一生，国庆节快乐！"));
        mMsgs.add(new Msg(7, 1, "工作永远没有尽头，日子永远忙忙碌碌，身体需要自己保护，健康才是真有用途，空时可以跟我倾诉，短信送来节日祝福。国庆长假，放松心情快乐欢呼！"));
        mMsgs.add(new Msg(8, 1, "缕缕的阳光激励你辣辣的热情；丝丝的微风赋予你滋滋的凉爽；淋淋的细雨冲刷你粒粒的尘埃；但愿我短短的祝福带给你频频的好运。愿国庆节快乐！"));
        mMsgs.add(new Msg(9, 1, "用云朵做成精美的包装盒，借风儿用丝线轻轻地扎紧，蘸着雨点擦得晶莹闪亮，彩虹再悄悄画上新装。用太阳光做的金笔写上祝福，月亮看了满意地点赞。国庆节就要到了，特别的祝福给特别的你，祝你节日快乐，平安健康，吉祥如意，永远幸福。"));


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

    //返回节日id的全部短信
    public List<Msg> getMsgsByFestivalId(int id)
    {
        List<Msg> msgs = new ArrayList<>();
        for (Msg msg : mMsgs)
        {
            if (msg.getFestivalId() == id)
            {
                msgs.add(msg);
            }
        }
        return msgs;
    }

    //通过短信id返回数据
    public Msg getMsgById(int id)
    {
        for (Msg msg : mMsgs)
        {
            if (msg.getId() == id)
            {
                return msg;
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
