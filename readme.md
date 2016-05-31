                 自定义组合View

##第一步：在 res/values下面新建一个   atts.xml  
###参数说明：
> * declare-styleable 标签的name 即为自定义view的名字。

> * attr 的name 是自定义属性名。

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="Topbar">
        <attr name="toptitle" format="string"/>
        <attr name="toptitletxtsize" format="dimension"/>
        <attr name="toptitletxtcolor" format="color"/>
        
        <attr name="righttxt" format="string"/>
        <attr name="righttxtcolor" format="color"/>
        <attr name="righttxtsize" format="dimension"/>
        <attr name="rightbackground" format="color|reference"/>
        
        <attr name="lefttxt" format="string"/>
        <attr name="lefttxtcolor" format="color"/>
        <attr name="lefttxtsize" format="dimension"/>
        <attr name="leftbackground" format="color|reference"/>
    </declare-styleable>
    
</resources>
```
## 第二步创建  java包下创建Topbar.class

### 说明：

 > * 首先声明用到的 控件 以及 自定义的属性。
 > *  将属性与对的的控件组合在一起。

```java
public class Topbar extends RelativeLayout {


    private  LayoutParams leftParams ,rightParams,titleparams;
    private TextView title;
    private Button leftbt,rightbt;

    private int lefttxtcolor;
    private float lefttxtsize;
    private String lefttxt;
    private Drawable leftbackground;

    private int righttxtcolor;
    private float righttxtsize;
    private String righttxt;
    private Drawable rightbackground;

    private  int titletxtcolor;
    private  float titletxtsize;
    private String titletxt;
    public TopbarListener topbarListener;

    public interface TopbarListener{
       public void onleftclick();
       public void onrightclick();
       public  void ontitleclick();

    }
    public void setOnTopbarClicklistener(TopbarListener listener){
       this.topbarListener=listener;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.Topbar);

        lefttxtcolor=ta.getColor(R.styleable.Topbar_lefttxtcolor,0);
         lefttxtsize=ta.getDimension(R.styleable.Topbar_lefttxtsize,0);
         lefttxt=ta.getString(R.styleable.Topbar_lefttxt);
        leftbackground=ta.getDrawable(R.styleable.Topbar_leftbackground);

        righttxtcolor=ta.getColor(R.styleable.Topbar_righttxtcolor,0);
         righttxtsize=ta.getDimension(R.styleable.Topbar_righttxtsize,0);
         righttxt=ta.getString(R.styleable.Topbar_righttxt);
        rightbackground=ta.getDrawable(R.styleable.Topbar_leftbackground);

        titletxtcolor=ta.getColor(R.styleable.Topbar_toptitletxtcolor,0);
         titletxtsize=ta.getDimension(R.styleable.Topbar_toptitletxtsize,0);
         titletxt=ta.getString(R.styleable.Topbar_toptitle);

        ta.recycle();
        title=new TextView(context);
        leftbt=new Button(context);
        rightbt=new Button(context);

        rightbt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               topbarListener.onrightclick();
            }
        });
        leftbt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               topbarListener.onleftclick();
            }
        });
        title.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
              topbarListener.ontitleclick();
            }
        });

        title.setText(titletxt);
        title.setTextSize(titletxtsize);
        title.setTextColor(titletxtcolor);
        title.setGravity(Gravity.CENTER);

        leftbt.setTextColor(lefttxtcolor);
        leftbt.setText(lefttxt);
        leftbt.setTextSize(lefttxtsize);
        leftbt.setBackground(leftbackground);


        rightbt.setTextColor(righttxtcolor);
        rightbt.setText(righttxt);
        rightbt.setTextSize(righttxtsize);
        rightbt.setBackground(rightbackground);


        leftParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);

        addView(leftbt,leftParams);
        rightParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
         addView(rightbt,rightParams);

        titleparams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        titleparams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(title,titleparams);
    }



}

```

##第三步：使用自定义的View  Topbar

###在布局文件中声明要用到自定义的属性


```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
 
    //此处要声明你要用到的自定义属性 为res-auto
    xmlns:custom="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.administrator.dispatchtouchevent.MainActivity">

  <com.example.administrator.dispatchtouchevent.Topbar
      android:layout_width="match_parent"
      android:layout_height="50dp"
      custom:toptitle="微信"
      custom:toptitletxtcolor="@color/red"
      custom:toptitletxtsize="14sp"
      custom:rightbackground="@color/black"
      custom:righttxt="右边"
      custom:righttxtcolor="@color/blue"
      custom:righttxtsize="12sp"
      custom:leftbackground="@color/black"
      custom:lefttxt="左边"
      custom:lefttxtcolor="@color/yello"
      custom:lefttxtsize="12sp"
      >

  </com.example.administrator.dispatchtouchevent.Topbar>
</RelativeLayout>
```

#总结：

>* 回调监听即 在目标对象中定义一个方法 该方法中的 参数为一个接口，在要监听的地方调用该接口。