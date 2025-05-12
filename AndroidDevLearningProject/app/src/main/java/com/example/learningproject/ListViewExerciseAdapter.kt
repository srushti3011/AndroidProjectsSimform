package com.example.learningproject

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView

class ListViewExerciseAdapter(
    private val context: Activity,
    private val data: List<ListExerciseData>
): ArrayAdapter<ListExerciseData>(
    context,
    R.layout.exercise_list_item,
    data
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val holder: ViewHolder
        val view = LayoutInflater.from(context).inflate(R.layout.exercise_list_item, parent, false)
        if (convertView == null) {
            val img = view.findViewById<AppCompatImageView>(R.id.imgView)
            val heading = view.findViewById<AppCompatTextView>(R.id.tvHeading)
            val subHeading = view.findViewById<AppCompatTextView>(R.id.tvSubHeading)
            holder = ViewHolder(
                img,
                heading,
                subHeading
            )
            view.setTag(holder)
        }
        else {
            holder = view.tag as ViewHolder
        }
        holder.imgView.setImageResource(data[position].img)
        holder.tvHeading.text = data[position].title
        holder.tvSubHeading.text = data[position].subtitle
        return view
    }
    class ViewHolder(
        val imgView: AppCompatImageView,
        val tvHeading: AppCompatTextView,
        val tvSubHeading: AppCompatTextView
    ) {
    }
}