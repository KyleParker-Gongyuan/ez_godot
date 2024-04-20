package com.example.ez_godot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.godotengine.godot.Godot
import org.godotengine.godot.GodotFragment
import org.godotengine.godot.GodotHost
import org.godotengine.godot.plugin.GodotPlugin

/*
class MainActivitytwo : AppCompatActivity(),GodotHost {

    private var godotFragment: GodotFragment? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activitytwo)

        val currentGodotFragment = supportFragmentManager.findFragmentById(R.id.godot_fragment_container)
        if (currentGodotFragment is GodotFragment){
            godotFragment = currentGodotFragment
        }
        else { godotFragment = GodotFragment()
            supportFragmentManager.beginTransaction().replace(R.id.godot_fragment_container, godotFragment!!).commitNowAllowingStateLoss()
        }
    }





        override fun getActivity() = this


    override fun getGodot() = godotFragment?.godot
}


*/


/**
 * Implements the [GodotHost] interface so it can access functionality from the [Godot] instance.
 */
class MainActivitytwo: AppCompatActivity(), GodotHost {

    private var godotFragment: GodotFragment? = null

    //internal var appPlugin: AppPlugin? = null

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
        //! this is for stuff we dont care about
        /*


        initAppPluginIfNeeded(godot!!)

        var itemsSelectionFragment = supportFragmentManager.findFragmentById(R.id.item_selection_fragment_container)
        if (itemsSelectionFragment !is ItemsSelectionFragment) {
            itemsSelectionFragment = ItemsSelectionFragment.newInstance(1)
            supportFragmentManager.beginTransaction()
                .replace(R.id.item_selection_fragment_container, itemsSelectionFragment)
                .commitAllowingStateLoss()
        }*/
    }
    /*


    private fun initAppPluginIfNeeded(godot: Godot) {
        if (appPlugin == null) {
            appPlugin = AppPlugin(godot)
        }
    } */

    override fun getActivity() = this

    override fun getGodot() = godotFragment?.godot

    /*
    override fun getHostPlugins(godot: Godot): Set<GodotPlugin> {
        initAppPluginIfNeeded(godot)

        return setOf(appPlugin!!)
    }
    */
}




