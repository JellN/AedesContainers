import 'package:app/app/models/user_model.dart';
import 'package:firebase_auth/firebase_auth.dart';

abstract class IAuthRepository {
  Future<FirebaseUser> emailSignIn(UserModel userModel);

  Future<FirebaseUser> getGoogleLogin();

  Future<FirebaseUser> phoneSignIn(
    UserModel userModel,
    Function(AuthCredential) verificationCompleted,
    Function(String, [int]) codeSent,
  );

  Future<String> getToken();

  Future<FirebaseUser> signUp(UserModel model);

  Future<FirebaseUser> getUser();

  Future signOut();
//TODO: SIGN IN WITH PHONE
}
