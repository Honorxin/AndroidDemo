package com.ccx.demochenapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ccx.demochenapp.adapter.NewsListViewAdapter;
import com.ccx.demochenapp.entity.News;
import com.ccx.demochenapp.utils.NewsUtil;

import java.util.List;


/**
 * @author chuangxin.chen
 */
public class FirstFragment extends Fragment {


    private List<News> news;
    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_first, container, false);

        //创建新闻信息
        news = NewsUtil.getNews();
        ListView listView = inflate.findViewById(R.id.listView);
        NewsListViewAdapter newsListViewAdapter = new NewsListViewAdapter(getActivity(), news);
        listView.setAdapter(newsListViewAdapter);

        //设置listview点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 获取点击的新闻项
                News clickedNews = news.get(i);

                // 创建 Intent
                intent = new Intent(adapterView.getContext(), MainActivity2.class);

                // 将点击的新闻数据放入 Intent
                intent.putExtra("title", clickedNews.getTitle());
                intent.putExtra("dataTime", clickedNews.getDataTime());
                intent.putExtra("author", clickedNews.getAuthor());
                intent.putExtra("icon", clickedNews.getIcon());
                intent.putExtra("newsContent", clickedNews.getNewsContent());

                // 启动新的 Activity
                startActivity(intent);
            }
        });
        return inflate;
    }

}





