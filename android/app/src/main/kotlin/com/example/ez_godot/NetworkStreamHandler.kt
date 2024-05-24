package com.example.ez_godot

import android.app.Activity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import io.flutter.plugin.common.EventChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NetworkStreamHandler(private var activity: Activity?, private val lifecycle: Lifecycle?) : EventChannel.StreamHandler {

    fun receiveStringFromFlutter(value: String) {
        // Do something with the received string 'value'
        faf = value
        //Log.d("NetworkStreamHandler", "Received string from Flutter: $value")
    }
    var faf = ""
    //val thegdFile = godotpluginMaster(null)

    private var eventSink: EventChannel.EventSink? = null
    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        eventSink = events

        lifecycle?.coroutineScope?.launch {
            startListeningNetworkChanges()
        }

    }
    override fun onCancel(arguments: Any?) {
        eventSink = null
        activity = null
    }
    private suspend fun startListeningNetworkChanges(){
        withContext(Dispatchers.Main){
            //faf = thegdFile.daf


            //activity?.runOnUiThread{ eventSink?.success(faf)}
            //val cars = arrayOf("Vol", "BMW")
            while(true){
                delay(1000)
                println("this is the listening network")
                println("newthing ==: "+ faf)
                activity?.runOnUiThread{ eventSink?.success("idk lover")}
                faf += "ab"
            }

        }
    }
    private fun stopListeningNetworkChanges(){

    }
}