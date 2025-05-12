package com.example.learningproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityNestedScrollViewPracticeBinding

class NestedScrollViewPracticeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNestedScrollViewPracticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNestedScrollViewPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val heading = "This is the heading"
        binding.tvHeading.text = heading

        val content = "For HTML code that is limited in terms of scope, we can implement the " +
                "static method fromHtml() that belongs to the HTML Utility class for parsing " +
                "HTML-formatted string and displaying it in a TextView. TextView can render " +
                "simple formatting like styles (bold, italic, etc.), font faces (serif, sans " +
                "serif, etc.), colors, links, and so forth. However, when it comes to complex " +
                "formatting and larger scope in terms of HTML, then TextView fails to handle it " +
                "well. For example browsing Facebook won’t be possible through a TextView. " +
                "In such cases, WebView will be the more appropriate widget, as it can handle " +
                "a much wider range of HTML tags. WebView can also handle CSS and JavaScript, " +
                "which Html.fromHtml() would simply ignore. WebView can also assist with common " +
                "browsing metaphors, such as history list of visited URLs to support backwards a" +
                "nd forwards navigation. " + "For HTML code that is limited in terms of scope, " +
                "we can implement the static method fromHtml() that belongs to the HTML " +
                " Utility class for parsing " +
                "HTML-formatted string and displaying it in a TextView. TextView can render " +
                "simple formatting like styles (bold, italic, etc.), font faces (serif, sans " +
                "serif, etc.), colors, links, and so forth. However, when it comes to complex " +
                "For HTML code that is limited in terms of scope, we can implement the " +
                "static method fromHtml() that belongs to the HTML Utility class for parsing " +
                "HTML-formatted string and displaying it in a TextView. TextView can render " +
                "simple formatting like styles (bold, italic, etc.), font faces (serif, sans " +
                "serif, etc.), colors, links, and so forth. However, when it comes to complex "
        binding.tvNestedScroll.text = content

    }
}