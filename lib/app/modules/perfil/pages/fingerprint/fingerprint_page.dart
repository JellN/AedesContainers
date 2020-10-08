import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'fingerprint_controller.dart';

class FingerprintPage extends StatefulWidget {
  final String title;

  const FingerprintPage({Key key, this.title = "Impressão digital"})
      : super(key: key);

  @override
  _FingerprintPageState createState() => _FingerprintPageState();
}

class _FingerprintPageState
    extends ModularState<FingerprintPage, FingerprintController> {
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
