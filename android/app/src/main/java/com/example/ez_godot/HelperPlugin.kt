package com.example.ez_godot


import io.flutter.plugin.common.EventChannel


class HelperPlugin : EventChannel.StreamHandler {



    //private var messageChannel: EventChannel? = null

    private var eventSink: EventChannel.EventSink? = null


    override fun onListen(arguments: Any?, sink: EventChannel.EventSink) {
        eventSink = sink
    }

    override fun onCancel(arguments: Any?) {
        eventSink = null

    }

    fun testEvent(data: String){
        eventSink?.success("TEST ADD EVENT")
    }

}
