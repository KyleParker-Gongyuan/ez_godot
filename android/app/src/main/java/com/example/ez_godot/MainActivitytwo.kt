package com.example.ez_godot


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import godotpluginHome.GodotpluginMaster
import io.flutter.plugin.common.EventChannel

import org.godotengine.godot.Godot
import org.godotengine.godot.GodotFragment
import org.godotengine.godot.GodotHost
import org.godotengine.godot.plugin.GodotPlugin



/**
 * Implements the [GodotHost] interface so it can access functionality from the [Godot] instance.
 */
class MainActivitytwo: AppCompatActivity(), GodotHost, EventChannel.StreamHandler{


    val probz = activity

    private var eventSink: EventChannel.EventSink? = null
    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        eventSink = events
        println("started EventSink")

    }

    private var godotFragment: GodotFragment? = null



    var appPlugin: GodotpluginMaster? = null


    var dataToRetrieve: String? = null

    // Method to update data (could be triggered by an event)

    // Interface for data callback



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_activitytwo)

        val currentGodotFragment = supportFragmentManager.findFragmentById(R.id.godot_fragment_container)
        if (currentGodotFragment is GodotFragment) {
            godotFragment = currentGodotFragment
        } else {
            godotFragment = GodotFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.godot_fragment_container, godotFragment!!)
                .commitNowAllowingStateLoss()
        }

        println("yeah boi")


        println("shoulda Created dat nigga")





    //! this is for stuff we dont care about



        initAppPluginIfNeeded(godot!!)


        // Initialize appPlugin and set the callback
        godotFragment?.godot?.let { initAppPluginIfNeeded(it) }




        /*
                var itemsSelectionFragment = supportFragmentManager.findFragmentById(R.id.item_selection_fragment_container)
                if (itemsSelectionFragment !is ItemsSelectionFragment) {
                    itemsSelectionFragment = ItemsSelectionFragment.newInstance(1)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.item_selection_fragment_container, itemsSelectionFragment)
                        .commitAllowingStateLoss()
                }*/




        // Schedule sending function every 10 seconds
       /* val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                sendEvery10secs()

            }
        }, 0, 10000) // Execute every 10 seconds (10000 milliseconds)
*/

}

// Method to set the data received listener

    fun sendz(){
        eventSink?.success("send dat data")
    }

private fun initAppPluginIfNeeded(godot: Godot) {
if (appPlugin == null) {
    appPlugin = GodotpluginMaster(godot)
}
}

override fun getActivity() = this

override fun getGodot() = godotFragment?.godot





override fun getHostPlugins(godot: Godot): Set<GodotPlugin> {
initAppPluginIfNeeded(godot)

return setOf(appPlugin!!)
}




/*override fun onListen(arguments: Any?, eventSink: EventSink) {

godotEvents = eventSink


/*
// Start sending data every second
val runnable = object : Runnable { // IM 99% sure this is the wrong way of doing this
    override fun run() {
        handler.post {
            // Get data from your source (e.g., GodotFragment callback)
            var data = dataToRetrieve
            if (data != null){
                Log.d("MainActivitytwo", "Sending data: $data")
                godotEvents?.success(data)

            }
        }
        handler.postDelayed(this, 1000)
    }
}
handler.post(runnable*/

//






}



override fun onCancel(arguments: Any?) {
godotEvents= null
//TODO("Not yet implemented")
}*/





fun sendEvery10secs(){

probz.appPlugin?.send2Godot("yo godot wazz up!!!")
//probz.appPlugin?.sendDataToFlutter("yo godot wazz up!!!")



//eventSink?.success("other data: yo godot wazz up!!!")
}



    override fun onCancel(arguments: Any?) {
        TODO("Not yet implemented")
    }


    /*
    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {


    if (call.method == "sendData2Godot") {
        val data = call.argument<String>("data")
        if (data != null) {

            val probz = activity
            probz.appPlugin?.send2Godot(data)
            result.success(null) // Acknowledge the method call
        } else {
            result.error("INVALID_ARGUMENT", "Missing 'data' argument", null)
        }
    }

    else {
        print("FUCKYYY")
    }


    TODO("Not yet implemented")
    }*/


}




