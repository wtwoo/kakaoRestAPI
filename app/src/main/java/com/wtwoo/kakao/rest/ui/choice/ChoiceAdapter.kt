package com.wtwoo.kakao.rest.ui.choice

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wtwoo.kakao.rest.R
import com.wtwoo.kakao.rest.model.Choice
import io.reactivex.annotations.NonNull


class ChoiceAdapter(private val activity: Activity, private val choices: List<Choice>) :
    RecyclerView.Adapter<ChoiceAdapter.ViewHolder>() {

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_choice, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        val choice = choices[position]
        holder.bind(choice)
    }

    override fun getItemCount(): Int {
        return choices.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleText: TextView
        private val descText: TextView
        private val launchButton: Button

        init {
            titleText = itemView.findViewById(R.id.item_title)
            descText = itemView.findViewById(R.id.item_description)
            launchButton = itemView.findViewById(R.id.item_launch_button)
        }

        fun bind(choice: Choice) {
            titleText.text = choice.title
            descText.text = choice.description
            launchButton.setOnClickListener { activity.startActivity(choice.launchIntent) }
        }
    }

}
