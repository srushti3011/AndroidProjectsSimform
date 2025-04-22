package com.example.learningproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityLayoutsHomeBinding

class LayoutsHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLayoutsHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLayoutsHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnGoToConstraint.setOnClickListener {
            val intent = Intent(this, ConstraintLayoutActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToLinear.setOnClickListener {
            val intent = Intent(this, LinearLayoutActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToAbsolute.setOnClickListener {
            val intent = Intent(this, AbsoluteLayoutActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToRelative.setOnClickListener {
            val intent = Intent(this, RelativeLayoutActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToAppBar.setOnClickListener {
            val intent = Intent(this, AppBarLayoutActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToFrame.setOnClickListener {
            val intent = Intent(this, FrameLayoutPractice::class.java)
            startActivity(intent)
        }

        binding.btnGoToCircularConstraints.setOnClickListener {
            val intent = Intent(this, CircularConstraintActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToConstraintChainGroup.setOnClickListener {
            val intent = Intent(this, ConstraintChainAndGroupActivity::class.java)
            startActivity(intent)
        }

        binding.btnAppBarCustomization.setOnClickListener {
            val intent = Intent(this, AppBarCustomizationActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToNavigationComponent.setOnClickListener {
            val intent = Intent(this, NavigationComponentActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToTabBar.setOnClickListener {
            val intent = Intent(this, TabbarLayoutActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToStateListDrawable.setOnClickListener {
            val intent = Intent(this, StateListDrawableActivity::class.java)
            startActivity(intent)
        }
    }
}