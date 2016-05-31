package com.example.administrator.dispatchtouchevent;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/5/31.
 */
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
