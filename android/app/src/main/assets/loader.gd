extends Node


# Called when the node enters the scene tree for the first time.
func _ready():
	#ProjectSettings.load_resource_pack("res://mod.pck")
	# we want to send the file into res i think
	# I need to get the file and then send that file into this
	
	# Now you can use the assets as if you had them in the project from the start
	#var imported_scene = load("res://modScene.tscn")
	#! We need to make sure this works for ios and android (how idk, we ciykd just do loaderIOS and loaderAndroid)
	#get_tree().change_scene_to_file(imported_scene)
	pass # Replace with function body.


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	pass
