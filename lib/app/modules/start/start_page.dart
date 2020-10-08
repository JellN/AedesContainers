import 'package:app/app/modules/perfil/perfil_module.dart';
import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'start_controller.dart';

class StartPage extends StatefulWidget {
  final String title;

  const StartPage({Key key, this.title = "Start"}) : super(key: key);

  @override
  _StartPageState createState() => _StartPageState();
}

class _StartPageState extends ModularState<StartPage, StartController> {
  //use 'controller' variable to access controller

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: PageView(
        controller: controller.pageViewController,
        children: [
          Container(),
          Container(),
          Container(),
          Container(),
          RouterOutlet(
            module: PerfilModule(),
          ),
        ],
      ),
      bottomNavigationBar: AnimatedBuilder(
          animation: controller.pageViewController,
          builder: (context, snapshot) {
            return Container(
              decoration: BoxDecoration(color: Colors.green),
              child: BottomNavigationBar(
                  unselectedItemColor: Colors.grey[600],
                  selectedItemColor: Color(0xff8adf4f),
                  currentIndex:
                      controller.pageViewController?.page?.round() ?? 0,
                  onTap: (index) {
                    controller.pageViewController.animateToPage(index,
                        curve: Curves.easeInOutQuint,
                        duration: Duration(milliseconds: 500));
                  },
                  items: [
                    BottomNavigationBarItem(
                      activeIcon: Icon(Icons.email),
                      icon: Icon(Icons.home),
                      title: Text("Home"),
                    ),
                    BottomNavigationBarItem(
                      icon: Icon(Icons.shopping_basket),
                      title: Text("Shopping"),
                    ),
                    BottomNavigationBarItem(
                      icon: Icon(Icons.account_balance_wallet),
                      title: Text("Carteira"),
                    ),
                    BottomNavigationBarItem(
                      icon: Icon(Icons.trending_up),
                      title: Text("Gestão"),
                    ),
                    BottomNavigationBarItem(
                      activeIcon: Icon(Icons.person),
                      icon: Icon(Icons.person_outline),
                      title: Text("Perfil"),
                    )
                  ]),
            );
          }),
    );
  }
}
