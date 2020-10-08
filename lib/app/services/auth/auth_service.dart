import 'package:app/app/interfaces/auth/auth_interface.dart';
import 'package:app/app/models/user_model.dart';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'package:mobx/mobx.dart';

part 'auth_service.g.dart';

class AuthService = _AuthServiceBase with _$AuthService;

abstract class _AuthServiceBase with Store {
  final IAuthRepository _authRepository = Modular.get();

  @observable
  AuthStatus status = AuthStatus.loading;

  @observable
  FirebaseUser user;

  @action
  setUser(FirebaseUser value) {
    user = value;
    status = user == null ? AuthStatus.logoff : AuthStatus.login;
    // status = user == null ? AuthStatus.loggedOut : AuthStatus.loggedIn;
  }

  _AuthServiceBase() {
    _authRepository.getUser().then(setUser);
  }

  @action
  Future loginWithGoogle() async {
    user = await _authRepository.getGoogleLogin();
    return user;
  }

  @action
  Future emailSignIn(UserModel model) async {
    user = await _authRepository.emailSignIn(model);
  }

  @action
  Future phoneSignIn(
    UserModel userModel,
    Function(AuthCredential) verificationCompleted,
    Function(String, [int]) codeSent,
  ) async {
    user = await _authRepository.phoneSignIn(
        userModel, verificationCompleted, codeSent);
    return user;
  }

  @action
  Future signUp(UserModel model) async {
    user = await _authRepository.signUp(model).then((user) {
      Firestore.instance.collection('users').add({
        'name': model.name,
        'email': model.email,
      });
      return user;
    });
  }

  @action
  Future signOut() {
    return _authRepository.signOut();
  }
}

enum AuthStatus { loading, login, logoff }
