package com.example.lzl.java.myapplication.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.lzl.java.myapplication.utils.util;

import java.util.concurrent.ConcurrentHashMap;

public class Sun extends View {
    //前两个动画执行所需的时间
    private static final int ANIMATION_TIME = 200;
    Paint mPaint;
    private static final int COLOR_YELLOW = Color.argb(255, 255, 215, 00);
    private static final int COLOR_WHITE = Color.WHITE;
    private float mHeight;
    private float mWidth;
    private static float MAXRING = util.dip2px(100);

    private RectF mOutRect;
    private RectF mCenterRect;
    float centerStartAngle = -90;
    float centerArcAngle = -90;
    float outStratAngle = -90;
    float outArcAngle = 2;

    private boolean isDrawCircle = false;
    private boolean isDrawArc = false;
    //存储所有动画的ObjectAnimator方便管理
    private ConcurrentHashMap<String, ObjectAnimator> animMap2 = new ConcurrentHashMap<>();

    public float getCenterStartAngle() {
        return centerStartAngle;
    }

    public void setCenterStartAngle(float centerStartAngle) {
        this.centerStartAngle = centerStartAngle;
        invalidate();
    }

    public float getCenterArcAngle() {
        return centerArcAngle;
    }

    public void setCenterArcAngle(float centerArcAngle) {
        this.centerArcAngle = centerArcAngle;
        invalidate();
    }

    public float getOutStratAngle() {
        return outStratAngle;
    }

    public void setOutStratAngle(float outStratAngle) {
        this.outStratAngle = outStratAngle;
        invalidate();
    }

    public float getOutArcAngle() {
        return outArcAngle;
    }

    public void setOutArcAngle(float outArcAngle) {
        this.outArcAngle = outArcAngle;
        invalidate();
    }

    public Sun(Context context) {
        super(context);
    }

    public Sun(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public float getRingYellow() {
        return ringYellow;
    }

    public void setRingYellow(float ringYellow) {
        this.ringYellow = ringYellow;
        invalidate();
    }

    public float getRingWhite() {
        return ringWhite;
    }

    public void setRingWhite(float ringWhite) {
        this.ringWhite = ringWhite;
        invalidate();
    }

    private float ringYellow = util.dip2px(0);
    private float ringWhite = util.dip2px(4);

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);

