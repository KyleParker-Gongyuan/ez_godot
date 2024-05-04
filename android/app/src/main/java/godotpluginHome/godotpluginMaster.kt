package godotpluginHome

import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin
import org.godotengine.godot.plugin.UsedByGodot

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel

class godotpluginMaster(godot: Godot?) : GodotPlugin(godot), EventChannel.StreamHandler {

    private val eventName =  "kaiyo.ezgodot/generic";

    private var eventSink: EventChannel.EventSink? = null;

    //override fun getPluginName() = BuildConfig.GODOT_PLUGIN_NAME
    override fun getPluginName() = "ez_godot"


    /**
     * Example showing how to declare a method that's used by Godot.
     *
     * Shows a 'Hello World' toast.
     */
    @UsedByGodot
    private fun helloWorld() {
        runOnUiThread {
            Toast.makeText(activity, "Hello World", Toast.LENGTH_LONG).show()
            Log.v(pluginName, "Hello World")
            eventSink?.success("Hello from Android!")

        }
    }

    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        this.eventSink = eventSink
        TODO("Not yet implemented")
    }

    override fun onCancel(arguments: Any?) {
        eventSink = null
        TODO("Not yet implemented")
    }
}