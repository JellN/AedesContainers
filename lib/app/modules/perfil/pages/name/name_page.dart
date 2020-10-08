import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'name_controller.dart';

class NamePage extends StatefulWidget {
  final String title;

  const NamePage({Key key, this.title = "Alterar apelido"}) : super(key: key);

  @override
  _NamePageState createState() => _NamePageState();
}

class _NamePageState extends ModularState<NamePage, NameController> {
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
