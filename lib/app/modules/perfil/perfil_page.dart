import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'components/inputs/label_input.dart';
import 'perfil_controller.dart';

class PerfilPage extends StatefulWidget {
  final String title;

  const PerfilPage({Key key, this.title = "Perfil"}) : super(key: key);

  @override
  _PerfilPageState createState() => _PerfilPageState();
}

class _PerfilPageState extends ModularState<PerfilPage, PerfilController> {
  //use 'controller' variable to access controller
  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Container(
            child: Container(
              padding: EdgeInsets.only(left: 18.0, top: 50.0),
              width: 152.0,
              height: 24.0,
              child: Text(
                "NOME DO USUÁRIO",
                style: TextStyle(
                    fontSize: 14.0,
                    fontWeight: FontWeight.bold,
                    letterSpacing: 0,
                    color: Color(0xff201f1f)),
              ),
            ),
            color: Color(0xffdadada),
            height: 96,
            width: MediaQuery.of(context).size.width,
            margin: EdgeInsets.only(bottom: 8.0),
          ),
          LabelInput(icon: Icons.person, textLabel: "Nome de preferência", routeName:  "/name"),
          LabelInput(icon: Icons.email, textLabel: "Email", routeName:  "/email"),
          LabelInput(icon: Icons.lock, textLabel: "Alterar senha", routeName:  "/pass"),
          LabelInput(icon: Icons.fingerprint, textLabel: "Habilitar digital", routeName:  "/fingerprint"),
          LabelInput(icon: Icons.tag_faces, textLabel: "Habilitar reconhecimento facial", routeName:  "/face"),
          LabelInput(icon: Icons.place, textLabel: "Endereços", routeName:  "/address"),
          LabelInput(icon: Icons.notifications, textLabel: "Notificações", routeName: "/notifications",),
          LabelInput(icon: Icons.exit_to_app, textLabel: "Sair"),
        ],
      ),
    );
  }
}
