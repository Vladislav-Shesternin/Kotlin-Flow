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

    private fun initFlow() = flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .onEach {
                Log.i(TAG, "flow: $it")
                delay(1000)
            }
            .filter { it > 5 }
            .flowOn(Dispatchers.Default)

}