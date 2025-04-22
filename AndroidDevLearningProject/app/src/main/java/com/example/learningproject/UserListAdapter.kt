package com.example.learningproject

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView

class UserListAdapter(
    private val context: Activity,
    private val users: List<User>
    ): ArrayAdapter<User>(
        context,
        R.layout.user_list_item,
        users
    ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.user_list_item, null)
        val nameTextView = view.findViewById<TextView>(R.id.tvPersonName)
        nameTextView.text = users[position].name
        view.findViewById<Button>(R.id.btnShowDetailOfPerson).setOnClickListener {
            Log.i("ButtonClick", "${users[position].name} clicked")
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra("user", users[position])
            context.startActivity(intent)
        }
        return view
    }
}