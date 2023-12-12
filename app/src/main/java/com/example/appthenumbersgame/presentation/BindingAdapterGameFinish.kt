package com.example.appthenumbersgame.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.appthenumbersgame.R

@BindingAdapter("requiredAnswer")
fun bidingRequiredAnswer(textView: TextView,count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.required_answer),
        count
    )
}
@BindingAdapter("requiredPercent")
fun bidingRequiredPercent(textView: TextView,count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.required_percent),
        count
    )
}
@BindingAdapter("scorePercentage")
fun bidingScorePercentage(textView: TextView,count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        count
    )
}
@BindingAdapter("scoreAnswers")
fun bidingScoreAnswers(textView: TextView,count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("resultImage")
fun bidingResultImage(imageView: ImageView,winners : Boolean){
    if (winners){
        imageView.setImageResource(R.drawable.image_win)
    }else{
        imageView.setImageResource(R.drawable.image_lose)
    }

}

