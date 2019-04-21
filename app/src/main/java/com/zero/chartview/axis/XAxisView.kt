package com.zero.chartview.axis

import android.content.Context
import android.graphics.Paint
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.View
import com.zero.chartview.R
import com.zero.chartview.model.FloatRange

class XAxisView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val legendPaint = Paint()

    private var paddingLeft = resources.getDimension(R.dimen.abscissa_legend_padding_left_default)
    private var paddingRight = resources.getDimension(R.dimen.abscissa_legend_padding_right_default)
    private var labelMargin = resources.getDimension(R.dimen.abscissa_legend_label_margin_default)
    private var textMarginTop = resources.getDimension(R.dimen.abscissa_legend_margin_top_default)
    private var textWidth = resources.getDimension(R.dimen.abscissa_legend_label_width_default)

    private lateinit var correspondingLegends: Map<Float, String>
    private lateinit var coordinates: List<Float>
    private var range: FloatRange = FloatRange(0F, 0F)

    init {
        var textSize = resources.getDimension(R.dimen.legend_text_size_default)
        context.theme.obtainStyledAttributes(attrs, R.styleable.XAxisView, defStyleAttr, defStyleRes).apply {
            textSize = getDimension(R.styleable.XAxisView_legendTextSize, textSize)
            paddingLeft = getDimension(R.styleable.XAxisView_abscissaLegendPaddingLeft, paddingLeft)
            paddingRight = getDimension(R.styleable.XAxisView_abscissaLegendPaddingRight, paddingRight)
            labelMargin = getDimension(R.styleable.XAxisView_abscissaLegendLabelMargin, labelMargin)
            textMarginTop = getDimension(R.styleable.XAxisView_abscissaLegendMarginTop, textMarginTop)
            recycle()
        }
        legendPaint.textSize = textSize
    }

    fun setCoordinates(coordinates: List<Float>) {
        this.coordinates = coordinates
    }

    fun setCorrespondingLegends(correspondingLegends: Map<Float, String>) {
        this.correspondingLegends = correspondingLegends
    }

    fun setRange(start: Float, endInclusive: Float) {
        range.start = start
        range.endInclusive = endInclusive
        invalidate()
    }

    fun onThemeChanged(@ColorInt colorLegend: Int) {
        legendPaint.color = colorLegend
    }
}