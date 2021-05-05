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

    private fun initFlow() = (1..10).asFlow()
            .onStart {
                Log.i(TAG, "Start flow")
            }
            .onEach {
                Log.i(TAG, "flow: $it")
                delay(1000)
            }
            .map {
                Log.i(TAG, "map")
                it * it
            }
            .onCompletion {
                Log.i(TAG, "Finish flow")
            }
            .flowOn(Dispatchers.Default)

}