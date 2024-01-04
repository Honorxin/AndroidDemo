## 阶段一的demo文档

### 基本功能

实现底部的导航栏（新闻，时间，我的），顶部状态栏（与底部导航栏相对应）三个导航栏点击时会切换到相对应的页面，且页面滑动也会出现相对应的效果。

#### 1. 新闻页面：实现新闻列表的展示

> ![企业微信截图_20240104142543](https://github.com/Honorxin/AndroidDemo/assets/91577518/f3b57534-2cd7-4789-ba56-f3ecb5f595fd)


##### 1.1 新闻详情页面

> ![企业微信截图_20240104165710](https://github.com/Honorxin/AndroidDemo/assets/91577518/3bb1d363-10ce-4df7-9ead-15d191887355)

#### 2. 时间页面：实现自定义时钟，并且跟随系统时间改变

> ![企业微信截图_20240104145525](https://github.com/Honorxin/AndroidDemo/assets/91577518/ba0c52c0-c7fd-468c-947b-3d2ad1a201af)

>
> 

#### 3. 我的页面：实现姓名，电话，性别，关于app
>![企业微信截图_20240104145658](https://github.com/Honorxin/AndroidDemo/assets/91577518/c96fe66a-d8a5-49d7-8c6b-cf94308774f4)
>![企业微信截图_20240104150102](https://github.com/Honorxin/AndroidDemo/assets/91577518/a2bbc34c-e39f-40a3-8885-e544b545d26e)
>![企业微信截图_20240104150117](https://github.com/Honorxin/AndroidDemo/assets/91577518/8e03940f-6b67-4ec7-aa45-feef3600d1ed)
>![企业微信截图_20240104150130](https://github.com/Honorxin/AndroidDemo/assets/91577518/b16059d9-d33b-4d44-bf07-c15663e8da48)
>![企业微信截图_20240104150117](https://github.com/Honorxin/AndroidDemo/assets/91577518/67cd83dc-20aa-4637-869a-4adc90024d54)


### 实现步骤：

#### 1.定义底部导航栏

首先定义导航栏页面，我采用ViewPager+Fragment+RadioGroup实现底部导航，下面是主页面的xml布局文件

```xml
android:drawableTop="@drawable/first_drawable"
```

这是底部导航栏实现图片样式的效果，选中和非选中呈现不同的效果

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/text_top"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        android:gravity="center"
        android:text=""
        android:textSize="24dp"
        android:textColor="@color/white"
        android:background="#ED5D5D"/>


    <!-- 使用ViewPager替代FrameLayout -->
    <androidx.viewpager.widget.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_above="@+id/rg_group"
        android:layout_below="@+id/text_top"/>

    <RadioGroup
        android:id="@+id/rg_group"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:background="#ffff"
        android:orientation="horizontal">

        <RadioButton
            android:id="@+id/news"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:button="@null"
            android:drawableTop="@drawable/first_drawable"
            android:gravity="center"
            android:text="新闻" />

        <RadioButton
            android:id="@+id/time"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:button="@null"
            android:drawableTop="@drawable/time_drawable"
            android:gravity="center"
            android:text="时间" />

        <RadioButton
            android:id="@+id/me"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:button="@null"
            android:drawableTop="@drawable/me_drawable"
            android:gravity="center"
            android:text="我的" />
    </RadioGroup>
</RelativeLayout>

```

#### 2.导航栏链接对应Fragment

> 定义三个对应的 Fragment_xxx.xml 文件

##### 2.1  新闻页面

###### 2.1.1 定义页面布局

> **fragment_news.xml 及 两个自定义的视图**

> ```xml
> <?xml version="1.0" encoding="utf-8"?>
> <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
>     xmlns:tools="http://schemas.android.com/tools"
>     android:layout_width="match_parent"
>     android:layout_height="match_parent"
>     tools:context=".FirstFragment">
>         <ListView
>             android:id="@+id/listView"
>             android:layout_width="match_parent"
>             android:layout_height="match_parent"/>
> </FrameLayout>
> ```
>
> - 两个自定义的布局视图
>
>   ```xml
>   <?xml version="1.0" encoding="utf-8"?>
>   <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
>       android:layout_width="match_parent"
>       android:layout_height="match_parent">
>   
>       <LinearLayout
>           android:layout_width="match_parent"
>           android:layout_height="match_parent"
>           android:divider="@drawable/line_drawable"
>   
>           android:orientation="vertical"
>           android:showDividers="middle">
>   
>           <LinearLayout
>               android:id="@+id/first_item"
>               android:layout_width="match_parent"
>               android:layout_height="wrap_content"
>               android:orientation="vertical">
>   
>               <TextView
>                   android:id="@+id/first_item_image"
>                   android:layout_width="match_parent"
>                   android:layout_height="wrap_content"
>                   android:layout_gravity="center|left"
>                   android:drawablePadding="-20dp"
>                   android:lines="1"
>                   android:textSize="15sp"
>                   android:textStyle="bold"/>
>   
>               <LinearLayout
>                   android:id="@+id/first_item2"
>                   android:layout_width="match_parent"
>                   android:layout_height="30dp"
>                   android:orientation="horizontal">
>   
>                   <TextView
>                       android:id="@+id/first_item_time"
>                       android:layout_width="0dp"
>                       android:layout_height="match_parent"
>                       android:layout_margin="5dp"
>                       android:layout_weight="1"
>                       android:gravity="left|center"
>   
>                       android:text="" />
>   
>                   <TextView
>                       android:id="@+id/first_item_author"
>                       android:layout_width="0dp"
>                       android:layout_height="match_parent"
>                       android:layout_marginRight="10dp"
>                       android:layout_weight="1"
>                       android:gravity="right|center"
>                       android:text="" />
>               </LinearLayout>
>           </LinearLayout>
>       </LinearLayout>
>   </LinearLayout>
>   ```
>
>   ```xml
>   <?xml version="1.0" encoding="utf-8"?>
>   <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
>       android:layout_width="match_parent"
>       android:layout_height="match_parent">
>       <LinearLayout
>           android:id="@+id/news_item"
>           android:layout_width="match_parent"
>           android:layout_height="180dp"
>           android:orientation="vertical">
>           <LinearLayout
>               android:layout_width="match_parent"
>               android:layout_height="160dp"
>               android:layout_weight="1"
>               android:orientation="horizontal">
>               <TextView
>                   android:id="@+id/news_item_title"
>                   android:layout_width="0dp"
>                   android:layout_height="160dp"
>                   android:layout_weight="1"
>                   android:padding="20dp"
>                   android:text=""
>                   android:textSize="16sp"
>                   android:textStyle="bold" />
>               <ImageView
>                   android:id="@+id/news_item_image"
>                   android:layout_width="0dp"
>                   android:layout_height="160dp"
>                   android:layout_gravity="right"
>                   android:layout_margin="5dp"
>                   android:layout_weight="1" />
>           </LinearLayout>
>           <LinearLayout
>               android:layout_width="match_parent"
>               android:layout_height="20dp"
>               android:orientation="horizontal">
>               <TextView
>                   android:id="@+id/news_item_time"
>                   android:layout_width="0dp"
>                   android:layout_height="match_parent"
>                   android:layout_marginLeft="10dp"
>                   android:layout_weight="1"
>                   android:gravity="left|center"
>                   android:text="" />
>               <TextView
>                   android:id="@+id/news_item_author"
>                   android:layout_width="0dp"
>                   android:layout_height="match_parent"
>                   android:layout_marginRight="10dp"
>                   android:layout_weight="1"
>                   android:gravity="right|center"
>                   android:text="" />
>           </LinearLayout>
>       </LinearLayout>
>   </LinearLayout>
>   ```

###### 2.1.2 定义适配器及新闻实体

> 1. 需要定义基本的新闻信息实体，包含：新闻图片，标题，创建时间，出品方等再有就是定义创建哪种视图的类型（0，1）
> 2. 创建添加信息的工具类，添加新闻信息
> 3. 新闻列表中有两种不同的布局，可以通过自定义的 ListView 列表，继承 BaseAdapter 适配器，去创建新闻列表

> ```java
> /**
>  * @author chuangxin.chen
>  */
> public class NewsListViewAdapter extends BaseAdapter {
> 
> 
>     /**
>      * 设置要创建视图的类型
>      */
>     private static final int VIEW_TYPE_1 = 0;
>     private static final int VIEW_TYPE_2 = 1;
>     private String TAG = "tag";
> 
>     private Context context;
>     private List<News> newsList;
> 
>     private ViewHolder viewHolder;
>     /**
>      * 动态加载对象
>      */
>     private LayoutInflater inflater;
> 
>     public static class ViewHolder {
> 
>         TextView first_item_image;
>         TextView first_item_time;
>         TextView first_item_author;
>         TextView news_item_title;
>         ImageView news_item_image;
>         TextView news_item_time;
>         TextView news_item_author;
>         LinearLayout first_item2;
>     }
> 
>     public NewsListViewAdapter(Context context, List<News> newsList) {
>         this.context = context;
>         this.newsList = newsList;
>     }
> 
> 
>     // Rest of the adapter code remains the same...
> 
>     /**
>      * 获取当前视图的绘制类型
>      * @param position The position of the item within the adapter's data set whose view type we
>      *        want.
>      * @return
>      */
>     @Override
>     public int getItemViewType(int position) {
>         return newsList.get(position).getType();
>     }
> 
>     @Override
>     public int getCount() {
>         return newsList.size();
>     }
> 
>     @Override
>     public Object getItem(int i) {
>         return newsList.get(i);
>     }
> 
>     @Override
>     public long getItemId(int i) {
>         return i;
>     }
> 
>     /**
>      * 创建视图到页面
>      */
>     @Override
>     public View getView(int i, View view, ViewGroup viewGroup) {
>         int viewType = getItemViewType(i);
>         viewHolder = new ViewHolder();
>         //如果没有视图则创建
>         if (view == null) {
>             inflater = LayoutInflater.from(context);
>             switch (viewType) {
>                 case VIEW_TYPE_1:
>                     view = inflater.inflate(R.layout.first_item, viewGroup, false);
>                     viewHolder.first_item_image = view.findViewById(R.id.first_item_image);
>                     viewHolder.first_item_time = view.findViewById(R.id.first_item_time);
>                     viewHolder.first_item_author = view.findViewById(R.id.first_item_author);
>                     viewHolder.first_item2 = view.findViewById(R.id.first_item2);
>                     break;
>                 case VIEW_TYPE_2:
>                     view = inflater.inflate(R.layout.news_item, viewGroup, false);
>                     viewHolder.news_item_title = view.findViewById(R.id.news_item_title);
>                     viewHolder.news_item_image = view.findViewById(R.id.news_item_image);
>                     viewHolder.news_item_time = view.findViewById(R.id.news_item_time);
>                     viewHolder.news_item_author = view.findViewById(R.id.news_item_author);
>                     break;
>                 default:
>                     break;
>             }
>             view.setTag(viewHolder);
>         } else {
>             viewHolder = (ViewHolder) view.getTag();
>         }
> 
>         //将信息填入视图中
>         News news = newsList.get(i);
>         if (news != null) {
>             switch (viewType) {
>                 case VIEW_TYPE_1:
>                     Log.d(TAG, "getView:VIEW_TYPE_1 " + news);
> 
>                     viewHolder.first_item_image.setCompoundDrawablePadding(-20);
>                     viewHolder.first_item_image.setGravity(Gravity.CENTER_HORIZONTAL);
>                     //设置图片在文字之下
>                     Drawable image = inflater.getContext().getDrawable(news.getIcon()[0]);
>                     image.setBounds(0, 0, image.getMinimumWidth(), image.getMinimumHeight());
>                     viewHolder.first_item_image.setCompoundDrawables(null, image, null, null);
>                     viewHolder.first_item_image.setText(news.getTitle());
>                     
>                     viewHolder.first_item_time.setText(news.getDataTime());
>                     viewHolder.first_item_author.setText(news.getAuthor());
>                     break;
>                 case VIEW_TYPE_2:
>                     Log.d(TAG, "getView: VIEW_TYPE_2" + news);
>                     viewHolder.news_item_title.setText(news.getTitle());
>                     viewHolder.news_item_image.setImageResource(news.getIcon()[0]);
>                     viewHolder.news_item_time.setText(news.getDataTime());
>                     viewHolder.news_item_author.setText(news.getAuthor());
>                     break;
>                 default:
>                     break;
>             }
>         }
>         return view;
>     }
> 
> }
> 
> ```

###### 2.1.3 效果实现及信息传递

> 新闻详情页面布局
>
> ```xml
> <?xml version="1.0" encoding="utf-8"?>
> <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
>     xmlns:tools="http://schemas.android.com/tools"
>     android:layout_width="match_parent"
>     android:layout_height="match_parent"
>     android:orientation="vertical"
>     tools:context=".MainActivity2">
> 
>     <!-- 添加Toolbar -->
>     <LinearLayout
>         android:layout_width="match_parent"
>         android:layout_height="45dp"
>         android:background="#ED5D5D"
>         android:orientation="horizontal">
> 
>         <ImageButton
>             android:id="@+id/left_btn"
>             android:layout_width="40dp"
>             android:layout_height="match_parent"
>             android:layout_margin="5dp"
>             android:background="@drawable/left" />
> 
>         <TextView
>             android:layout_width="match_parent"
>             android:layout_height="match_parent"
>             android:layout_marginLeft="10dp"
>             android:gravity="center|left"
>             android:text="新闻详情"
>             android:textColor="@color/white"
>             android:textSize="18sp" />
>     </LinearLayout>
> 
>     <ScrollView
>         android:layout_width="match_parent"
>         android:layout_height="match_parent">
> 
>         <LinearLayout
>             android:layout_width="match_parent"
>             android:layout_height="match_parent"
>             android:orientation="vertical">
> 
>             <TextView
>                 android:id="@+id/textView"
>                 android:layout_width="match_parent"
>                 android:layout_height="wrap_content"
> 
>                 android:layout_margin="5dp"
>                 android:text=""
>                 android:textSize="20dp"
>                 android:textStyle="bold" />
> 
> 
>             <LinearLayout
>                 android:layout_width="match_parent"
>                 android:layout_height="40dp"
>                 android:orientation="horizontal">
> 
>                 <TextView
>                     android:id="@+id/time"
>                     android:layout_width="0dp"
>                     android:layout_height="match_parent"
>                     android:layout_margin="10dp"
>                     android:layout_weight="1"
>                     android:gravity="left|center"
> 
>                     android:text="" />
> 
>                 <TextView
>                     android:id="@+id/author"
>                     android:layout_width="0dp"
>                     android:layout_height="match_parent"
>                     android:layout_marginRight="10dp"
>                     android:layout_weight="1"
>                     android:gravity="right|center"
>                     android:text="" />
> 
>             </LinearLayout>
> 
>             <LinearLayout
>                 android:layout_width="match_parent"
>                 android:layout_height="match_parent"
>                 android:layout_margin="5dp"
>                 android:orientation="vertical">
> 
>                 <ImageView
>                     android:id="@+id/newsDetails_image"
>                     android:layout_width="wrap_content"
>                     android:layout_height="250dp"
>                     android:layout_gravity="center"></ImageView>
> 
>                 <TextView
>                     android:id="@+id/newsContent"
>                     android:layout_width="match_parent"
>                     android:layout_height="match_parent"
>                     android:layout_margin="5dp"
>                     android:text=""
>                     android:textSize="16dp"
>                     android:textStyle="bold" />
> 
>                 <ImageView
>                     android:id="@+id/newsDetails_image2"
>                     android:layout_width="wrap_content"
>                     android:layout_height="250dp"
>                     android:layout_gravity="center"></ImageView>
> 
>                 <TextView
>                     android:id="@+id/newsContent2"
>                     android:layout_width="match_parent"
>                     android:layout_height="match_parent"
>                     android:layout_margin="5dp"
>                     android:text=""
>                     android:textSize="16dp"
>                     android:textStyle="bold" />
>             </LinearLayout>
>         </LinearLayout>
>     </ScrollView>
> </LinearLayout>
> ```
>
> 最后在定义的定义的相对应的fragment类中去实现效果，向详情活动页面传递信息
>
> > ```java
> > public class FirstFragment extends Fragment {
> > 
> > 
> >     private List<News> news;
> >     private Intent intent;
> > 
> >     @Override
> >     public View onCreateView(LayoutInflater inflater, ViewGroup container,
> >                              Bundle savedInstanceState) {
> >         View inflate = inflater.inflate(R.layout.fragment_first, container, false);
> > 
> >         //创建新闻信息
> >         news = NewsUtil.getNews();
> >         ListView listView = inflate.findViewById(R.id.listView);
> >         NewsListViewAdapter newsListViewAdapter = new NewsListViewAdapter(getActivity(), news);
> >         listView.setAdapter(newsListViewAdapter);
> > 
> >         //设置listview点击事件
> >         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
> >             @Override
> >             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
> >                 // 获取点击的新闻项
> >                 News clickedNews = news.get(i);
> > 
> >                 // 创建 Intent
> >                 intent = new Intent(adapterView.getContext(), MainActivity2.class);
> > 
> >                 // 将点击的新闻数据放入 Intent
> >                 intent.putExtra("title", clickedNews.getTitle());
> >                 intent.putExtra("dataTime", clickedNews.getDataTime());
> >                 intent.putExtra("author", clickedNews.getAuthor());
> >                 intent.putExtra("icon", clickedNews.getIcon());
> >                 intent.putExtra("newsContent", clickedNews.getNewsContent());
> > 
> >                 // 启动新的 Activity
> >                 startActivity(intent);
> >             }
> >         });
> >         return inflate;
> >     }
> > 
> > }
> > ```
> >
> > 详情活动页面的 类，实现展示效果
> >
> > ```java
> > /**
> >  * @author chuangxin.chen
> >  */
> > public class MainActivity2 extends AppCompatActivity {
> > 
> >     @Override
> >     protected void onCreate(Bundle savedInstanceState) {
> >         super.onCreate(savedInstanceState);
> >         setContentView(R.layout.activity_main2);
> > 
> >         // 设置Toolbar
> >         ImageButton left_btn = findViewById(R.id.left_btn);
> >         left_btn.setOnClickListener(view -> {
> >             onBackPressed();
> >         });
> > 
> >         Intent intent = getIntent();
> >         String title = intent.getStringExtra("title");
> >         String dataTime = intent.getStringExtra("dataTime");
> >         String author1 = intent.getStringExtra("author");
> >         int[] icon = intent.getIntArrayExtra("icon");
> >         String newsContent = intent.getStringExtra("newsContent");
> > 
> >         TextView textView = findViewById(R.id.textView);
> >         TextView time = findViewById(R.id.time);
> >         TextView author = findViewById(R.id.author);
> >         ImageView newsDetails_image1 = findViewById(R.id.newsDetails_image);
> >         ImageView newsDetails_image2 = findViewById(R.id.newsDetails_image2);
> >         TextView newsContent1 = findViewById(R.id.newsContent);
> >         TextView newsContent2 = findViewById(R.id.newsContent2);
> > 
> >         textView.setText(title);
> >         time.setText(dataTime);
> >         author.setText(author1);
> > 
> >         if (icon.length > 1) {
> >             String substring = newsContent.substring(0, newsContent.length() / 2);
> >             String substring1 = newsContent.substring(newsContent.length() / 2);
> >             newsContent1.setText(substring);
> >             newsContent2.setText(substring1);
> >             newsDetails_image1.setImageResource(icon[0]);
> >             newsDetails_image2.setImageResource(icon[1]);
> >         } else {
> >             newsContent1.setText(newsContent);
> >             newsDetails_image1.setImageResource(icon[0]);
> >         }
> >     }
> > }
> > ```
> >
> > 

##### 2.2 时钟页面 

###### 2.2.1 定义时钟视图

> 自定义时钟视图 fragment_time.xml,创建一个基本布局
>
> ```xml
> <?xml version="1.0" encoding="utf-8"?>
> <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
>     xmlns:tools="http://schemas.android.com/tools"
>     android:layout_width="match_parent"
>     android:layout_height="match_parent"
>     tools:context=".TimeFragment">
> 
>     <TextView
>         android:id="@+id/time_text"
>         android:layout_width="match_parent"
>         android:layout_height="40dp"
>         android:gravity="center"
>         android:textSize="20sp" />
> 
>     <com.ccx.demochenapp.ClockView
>         android:id="@+id/clock"
>         android:layout_width="match_parent"
>         android:layout_height="match_parent"
>         android:layout_marginHorizontal="5dp"
>         android:layout_marginTop="50dp"/>
> 
> </FrameLayout>
> ```
>
> 

###### 2.2.2 进行自定义时钟的绘制

> - ###### **先定义画笔的样式**
>
> - **在重写onMeasure()方法中获取视图的高度，宽度**
>
> - **然后自定义绘制方法，画图**
>
> - **最后在重写onDraw()中实现**

> ```java
> public class ClockView extends View {
>     private Paint circlePaint;
>     private Paint hourHand;
>     private Paint minuteHand;
>     private Paint secondHand;
>     /**
>      * 视图的宽高
>      */
>     private float mWidth, mHeight;
>     /**
>      * 圆的半径
>      */
>     private float circleRadius;
>     /**
>      * 圆心X,Y坐标
>      */
>     private float circleX, circleY;
>     private int second, minute;
>     private double hour;
>     private Paint centerPoint;
> 
> 
>     public ClockView(Context context, AttributeSet attrs) {
>         super(context, attrs);
>         initPaint();
>     }
> 
>     private void initPaint() {
>         // 刻盘画笔
>         circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
>         circlePaint.setColor(Color.BLACK);
>         circlePaint.setStyle(Paint.Style.STROKE);
>         circlePaint.setStrokeWidth(10);
> 
>         //定义时针，中心点画笔
>         centerPoint = new Paint(Paint.ANTI_ALIAS_FLAG);
>         centerPoint.setColor(Color.RED);
> 
> 
>         hourHand = new Paint(Paint.ANTI_ALIAS_FLAG);
>         hourHand.setColor(Color.RED);
>         hourHand.setStyle(Paint.Style.STROKE);
>         hourHand.setStrokeWidth(6);
>         //定义分针画笔
>         minuteHand = new Paint(Paint.ANTI_ALIAS_FLAG);
>         minuteHand.setColor(Color.parseColor("#C427DF"));
>         minuteHand.setStrokeWidth(6);
>         // 秒针的画笔
>         secondHand = new Paint(Paint.ANTI_ALIAS_FLAG);
>         secondHand.setColor(Color.BLACK);
>         secondHand.setStrokeWidth(4);
> 
>     }
> 
>     @Override
>     protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
>         super.onMeasure(widthMeasureSpec, heightMeasureSpec);
>         mWidth = getMeasuredWidth();
>         mHeight = getMeasuredHeight();
>         if (mWidth < mHeight) {
>             // 圆的半径为view的宽度的一半
>             circleRadius = mWidth / 2;
>         } else {
>             circleRadius = mHeight / 2;
>         }
>         circleX = mWidth / 2;
>         circleY = mHeight / 2;
>     }
> 
>     @Override
>     protected void onDraw(Canvas canvas) {
>         super.onDraw(canvas);
>         setTimes();
>         drawCirclePoint(canvas);
>         drawDial(canvas);
>         drawPointer(canvas);
>     }
> 
>     /**
>      * 圆心
>      *
>      * @param canvas
>      */
>     private void drawCirclePoint(Canvas canvas) {
>         canvas.drawCircle(circleX, circleY, 20, centerPoint);
>     }
> 
>     /**
>      * 画刻度及时间
>      */
>     private void drawDial(Canvas canvas) {
>         // 开始画刻度和数字，总共60个刻度，12个时钟刻度，被5整除画一个时钟刻度，被其余的为分针刻度
>         for (int i = 0; i < 60; i++) {
>             if (i % 5 == 0) {
>                 // 时针刻度
>                 canvas.drawLine(circleX, circleY - circleRadius,
>                         circleX, circleY - circleRadius + 40, circlePaint);
> 
>             } else {
>                 // 画分针刻度
>                 canvas.drawLine(circleX, circleY - circleRadius,
>                         circleX, circleY - circleRadius + 10, circlePaint);
>             }
>             // 画布旋转6度
>             canvas.rotate(360 / 60, circleX, circleY);
>         }
>     }
> 
>     /**
>      * 画指针 X点坐标 cos(弧度)*r Y点坐标 sin(弧度)*r toRadians将角度转成弧度
>      * 安卓坐标系与数学坐标系不同的地方是X轴是相反的，所以为了调整方向，需要将角度+270度
>      */
>     private void drawPointer(Canvas canvas) {
>         //指定中心位置
>         canvas.translate(circleX, circleY);
> 
>         float hourX1 = (float) Math.cos(Math.toRadians(hour * 30 + 450))
>                 * circleRadius * 0.1f;
>         float hourY1 = (float) Math.sin(Math.toRadians(hour * 30 + 450))
>                 * circleRadius * 0.1f;
>         float hourX = (float) Math.cos(Math.toRadians(hour * 30 + 270))
>                 * circleRadius * 0.9f;
>         float hourY = (float) Math.sin(Math.toRadians(hour * 30 + 270))
>                 * circleRadius * 0.9f;
>         float minuteX = (float) Math.cos(Math.toRadians(minute * 6 + 270))
>                 * circleRadius * 0.75f;
>         float minuteY = (float) Math.sin(Math.toRadians(minute * 6 + 270))
>                 * circleRadius * 0.75f;
>         float secondX = (float) Math.cos(Math.toRadians(second * 6 + 270))
>                 * circleRadius * 0.65f;
>         float secondY = (float) Math.sin(Math.toRadians(second * 6 + 270))
>                 * circleRadius * 0.65f;
> 
> 
>         canvas.drawLine(0, 0, hourX1, hourY1, hourHand);
>         canvas.drawLine(0, 0, hourX, hourY, hourHand);
>         canvas.drawLine(0, 0, minuteX, minuteY, minuteHand);
>         canvas.drawLine(0, 0, secondX, secondY, secondHand);
>     }
> 
> 
>     /**
>      * 设置时间
>      */
>     private void setTimes() {
>         Calendar calendar = Calendar.getInstance();
> 
>         second = calendar.get(Calendar.SECOND);
>         minute = calendar.get(Calendar.MINUTE);
>         hour = calendar.get(Calendar.HOUR) + minute / 12 * 0.2;
>         ;
> 
>     }
> 
> 
> }
> ```



###### 2.2.3 在对应fragment实现效果

> <u>**最后使时钟能跟随系统时间转动，**</u>
>
> 视图ui更新的线程要在**[非主线程]()**中去执行
>
> ```java
> /**
>  * @author chuangxin.chen
>  */
> public class TimeFragment extends Fragment {
> 
>     private ClockView clockView;
>     private TextView time_text;
>     private Handler handler;
> 
>     @Override
>     public View onCreateView(LayoutInflater inflater, ViewGroup container,
>                              Bundle savedInstanceState) {
> 
>         View inflate = inflater.inflate(R.layout.fragment_time, container, false);
>         clockView = inflate.findViewById(R.id.clock);
>         time_text = inflate.findViewById(R.id.time_text);
>         //创建线程去执行
>         HandlerThread handlerThread = new HandlerThread("TimeHandler");
>         handlerThread.start();
>         handler = new Handler(handlerThread.getLooper()) {
>             @Override
>             public void handleMessage(@NonNull Message msg) {
>                 updateTime();
>                 sendEmptyMessageDelayed(0, 1);
> 
>             }
>         };
>         handler.sendEmptyMessage(0);
>         return inflate;
>     }
> 
>     /**
>      * 更新时间
>      */
>     private void updateTime() {
>         clockView.post(new Runnable() {
>             @Override
>             public void run() {
>                 clockView.invalidate();
>             }
>         });
>         time_text.post(new Runnable() {
>             @Override
>             public void run() {
>                 // 获取当前时间
>                 Calendar calendar = Calendar.getInstance();
>                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
>                 String currentTime = sdf.format(calendar.getTime());
>                 time_text.setText(currentTime);
>             }
>         });
>     }
> 
>     @Override
>     public void onDestroy() {
>         super.onDestroy();
>         handler.removeCallbacksAndMessages(null);
>     }
> }
> ```



##### 2.3 我的页面

###### 2.3.1 定义我的页面的基本布局视图

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MeFragment">

    <!-- TODO: Update blank fragment layout -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <!--图片-->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            tools:ignore="Suspicious0dp">

            <ImageView
                android:layout_width="fill_parent"
                android:layout_height="wrap_content"
                android:background="#F1F1F1"
                android:src="@drawable/tinno"></ImageView>
        </LinearLayout>

        <!--姓名-->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:divider="@drawable/line_drawable"
            android:orientation="vertical"
            android:showDividers="end">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal"
                tools:ignore="Suspicious0dp">

                <TextView
                    android:layout_width="50dp"
                    android:layout_height="match_parent"
                    android:gravity="center"
                    android:text="姓名:"
                    android:textSize="20sp"/>

                <TextView
                    android:id="@+id/name_text"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:gravity="center|left"
                    android:text=""
                    android:textSize="20sp" />
            </LinearLayout>
        </LinearLayout>
        <!--电话-->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:divider="@drawable/line_drawable"
            android:orientation="vertical"
            android:showDividers="end">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:divider="@drawable/line_drawable"
                android:orientation="horizontal"
                android:showDividers="end"
                tools:ignore="Suspicious0dp">

                <TextView
                    android:layout_width="50dp"
                    android:layout_height="match_parent"
                    android:gravity="center"
                    android:text="电话:"
                    android:textSize="20sp"/>

                <TextView
                    android:id="@+id/phone_text"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:gravity="center|left"
                    android:text=""
                    android:textSize="20sp"
                    />
            </LinearLayout>
        </LinearLayout>


        <!--性别-->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:divider="@drawable/line_drawable"
            android:orientation="vertical"
            android:showDividers="end">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"

                android:orientation="horizontal"
                tools:ignore="Suspicious0dp">

                <TextView
                    android:layout_width="50dp"
                    android:layout_height="wrap_content"
                    android:gravity="center"
                    android:text="性别:"
                    android:textSize="20sp"/>

                <TextView
                    android:id="@+id/sex_choice"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:gravity="center|left"
                    android:text=""
                    android:textSize="20sp" />
            </LinearLayout>
        </LinearLayout>

        <!--关于app-->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            tools:ignore="Suspicious0dp">

            <TextView
                android:id="@+id/app_text"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:text="关于APP"
                android:textSize="20sp"/>
        </LinearLayout>

    </LinearLayout>

</FrameLayout>
```



###### 2.3.2 定义信息弹出框效果

> 点击信息框的时候会有弹出框提示输入信息，填写信息并确认，信息会保存到本地，且在下次打开的时候会将保存的信息填入对应文本
>
> - 自定义姓名弹出框
>
>   ```xml
>   <?xml version="1.0" encoding="utf-8"?>
>   <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
>       android:layout_width="match_parent"
>       android:layout_height="wrap_content"
>       android:orientation="vertical"
>       android:padding="16dp">
>   
>       <EditText
>           android:id="@+id/input_name"
>           android:layout_width="match_parent"
>           android:layout_height="wrap_content"
>           android:hint="请输入姓名"
>           android:inputType="text" />
>   
>   </LinearLayout>
>   ```
>
> - 自定义电话弹出框（只能输入数字）
>
>   ```xml
>   <?xml version="1.0" encoding="utf-8"?>
>   <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
>       android:layout_width="match_parent"
>       android:layout_height="wrap_content"
>       android:orientation="vertical"
>       android:padding="16dp">
>   
>       <EditText
>           android:id="@+id/input_phone"
>           android:layout_width="match_parent"
>           android:layout_height="wrap_content"
>           android:hint="请输入电话号码"
>           android:inputType="phone" />
>   
>   </LinearLayout>
>   ```
>
> - 性别单选框
>
>   ```xml
>   <?xml version="1.0" encoding="utf-8"?>
>   <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
>       android:layout_width="match_parent"
>       android:layout_height="wrap_content"
>       android:orientation="vertical"
>       android:padding="16dp">
>       <RadioGroup
>           android:id="@+id/sexRadioGroup"
>           android:layout_width="match_parent"
>           android:layout_height="200dp">
>           <RadioButton
>               android:id="@+id/none"
>               android:layout_width="match_parent"
>               android:layout_height="wrap_content"
>               android:text="保密"/>
>           <RadioButton
>               android:id="@+id/boy"
>               android:layout_width="match_parent"
>               android:layout_height="wrap_content"
>               android:text="男"/>
>   
>           <RadioButton
>               android:id="@+id/girl"
>               android:layout_width="match_parent"
>               android:layout_height="wrap_content"
>               android:text="女"/>
>   
>       </RadioGroup>
>   </LinearLayout>
>   ```



###### 2.3.3  对应fragment实现效果

```java
public class MeFragment extends Fragment {

    private String mParam1;
    private String mParam2;
    private TextView name_text;
    private TextView phone_text;
    private TextView sex_choice;
    private TextView app_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_me, container, false);
        Log.d("ccxDemo", "onCreate: 创建了");

        name_text = inflate.findViewById(R.id.name_text);
        phone_text = inflate.findViewById(R.id.phone_text);
        sex_choice = inflate.findViewById(R.id.sex_choice);
        app_text = inflate.findViewById(R.id.app_text);

        SharedPreferences info = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
        String name = info.getString("name", "");
        String phone = info.getString("phoneNumber", "");
        String sex = info.getString("sex","");
        name_text.setText(name);
        phone_text.setText(phone);
        sex_choice.setText(sex);

        //设置姓名弹出框
        name_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("请输入姓名");
                View dialogNameView = getLayoutInflater().inflate(R.layout.input_info, null);
                builder.setView(dialogNameView);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText input_name = dialogNameView.findViewById(R.id.input_name);
                        String name = input_name.getText().toString();
                        //获取SharedPreferences对象
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("name", name);
                        edit.apply();
                        name_text.setText(name);
                        Log.d("SP_name", name);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "继续操作", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
            }
        });
        //设置电话号码弹出框
        phone_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("请输入电话");
                View dialogNameView = getLayoutInflater().inflate(R.layout.input_phoneinfo, null);
                builder.setView(dialogNameView);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText input_phone = dialogNameView.findViewById(R.id.input_phone);
                        String phoneNumber = input_phone.getText().toString();
                        //获取SharedPreferences对象
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();

                        edit.putString("phoneNumber", phoneNumber);
                        edit.apply();
                        phone_text.setText(phoneNumber);
                        Log.d("SP_phone", phoneNumber);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "继续操作", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
            }
        });
        //选择性别
        sex_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("请选择性别");
                View dialogNameView = getLayoutInflater().inflate(R.layout.input_sexinfo, null);
                builder.setView(dialogNameView);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RadioGroup sexRadioGroup = dialogNameView.findViewById(R.id.sexRadioGroup);
                        int checkedRadioButtonId = sexRadioGroup.getCheckedRadioButtonId();
                        String sex = "";
                        if (R.id.none == checkedRadioButtonId) {
                            sex = "保密";
                        } else if (R.id.boy == checkedRadioButtonId) {
                            sex = "男";
                        } else if (R.id.girl == checkedRadioButtonId) {
                            sex = "女";
                        } else {
                            Toast.makeText(getActivity(), "您未选择性别", Toast.LENGTH_SHORT).show();
                        }

                        //获取SharedPreferences对象
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();

                        edit.putString("sex", sex);
                        edit.apply();
                        sex_choice.setText(sex);
                        Log.d("SP_sex", sex);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "继续操作", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
            }
        });
        //关于app
        app_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("关于app");
                builder.setMessage("版本：1.0");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "back", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
            }
        });
        return inflate;
    }
}
```





