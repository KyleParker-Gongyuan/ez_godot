extends Node





# The port we will listen to
const PORT = 9080
# Our WebSocketServer instance
var _server = WebSocketMultiplayerPeer.new()
var other = ENetMultiplayerPeer.new()
var ourIP = "127.0.0.1"

func _ready():
	# Connect base signals to get notified of new client connections,
	# disconnections, and disconnect requests.
	

	
	multiplayer.peer_connected.connect(self._client_connect)
	multiplayer.peer_disconnected.connect(self._client_disconnected)
	
	other.set_bind_ip(ourIP)
	
	other.create_server(PORT)
	multiplayer.multiplayer_peer = other
	# This signal is emitted when not using the Multiplayer API every time a
	# full packet is received.
	# Alternatively, you could check get_peer(PEER_ID).get_available_packets()
	# in a loop for each connected peer.
	
	# Start listening on the given port.
	var err = other.listen(PORT)
	if err != OK:
		print("Unable to start server")
		set_process(false)

@rpc
func izzz():
	print("ad")
func msgToClient():
	print("idk")
	
@rpc("any_peer")
func send_msg_to_game(message: String):
	#this is going to be from our flutter game holder
	if multiplayer.is_server():
		print("msg from flutter: "+message)
		send_msg_to_flutter.rpc("we got yo shit cuz");

# only called from the server
@rpc("authority")
func send_msg_to_flutter(message: String):
	print("flutter shuold have gotten this: " + message)

func startClient():
	multiplayer.connected_to_server.connect(self._connect_to_server)
	multiplayer.server_disconnected.connect(self._disconnected_from_server)
	
	print("making the flutter client...")
	var client = ENetMultiplayerPeer.new()
	client.create_client(ourIP,PORT)
	multiplayer.multiplayer_peer = client

func _process(delta):
	# Call this in _process or _physics_process.
	# Data transfer, and signals emission will only happen when calling this function.
	#_server.poll()
	pass
	
func _connect_to_server():
	print("idk cunt: I think we connected gamer!!!!!")
func _disconnected_from_server():
	print("idk cunt")
func _client_connect():
	print("mf dave joined")
func _client_disconnected():
	print("mf dave LEFT US ;(")
"""

@onready var _client: WebSocketPeer
@onready var _log_dest = $Panel/VBoxContainer/RichTextLabel
@onready var _line_edit = $Panel/VBoxContainer/Send/LineEdit
@onready var _host = $Panel/VBoxContainer/Connect/Host

var cur
var websocket_url = "localhost:1878"

func info(msg):
	print(msg)
	_log_dest.add_text(str(msg) + "\n")


# Client signals
func _on_web_socket_client_connection_closed():
	var ws = _client.get_socket()
	info("Client just disconnected with code: %s, reson: %s" % [ws.get_close_code(), ws.get_close_reason()])


func _on_web_socket_client_connected_to_server():
	info("Client just connected with protocol: %s" % _client.get_socket().get_selected_protocol())


func _on_web_socket_client_message_received(message):
	info("%s" % message)


func _on_connect_toggled(pressed):
	if not pressed:
		_client.close()
		return
	if _host.text == "":
		return
	info("Connecting to host: %s." % [_host.text])
	var err = _client.connect_to_url(_host.text)
	if err != OK:
		info("Error connecting to host: %s" % [_host.text])
		return

func _ready():
	
	# Initiate connection to the given URL.
	#var err = _client.connect_to_url(websocket_url)
	#if err != OK:
	#	print("Unable to connect")
	#	set_process(false)
	print("godotz: we got here")

func _on_data():
	# Print the received packet, you MUST always use get_peer(1).get_packet
	# to receive data from server, and not get_packet directly when not
	# using the MultiplayerAPI.
	var v = get_node("Label")
	v.text = "getting data"
	var z = _client.get_peer(1).get_packet().get_string_from_utf8()
	
	print("Got data from server: ", str(z)) 
	#! _client.get_peer(1).get_packet().get_string_from_utf8() #? we want to export this info
	
	
	v.text = "we fr got the data "+ str(z)
	cur+= 1
	#? when this is called then the output == switchstatement (match in godot)
	#! we get a string then we have it goto a switch statment

func _closed(was_clean = false):
	# was_clean will tell you if the disconnection was correctly notified
	# by the remote peer before closing the socket.
	print("Closed, clean: ", was_clean)
	set_process(false)

func _connected(proto = ""):
	# This is called on connection, "proto" will be the selected WebSocket
	# sub-protocol (which is optional)
	print("Connected with protocol: ", proto)
	# You MUST always use get_peer(1).put_packet to send data to server,
	# and not put_packet directly when not using the MultiplayerAPI.
	#_client.get_peer(1).put_packet("Test packet".to_utf8())
	_client.get_peer(1).put_packet(JSON.new().stringify({"command":"ping", "val":"name"}).to_utf8_buffer())
	#send_data("sending shiii")


func send_data(packet = ""):
	
	_client.get_peer(1).put_packet(str(packet).to_utf8_buffer())

func _process(_delta):
	# Call this in _process or _physics_process. Data transfer, and signals
	# emission will only happen when calling this function.
	_client.poll()


func sendPos(pos): ## sends the player posintion to the server
	
	var message = '{"command":"ping", "val":"name"}'
	var packet: PackedByteArray = JSON.stringify(message).to_utf8_buffer()
	print("Sending packet ", packet.get_string_from_utf8())

	
	_client.get_peer(1).put_packet(packet)
	
	var v = get_node("Label")
	v.text = str(pos)


func parserv2(packet):
	var packettype: String = packet.command
	match packettype:
		"ping":
			print("server pinged us")
			var v = get_node("Label")
			v.text = "s"
			# If health == 1, run this block of code
		_:
			print("dad")
		
			# If nothing matches, run this block of code

	
"""
	
	#if Engine.has_singleton("HelloWorld"):
	#	helloworld = Engine.get_singleton("HelloWorld")
	#	helloworld.sharedText("test text") #! this is for our sending of data 
	#else:
	#	print("WTF WE DONT HAVE THE SINGLETON!!!!")
	
	
		#1 create a file for communicating
		#2 use SQL and somehow make it so they are connecting to the same SQL idk how tho
		#3 make it like a server (we did this but it was fucking stupid, this will be 2% less stupid)
		
		
	#! ProjectSettings.load_resource_pack("res://mod.pck")
	# we want to send the file into res i think
	# I need to get the file and then send that file into this
	
	# Now you can use the assets as if you had them in the project from the start
	
	#! var imported_scene = load("res://modScene.tscn")
	
	#! We need to make sure this works for ios and android (how idk, we ciykd just do loaderIOS and loaderAndroid)
	
	#get_tree().change_scene_to_file(imported_scene)
	#pass # Replace with function body.


# Called every frame. 'delta' is the elapsed time since the previous frame.
#func _process(delta):
#	pass
