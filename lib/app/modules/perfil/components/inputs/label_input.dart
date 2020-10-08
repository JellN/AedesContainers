import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';

class LabelInput extends StatelessWidget {
  final IconData icon;
  final String textLabel;
  final String routeName;

  LabelInput({this.icon, this.textLabel, this.routeName});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        Navigator.of(context).pushNamed(routeName);
      },
      child: Container(
        width: MediaQuery.of(context).size.width,
        height: 24,
        margin: EdgeInsets.only(left: 18.0, top: 10.0),
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            Icon(
              icon,
              size: 24.0,
            ),
            SizedBox(
              width: 25.0,
            ),
            Text(
              textLabel,
              style: TextStyle(
                  fontSize: 14.0, letterSpacing: 0, color: Color(0xff201f1f)),
            ),
          ],
        ),
      ),
    );
  }
}
