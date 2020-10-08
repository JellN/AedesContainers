import 'package:app/app/services/auth/auth_service.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:mobx/mobx.dart';
import 'package:flutter_modular/flutter_modular.dart';

part 'login_controller.g.dart';

@Injectable()
class LoginController = _LoginControllerBase with _$LoginController;

abstract class _LoginControllerBase with Store {
  AuthService authService = Modular.get();

  @observable
  bool loading = false;

  @action
  Future loginWithGoogle() async {
    try {
      loading = true;
      await authService.loginWithGoogle();
      await Firestore.instance
          .collection("users")
          .document(authService.user.uid)
          .setData({
        "name": authService.user.displayName,
        "email": authService.user.email,
      });
      Modular.to.pushReplacementNamed("/start");
    } catch (e) {
      loading = false;
    }
  }

  Future signOut() {
    return authService.signOut();
  }
}
