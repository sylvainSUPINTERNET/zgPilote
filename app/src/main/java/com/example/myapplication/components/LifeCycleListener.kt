package com.example.myapplication.components

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LifeCycleListener: LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun test2() {
        Log.d("START => ", "app start")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun test() {
        Log.d("hello", "this is test on pause")
    }
}