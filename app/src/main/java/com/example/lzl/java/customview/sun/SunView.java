package com.example.lzl.java.customview.sun;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SunView extends View {

    private Paint mPaint;

    public SunView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿设置
        mPaint.setDither(true);//防抖动
        //第二个动画圆弧所需的矩形
        mRectCenterArc=new RectF();
        mRectOutSideArc =new RectF();
        //第三个动画太阳
        mRectFSunFlower =new RectF();
        //云的path
        mPath=new Path();
        //加入云Path的各部位的信息
        mCircleInfoTopOne=new CircleInfo();
        mCircleInfoTopTwo=new CircleInfo();
        mCircleInfoBottomOne=new CircleInfo();
        mCircleInfoBottomTwo=new CircleInfo();
        mCircleInfoBottomThree=new CircleInfo();
        //云阴影Path
        mCloudShadowPath =new Path();
        mRectFCloudShadow =new RectF();
        //太阳的
        mRectFSunShadow =new RectF();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=getMeasuredWidth();
        setMeasuredDimension(width, (int) (width*1.4f));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isDrawRing){
            drawZoomRing(canvas);
        }
        if (isDrawArcLine){
            drawArcLine(canvas);
        }
        if(isDrawSun){
            drawSun(canvas);
        }
        if (isDrawCloud) {
            drawCloud(canvas);
        }
        if (isDrawCloudShadow) {
            drawCloudShadow(canvas);
        }
        if (isDrawSunShadow) {
            drawSunShadow(canvas);
        }
    }

    //画第一步的动画圆
    private boolean isDrawRing = false;//
    private int ringColor= Color.parseColor("#ffcf45");
    private float minRingCenterWidth;
    private float ringWidth;
    private void drawZoomRing(Canvas canvas) {
        mPaint.setShader(null);
        mPaint.setStrokeWidth(0);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(ringColor);
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,ringWidth/2,mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,minRingCenterWidth/2,mPaint);
    }

    /**
     * 重置
     */
    private void resetAnim(){
        stopAnimAndRemoveCallbacks();
        //相应绘制位置的控制初始化
        isDrawRing=false;
        isDrawArcLine= false;
        isDrawSun=false;
        isDrawSunShadow=false;
        isDrawCloud = false;
        isDrawCloudShadow =false;
        //圆环部分初始化
        minRingCenterWidth=10;//白色圆
        ringWidth=3*minRingCenterWidth;//黄色圆
        //圆弧部分
        centerArcAngle=2;
        centerArcEndAngle =270+centerArcAngle;
        outSideArcStartAngle=180;
        outSideArcAngle=90;
        mRectCenterArc.set(getMeasuredWidth()/2-getMeasuredWidth()/6,getMeasuredHeight()/2-getMeasuredWidth()/6
                ,getMeasuredWidth()/2+getMeasuredWidth()/6,getMeasuredHeight()/2+getMeasuredWidth()/6);
        mRectOutSideArc.set(getMeasuredWidth()/2-getMeasuredWidth()/4,getMeasuredHeight()/2-getMeasuredWidth()/4
                ,getMeasuredWidth()/2+getMeasuredWidth()/4,getMeasuredHeight()/2+getMeasuredWidth()/4);
        //太阳旋转
        sunRotateAngle=0;
        //太阳出现
        sunWidth=0;
        finalSunWidth =getMeasuredWidth()*0.7f;
        maxSunFlowerWidth= (float) Math.sqrt(Math.pow(getMeasuredWidth()/2.0f,2)*2);
        mRectFSunFlower.set(0,0,0,0);
        mFlowerLinearGradient=new LinearGradient(getMeasuredWidth()/2-maxSunFlowerWidth/2,getMeasuredHeight()/2-maxSunFlowerWidth/2,
                getMeasuredWidth()/2+maxSunFlowerWidth/2,getMeasuredHeight()/2+maxSunFlowerWidth/2,
                new int[]{Color.parseColor("#fff38e"),Color.parseColor("#ebb228") ,Color.parseColor("#ae8200")},new float[]{0,0.5f,1}, Shader.TileMode.REPEAT);
        mFlowerRotateLinearGradient=new LinearGradient(getMeasuredWidth()/2-maxSunFlowerWidth/2,getMeasuredHeight()/2-maxSunFlowerWidth/2,
                getMeasuredWidth()/2+maxSunFlowerWidth/2,getMeasuredHeight()/2-maxSunFlowerWidth/2,
                Color.parseColor("#f7b600") ,Color.parseColor("#ae8200"), Shader.TileMode.REPEAT);
        //云
        mCircleInfoBottomOne.setCircleInfo(getMeasuredWidth()/2,getMeasuredHeight()/2+getMeasuredWidth()/10,getMeasuredWidth()/10,false);//左下1
        mCircleInfoTopOne.setCircleInfo(getMeasuredWidth()/2+getMeasuredWidth()/14,getMeasuredHeight()/2-getMeasuredWidth()/30,getMeasuredWidth()/9,false);//顶1
        mCircleInfoBottomTwo.setCircleInfo(getMeasuredWidth()/2+getMeasuredWidth()/10*1.8f,getMeasuredHeight()/2+getMeasuredWidth()/10,getMeasuredWidth()/9,false);//底部2
        mCircleInfoTopTwo.setCircleInfo(getMeasuredWidth()/2+getMeasuredWidth()/10*2.2f,getMeasuredHeight()/2-getMeasuredWidth()/120,getMeasuredWidth()/10,false);//顶2
        mCircleInfoBottomThree.setCircleInfo(getMeasuredWidth()/2+getMeasuredWidth()/10*3.5f,getMeasuredHeight()/2+getMeasuredWidth()/10,getMeasuredWidth()/12,false);//底3
        mCloudLinearGradient =new LinearGradient(mCircleInfoBottomOne.getX()-mCircleInfoBottomOne.getRadius(),mCircleInfoTopOne.getY()-mCircleInfoTopOne.getRadius(),getMeasuredWidth(),getMeasuredHeight()/2+getMeasuredWidth()/7f,
                new int[]{Color.parseColor("#fcfbf3"),Color.parseColor("#efebdf") ,Color.parseColor("#d7d7c7")},new float[]{0,0.5f,1}, Shader.TileMode.REPEAT);
        //云阴影
        cloudShadowAlpha=0;
        //整个天气View的阴影
        sunShadowHeight =getMeasuredWidth()/25;
        invalidate();
    }
    public void startAnim(){
        resetAnim();
        startInvalidateAnim();
    }
    //动画是否开始
    private boolean isStart=false;
    //存储所有动画的ValueAnimator方便管理
    private ConcurrentHashMap<String, ValueAnimator> animMap=new ConcurrentHashMap<>();
    private static final String ANIM_CONTROL_INVALIDATE="anim_control_invalidate";
    private void startInvalidateAnim() {
        isStart = true;
        ValueAnimator valueAnimator=animMap.get(ANIM_CONTROL_INVALIDATE);
        if(valueAnimator == null){
            valueAnimator = ValueAnimator.ofFloat(0,1);//通过静态方法创建valueAnimator
            valueAnimator.setDuration(300);
            valueAnimator.setInterpolator(new LinearInterpolator());//设置差值器
            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    invalidate();
                }
            });
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    startRing();
                }
            });
            animMap.put(ANIM_CONTROL_INVALIDATE,valueAnimator);
        }
        startValueAnimator(valueAnimator);
    }

    /**
     * 执行动画
     * @param valueAnimator
     */
    private void startValueAnimator(ValueAnimator valueAnimator){
        if (isStart) {
            valueAnimator.start();
        }
    }
    //两个圆环放大的过程，
    public static final String ANIM_RING_ZOOM="anim_ring_zoom";
    public static final String ANIM_RING_CIRCLE_ZOOM="anim_ring_circle_zoom";
    private void startRing() {
        isDrawRing = true;
        //黄圈动画
        ValueAnimator zoomAnim= animMap.get(ANIM_RING_ZOOM);
        if(zoomAnim ==null){
            zoomAnim = ValueAnimator.ofFloat().setDuration(500);
            //在动画执行过程中修改绘制数据
            zoomAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    ringWidth = (float) animation.getAnimatedValue();
                }
            });
            zoomAnim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    startArcLine();
                }
            });
            animMap.put(ANIM_RING_ZOOM,zoomAnim);
        }
        zoomAnim.setFloatValues(ringWidth,getMeasuredWidth()*0.8f);//30 到屏幕宽度的0.8倍
        startValueAnimator(zoomAnim);

        //白圈动画
        ValueAnimator circleZoom= animMap.get(ANIM_RING_CIRCLE_ZOOM);
        if(circleZoom == null){
            circleZoom = ValueAnimator.ofFloat().setDuration(300);
            circleZoom.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    minRingCenterWidth = (float) animation.getAnimatedValue();
                }
            });
            circleZoom.setStartDelay(300);//延迟执行
            circleZoom.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    isDrawRing = false;//动画执行完，不再绘制这一部分
                }
            });
            animMap.put(ANIM_RING_CIRCLE_ZOOM,circleZoom);
        }
        circleZoom.setFloatValues(minRingCenterWidth,getMeasuredWidth()*0.8f);
        startValueAnimator(circleZoom);
    }


    /**
     * 绘制第二个动画的圆弧
     * @param canvas
     */
    private RectF mRectCenterArc;//小圆弧
    private RectF mRectOutSideArc;//大圆弧
    private float centerArcAngle, centerArcEndAngle;
    private float outSideArcAngle,outSideArcStartAngle;
    private void drawArcLine(Canvas canvas) {
        mPaint.setColor(ringColor);
        mPaint.setStyle(Paint.Style.STROKE);//圆环
        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆头
        mPaint.setStrokeWidth(getMeasuredWidth()/40);//环的宽度
        canvas.drawArc(mRectCenterArc, centerArcEndAngle-centerArcAngle,centerArcAngle,false,mPaint);
        mPaint.setStrokeWidth(getMeasuredWidth()/25);
        canvas.drawArc(mRectOutSideArc,outSideArcStartAngle,outSideArcAngle,false,mPaint);
    }
    /**
     * 第二个动画的开始画弧转动的动画
     */
    public static final String ANIM_ARC_LINE_CENTER_ANGLE="anim_arc_line_center_angle";
    public static final String ANIM_ARC_LINE_CENTER_MOVE ="anim_arc_line_center_move";
    public static final String ANIM_ARC_LINE_OUTSIZE_ANGLE="anim_arc_line_outsize_angle";
    public static final String ANIM_ARC_LINE_OUTSIZE_MOVE ="anim_arc_line_outsize_move";
    private boolean isDrawArcLine=false;
    private void startArcLine() {
        isDrawArcLine=true;
        //小弧长控制
        ValueAnimator centerArcLineAngleAnim= animMap.get(ANIM_ARC_LINE_CENTER_ANGLE);
        if (centerArcLineAngleAnim==null){
            centerArcLineAngleAnim=ValueAnimator.ofFloat().setDuration(500);
            centerArcLineAngleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    centerArcAngle= (float) animation.getAnimatedValue();
                }
            });
            animMap.put(ANIM_ARC_LINE_CENTER_ANGLE,centerArcLineAngleAnim);
        }
        centerArcLineAngleAnim.setFloatValues(centerArcAngle,180,0);//2度到180度再到0度
        startValueAnimator(centerArcLineAngleAnim);
        //小弧移动控制
        ValueAnimator centerArcLineMoveAnim= animMap.get(ANIM_ARC_LINE_CENTER_MOVE);
        if (centerArcLineMoveAnim==null){
            centerArcLineMoveAnim=ValueAnimator.ofFloat().setDuration(400);
            centerArcLineMoveAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    centerArcEndAngle = (float) animation.getAnimatedValue();
                }
            });
            centerArcLineMoveAnim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    isDrawArcLine=false;
                }
            });
            animMap.put(ANIM_ARC_LINE_CENTER_MOVE,centerArcLineMoveAnim);
        }
        centerArcLineMoveAnim.setFloatValues(centerArcEndAngle,630);
        startValueAnimator(centerArcLineMoveAnim);

        //外部圆弧长度控制
        ValueAnimator outSizeArcLineAngleAnim= animMap.get(ANIM_ARC_LINE_OUTSIZE_ANGLE);
        if (outSizeArcLineAngleAnim==null){
            outSizeArcLineAngleAnim=ValueAnimator.ofFloat().setDuration(400);
            outSizeArcLineAngleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    outSideArcAngle= (float) animation.getAnimatedValue();
                }
            });
            animMap.put(ANIM_ARC_LINE_OUTSIZE_ANGLE,outSizeArcLineAngleAnim);
        }
        outSizeArcLineAngleAnim.setFloatValues(outSideArcAngle,180,0);
        startValueAnimator(outSizeArcLineAngleAnim);

        //外部圆弧移动控制
        ValueAnimator outSizeArcLineMoveAnim= animMap.get(ANIM_ARC_LINE_OUTSIZE_MOVE);
        if (outSizeArcLineMoveAnim==null){
            outSizeArcLineMoveAnim=ValueAnimator.ofFloat().setDuration(300);
            outSizeArcLineMoveAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    outSideArcStartAngle= (float) animation.getAnimatedValue();
                }
            });
            animMap.put(ANIM_ARC_LINE_OUTSIZE_MOVE,outSizeArcLineMoveAnim);
        }
        outSizeArcLineMoveAnim.setFloatValues(outSideArcStartAngle,-90);
        startValueAnimator(outSizeArcLineMoveAnim);
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                startSun();
            }
        },250);
    }


    //太阳花朵，白云的Shade
    private LinearGradient mCloudLinearGradient,mFlowerLinearGradient,mFlowerRotateLinearGradient;
    private float sunRotateAngle=0;
    private int cloudShadowAlpha =0;
    private RectF mRectFSunFlower;
    private float sunWidth;
    private float finalSunWidth;
    private float maxSunFlowerWidth;
    private void drawSun(Canvas canvas) {
        mPaint.setStrokeWidth(0);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(ringColor);
        mPaint.setShader(mFlowerLinearGradient);
        canvas.save();
        canvas.rotate(sunRotateAngle,getMeasuredWidth()/2,getMeasuredHeight()/2);
        canvas.drawRect(mRectFSunFlower,mPaint);
        canvas.rotate(45,getMeasuredWidth()/2,getMeasuredHeight()/2);
        mPaint.setShader(mFlowerRotateLinearGradient);
        canvas.drawRect(mRectFSunFlower,mPaint);
        canvas.restore();
        mPaint.setShader(null);
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,sunWidth/2,mPaint);
    }
    /**
     * 第三块动画太阳升起
     */
    private boolean isDrawSun=false;
    public static final String ANIM_SUN_ZOOM="anim_sun_zoom";
    public static final String ANIM_FLOWER_ZOOM="anim_flower_zoom";
    private void startSun() {
        isDrawSun=true;
        //太阳内部圆
        ValueAnimator sunAnim= animMap.get(ANIM_SUN_ZOOM);
        if (sunAnim==null){
            sunAnim=ValueAnimator.ofFloat().setDuration(400);
            sunAnim.setInterpolator(new AccelerateDecelerateInterpolator());
            sunAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    sunWidth= (float) animation.getAnimatedValue();
                }
            });
            animMap.put(ANIM_SUN_ZOOM,sunAnim);
        }
        sunAnim.setFloatValues(sunWidth,getMeasuredWidth(),finalSunWidth);//0-屏幕宽度-0.7倍的屏幕宽度
        startValueAnimator(sunAnim);

        //太阳光环
        ValueAnimator flowerAnim= animMap.get(ANIM_FLOWER_ZOOM);
        if (flowerAnim==null){
            flowerAnim=ValueAnimator.ofFloat().setDuration(400);
            flowerAnim.setInterpolator(new AccelerateDecelerateInterpolator());//插值器，加速减速
            flowerAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float width= (float) animation.getAnimatedValue();
                    mRectFSunFlower.set(getMeasuredWidth()/2f-width/2,getMeasuredHeight()/2f-width/2
                            ,getMeasuredWidth()/2f+width/2,getMeasuredHeight()/2f+width/2);
                }
            });
            flowerAnim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    startSunRotate();
                }
            });
            flowerAnim.setStartDelay(100);
            animMap.put(ANIM_FLOWER_ZOOM,flowerAnim);
        }
        flowerAnim.setFloatValues(0,maxSunFlowerWidth,maxSunFlowerWidth*0.9f);
        startValueAnimator(flowerAnim);
        //太阳画完了，开始画云
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                startCloud();
            }
        },300);
        //太阳画完了，开始画太阳阴影
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                startSunShadow();
            }
        },200);
    }


    /**
     * 方形旋转的动画
     */
    public static final String ANIM_SUN_ROTATE="anim_sun_rotate";
    private void startSunRotate() {
        ValueAnimator rotateAnim=animMap.get(ANIM_SUN_ROTATE);
        if (rotateAnim==null){
            rotateAnim =ValueAnimator.ofFloat(0,360f).setDuration(30*1000);
            rotateAnim.setRepeatCount(ValueAnimator.INFINITE);
            rotateAnim.setInterpolator(new LinearInterpolator());
            rotateAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    sunRotateAngle= (float) animation.getAnimatedValue();
                }
            });
            animMap.put(ANIM_SUN_ROTATE,rotateAnim);
        }
        startValueAnimator(rotateAnim);
    }

    /**
     * 画云
     * @param canvas
     */
    private Path mPath;
    private boolean isDrawCloud = false;
    //云朵的组成部分信息
    private CircleInfo mCircleInfoTopOne,mCircleInfoTopTwo,mCircleInfoBottomOne,mCircleInfoBottomTwo,mCircleInfoBottomThree;
    public static final String ANIM_CLOUD_TOP_ONE="anim_cloud_top_one";
    public static final String ANIM_CLOUD_TOP_TWO="anim_cloud_top_two";
    public static final String ANIM_CLOUD_BOTTOM_ONE="anim_cloud_bottom_one";
    public static final String ANIM_CLOUD_BOTTOM_TWO="anim_cloud_bottom_two";
    public static final String ANIM_CLOUD_BOTTOM_THREE="anim_cloud_bottom_three";
    private void drawCloud(Canvas canvas) {
        mPath.reset();
        mPaint.setShader(mCloudLinearGradient);
        if (mCircleInfoBottomOne.isCanDraw())
            mPath.addCircle(mCircleInfoBottomOne.getX(),mCircleInfoBottomOne.getY(),mCircleInfoBottomOne.getRadius(), Path.Direction.CW);//左下1
        if (mCircleInfoBottomTwo.isCanDraw())
            mPath.addCircle(mCircleInfoBottomTwo.getX(),mCircleInfoBottomTwo.getY(),mCircleInfoBottomTwo.getRadius(), Path.Direction.CW);//底部2
        if (mCircleInfoBottomThree.isCanDraw())
            mPath.addCircle(mCircleInfoBottomThree.getX(),mCircleInfoBottomThree.getY(),mCircleInfoBottomThree.getRadius(), Path.Direction.CW);//底3
        if (mCircleInfoTopOne.isCanDraw())
            mPath.addCircle(mCircleInfoTopOne.getX(),mCircleInfoTopOne.getY(),mCircleInfoTopOne.getRadius(), Path.Direction.CW);//顶1
        if (mCircleInfoTopTwo.isCanDraw())
            mPath.addCircle(mCircleInfoTopTwo.getX(),mCircleInfoTopTwo.getY(),mCircleInfoTopTwo.getRadius(), Path.Direction.CW);//顶2
        canvas.save();
        canvas.clipRect(0,0,getMeasuredWidth(),getMeasuredHeight()/2+getMeasuredWidth()/7f);
        canvas.drawPath(mPath,mPaint);
        canvas.restore();
        mPaint.setShader(null);
    }

    /**
     * 第四组动画云的动画
     */
    private void startCloud() {
        isDrawCloud=true;
        startCloudTemplate(ANIM_CLOUD_BOTTOM_ONE,0,mCircleInfoBottomOne);
        startCloudTemplate(ANIM_CLOUD_BOTTOM_TWO,200,mCircleInfoBottomTwo);
        startCloudTemplate(ANIM_CLOUD_BOTTOM_THREE,450,mCircleInfoBottomThree);
        startCloudTemplate(ANIM_CLOUD_TOP_ONE,300,mCircleInfoTopOne);
        startCloudTemplate(ANIM_CLOUD_TOP_TWO,350,mCircleInfoTopTwo);
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                startCloudShadow();
            }
        },600);
    }

    /**
     * 每个圆的生成动画，云
     * @param mapTag
     * @param delay
     * @param circleInfo
     */
    private void startCloudTemplate(String mapTag, long delay, final CircleInfo circleInfo){
        ValueAnimator valueAnimator=animMap.get(mapTag);
        if (valueAnimator==null){
            //TypeEvaleator 是动画执行的百分比转换为数值
            valueAnimator=ValueAnimator.ofObject(new CircleTypeEvaluator(circleInfo),new CircleInfo(circleInfo.getX(),circleInfo.getY()+circleInfo.getRadius(),0)
                    ,new CircleInfo(circleInfo.getX(),circleInfo.getY(),circleInfo.getRadius()));//半径从0到设置的值，位置从下面上来。
            valueAnimator.setDuration(600);
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    circleInfo.setCanDraw(true);
                }
            });
            animMap.put(mapTag,valueAnimator);
        }
        ////半径从0到设置的值，位置从下面上来。
        valueAnimator.setObjectValues(new CircleInfo(circleInfo.getX(),circleInfo.getY()+circleInfo.getRadius(),0)
                ,new CircleInfo(circleInfo.getX(),circleInfo.getY(),circleInfo.getRadius()));
        valueAnimator.setStartDelay(delay);
        startValueAnimator(valueAnimator);
    }

    /**
     * 画云
     * @param canvas
     */
    private Path mCloudShadowPath;
    private int cloudShadowColor=Color.parseColor("#bc9a31");
    private RectF mRectFCloudShadow;
    private void drawCloudShadow(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(cloudShadowColor);
        mPaint.setAlpha(cloudShadowAlpha);
        canvas.save();
        canvas.clipRect(0,getMeasuredHeight()/2+getMeasuredWidth()/7f,getMeasuredWidth(),getMeasuredHeight());
        mRectFCloudShadow.set(getMeasuredWidth()/2-finalSunWidth/2,getMeasuredHeight()/2-finalSunWidth/2,getMeasuredWidth()/2+finalSunWidth/2,getMeasuredHeight()/2+finalSunWidth/2);
        mCloudShadowPath.reset();
        mCloudShadowPath.moveTo(mCircleInfoBottomOne.getX(),getMeasuredHeight()/2+getMeasuredWidth()/7f);//移动到某个位置
        mCloudShadowPath.arcTo(mRectFCloudShadow,15,45,false);//画弧，直接连接到弧的起点，false是有过程
        canvas.drawPath(mCloudShadowPath,mPaint);
        canvas.restore();

        mPaint.setAlpha(255);
    }
    /**
     * 第五组动画，云的阴影动画
     */
    private Boolean isDrawCloudShadow = false;
    public static final String ANIM_CLOUD_SHADOW="anim_cloud_shadow";
    private void startCloudShadow() {
        isDrawCloudShadow=true;
        ValueAnimator valueAnimator=animMap.get(ANIM_CLOUD_SHADOW);
        if (valueAnimator==null){
            valueAnimator=ValueAnimator.ofInt(0,255).setDuration(600);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    cloudShadowAlpha= (int) animation.getAnimatedValue();
                }
            });
            animMap.put(ANIM_CLOUD_SHADOW,valueAnimator);
        }
        startValueAnimator(valueAnimator);
    }

    /**
     * 第六组动画，画太阳的阴影
     * @param canvas
     */
    private int sunShadowColor=Color.parseColor("#bac3c3");
    private float sunShadowWidth, sunShadowHeight;
    private RectF mRectFSunShadow;
    private void drawSunShadow(Canvas canvas) {
        mPaint.setColor(sunShadowColor);
        mPaint.setStyle(Paint.Style.FILL);
        mRectFSunShadow.set(getMeasuredWidth()/2-sunShadowWidth/2,getMeasuredHeight()- sunShadowHeight,
                getMeasuredWidth()/2+sunShadowWidth/2,getMeasuredHeight());
        canvas.drawOval(mRectFSunShadow,mPaint);
    }

    /**
     * 太阳阴影动画
     */
    public static final String ANIM_WEATHER_SHADOW ="anim_weather_shadow";
    private boolean isDrawSunShadow =false;
    private void startSunShadow() {
        isDrawSunShadow=true;
        ValueAnimator valueAnimator=animMap.get(ANIM_WEATHER_SHADOW);
        if (valueAnimator==null){
            valueAnimator=ValueAnimator.ofFloat().setDuration(400);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    sunShadowWidth= (float) animation.getAnimatedValue();
                }
            });
            animMap.put(ANIM_WEATHER_SHADOW,valueAnimator);
        }
        valueAnimator.setFloatValues(0,getMeasuredWidth(),getMeasuredWidth()*0.8f);
        startValueAnimator(valueAnimator);
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimAndRemoveCallbacks();
    }

    /**
     * 停止结束所有动画，以及当前运行的动画
     */
    private void stopAnimAndRemoveCallbacks(){
        isStart=false;
        for (Map.Entry<String, ValueAnimator> entry : animMap.entrySet()) {
            entry.getValue().end();
        }
        Handler handler=this.getHandler();
        if (handler!=null){
            handler.removeCallbacksAndMessages(null);
        }
    }
}
