package apy.utils.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import apy.utils.R;

/**
 * Created by apy on 2017/7/22.
 */

public class CommomDialog extends Dialog implements View.OnClickListener {

    private TextView contentTxt;
    private TextView titleTxt;
    private TextView rightBtn;
    private TextView leftBtn;

    private Context mContext;
    private String content;
    private OnRightBtnListener listener;
    private OnLeftBtnListener leftBtnListener;
    private String positiveName;
    private String negativeName;
    private String title;

    public CommomDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public CommomDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public CommomDialog(Context context, int themeResId, String content, OnRightBtnListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }
    public CommomDialog(Context context, int themeResId, String content, OnLeftBtnListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.leftBtnListener = listener;
    }

    public CommomDialog(Context context, int themeResId, String content, OnLeftBtnListener listener, OnRightBtnListener rightBtnListener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.leftBtnListener = listener;
        this.listener = rightBtnListener;
    }


    protected CommomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public CommomDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public CommomDialog setPositiveButton(String name) {
        this.positiveName = name;
        return this;
    }

    public CommomDialog setNegativeButton(String name) {
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_commom);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        contentTxt = (TextView) findViewById(R.id.content);
        titleTxt = (TextView) findViewById(R.id.title);
        rightBtn = (TextView) findViewById(R.id.rigltBtn);
        rightBtn.setOnClickListener(this);
        leftBtn = (TextView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(this);

        contentTxt.setText(content);
        if (!TextUtils.isEmpty(positiveName)) {
            rightBtn.setText(positiveName);
        }

        if (!TextUtils.isEmpty(negativeName)) {
            leftBtn.setText(negativeName);
        }

        if (!TextUtils.isEmpty(title)) {
            titleTxt.setText(title);
        }

    }

    public void setGoneView(){
        leftBtn.setVisibility(View.GONE);
    }

    public void setLeftBtnTv(String str){
        leftBtn.setText(str);
    }

    public void setRightBtnTv(String str){
        rightBtn.setText(str);
    }
    public void setLeftColor(int color){
        leftBtn.setTextColor(color);
    }

    public void setRightColor(int color){
        rightBtn.setTextColor(color);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.leftBtn) {
            if (leftBtnListener != null) {
                leftBtnListener.onClick(this, true);
            }
            this.dismiss();

        } else if (i == R.id.rigltBtn) {
            if (listener != null) {
                listener.onClick(this, true);
            }
            this.dismiss();

        }
    }

    public interface OnRightBtnListener {
        void onClick(Dialog dialog, boolean confirm);
    }

    public interface OnLeftBtnListener {
        void onClick(Dialog dialog, boolean confirm);
    }
}
