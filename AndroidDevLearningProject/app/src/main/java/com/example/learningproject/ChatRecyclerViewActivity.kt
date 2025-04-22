package com.example.learningproject

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningproject.databinding.ActivityChatRecyclerViewBinding

class ChatRecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityChatRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(ContextCompat.getColor(this, R.color.whatsapp_green))
        )
        configureMessageRecyclerView()
    }
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            currentFocus?.let { view ->
                val outRect = android.graphics.Rect()
                view.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    view.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.whatsapp_menu, menu)
        return true
    }

    fun configureMessageRecyclerView() {
        binding.rvChat.adapter = ChatRecyclerViewAdapter(messages)
        binding.rvChat.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
        }
//        binding.rvChat.layoutManager = LinearLayoutManager(this)
        binding.rvChat.scrollToPosition(messages.lastIndex)

        val decoration = StickyDateItemDecoration(
            getDateLabel = { position ->
                messages.getOrNull(position)?.date
            },
            isDateLabel = { position ->
                messages.getOrNull(position) != null && messages[position].messages.isNotEmpty()
            },
            labelBackgroundColor = ContextCompat.getColor(this, R.color.whatsapp_blue),
            labelTextSize = (35.0).toFloat(),
            labelPadding = 20
        )
        binding.rvChat.addItemDecoration(decoration)
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}