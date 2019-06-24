package com.airy.juju.widget

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.airy.juju.R
import com.airy.juju.util.DensityUtil


/**
 * Created by Airy on 2019/3/13
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

open class JiKeLikeView : View {

    // Text
    private lateinit var textRounds: Rect  // Text display area
    private lateinit var widths: FloatArray
    private var textAlpha: Float = 1f
    private var textMaxMove = 0f
    private var textMoveDistance = 0f

    // Paint
    private lateinit var bitmapPaint: Paint
    private lateinit var newTextPaint: Paint
    private lateinit var oldTextPaint: Paint
    private lateinit var circlePaint: Paint

    // Bitmap
    private lateinit var unLikeBitmap: Bitmap
    private lateinit var likeBitmap: Bitmap
    private lateinit var shiningBitmap: Bitmap

    // View State
    private var likeNumber : Int = 0
    private var isLike = false
    private var handScale = 1.0f
    private var isFirst = false
    private var shingCircleScale = 1f
    private var shingCircleAlpha = 1f
    private var shiningAlpha = 1f
    private var shiningScale = 1f


    // Animator
    private val duration = 250L


    constructor(context: Context, attrs: AttributeSet?)
            : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)


    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs) {
        val typedArray : TypedArray = context.obtainStyledAttributes(attrs, R.styleable.JiKeLikeView)

        likeNumber = typedArray.getInt(R.styleable.JiKeLikeView_like_num, 9999)
        typedArray.recycle() // do recycle work
        init()
    }

    private fun init() {
        textRounds = Rect() // text display area
        widths = FloatArray(8)
        // draw the thumb
        bitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        // hit like the num
        newTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        newTextPaint.color = Color.GRAY
        newTextPaint.textSize = DensityUtil.sp2px(context, 14f).toFloat()
        // before hit like the num
        oldTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        oldTextPaint.color = Color.GRAY
        oldTextPaint.textSize = DensityUtil.sp2px(context, 14f).toFloat()
        // draw the outline
        circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        circlePaint.color = Color.RED
        circlePaint.style = Paint.Style.STROKE
        // outline width
        circlePaint.strokeWidth = DensityUtil.dp2px(context, 2f).toFloat()
        circlePaint.setShadowLayer(DensityUtil.dp2px(context, 1f).toFloat(),
            DensityUtil.dp2px(context, 1f).toFloat(),
            DensityUtil.dp2px(context, 1f).toFloat(),
            Color.RED)
    }

    // call in resume, like init fun
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val resources = resources
        unLikeBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_message_unlike)
        likeBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_message_like)
        shiningBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_message_like_shining)
    }

    // do recycle things
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        unLikeBitmap.recycle()
        likeBitmap.recycle()
        shiningBitmap.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // make sure the view has default size working in warp_content
        // leave 10dp for margin up and down
        val viewHeightSpec = MeasureSpec.makeMeasureSpec(unLikeBitmap.height + DensityUtil.dp2px(context, 20f), MeasureSpec.EXACTLY)
        val stringNum = likeNumber.toString()
        val textWidth = oldTextPaint.measureText(stringNum, 0, stringNum.length)
        // view width is the bitmap_width + text_width + 30px
        val viewWidthSpec = MeasureSpec.makeMeasureSpec((unLikeBitmap.width + textWidth + DensityUtil.dp2px(context, 30f)).toInt(), MeasureSpec.EXACTLY)
        super.onMeasure(viewWidthSpec, viewHeightSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val viewHeight = height
        // get center
        val centerY = viewHeight / 2
        val handBitmap = if (isLike) { likeBitmap } else { unLikeBitmap }
        val handBitmapHeight = handBitmap.height
        val handBitmapWidth = handBitmap.width

        val handTop = (viewHeight - handBitmapHeight) / 2
        canvas!!.save()

        canvas.scale(handScale, handScale, (handBitmapWidth / 2).toFloat(), centerY.toFloat())
        canvas.drawBitmap(handBitmap, DensityUtil.dp2px(context, 10f).toFloat(), handTop.toFloat(), bitmapPaint)
        // save the canvas status before scale
        canvas.restore()


        // draw the shining
        val shiningTop = handTop - shiningBitmap.height + DensityUtil.dp2px(context, 17f)
        bitmapPaint.alpha = (255 * shiningAlpha).toInt()
        canvas.save()
        canvas.scale(shiningScale, shiningScale, (handBitmapWidth/2).toFloat(), handTop.toFloat())

        canvas.restore()
        bitmapPaint.alpha = 255
        if (isLike) {
            canvas.drawBitmap(shiningBitmap, DensityUtil.dp2px(context, 15f).toFloat(), shiningTop.toFloat(), bitmapPaint)
        } else {
            canvas.save()

            bitmapPaint.alpha = 0
            canvas.drawBitmap(shiningBitmap, DensityUtil.dp2px(context, 15f).toFloat(), shiningTop.toFloat(), bitmapPaint)
            canvas.restore()
            bitmapPaint.alpha = 255
        }

        // draw text when 99 turn to 100 or 999 to 1000
        val textValue = likeNumber.toString()
        lateinit var textCancelValue: String
        if (isLike) {
            textCancelValue = (likeNumber - 1).toString()
        } else {
            if (isFirst) {
                textCancelValue = (likeNumber + 1).toString()
            } else {
                isFirst = !isFirst
                textCancelValue = likeNumber.toString()
            }
        }

        val textLength = textValue.length
        newTextPaint.getTextBounds(textValue, 0, textValue.length, textRounds)

        // 10dp to hand
        var textX: Float = (handBitmapWidth + DensityUtil.dp2px(context, 20f)).toFloat()
        val textY: Float = (viewHeight/2 - (textRounds.top + textRounds.bottom)/2).toFloat()
        if (textLength != textCancelValue.length || textMaxMove == 0f) {
            if (isLike) {
                circlePaint.alpha = (255 * shingCircleAlpha).toInt()
                canvas.drawCircle((handBitmapWidth/2 + DensityUtil.dp2px(context, 10f)).toFloat(), handBitmapHeight/2 + DensityUtil.dp2px(context, 10f).toFloat(), ((handBitmapHeight/2 + DensityUtil.dp2px(context, 2f).toFloat() * shingCircleScale)), circlePaint)
                // old Text
                oldTextPaint.alpha = (255 * (1 - textAlpha)).toInt()
                canvas.drawText(textCancelValue, textX, textY - textMaxMove + textMoveDistance, oldTextPaint)
                // new text
                newTextPaint.alpha = (255 * textAlpha).toInt()
                canvas.drawText(textValue, textX, textY + textMoveDistance, newTextPaint)
            } else {
                oldTextPaint.alpha = (255 * (1 - textAlpha)).toInt()
                canvas.drawText(textCancelValue, textX, textY + textMaxMove + textMoveDistance, oldTextPaint)
                newTextPaint.alpha = (255 * textAlpha).toInt()
                canvas.drawText(textValue, textX, textY + textMoveDistance, newTextPaint)
            }
            return
        }


        // opposite the situation in normal number change
        newTextPaint.getTextWidths(textValue, widths)

        val chars: CharArray = textValue.toCharArray()
        val oldChars: CharArray = textCancelValue.toCharArray()

        for (i in chars.indices) {
            if (chars[i] == oldChars[i]) {
                // if the same not change effect
                canvas.drawText(chars[i].toString(), textX, textY, newTextPaint)
            } else {

                if (isLike) {
                    circlePaint.alpha = (255 * shingCircleAlpha).toInt()
                    canvas.drawCircle((handBitmapWidth/2).toFloat() + DensityUtil.dp2px(context, 10f).toFloat(),
                        (handBitmapHeight/2 + DensityUtil.dp2px(context, 10f)).toFloat(),
                        ((handBitmapHeight/2 + DensityUtil.dp2px(context, 2f) * shingCircleScale)),
                        circlePaint)

                    oldTextPaint.alpha = (255 * (1 - textAlpha)).toInt()
                    canvas.drawText(oldChars[i].toString(), textX, textY - textMaxMove + textMoveDistance, oldTextPaint)
                    newTextPaint.alpha = (255 * textAlpha).toInt()
                    canvas.drawText(chars[i].toString(), textX, textY + textMoveDistance, newTextPaint)
                } else {
                    oldTextPaint.alpha = (255 * (1 - textAlpha)).toInt()
                    canvas.drawText(oldChars[i].toString(), textX, textY + textMaxMove + textMoveDistance, oldTextPaint)
                    newTextPaint.alpha = (255 * textAlpha).toInt()
                    canvas.drawText(chars[i].toString(), textX, textY + textMoveDistance, newTextPaint)
                }
            }
            // next bit number need the last width
            textX += widths[i]
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                performance()
            }
        }
        return super.onTouchEvent(event)
    }

    /***
     * animation display
     */
    private fun performance() {
        isLike = !isLike
        if (isLike) {
            ++likeNumber
            setLikeNumAnimation()
            val handScaleAnimator = ObjectAnimator.ofFloat(this,"handScale", 1f, 0.8f, 1f)
            handScaleAnimator.duration = duration
            //动画 点亮手指的四点 从0 - 1出现
            val shinAlphaAnimator = ObjectAnimator.ofFloat(this, "shingAlpha", 0f, 1f)

            //zoom in figure
            val shinScaleAnimator = ObjectAnimator.ofFloat(this, "shingScale", 0f, 1f)

            //画中心圆形有内到外扩散
            val shinChicleAnimator = ObjectAnimator.ofFloat(this, "shingCircleScale", 0.6f, 1f)
            //画出圆形有1到0消失
            val shinCircleAlphaAnimator = ObjectAnimator.ofFloat(this, "shingCircleAlpha", 0.3f, 0f)


            //动画集一起播放
            val animatorSet = AnimatorSet()
            animatorSet.playTogether(handScaleAnimator, shinAlphaAnimator, shinScaleAnimator, shinChicleAnimator, shinCircleAlphaAnimator)
            animatorSet.start()

        } else {
            // cancel like
            --likeNumber
            setLikeNumAnimation()
            val handScaleAnimator = ObjectAnimator.ofFloat(this, "handScale", 1f, 0.8f, 1f)
            handScaleAnimator.duration = duration
            handScaleAnimator.start()

            // hide the figure
            setShingAlpha(0f)
        }
    }

    fun setHandScale(handScale: Float) {
        this.handScale = handScale
        invalidate()
        // request redraw
    }

    fun setShingAlpha(shiningAlpha: Float) {
        this.shiningAlpha = shiningAlpha
        invalidate()
    }

    fun setShingScale(shiningScale: Float) {
        this.shiningScale = shiningScale
        invalidate()
    }

    /***
     *  num animation effect
     */
    private fun setLikeNumAnimation() {

        textMaxMove = DensityUtil.dp2px(context, 20f).toFloat()

        val startY: Float = if (isLike) {   // decide where the num move
            textMaxMove
        } else {
            -textMaxMove
        }

        val textInAlphaAnimation = ObjectAnimator.ofFloat(this, "textAlpha", 0f, 1f)
        textInAlphaAnimation.duration = duration
        val textMoveAnimator = ObjectAnimator.ofFloat(this, "textMoveDistance", startY, 0f)
        textMoveAnimator.duration = duration

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(textInAlphaAnimation, textMoveAnimator)
        animatorSet.start()
    }

    /**
     * 设置数值透明度
     */

    fun setTextAlpha(textAlpha: Float) {
        this.textAlpha = textAlpha
        invalidate()
    }

    /**
     * 设置数值移动
     */

    fun setTextMoveDistance(textTranslate: Float) {
        this.textMoveDistance = textTranslate
        invalidate()
    }

    /**
     * 画出圆形波纹
     */
    fun setShingCircleScale(shingCircleScale: Float) {
        this.shingCircleScale = shingCircleScale
        invalidate()
    }

    /**
     * 圆形透明度设置
     */
    fun setShingCircleAlpha(shingCircleAlpha: Float) {
        this.shingCircleAlpha = shingCircleAlpha
        invalidate()

    }


    fun getLikeNumber(): Int{
        return this.likeNumber
    }


     fun setLikeNumber(v: Int) {
        this.likeNumber = v
         invalidate()
    }

    fun isLike(): Boolean {
        return this.isLike
    }

    fun likeIt(){
        this.isLike = true
    }
}