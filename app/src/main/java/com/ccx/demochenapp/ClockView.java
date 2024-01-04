package com.ccx.demochenapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.Date;

/**
 * @author chuangxin.chen
 */
public class ClockView extends View {
    private Paint circlePaint;
    private Paint hourHand;
    private Paint minuteHand;
    private Paint secondHand;
    /**
     * 视图的宽高
     */
    private float mWidth, mHeight;
    /**
     * 圆的半径
     */
    private float circleRadius;
    /**
     * 圆心X,Y坐标
     */
    private float circleX, circleY;
    private int second, minute;
    private double hour;
    private Paint centerPoint;


    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        // 刻盘画笔
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(10);

        //定义时针，中心点画笔
        centerPoint = new Paint(Paint.ANTI_ALIAS_FLAG);
        centerPoint.setColor(Color.RED);


        hourHand = new Paint(Paint.ANTI_ALIAS_FLAG);
        hourHand.setColor(Color.RED);
        hourHand.setStyle(Paint.Style.STROKE);
        hourHand.setStrokeWidth(6);
        //定义分针画笔
        minuteHand = new Paint(Paint.ANTI_ALIAS_FLAG);
        minuteHand.setColor(Color.parseColor("#C427DF"));
        minuteHand.setStrokeWidth(6);
        // 秒针的画笔
        secondHand = new Paint(Paint.ANTI_ALIAS_FLAG);
        secondHand.setColor(Color.BLACK);
        secondHand.setStrokeWidth(4);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        if (mWidth < mHeight) {
            // 圆的半径为view的宽度的一半
            circleRadius = mWidth / 2;
        } else {
            circleRadius = mHeight / 2;
        }
        circleX = mWidth / 2;
        circleY = mHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setTimes();
        drawCirclePoint(canvas);
        drawDial(canvas);
        drawPointer(canvas);
    }

    /**
     * 圆心
     *
     * @param canvas
     */
    private void drawCirclePoint(Canvas canvas) {
        canvas.drawCircle(circleX, circleY, 20, centerPoint);
    }

    /**
     * 画刻度及时间
     */
    private void drawDial(Canvas canvas) {
        // 开始画刻度和数字，总共60个刻度，12个时钟刻度，被5整除画一个时钟刻度，被其余的为分针刻度
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                // 时针刻度
                canvas.drawLine(circleX, circleY - circleRadius,
                        circleX, circleY - circleRadius + 40, circlePaint);

            } else {
                // 画分针刻度
                canvas.drawLine(circleX, circleY - circleRadius,
                        circleX, circleY - circleRadius + 10, circlePaint);
            }
            // 画布旋转6度
            canvas.rotate(360 / 60, circleX, circleY);
        }
    }

    /**
     * 画指针 X点坐标 cos(弧度)*r Y点坐标 sin(弧度)*r toRadians将角度转成弧度
     * 安卓坐标系与数学坐标系不同的地方是X轴是相反的，所以为了调整方向，需要将角度+270度
     */
    private void drawPointer(Canvas canvas) {
        //指定中心位置
        canvas.translate(circleX, circleY);

        float hourX1 = (float) Math.cos(Math.toRadians(hour * 30 + 450))
                * circleRadius * 0.1f;
        float hourY1 = (float) Math.sin(Math.toRadians(hour * 30 + 450))
                * circleRadius * 0.1f;
        float hourX = (float) Math.cos(Math.toRadians(hour * 30 + 270))
                * circleRadius * 0.9f;
        float hourY = (float) Math.sin(Math.toRadians(hour * 30 + 270))
                * circleRadius * 0.9f;
        float minuteX = (float) Math.cos(Math.toRadians(minute * 6 + 270))
                * circleRadius * 0.75f;
        float minuteY = (float) Math.sin(Math.toRadians(minute * 6 + 270))
                * circleRadius * 0.75f;
        float secondX = (float) Math.cos(Math.toRadians(second * 6 + 270))
                * circleRadius * 0.65f;
        float secondY = (float) Math.sin(Math.toRadians(second * 6 + 270))
                * circleRadius * 0.65f;


        canvas.drawLine(0, 0, hourX1, hourY1, hourHand);
        canvas.drawLine(0, 0, hourX, hourY, hourHand);
        canvas.drawLine(0, 0, minuteX, minuteY, minuteHand);
        canvas.drawLine(0, 0, secondX, secondY, secondHand);
    }


    /**
     * 设置时间
     */
    private void setTimes() {
        Calendar calendar = Calendar.getInstance();

        second = calendar.get(Calendar.SECOND);
        minute = calendar.get(Calendar.MINUTE);
        hour = calendar.get(Calendar.HOUR) + minute / 12 * 0.2;
        ;

    }


}
