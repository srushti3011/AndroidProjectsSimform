package com.example.learningproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.learningproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnGoToWidgets.setOnClickListener {
            val intent = Intent(this, WidgetsHomeActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToLayouts.setOnClickListener {
            val intent = Intent(this, LayoutsHomeActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToLayoutExercise.setOnClickListener {
            val intent = Intent(this, LayoutExerciseHome::class.java)
            startActivity(intent)
        }

        binding.btnGoToDataBindingPractice.setOnClickListener {
            val intent = Intent(this, DataBindingPracticeActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToWidgetsExercise.setOnClickListener {
            val intent = Intent(this, WidgetsExerciseActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToListViewPractice.setOnClickListener {
            val intent = Intent(this, ListViewPracticeActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToRecyclerViewPractice.setOnClickListener {
            val intent = Intent(this, RecyclerViewPracticeActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToChatRecyclerView.setOnClickListener {
            val intent = Intent(this, ChatRecyclerViewActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToExpandableListView.setOnClickListener {
            val intent = Intent(this, ExpandableListViewActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToExpandableRecyclerView.setOnClickListener {
            val intent = Intent(this, ExpandableRecyclerViewActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToRecycledViewPool.setOnClickListener {
            val intent = Intent(this, RecycledViewPoolActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToDiffUtil.setOnClickListener {
            val intent = Intent(this, DiffUtilsPracticeActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToViewPager.setOnClickListener {
            val intent = Intent(this, ViewPagerHomeActivity::class.java)
            startActivity(intent)
        }

        binding.btnBottomNavigation.setOnClickListener {
            val intent = Intent(this, BottomNavigationActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToListRecyclerExercise.setOnClickListener {
            val intent = Intent(this, ListRecyclerExerciseHomeActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToLifecycle.setOnClickListener {
            val intent = Intent(this, LifecycleObservationActivity::class.java)
            intent.putExtra("value", "valueSent")
            startActivity(intent)
        }

        binding.btnGoToStartActivityForResults.setOnClickListener {
            val intent = Intent(this, IntentForResultActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToImplicitIntents.setOnClickListener {
            val intent = Intent(this, ImplicitIntentsHomeActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToFragTransactionHome.setOnClickListener {
            val intent = Intent(this, FragmentTransactionsHomeActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToFlexibleUI.setOnClickListener {
            val intent = Intent(this, FlexibleUIActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToInterFragmentCommunication.setOnClickListener {
            val intent = Intent(this, InterFragmentCommunicationActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToFragCommUseVM.setOnClickListener {
            val intent = Intent(this, InterFragCommUsingVMActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToFragStartActivity.setOnClickListener {
            val intent = Intent(this, StartActivityFromFragmentActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToServices.setOnClickListener {
            val intent = Intent(this, ServicesHomeActivity::class.java)
            startActivity(intent)
        }

        binding.btnSendBoradcastToOtherApps.setOnClickListener {
            val intent = Intent("com.example.learningproject.ACTION_SEND")
            intent.putExtra("com.example.learningproject.DATA_SENT", "Broadcast data sent")
            sendBroadcast(intent)
        }

        binding.btnLocalBroadcast.setOnClickListener {
            val intent = Intent("com.example.learningproject.LOCAL_BROADCAST")
            intent.putExtra("DATA_SENT", "local broadcast data")
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }

        binding.btnGoToLocalBroadcastActivity.setOnClickListener {
            val intentNav = Intent(this, LocalBroadcastActivity::class.java)
            startActivity(intentNav)
        }

        binding.btnGoToActivityIntentFragmentExercise.setOnClickListener {
            val intent = Intent(this, CoffeeLandingActivity::class.java)
            startActivity(intent)
        }

        binding.apply {
            btnGoToNestedAndHorizontalScrollPractice.setOnClickListener {
                val intent = Intent(
                    this@MainActivity,
                    NestedScrollViewPracticeActivity::class.java
                )
                startActivity(intent)
            }

            btnGoToWebViewPractice.setOnClickListener {
                val intent = Intent(
                    this@MainActivity,
                    WebViewActivity::class.java
                )
                startActivity(intent)
            }

            btnGoToSearchViewPractice.setOnClickListener {
                val intent = Intent(
                    this@MainActivity,
                    SearchViewActivity::class.java
                )
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("observe", "main destroyed")
    }
}