
@tool
extends EditorPlugin


var exportPlugin : AndroidExportPlugin

func _enter_tree():
	print("preplugin")
	# Initialization of the plugin goes here.
	exportPlugin = AndroidExportPlugin.new()
	add_export_plugin(exportPlugin)
	print("added plug")
	pass


func _exit_tree():
	# Clean-up of the plugin goes here.
	remove_export_plugin(exportPlugin)
	exportPlugin = null
	pass


class AndroidExportPlugin extends EditorExportPlugin:
	var pluginName = "HelloWorld"
	
	func _supports_platform(platform):
		if platform is EditorExportPlatformAndroid:
			print("this is an android")
			return true
		return false
		
	func _get_android_libraries(platform, debug):
		if debug:
			print("is debug yo")
			return PackedStringArray(["HelloWolrd/flutter_debug-1.0.aar"])
		else: 
			return PackedStringArray(["HelloWolrd/flutter_debug-1.0.aar"])
	func _get_android_dependencies_maven_repos(platform, debug):
			
		if debug: # might need flutter dependancies????
			return "https://storage.googleapis.com/download.flutter.io"
		else:
			return "https://storage.googleapis.com/download.flutter.io"
			
			
	func _get_name():
		return pluginName
	
