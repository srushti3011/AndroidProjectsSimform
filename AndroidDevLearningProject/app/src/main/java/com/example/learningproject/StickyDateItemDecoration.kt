package com.example.learningproject
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.text.TextPaint
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class StickyDateItemDecoration(
    private val getDateLabel: (Int) -> String?, // Function to get the date label for a given position
    private val isDateLabel: (Int) -> Boolean, // Function to check if an item at a position is a date label
    private val labelBackgroundColor: Int,
//    private val labelTextColor: Int,
    private val labelTextSize: Float,
    private val labelPadding: Int
) : ItemDecoration() {

    private val textPaint = TextPaint().apply {
//        color = labelTextColor
        textSize = labelTextSize
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
        typeface = Typeface.DEFAULT_BOLD
    }
    private val backgroundPaint = Paint().apply {
        color = labelBackgroundColor
    }

    private var stickyDateLabel: String? = null
    private var stickyDateLabelTop: Int = 0

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return

        if (isDateLabel(position) && position > 0 && !isDateLabel(position - 1)) {
            // Add padding for the first date label in a sequence
            outRect.top = labelPadding * 2 + textPaint.textSize.toInt()
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val layoutManager = parent.layoutManager as? LinearLayoutManager ?: return
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        if (firstVisibleItemPosition == RecyclerView.NO_POSITION) return

        val firstVisibleView = layoutManager.findViewByPosition(firstVisibleItemPosition) ?: return

        // Find the first visible date label
        var currentStickyLabel = getDateLabel(firstVisibleItemPosition)
        var nextDateLabelPosition = RecyclerView.NO_POSITION

        for (i in firstVisibleItemPosition + 1 until layoutManager.itemCount) {
            if (isDateLabel(i)) {
                nextDateLabelPosition = i
                break
            }
        }

        // If there's a next date label coming into view, adjust the sticky label's position
        if (nextDateLabelPosition != RecyclerView.NO_POSITION) {
            val nextDateLabelView = layoutManager.findViewByPosition(nextDateLabelPosition)
            if (nextDateLabelView != null) {
                val nextLabelTop = nextDateLabelView.top - (labelPadding * 2 + textPaint.textSize)
                if (nextLabelTop < parent.paddingTop) {
                    stickyDateLabelTop = nextLabelTop.toInt()
                    currentStickyLabel = getDateLabel(nextDateLabelPosition)
                } else {
                    stickyDateLabelTop = parent.paddingTop
                    currentStickyLabel = getDateLabel(firstVisibleItemPosition)
                }
            }
        } else {
            stickyDateLabelTop = parent.paddingTop
        }

        // Draw the sticky label
        currentStickyLabel?.let { label ->
            val labelWidth = textPaint.measureText(label)
            val labelHeight = textPaint.textSize

            val parentWidth = parent.width
            val labelStartX = (parentWidth - labelWidth) / 2f
            val labelBackgroundPadding = labelPadding * 1.5f

//            c.drawRect(
//                parent.paddingLeft.toFloat(),
//                stickyDateLabelTop.toFloat(),
//                parent.paddingLeft + labelPadding * 2 + labelWidth,
//                stickyDateLabelTop + labelPadding * 2 + labelHeight,
//                backgroundPaint
//            )
//
//            c.drawText(
//                label,
//                parent.paddingLeft + labelPadding.toFloat(),
//                stickyDateLabelTop + labelPadding + labelHeight,
//                textPaint
//            )

            c.drawRect(
                labelStartX - labelBackgroundPadding,
                stickyDateLabelTop.toFloat(),
                labelStartX + labelWidth + labelBackgroundPadding,
                stickyDateLabelTop + labelPadding * 2 + labelHeight,
                backgroundPaint
            )

            c.drawText(
                label,
                parentWidth / 2f, // Center the text horizontally
                stickyDateLabelTop + labelPadding + labelHeight,
                textPaint
            )
        }
    }
}