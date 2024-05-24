package com.example.ez_godot

import android.content.Context
import android.content.Intent
import godotpluginHome.GodotpluginMaster


import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel


class MainActivity: FlutterActivity(){

    private val networkEventChannel = "kaiyo.ezgodot/generic"
    private var methodChannel: MethodChannel? = null
    private var eventSink: EventChannel.EventSink? = null

    //private var eventHandler: MyEventHandler? = null
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, METHOD_CHANNEL_NAME)
        methodChannel?.setMethodCallHandler { call, result ->
            handleMethodCall(call, result)
        }

        EventChannel(flutterEngine.dartExecutor.binaryMessenger, networkEventChannel).setStreamHandler(GodotpluginMaster(null))

        //EventChannel(flutterEngine.dartExecutor.binaryMessenger, networkEventChannel).setStreamHandler(NetworkStreamHandler(this, null))

        //EventChannel(flutterEngine.dartExecutor.binaryMessenger, networkEventChannel).setStreamHandler(TimeHandler)





    }





    private var methodchannel: MethodChannel? = null
    private val eventName =  "kaiyo.ezgodot/generic";

    private val METHOD_CHANNEL_NAME = "com.kaiyo.ezgodot/method/start"





    // Initialize your plugins or handlers
    //private val godotPluginMaster = godotpluginMaster(null)
    private val mainActivityTwo = MainActivitytwo()


    private val METHOD_CHNNEL_NAME = "com.kaiyo.ezgodot/method/start";


    private fun handleMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "openGame" -> {
                val intent = Intent(this, MainActivitytwo::class.java)
                startActivity(intent)
                result.success("Game opened")
            }
            else -> {
                result.notImplemented()
            }
        }
    }


    private fun setupchannles(context: Context, messenger:BinaryMessenger){
        methodchannel = MethodChannel(messenger, METHOD_CHNNEL_NAME)

        methodchannel!!.setMethodCallHandler{
            call,result ->
            if (call.method == "openGame"){
                //val fileBytes = call.argument<ByteArray>("godotFile")
                //if (fileBytes !=null){
                  //  bytesToFile(fileBytes, "assets/mod.pck") //we need to make sure that this is finished then we can do X or Y
                    val intent = Intent(this,MainActivitytwo::class.java)
                    //mainActivityTwo.setDataSendListener(this)
                    startActivity(intent)
                //}
                //else{print("didnt put a file path ")}

            }

            else{
                result.notImplemented()
            }


        }

    }

}
