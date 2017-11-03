package com.example.yuan.demofestivalmsm;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yuan.demofestivalmsm.bean.Festival;
import com.example.yuan.demofestivalmsm.bean.FestivalLab;
import com.example.yuan.demofestivalmsm.bean.Msg;
import com.example.yuan.demofestivalmsm.view.FlowLayout;

public class SendMessageActivity extends AppCompatActivity
{

    //节日id
    private int mFestivalId;

    //消息id
    private int msgId;

    private Msg mMsg;

    private Festival mFestival;

    private EditText mEditText;

    private Button mBtnAdd;

    private FlowLayout mFLContacts;

    private FloatingActionButton mFabSend;

    private View mLayoutLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        //获取id数据
        initData();

        //初始化控件
        initViews();
    }

    private void initViews()
    {
        mEditText = (EditText) findViewById(R.id.edit_text_content);
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mFLContacts = (FlowLayout) findViewById(R.id.fl_contacts);
        mLayoutLoading = findViewById(R.id.layout_loading);
        mFabSend = (FloatingActionButton) findViewById(R.id.fab_send);

        //设置加载进度条不可见
        mLayoutLoading.setVisibility(View.GONE);
        
        //如果不是自定义短信内容
        if (msgId != -1)
        {
            //显示短信内容
            mMsg = FestivalLab.getInstance().getMsgById(msgId);
            mEditText.setText(mMsg.getContent());

        }
    }

    private void initData()
    {
        mFestivalId = getIntent().getIntExtra("festival_id", -1);
        msgId = getIntent().getIntExtra("msg_id", -1);

        //设置节日名称
        mFestival = FestivalLab.getInstance().getFestivalById(mFestivalId);
        setTitle(mFestival.getName());

    }


    //提供跳转的到发送短信界面的方法
    public static void toActivity(Context context, int festivalId, int msgId)
    {
        Intent intent = new Intent(context, SendMessageActivity.class);
        intent.putExtra("festival_id", festivalId);
        intent.putExtra("msg_id", msgId);
        context.startActivity(intent);
    }
}
