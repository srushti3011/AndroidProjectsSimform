package com.example.learningproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.learningproject.databinding.ActivityRecyclerViewPracticeBinding

class RecyclerViewPracticeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewPracticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecyclerViewPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val users = listOf(
            User(
                name = "One",
                age = 35,
                city = "Mumbai",
                occupation = "Businessman",
                img = R.drawable.location_icon
            ),
            User(
                name = "Two",
                age = 25,
                city = "Ahmedabad",
                occupation = "Doctor",
                img = R.drawable.carrot
            ),
            User(
                name = "Three",
                age = 40,
                city = "Bangalore",
                occupation = "Architect",
                img = R.drawable.carrot
            ),
            User(
                name = "Four",
                age = 44,
                city = "Mumbai",
                occupation = "Software Developer",
                img = R.drawable.location_icon
            ),
            User(
                name = "Five",
                age = 48,
                city = "Surat",
                occupation = "Software Developer",
                img = R.drawable.location_icon
            ),
            User(
                name = "Six",
                age = 44,
                city = "Rajasthan",
                occupation = "Developer",
                img = R.drawable.location_icon
            ),
            User(
                name = "Seven",
                age = 44,
                city = "Chandigarh",
                occupation = "Software Developer",
                img = R.drawable.location_icon
            ),
            User(
                name = "Eight",
                age = 44,
                city = "Pune",
                occupation = "Software Developer",
                img = R.drawable.location_icon
            ),
            User(
                name = "Nine",
                age = 44,
                city = "Mumbai",
                occupation = "Software Engineer",
                img = R.drawable.location_icon
            ),
        )
        binding.recyclerViewUser.layoutManager = GridLayoutManager(this, 2)
        (binding.recyclerViewUser.layoutManager as GridLayoutManager).isItemPrefetchEnabled = true
        (binding.recyclerViewUser.layoutManager as GridLayoutManager).initialPrefetchItemCount = 2
        binding.recyclerViewUser.adapter = UserRecyclerViewAdapter(users) { user ->
            Log.i("RecyclerViewSelection", "${user.toString()} selected")
            val intent = Intent(this, UserDetailActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
//        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
//        ResourcesCompat.getDrawable(resources, R.drawable.custom_divider, null)?.let {
//            dividerItemDecoration.setDrawable(it)
//        }
//        binding.recyclerViewUser.addItemDecoration(dividerItemDecoration)
//        val decoration = DividerItemDecorator(ResourcesCompat.getDrawable
//            (
//            resources,
//            R.drawable.custom_divider,
//            null)
//        )
//        binding.recyclerViewUser.addItemDecoration(decoration)
//
//        val itemMargin = CustomMarginDecoration()
//        binding.recyclerViewUser.addItemDecoration(itemMargin)
    }
}

//class DividerItemDecorator(private val divider: Drawable?) : RecyclerView.ItemDecoration() {
//    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//        val dividerLeft = parent.paddingLeft
//        val dividerRight = parent.width - parent.paddingRight
//        val childCount = parent.childCount
//        for (i in 0..childCount - 2) {
//            val child: View = parent.getChildAt(i)
//            val params = child.layoutParams as RecyclerView.LayoutParams
//            val dividerTop: Int = child.bottom + params.bottomMargin
//            val dividerBottom = dividerTop + (divider?.intrinsicHeight?:0)
//            divider?.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
//            divider?.draw(canvas)
//        }
//    }
//}