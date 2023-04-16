package com.example.navigationview.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.navigationview.R;


public class WidgetHeader extends RelativeLayout {

    //返回按钮
    private ImageButton mBackImage;
    //中央标题
    private TextView mTitleView;
    //返回按钮默认图标
    private static int DEFAULT_BACK_RESOURCE = R.drawable.icon_back_white;
    //中央标题颜色
    private int DEFAULT_TITLE_COLOR = Color.parseColor("#ffffff");

    private String mTitle;
    private int mTitleColor = DEFAULT_TITLE_COLOR;
    private int mBackImageSrc = DEFAULT_BACK_RESOURCE;

    private Context context;

    public WidgetHeader(Context context) {
        super(context);
        initUi(context, null);
    }

    public WidgetHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUi(context, attrs);
    }

    public WidgetHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUi(context, attrs);
    }

    private void initUi(Context mContext, AttributeSet attrs) {
        this.context = mContext;
        LayoutInflater.from(context).inflate(R.layout.widget_header, this);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WidgetHeader);
            mTitle = a.getString(R.styleable.WidgetHeader_header_title);
            mTitleColor = a.getColor(R.styleable.WidgetHeader_header_title_color, DEFAULT_TITLE_COLOR);
            mBackImageSrc = a.getResourceId(R.styleable.WidgetHeader_header_back_img_src, DEFAULT_BACK_RESOURCE);
            a.recycle();
        }
        initView();
    }

    private void initView() {
        mBackImage = findViewById(R.id.img_back);
        mTitleView = findViewById(R.id.tv_title);
        mBackImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });
        if (mBackImageSrc != 0) {
            mBackImage.setImageResource(mBackImageSrc);
        }
        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleColor);
    }

    /**
     * 设置中央(主要)标题
     */
    public void setTitle(String title) {
        if (mTitleView != null && !TextUtils.isEmpty(title)) {
            mTitleView.setText(title);
        }
    }

    public void setOnBackListener(OnClickListener listener) {
        mBackImage.setOnClickListener(listener);
    }

}




