package com.example.yuan.demofestivalmsm;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yuan.demofestivalmsm.bean.FestivalLab;
import com.example.yuan.demofestivalmsm.bean.Msg;

import java.lang.reflect.Array;

public class FestivalSecond extends AppCompatActivity
{

    private ListView mListView;

    private FloatingActionButton mFabToSend;

    private ArrayAdapter<Msg> mAdapter;

    private LayoutInflater mInflater;

    //第一个界面传入的节日id
    private int mFestivalId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_second);

        //获取第一个界面传入的节日id
        mFestivalId = getIntent().getIntExtra("festival_id", -1);
        Log.d("Activity", mFestivalId+"");

        //设置标题
        setTitle(FestivalLab.getInstance().getFestivalById(mFestivalId).getName());

        initViews();

        initEvent();
    }

    /**
     * fab按钮的点击事件，自定义短信
     */
    private void initEvent()
    {
        mFabToSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //跳转到发送短信界面，因为是自定义短信内容，所以这里短信id传入-1
                SendMessageActivity.toActivity(FestivalSecond.this, mFestivalId, -1);

            }
        });
    }

    private void initViews()
    {
        mInflater = LayoutInflater.from(this);
        mListView = (ListView) findViewById(R.id.list_view_msg);
        mFabToSend = (FloatingActionButton) findViewById(R.id.fab_toSend);
        //创建ListView的适配器
        mAdapter=new ArrayAdapter<Msg>(this, -1, FestivalLab.getInstance().getMsgsByFestivalId(mFestivalId)){
            @NonNull
            @Override
            public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)
            {
                if (convertView == null)
                {
                    convertView = mInflater.inflate(R.layout.item_msg, parent, false);
                }
                TextView text = (TextView) convertView.findViewById(R.id.msg_content);
                Button toSend = (Button) convertView.findViewById(R.id.btn_toSend);
                //设置短信内容
                text.setText("       "+getItem(position).getContent());
                toSend.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        //跳转到发送短信界面
                        SendMessageActivity.toActivity(FestivalSecond.this, mFestivalId, getItem(position).getId());

                    }
                });
                return convertView;
            }
        };
        //设置ListView的适配器
        mListView.setAdapter(mAdapter);
    }
}
