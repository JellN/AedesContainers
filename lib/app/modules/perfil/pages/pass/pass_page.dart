import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'pass_controller.dart';

class PassPage extends StatefulWidget {
  final String title;

  const PassPage({Key key, this.title = "Alterar Senha"}) : super(key: key);

  @override
  _PassPageState createState() => _PassPageState();
}

class _PassPageState extends ModularState<PassPage, PassController> {
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
