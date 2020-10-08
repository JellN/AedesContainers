import 'package:app/app/interfaces/auth/auth_interface.dart';
import 'package:app/app/models/user_model.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'package:google_sign_in/google_sign_in.dart';

class AuthRepository implements IAuthRepository {
  final GoogleSignIn _googleSignIn = GoogleSignIn();
  final FirebaseAuth auth = FirebaseAuth.instance;

  // final _userFlow = Modular.get<UserFlow>();

  @override
  Future<FirebaseUser> emailSignIn(UserModel userModel) async {
    await auth.signInWithEmailAndPassword(
        email: userModel.email, password: userModel.password);

    var user = auth.currentUser();

    return user;
  }

  @override
  Future<FirebaseUser> phoneSignIn(
    UserModel userModel,
    Function(AuthCredential) verificationCompleted,
    Function(String, [int]) codeSent,
  ) async {
    auth.verifyPhoneNumber(
      phoneNumber: "+55${userModel.phone}",
      timeout: Duration(seconds: 60),
      verificationCompleted: verificationCompleted,
      verificationFailed: (AuthException exception) {
        print(exception.message);
      },
      codeSent: codeSent,
      codeAutoRetrievalTimeout: null,
    );
    var user = auth.currentUser();
    return user;
  }

  @override
  Future<FirebaseUser> signUp(UserModel model) async {
    await auth.createUserWithEmailAndPassword(
        email: model.email, password: model.password);
    var user = auth.currentUser();
    return user;
  }

  @override
  Future<FirebaseUser> getUser() {
    return auth.currentUser();
  }

  @override
  Future signOut() {
    return auth.signOut();
  }

  @override
  Future<String> getToken() {}

  @override
  Future<FirebaseUser> getGoogleLogin() async {
    final GoogleSignInAccount googleUser = await _googleSignIn.signIn();
    final GoogleSignInAuthentication googleAuth =
        await googleUser.authentication;

    final AuthCredential credential = GoogleAuthProvider.getCredential(
      accessToken: googleAuth.accessToken,
      idToken: googleAuth.idToken,
    );

    final FirebaseUser user =
        (await auth.signInWithCredential(credential)).user;
    print("signed in " + user.displayName);
    return user;
  }
}
