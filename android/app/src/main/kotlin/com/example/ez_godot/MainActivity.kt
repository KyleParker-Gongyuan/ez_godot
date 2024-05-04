package com.example.ez_godot

import android.content.Context
import android.content.Intent
import androidx.annotation.NonNull
import godotpluginHome.godotpluginMaster
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel
import org.godotengine.godot.Godot


class MainActivity: FlutterActivity() {
    private val METHOD_CHNNEL_NAME = "com.kaiyo.ezgodot/method";
    private val GENERIC_CHNNEL_NAME = "com.kaiyo.ezgodot/generic";
    private val DATABASE_CHNNEL_NAME = "com.kaiyo.ezgodot/database"
    private val EXIT_CHNNEL_NAME = "com.kaiyo.ezgodot/exit"

    private var methodchannel: MethodChannel? = null
    private val eventName =  "kaiyo.ezgodot/generic";
    //private HelloWorld helloWorld = new MainActivitytwo();
    //val helloWorld = godotpluginMaster();
    val helloWorld = godotpluginMaster(null);

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        //setup channels
        setupchannles(this, flutterEngine.dartExecutor.binaryMessenger);
        
        val events = EventChannel(flutterEngine.dartExecutor.binaryMessenger, eventName).setStreamHandler(helloWorld);
    }

    override fun onDestroy() {
        teardownChannels()
        super.onDestroy()
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
                    startActivity(intent)
                //}
                //else{print("didnt put a file path ")}

            }
            else{
                result.notImplemented()
            }
        }

    }
    private fun teardownChannels(){

    }


    /*fun bytesToFile(bytes: ByteArray, filePath: String) {
        try {
            val file = File(filePath)
            val fos = FileOutputStream(file)
            fos.write(bytes)
            fos.close()
            println("File created at $filePath")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/


}
