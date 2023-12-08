package com.example.appthenumbersgame.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

interface OptionOnClickListener{
    fun optionOnClick(int: Int)
}

@BindingAdapter("bind_tv_sum")
fun bindSum(textView: TextView, int: Int) {
    textView.text = int.toString()
}
@BindingAdapter("bind_tv_visible")
fun bindVisible(textView: TextView, int: Int) {
    textView.text = int.toString()
}
@BindingAdapter("pb_set_progress")
fun setProgress(progressBar: ProgressBar, int: Int) {
    progressBar.setProgress(int,true)
}
@BindingAdapter("pb_progress_color")
fun bindProgressBar(progressBar: ProgressBar, boolean: Boolean) {
    val color = getColor(progressBar.context,boolean)
    progressBar.progressTintList = ColorStateList.valueOf(color)

}

@BindingAdapter("pb_secondary_progress")
fun secondaryProgress(progressBar: ProgressBar, minPercent: Int) {
   progressBar.secondaryProgress = minPercent
}
@BindingAdapter("progress_answers_text")
fun setTextPA(textView: TextView, progressAnswer: String) {
    textView.text = progressAnswer
}
@BindingAdapter("progress_answers_color")
fun colorPA(textView: TextView, boolean: Boolean) {
    val color = getColor(textView.context,boolean)
    textView.setTextColor(color)

}
@BindingAdapter("set_on_click")
fun setOnClickVariant(textView: TextView, click:OptionOnClickListener) {
   textView.setOnClickListener(){
       click.optionOnClick(textView.text.toString().toInt())
   }
}
private fun getColor(context: Context ,goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)

}