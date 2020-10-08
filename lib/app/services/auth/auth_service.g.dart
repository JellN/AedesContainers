// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'auth_service.dart';

// **************************************************************************
// StoreGenerator
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, unnecessary_brace_in_string_interps, unnecessary_lambdas, prefer_expression_function_bodies, lines_longer_than_80_chars, avoid_as, avoid_annotating_with_dynamic

mixin _$AuthService on _AuthServiceBase, Store {
  final _$statusAtom = Atom(name: '_AuthServiceBase.status');

  @override
  AuthStatus get status {
    _$statusAtom.reportRead();
    return super.status;
  }

  @override
  set status(AuthStatus value) {
    _$statusAtom.reportWrite(value, super.status, () {
      super.status = value;
    });
  }

  final _$userAtom = Atom(name: '_AuthServiceBase.user');

  @override
  FirebaseUser get user {
    _$userAtom.reportRead();
    return super.user;
  }

  @override
  set user(FirebaseUser value) {
    _$userAtom.reportWrite(value, super.user, () {
      super.user = value;
    });
  }

  final _$loginWithGoogleAsyncAction =
      AsyncAction('_AuthServiceBase.loginWithGoogle');

  @override
  Future<dynamic> loginWithGoogle() {
    return _$loginWithGoogleAsyncAction.run(() => super.loginWithGoogle());
  }

  final _$emailSignInAsyncAction = AsyncAction('_AuthServiceBase.emailSignIn');

  @override
  Future<dynamic> emailSignIn(UserModel model) {
    return _$emailSignInAsyncAction.run(() => super.emailSignIn(model));
  }

  final _$phoneSignInAsyncAction = AsyncAction('_AuthServiceBase.phoneSignIn');

  @override
  Future<dynamic> phoneSignIn(
      UserModel userModel,
      dynamic Function(AuthCredential) verificationCompleted,
      dynamic Function(String, [int]) codeSent) {
    return _$phoneSignInAsyncAction.run(
        () => super.phoneSignIn(userModel, verificationCompleted, codeSent));
  }

  final _$signUpAsyncAction = AsyncAction('_AuthServiceBase.signUp');

  @override
  Future<dynamic> signUp(UserModel model) {
    return _$signUpAsyncAction.run(() => super.signUp(model));
  }

  final _$_AuthServiceBaseActionController =
      ActionController(name: '_AuthServiceBase');

  @override
  dynamic setUser(FirebaseUser value) {
    final _$actionInfo = _$_AuthServiceBaseActionController.startAction(
        name: '_AuthServiceBase.setUser');
    try {
      return super.setUser(value);
    } finally {
      _$_AuthServiceBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  Future<dynamic> signOut() {
    final _$actionInfo = _$_AuthServiceBaseActionController.startAction(
        name: '_AuthServiceBase.signOut');
    try {
      return super.signOut();
    } finally {
      _$_AuthServiceBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  String toString() {
    return '''
status: ${status},
user: ${user}
    ''';
  }
}
