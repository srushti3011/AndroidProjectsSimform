package com.example.learningproject

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class ObserverClass: LifecycleEventObserver {

    val tag = "observe"

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_CREATE -> Log.i(tag, "$source's onCreate")
            else -> {Log.i(tag, "$source's other")}
        }
    }
}