extends Node
var singleton


# Called when the node enters the scene tree for the first time.
func _ready():
	
	if Engine.has_singleton("godotpluginMaster"):
		singleton = Engine.get_singleton("godotpluginMaster")
		singleton.connect("get_stang", examp) # this is what our get_stang is actually calling
		
		singleton.otherworld()
		print("Yo we out  here bruh")
		singleton.helloWorld() # this should call the hello world thing
	pass # Replace with function body.


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	pass


func _on_button_button_down():
	print("btn was pressed")
	singleton.otherworld()
	pass # Replace with function body.

func examp(ourExampleTxt: String):
	print("wow Flutter said: "+ ourExampleTxt)
	
func dataz():
	print("Godot says: ")
	
	pass
