import 'dart:io';

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
  static const methodChannel = MethodChannel("com.kaiyo.ezgodot/method");
  static const gernericChannel = EventChannel("com.kaiyo.ezgodot/generic");
  static const databaseChannel = EventChannel("com.kaiyo.ezgodot/database");
  static const exitChannel = EventChannel("com.kaiyo.ezgodot/exit");

  //methodCodes which shuold be an enum?
  static const methodCodes = ["openGame","closeGame", "sendDatabase","sendGeneric"];
  @override
  Widget build(BuildContext context) {
  
    return Scaffold(
    body: Column(children: [
      TextButton(onPressed:() async{
        //GodotHandler(File('lib/bad.txt'));
        await methodChannel.invokeMethod("openGame");//GodotHandler("ad");


      }, child: Text("openGame")),
    ],)
    );
  }
    /// we could have it so the [startingCode]== the actual game .pck?
  Future GodotHandler(File file)async{ //WORKS ON ANDROID!!!! HELLL YEAH!!!!!
    
    final bytes = await file.readAsBytes();
    
    try{
        // Read the file as bytes
        //final result = await methodChannel.invokeMethod('openGame');
        //print('File sent successfully: $result');
        //await methodChannel.invokeMethod("openGame");//GodotHandler("ad");

      } on PlatformException catch (e){
        debugPrint("fail: '${e.message}");
      }
    //return Container();
  }
}