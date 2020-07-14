package com.example.myapplication.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;
/*
*
* 这是一个禁止拖动的listview;
*
*
* */
public class MyListView extends ListView {
    private boolean whetherSelectScrolled;
    private int mPosition;
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setWhetherSelectScrolled(boolean t){
        this.whetherSelectScrolled=t;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int actionMasked =ev.getActionMasked()&MotionEvent.ACTION_MASK;
        if (actionMasked == MotionEvent.ACTION_MOVE && !whetherSelectScrolled) {
            return true;
        }

        if (actionMasked == MotionEvent.ACTION_DOWN) {
            // 记录手指按下时的位置
            mPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
            return super.dispatchTouchEvent(ev);
        }
        // 手指抬起时
        if (actionMasked == MotionEvent.ACTION_UP
                || actionMasked == MotionEvent.ACTION_CANCEL) {
            // 手指按下与抬起都在同一个视图内，交给父控件处理，这是一个点击事件
            if (pointToPosition((int) ev.getX(), (int) ev.getY()) == mPosition) {
                super.dispatchTouchEvent(ev);
            } else {
                // 如果手指已经移出按下时的Item，说明是滚动行为，清理Item pressed状态, 点击事件都能通过触摸后移动来取消这个事件
                setPressed(false);
                invalidate();
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
