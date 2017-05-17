package com.mxmh.crystallization.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.mxmh.crystallization.R;


public class SetPopWin extends PopupWindow {

    private final Button bt_goto_phone;
    private final Button bt_goto_interestiong_phone;
    private View view;

    private PopupWindowCallBack mCallBack;

    public SetPopWin(Context context, PopupWindowCallBack callBack) {

        this.mCallBack = callBack;
        this.view = LayoutInflater.from(context).inflate(R.layout.set_popwindow, null);

        bt_goto_phone = (Button) this.view.findViewById(R.id.bt_goto_phone);
        bt_goto_interestiong_phone = (Button) this.view.findViewById(R.id.bt_goto_interestiong_phone);

        bt_goto_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.goToLookPhotos();
            }
        });
        bt_goto_interestiong_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.goToLookInterestingPhotos();
            }
        });

        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setOutsideTouchable(true);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画
        this.setAnimationStyle(R.style.Transparent_Dialog);
    }

    public interface PopupWindowCallBack {
        void goToLookPhotos();
        void goToLookInterestingPhotos();
    }

}
