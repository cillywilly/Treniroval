package com.example.treniroval.Activitys

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView


class TrainingActivity : Activity() {

    /** Called when the activity is first created.  */
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val table = TableLayout(this)

        table.isStretchAllColumns = true
        table.isShrinkAllColumns = true

        val rowTitle = TableRow(this)
        rowTitle.gravity = Gravity.CENTER_HORIZONTAL

        val rowDayLabels = TableRow(this)
        val rowHighs = TableRow(this)
        val rowLows = TableRow(this)
        val rowConditions = TableRow(this)
        rowConditions.gravity = Gravity.CENTER

        val empty = TextView(this)

        // title column/row

        // title column/row
        val title = TextView(this)
        title.text = "Java Weather Table"

        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18f)
        title.gravity = Gravity.CENTER
        title.setTypeface(Typeface.SERIF, Typeface.BOLD)

        val params = TableRow.LayoutParams()
        params.span = 6

        rowTitle.addView(title, params)

        // labels column

        // labels column
        val highsLabel = TextView(this)
        highsLabel.text = "Day High"
        highsLabel.setTypeface(Typeface.DEFAULT_BOLD)

        val lowsLabel = TextView(this)
        lowsLabel.text = "Day Low"
        lowsLabel.setTypeface(Typeface.DEFAULT_BOLD)

        val conditionsLabel = TextView(this)
        conditionsLabel.text = "Conditions"
        conditionsLabel.setTypeface(Typeface.DEFAULT_BOLD)

        rowDayLabels.addView(empty)
        rowHighs.addView(highsLabel)
        rowLows.addView(lowsLabel)
        rowConditions.addView(conditionsLabel)

        // day 1 column

        // day 1 column
        val day1Label = TextView(this)
        day1Label.text = "Feb 7"
        day1Label.setTypeface(Typeface.SERIF, Typeface.BOLD)

        val day1High = TextView(this)
        day1High.text = "28°F"
        day1High.gravity = Gravity.CENTER_HORIZONTAL

        val day1Low = TextView(this)
        day1Low.text = "15°F"
        day1Low.gravity = Gravity.CENTER_HORIZONTAL

        val day1Conditions = ImageView(this)

        rowDayLabels.addView(day1Label)
        rowHighs.addView(day1High)
        rowLows.addView(day1Low)
        rowConditions.addView(day1Conditions)

        // day2 column

        // day2 column
        val day2Label = TextView(this)
        day2Label.text = "Feb 8"
        day2Label.setTypeface(Typeface.SERIF, Typeface.BOLD)

        val day2High = TextView(this)
        day2High.text = "26°F"
        day2High.gravity = Gravity.CENTER_HORIZONTAL

        val day2Low = TextView(this)
        day2Low.text = "14°F"
        day2Low.gravity = Gravity.CENTER_HORIZONTAL

        val day2Conditions = ImageView(this)

        rowDayLabels.addView(day2Label)
        rowHighs.addView(day2High)
        rowLows.addView(day2Low)
        rowConditions.addView(day2Conditions)

        // day3 column

        // day3 column
        val day3Label = TextView(this)
        day3Label.text = "Feb 9"
        day3Label.setTypeface(Typeface.SERIF, Typeface.BOLD)

        val day3High = TextView(this)
        day3High.text = "23°F"
        day3High.gravity = Gravity.CENTER_HORIZONTAL

        val day3Low = TextView(this)
        day3Low.text = "3°F"
        day3Low.gravity = Gravity.CENTER_HORIZONTAL

        val day3Conditions = ImageView(this)

        rowDayLabels.addView(day3Label)
        rowHighs.addView(day3High)
        rowLows.addView(day3Low)
        rowConditions.addView(day3Conditions)

        // day4 column

        // day4 column
        val day4Label = TextView(this)
        day4Label.text = "Feb 10"
        day4Label.setTypeface(Typeface.SERIF, Typeface.BOLD)

        val day4High = TextView(this)
        day4High.text = "17°F"
        day4High.gravity = Gravity.CENTER_HORIZONTAL

        val day4Low = TextView(this)
        day4Low.text = "5°F"
        day4Low.gravity = Gravity.CENTER_HORIZONTAL

        val day4Conditions = ImageView(this)

        rowDayLabels.addView(day4Label)
        rowHighs.addView(day4High)
        rowLows.addView(day4Low)
        rowConditions.addView(day4Conditions)


        table.addView(rowTitle)
        table.addView(rowDayLabels)
        table.addView(rowHighs)
        table.addView(rowLows)
        table.addView(rowConditions)

        setContentView(table)
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, PastTrainingsActivity::class.java)
        startActivity(intent)
    }
}


