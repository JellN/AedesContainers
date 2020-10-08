import 'package:app/app/services/auth/auth_service.dart';
import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'package:mobx/mobx.dart';

class SplashPage extends StatefulWidget {
  final String title;

  const SplashPage({Key key, this.title = "Splash"}) : super(key: key);

  @override
  _SplashPageState createState() => _SplashPageState();
}

class _SplashPageState extends State<SplashPage> {
  ReactionDisposer disposer;

  @override
  void initState() {
    super.initState();
    Future.delayed(Duration(seconds: 2)).then((_) {
      //Autorun para verificação de login com firebase
      disposer = autorun((_) {
       /* final auth = Modular.get<AuthService>();
        if (auth.status == AuthStatus.login) {
          Modular.to.pushReplacementNamed('/start');
        } else if (auth.status == AuthStatus.logoff) {
          Modular.to.pushReplacementNamed('/login');
        }*/
        Modular.to.pushReplacementNamed('/login');
      });
    });

  }

  @override
  void dispose() {
    super.dispose();
    disposer();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
          child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Padding(padding: EdgeInsets.all(5.0), child: Text("Tela de Splash")),
          CircularProgressIndicator(),
        ],
      )),
    );
  }
}
