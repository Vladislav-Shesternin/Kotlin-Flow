package com.veldan.kotlin_flow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class MainActivity : AppCompatActivity() {

    private val TAG = this::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        CoroutineScope(Dispatchers.Main).launch {
            initFlow().collect {
                Log.i(TAG, "collect: $it")
            }
        }
    }

    private fun initFlow() = flow {
        Log.i(TAG, "Start flow")
        (1..10).forEach {
            Log.i(TAG, "flow: $it")
            emit(it)
            delay(300)
        }
        Log.i(TAG, "Finish flow")
    }.flowOn(Dispatchers.Default)
}