        //创建矩形
        mOutRect = new RectF();
        mCenterRect = new RectF();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
        Log.e("yyy", "初始化时候的宽高参数" + mHeight + "--" + mWidth);
        creatRectF();
    }

    private void creatRectF() {
        int X1 = (int) (mWidth / 2 - MAXRING);
        int Y1 = (int) (mHeight / 2 - MAXRING);
        int X2 = (int) (mWidth / 2 + MAXRING);
        int Y2 = (int) (mHeight / 2 + MAXRING);
        mOutRect.left = X1;
        mOutRect.top = Y1;
        mOutRect.right = X2;
        mOutRect.bottom = Y2;

        int centerRing = (int) (MAXRING - util.dip2px(20));
        int X3 = (int) (mWidth / 2 - centerRing);
        int Y3 = (int) (mHeight / 2 - centerRing);
        int X4 = (int) (mWidth / 2 + centerRing);
        int Y4 = (int) (mHeight / 2 + centerRing);
        mCenterRect.left = X3;
        mCenterRect.top = Y3;
        mCenterRect.right = X4;
        mCenterRect.bottom = Y4;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1.圆出现消失的绘制
        if (isDrawCircle) {
            mPaint.setShader(null);
            mPaint.setStrokeWidth(0);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(COLOR_YELLOW);
            canvas.drawCircle(mWidth / 2, mHeight / 2, ringYellow, mPaint);
            mPaint.setColor(COLOR_WHITE);
            canvas.drawCircle(mWidth / 2, mHeight / 2, ringWhite, mPaint);
        }
        if (isDrawArc) {
            //2.圆弧出现消失的逻辑
            mPaint.setColor(COLOR_YELLOW);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeWidth(mWidth / 40);
            canvas.drawArc(mCenterRect, outStratAngle, outArcAngle, false, mPaint);//内弧
            mPaint.setStrokeWidth(mWidth / 25);
            canvas.drawArc(mOutRect, centerStartAngle, centerArcAngle, false, mPaint);//外弧
        }
    }

    private static final String CIRCLEY_SCALE_ANIMATION = "circle_scale_y";
    private static final String CIRCLEW_SCALE_ANIMATION = "circle_scale_w";

    /**
     * 为了更灵活的操作动画。每个小块的动画进心拆分好进行时间的控制，以及删除和显示
     * 第一个动画，圆的缩放
     */
    private void startScaleCircleAnimation() {
        //1.外圈的黄色圆的动画
        isDrawCircle = true;
        ObjectAnimator objectAnimatorY = animMap2.get(CIRCLEY_SCALE_ANIMATION);
        if (objectAnimatorY == null) {
            objectAnimatorY = ObjectAnimator.ofFloat(this, "ringYellow", 0, MAXRING);
//            ObjectAnimator animator2 = ObjectAnimator.ofFloat(this,"ringWhite",0,MAXRING+1);
            objectAnimatorY.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    startArcAnimation();
                }
            });
            objectAnimatorY.setDuration(500);
            animMap2.put(CIRCLEY_SCALE_ANIMATION, objectAnimatorY);
        }
        objectAnimatorY.start();
        //2.内圈白的圆的动画
        ObjectAnimator objectAnimatorW = animMap2.get(CIRCLEW_SCALE_ANIMATION);
        if (objectAnimatorW == null) {
            objectAnimatorW = ObjectAnimator.ofFloat(this, "ringWhite", util.dip2px(4), MAXRING + 1);
            objectAnimatorW.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    isDrawCircle = false;
                }
            });
            objectAnimatorW.setStartDelay(300);
            objectAnimatorW.setDuration(300);
            animMap2.put(CIRCLEW_SCALE_ANIMATION, objectAnimatorW);
        }
        objectAnimatorW.start();
    }

    private static final String ARC_CENTER_ANIMATION_ANGLE = "arc_center_animation_angle";
    private static final String ARC_OUT_ANIMATION_ANGLE = "arc_out_animation_angle";
    private static final String ARC_CENTER_ANIMATION_MOVE = "arc_center_animation_move";
    private static final String ARC_OUT_ANIMATION_MOVE = "arc_out_animation_move";

    private void startArcAnimation() {
        isDrawArc = true;
        //1)第一帧，外弧从90度到180，内弧从2度到180度（大小意义上的）

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(this, "outArcAngle", 2, 178);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(this, "centerArcAngle", -90, -180);
        animator1.setDuration(4000);
        animator2.setDuration(5000);
        animator1.start();
        animator2.start();
        //2）第二针，内外弧在180度的状态下旋转180度
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(this, "outStratAngle", -90, 90);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(this, "centerStartAngle", -90, -270);
        animator3.setStartDelay(4000);
        animator3.setDuration(3000);
        animator4.setStartDelay(5000);
        animator4.setDuration(4000);
        animator3.start();
        animator4.start();

        //3）将内外弧收回到0度
        ObjectAnimator animator7 = ObjectAnimator.ofFloat(this, "outStratAngle", 90, 270);
        ObjectAnimator animator5 = ObjectAnimator.ofFloat(this, "outArcAngle", 180, 0);
        animator5.setStartDelay(7000);
        animator5.setDuration(4000);
        animator5.start();
        animator7.setDuration(7000);
        animator7.setDuration(4000);
        animator7.start();
//
//        ObjectAnimator animator8 = ObjectAnimator.ofFloat(this, "centerStartAngle", -270, -450);
//        ObjectAnimator animator6 = ObjectAnimator.ofFloat(this, "centerArcAngle", -180, 0);
//        animator6.setStartDelay(9000);
//        animator6.setDuration(4000);
//        animator6.start();
//        animator8.setDuration(9000);
//        animator8.setDuration(4000);
//        animator8.start();

//        animator8.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                isDrawArc = false;
//            }
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//            }
//        });

        this.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 250);


    }

    /**
     * 动画开始
     */
    public void startAnimation() {
        startScaleCircleAnimation();
    }


    /**
     * 第二部分，圆环的动画。
     *
     * @return
     */
    private AnimatorSet drawArcAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        //1)第一帧，外弧从90度到180，内弧从2度到180度（大小意义上的）
        AnimatorSet animatorSet1 = new AnimatorSet();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(this, "outArcAngle", 2, 178);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(this, "centerArcAngle", -90, -180);
        animatorSet1.playTogether(animator1, animator2);
        //2）第二针，内外弧在180度的状态下旋转180度
        AnimatorSet animatorSet2 = new AnimatorSet();
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(this, "outStratAngle", -90, 90);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(this, "centerStartAngle", -90, -270);
        animatorSet2.playTogether(animator3, animator4);
        //3）将内外弧收回到0度
        AnimatorSet animatorSet5 = new AnimatorSet();

        AnimatorSet animatorSet3 = new AnimatorSet();
        ObjectAnimator animator7 = ObjectAnimator.ofFloat(this, "outStratAngle", 90, 270);
        ObjectAnimator animator5 = ObjectAnimator.ofFloat(this, "outArcAngle", 180, 0);
        animatorSet3.playTogether(animator5, animator7);

        AnimatorSet animatorSet4 = new AnimatorSet();
        ObjectAnimator animator8 = ObjectAnimator.ofFloat(this, "centerStartAngle", -270, -450);
        ObjectAnimator animator6 = ObjectAnimator.ofFloat(this, "centerArcAngle", -180, 0);
        animatorSet4.playTogether(animator8, animator6);

        animatorSet5.playTogether(animatorSet3, animatorSet4);

        animatorSet.playSequentially(animatorSet1, animatorSet2, animatorSet5);
        return animatorSet;
    }

    public void restart() {
        centerStartAngle = -90;
        centerArcAngle = -90;
        outStratAngle = -90;
        outArcAngle = 2;
        isDrawArc = false;
        isDrawCircle = false;
    }
}
