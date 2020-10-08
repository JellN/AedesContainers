import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'address_controller.dart';

class AddressPage extends StatefulWidget {
  final String title;

  const AddressPage({Key key, this.title = "Endereço cadastrado"})
      : super(key: key);

  @override
  _AddressPageState createState() => _AddressPageState();
}

class _AddressPageState extends ModularState<AddressPage, AddressController> {
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
