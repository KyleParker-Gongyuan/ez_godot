extends Node

const PORT = 8080

var server
var clients = []

func _ready():
	server = ENetMultiplayerPeer.new()
	server.create_server(PORT, clients.max_size())
	server.connect("connected", Callable(self, "_on_client_connected"))
	server.connect("peer_disconnected", Callable(self, "_on_client_disconnected"))
	server.connect("peer_packet", Callable(self, "_on_client_packet"))

func _on_client_connected():
	print("Client connected: ")
	

func _on_client_disconnected():
	print("Client disconnected: ")
	

func _on_client_packet(id, data):
	# Handle incoming data from the client
	# Implement WebSocket handshake and message handling here
	# This is where you would parse and process WebSocket frames
	pass

func _process(delta):
	# Implement WebSocket server logic here
	pass
