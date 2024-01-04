package com.ccx.demochenapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ccx.demochenapp.R;
import com.ccx.demochenapp.entity.News;
import java.util.List;


/**
 * @author chuangxin.chen
 */
public class NewsListViewAdapter extends BaseAdapter {


    /**
     * 设置要创建视图的类型
     */
    private static final int VIEW_TYPE_1 = 0;
    private static final int VIEW_TYPE_2 = 1;
    private String TAG = "tag";

    private Context context;
    private List<News> newsList;

    private ViewHolder viewHolder;
    /**
     * 动态加载对象
     */
    private LayoutInflater inflater;

    public static class ViewHolder {

        TextView first_item_image;
        TextView first_item_time;
        TextView first_item_author;
        TextView news_item_title;
        ImageView news_item_image;
        TextView news_item_time;
        TextView news_item_author;
        LinearLayout first_item2;
    }

    public NewsListViewAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }


    // Rest of the adapter code remains the same...

    /**
     * 获取当前视图的绘制类型
     * @param position The position of the item within the adapter's data set whose view type we
     *        want.
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return newsList.get(position).getType();
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int i) {
        return newsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 创建视图到页面
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int viewType = getItemViewType(i);
        viewHolder = new ViewHolder();
        //如果没有视图则创建
        if (view == null) {
            inflater = LayoutInflater.from(context);
            switch (viewType) {
                case VIEW_TYPE_1:
                    view = inflater.inflate(R.layout.first_item, viewGroup, false);
                    viewHolder.first_item_image = view.findViewById(R.id.first_item_image);
                    viewHolder.first_item_time = view.findViewById(R.id.first_item_time);
                    viewHolder.first_item_author = view.findViewById(R.id.first_item_author);
                    viewHolder.first_item2 = view.findViewById(R.id.first_item2);
                    break;
                case VIEW_TYPE_2:
                    view = inflater.inflate(R.layout.news_item, viewGroup, false);
                    viewHolder.news_item_title = view.findViewById(R.id.news_item_title);
                    viewHolder.news_item_image = view.findViewById(R.id.news_item_image);
                    viewHolder.news_item_time = view.findViewById(R.id.news_item_time);
                    viewHolder.news_item_author = view.findViewById(R.id.news_item_author);
                    break;
                default:
                    break;
            }
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //将信息填入视图中
        News news = newsList.get(i);
        if (news != null) {
            switch (viewType) {
                case VIEW_TYPE_1:
                    Log.d(TAG, "getView:VIEW_TYPE_1 " + news);

                    viewHolder.first_item_image.setCompoundDrawablePadding(-20);
                    viewHolder.first_item_image.setGravity(Gravity.CENTER_HORIZONTAL);
                    //设置图片在文字之下
                    Drawable image = inflater.getContext().getDrawable(news.getIcon()[0]);
                    image.setBounds(0, 0, image.getMinimumWidth(), image.getMinimumHeight());
                    viewHolder.first_item_image.setCompoundDrawables(null, image, null, null);
                    viewHolder.first_item_image.setText(news.getTitle());

                    viewHolder.first_item_time.setText(news.getDataTime());
                    viewHolder.first_item_author.setText(news.getAuthor());
                    break;
                case VIEW_TYPE_2:
                    Log.d(TAG, "getView: VIEW_TYPE_2" + news);
                    viewHolder.news_item_title.setText(news.getTitle());
                    viewHolder.news_item_image.setImageResource(news.getIcon()[0]);
                    viewHolder.news_item_time.setText(news.getDataTime());
                    viewHolder.news_item_author.setText(news.getAuthor());
                    break;
                default:
                    break;
            }
        }
        return view;
    }

}
