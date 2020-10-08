import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'email_controller.dart';

class EmailPage extends StatefulWidget {
  final String title;

  const EmailPage({Key key, this.title = "Email do usuário"}) : super(key: key);

  @override
  _EmailPageState createState() => _EmailPageState();
}

class _EmailPageState extends ModularState<EmailPage, EmailController> {
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
