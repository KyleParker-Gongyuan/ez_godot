import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    print("it started I guess");
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const methodChannel = MethodChannel("com.kaiyo.ezgodot/method/start");
  //static const eventChannel = EventChannel('kaiyo.ezgodot/generic');

  

  //methodCodes which shuold be an enum?
  static const methodCodes = ["openGame","closeGame", "sendDatabase","sendGeneric"];
  static const eventCodes = ["openGame","closeGame", "sendDatabase","sendGeneric"];
  
  final _eventStream = const EventChannel("kaiyo.ezgodot/generic");


  @override
  void initState() {
    super.initState();

    //startEvent();
    

    // Start listening to events from the EventChannel
    /*
    _godotStreamSubsciption = GodoEventDataChannel.godotDataStream()!.listen(
      (dynamic event) {
        // Handle incoming events here
        print('Received data: $event');
        // Update UI or perform other actions based on the received event
      }, 
      onError: (error) {
        // Handle any errors here
        print('Received error: $error');

      },
    );
      */

    methodChannel.setMethodCallHandler((call)async{

      if(call.method == "hiya"){
        print(call.arguments);
        print("cant belive this");
      }
      return null;
    });
    networkStream().listen((event) { print("Received data from GD-Android: "+event);});
  }

  @override
  void dispose() {
    // Cancel the event stream subscription when widget is disposed
    
    super.dispose();
  }
  // Method to send a string to the native side via MethodChannel
  



  @override
  Widget build(BuildContext context) {
    final networkStream = _eventStream.receiveBroadcastStream().distinct().map((dynamic event) {
      print("flutter data $event");
      return event;
    });


    return Scaffold(
    body: Column(children: [
      TextButton(onPressed:() async{
        
        await openGodot();
        //await sendData2Game("GUY NAMED DAVE!");


      }, child: Text("openGame")),
      
    ],)
    );
  }
  Future<void> openGodot() async{

    //startEvent();
    await methodChannel.invokeMethod("openGame");//GodotHandler("ad");
  }
  
  Future<void> sendData2Game(String data) async {
    try {
      await methodChannel!.invokeMethod("sendData2Godot", {"data": data});
    } catch (e) {
      print("Error sending data to native godot: $e");
    }
  }


  void dataSend(){ // we need to fix this!!!
    final networkStream = _eventStream.receiveBroadcastStream().distinct().map((dynamic event) {
      print("flutter data $event");
      return event;
    });
  }


  Stream<dynamic> networkStream(){return _eventStream.receiveBroadcastStream().distinct().map((dynamic event) {
      debugPrint("flutter data $event");
      return event;
    });}

  // Define your data handler
  void _onDataReceived(dynamic data) {
    // Handle the data received from Android
    print('Received data from GD-Android: $data');
  }

  // Define your error handler
  void _onError(Object error) {
    // Handle the error received from Android
    print('Received error from Android: $error');
  }
  void startEvent(){
    // Define the EventChannel
    EventChannel eventChannel = const EventChannel("kaiyo.ezgodot/generic");
    // Listen to the EventChannel
    eventChannel.receiveBroadcastStream().listen(
      _onDataReceived, 
      onError: _onError,
    );
  }

}

