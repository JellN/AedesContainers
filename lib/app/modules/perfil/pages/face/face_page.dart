import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'face_controller.dart';

class FacePage extends StatefulWidget {
  final String title;

  const FacePage({Key key, this.title = "Reconhecimento Facial"})
      : super(key: key);

  @override
  _FacePageState createState() => _FacePageState();
}

class _FacePageState extends ModularState<FacePage, FaceController> {
  //use 'controller' variable to access controller

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Column(
        children: <Widget>[],
      ),
    );
  }
}
