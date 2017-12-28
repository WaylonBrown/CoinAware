package com.waylonbrown.coinaware.util

import android.os.Looper

fun isOnMainThread() = 
        Looper.getMainLooper().thread == Thread.currentThread()