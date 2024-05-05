extends Node

var plug

# Called when the node enters the scene tree for the first time.
func _ready():
	#if Engine.has_singleton("ez_godot"):
	#	plug = Engine.get_singleton("ez_godot")
	pass # Replace with function body.


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	pass


func _on_button_pressed():
	#if (plug != null):
	#	plug.helloWorld()
	if Engine.has_singleton("godotpluginMaster"):
		plug = Engine.get_singleton("godotpluginMaster")
		plug.helloWorld()
		print("wtf is thjis shit bru")
	else: 
		
		print("aint got the plugin ")
	pass # Replace with function body.

