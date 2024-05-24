package godotpluginHome

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.os.Handler
import android.os.Looper


import android.util.Log

import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin
import org.godotengine.godot.plugin.SignalInfo
import org.godotengine.godot.plugin.UsedByGodot



class GodotpluginMaster(godot: Godot?, ) :  GodotPlugin(godot), EventChannel.StreamHandler{

    private var faf: String = ""

    private lateinit var eventSink: EventChannel.EventSink


    override fun onListen(arguments: Any?, events: EventChannel.EventSink) {
        eventSink = events
        events.success("he we started")

    }


    @UsedByGodot
    public fun sendData(mydata: String) { // send to flutter
        faf = mydata
        println("OLD lord of Data: " + faf)
        eventSink.success(faf)
    }


    companion object {
        val SHOW_STRANG = SignalInfo("get_stang", String::class.java)
    }

    override fun getPluginName(): String {
        return "godotpluginMaster"
    }

    override fun getPluginSignals() = setOf(SHOW_STRANG)

    /*internal fun send2Godot(theString: String) {
        print("pre send to godot")
        emitSignal(
            SHOW_STRANG.name,
            theString
        ) // this send out a signal to get the string for x y Z
        //eventSink!!.success("we cook")
        eventSink?.success("we cook")

    }*/


    internal fun send2Godot(ad: String) {
        //eventSink?.success("JONE")

        sendDataToFlutter("DUBASS")

    }

    private fun sendDataToFlutter(FA: String) {




    }




    fun bysend(data: String) {
        print("we should have the data!!!!")
        //?.success(data)
        print("should have send the data")
    }


    private var methodCall: MethodChannel.Result? = null;




    /**
     * Example showing how to declare a method that's used by Godot.
     *
     * Shows a 'Hello World' toast.
     */

    // Function to send event via EventChannel
    @UsedByGodot
    private fun helloWorld() {
        // Call this function to send an event via EventChannel
        print("we got 2 helloworld")
        sendData2Flut("helloWorldEvent", "Hello from Godot!")
    }

    private fun sendData2Flut(eventName: String, eventData: Any) {
        Log.w("FUCKY", "SEND HELP")
       // GodotHandler.ourevent()// eventSink?.success("AHHh")


    }

    @UsedByGodot
    private fun otherworld() {
        print("godot gave us ")
        sendData2Flut("sup", "FUCK ME")
    }




    override fun onCancel(arguments: Any?) {
        //eventSink = null

    }

    /*override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        this.methodCall = methodCall

        if (call.method == "sendData2Godot") {
            val data = call.argument<String>("data")
            if (data != null) {

                send2Godot(data)
                result.success(null) // Acknowledge the method call
            } else {
                result.error("INVALID_ARGUMENT", "Missing 'data' argument", null)
            }
        }
        TODO("Not yet implemented")
    }

    private lateinit var methodChannel: MethodChannel

    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        methodChannel = MethodChannel(binding.binaryMessenger, "com.kaiyo.ezgodot/method/start")
        methodChannel.setMethodCallHandler(this)



    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        methodChannel.setMethodCallHandler(null)
    }*/


}


