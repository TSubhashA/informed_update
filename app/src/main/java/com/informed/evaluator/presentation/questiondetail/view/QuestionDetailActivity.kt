package com.informed.evaluator.presentation.questiondetail.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.base.BaseActivity
import com.informed.evaluator.presentation.fullquestion.view.FullQuestionActivity
import kotlinx.android.synthetic.main.activity_question_detail.*
import kotlinx.android.synthetic.main.activity_question_detail.hint
import java.util.ArrayList

class QuestionDetailActivity : BaseActivity() {

    lateinit var chart:LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_detail)

        setTopBar()

        chartUpdate()

        hint.setOnClickListener {
            if (hint.text=="Hide Question")
            {
                with(hint) { setText("Show Question") }
                question_details.visibility=GONE
            }else{
                hint.setText("Hide Question")
                question_details.visibility= VISIBLE
            }

        }
        question_details.setOnClickListener { startActivity(Intent(this,FullQuestionActivity::class.java)) }

    }

    private fun setTopBar() {
        val toolbar=findViewById(R.id.my_tool_bar) as MaterialToolbar
        toolbar.setTitle("")
        val text=toolbar.findViewById(R.id.action_bar_title) as TextView
        text.text="Cateract Surgery"
        text.setTextColor(resources.getColor(R.color.black))
        setSupportActionBar(toolbar)

        val actionBar=supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_black)
    }


    private fun chartUpdate() {

        chart = findViewById(R.id.chart1) as LineChart
        chart.setViewPortOffsets(0f, 0f, 0f, 20f)
//        chart.setBackgroundColor(Color.rgb(104, 241, 175))

        // no description text

        // no description text
        chart.getDescription().setEnabled(false)

        // enable touch gestures

        // enable touch gestures
        chart.setTouchEnabled(false)

        // enable scaling and dragging

        // enable scaling and dragging
        chart.setDragEnabled(false)
        chart.setScaleEnabled(true)

        setData(6,10f)
        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false)

        chart.setDrawGridBackground(false)
        chart.setMaxHighlightDistance(120f)

        val x: XAxis = chart.getXAxis()

        val xAxisLabel = ArrayList<String>()
        xAxisLabel.add("Jan")
        xAxisLabel.add("Feb")
        xAxisLabel.add("Mar")
        xAxisLabel.add("Apr")
        xAxisLabel.add("May")
        xAxisLabel.add("Jun")



        x.setLabelCount(6, true)
        x.textColor = resources.getColor(R.color.grey)
        x.setPosition(XAxis.XAxisPosition.BOTTOM)
        x.setDrawGridLines(false)
        x.valueFormatter= IndexAxisValueFormatter(xAxisLabel)


        val y: YAxis = chart.getAxisLeft()

        y.setLabelCount(6, false)
        y.textColor = resources.getColor(R.color.grey)
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        y.setDrawGridLines(true)
        y.axisLineColor = resources.getColor(R.color.grey0)

        chart.getAxisRight().setEnabled(false)

        chart.getLegend().setEnabled(false)

        chart.animateXY(1000, 1000)
        chart.setViewPortOffsets(60F, 0F, 50F, 60F);

        // don't forget to refresh the drawing
        chart.invalidate()

    }


    private fun setData(count: Int, range: Float) {
        val values = ArrayList<Entry>()
        for (i in 0 until count) {
            val `val` = (Math.random() * (range + 1)).toFloat() + 20
            Log.w("Data Entry : "," $i - $`val`")
            values.add(Entry(i.toFloat(),`val`))
        }


        val set1: LineDataSet
        if (

            chart.getData() != null &&
            chart.getData().getDataSetCount() > 0
        )

        {
            set1 = chart.getData().getDataSetByIndex(0) as LineDataSet
            set1.values = values
            chart.getData().notifyDataChanged()
            chart.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, "DataSet 1")

            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
            set1.cubicIntensity = 0.2f
            set1.setDrawFilled(true)
            set1.setDrawCircles(false)
            set1.lineWidth = 1.8f
            set1.circleRadius = 4f
            set1.setCircleColor(Color.WHITE)
            set1.highLightColor = Color.rgb(244, 117, 117)
            set1.color = resources.getColor(R.color.purple)
//            set1.fillColor = resources.getColor(R.color.purple)
            set1.fillDrawable = ContextCompat.getDrawable(this, R.drawable.chart_gradient)

            set1.setDrawValues(false)
            set1.setDrawHorizontalHighlightIndicator(false)
            set1.fillFormatter =
                IFillFormatter { dataSet, dataProvider -> chart.getAxisLeft().getAxisMinimum() }

            // create a data object with the data sets
            val data = LineData(set1)

            data.setValueTextSize(9f)
            data.setDrawValues(false)

            // set data
            chart.setData(data)
        }
    }
}