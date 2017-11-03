package com.example.yuan.demofestivalmsm.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.example.yuan.demofestivalmsm.R;
import com.example.yuan.demofestivalmsm.bean.Festival;
import com.example.yuan.demofestivalmsm.bean.FestivalLab;

/**
 * Created by yuan on 2017/11/3.
 */

public class FestivalFragment extends Fragment
{
    private GridView mGridView;

    private ArrayAdapter<Festival> mAdapter;

    private LayoutInflater mInflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.activity_festival_first, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        //初始化LayoutInflater
        mInflater = LayoutInflater.from(getActivity());
        //获取GridView实例
        mGridView = (GridView) view.findViewById(R.id.grid_view);
        //创建GridView的适配器
        mAdapter = new ArrayAdapter<Festival>(getActivity(), -1, FestivalLab.getInstance().getFestivals()){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
            {
                if (convertView == null)
                {
                    convertView = mInflater.inflate(R.layout.item_festival, parent, false);
                }
                TextView textView = (TextView) convertView.findViewById(R.id.festival_name);
                textView.setText(getItem(position).getName());

                return convertView;
            }
        };

        //设置适配器
        mGridView.setAdapter(mAdapter);
        //给GridView添加点击事件
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                //TODO
            }
        });

    }
}
