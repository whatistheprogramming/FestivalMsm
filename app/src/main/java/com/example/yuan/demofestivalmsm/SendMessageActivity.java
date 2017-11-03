package com.example.yuan.demofestivalmsm;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yuan.demofestivalmsm.bean.Festival;
import com.example.yuan.demofestivalmsm.bean.FestivalLab;
import com.example.yuan.demofestivalmsm.bean.Msg;
import com.example.yuan.demofestivalmsm.view.FlowLayout;

import java.util.HashSet;

public class SendMessageActivity extends AppCompatActivity
{

    private static final int CODE_RESULT = 1;
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

    //加载进度条的view
    private View mLayoutLoading;

    //存放选中联系人的姓名
    private HashSet<String> mContactNames = new HashSet<>();
    //选中联系人的电话
    private HashSet<String> mContactNumber = new HashSet<>();

    private LayoutInflater mInflater;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        mInflater = LayoutInflater.from(this);

        //获取id数据
        initData();

        //初始化控件
        initViews();

        //事件的初始化
        initEvent();
    }

    private void initEvent()
    {
        mBtnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //跳转到联系人
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent,CODE_RESULT);
            }
        });
    }

    /**
     * 当选择联系人后会回调这个方法
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case CODE_RESULT:
                if (resultCode == RESULT_OK)
                {
                    //获取联系人的姓名和号码
                    Uri contactUri = data.getData();
                    Cursor cursor = getContentResolver().query(contactUri, null, null, null, null);
                    cursor.moveToFirst();
                    //联系人的姓名
                    String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    //联系人的号码
                    String number = getContactNumber(cursor);
                    if (!TextUtils.isEmpty(number))
                    {
                        mContactNumber.add(number);
                        mContactNames.add(contactName);

                        //显示添加的联系人
                        addTag(contactName);
                    }



                }
        }
    }

    private void addTag(String contactName)
    {
        TextView view = (TextView) mInflater.inflate(R.layout.tag, mFLContacts, false);
        view.setText(contactName);
        //加入到FlowLayout中
        mFLContacts.addView(view);

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

    /**
     * 获取联系人的号码
     */
    public String getContactNumber(Cursor cursor)
    {
        int numberCount = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
        String number = null;
        //如果有电话号码
        if (numberCount > 0)
        {
            int contactId = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phoneCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
            phoneCursor.moveToFirst();
            //有多个号码，选第一个
            number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            phoneCursor.close();
        }

        cursor.close();
        return number;
    }
}
