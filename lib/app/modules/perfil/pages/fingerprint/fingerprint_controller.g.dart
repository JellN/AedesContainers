// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'fingerprint_controller.dart';

// **************************************************************************
// InjectionGenerator
// **************************************************************************

final $FingerprintController = BindInject(
  (i) => FingerprintController(),
  singleton: true,
  lazy: true,
);

// **************************************************************************
// StoreGenerator
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, unnecessary_brace_in_string_interps, unnecessary_lambdas, prefer_expression_function_bodies, lines_longer_than_80_chars, avoid_as, avoid_annotating_with_dynamic

mixin _$FingerprintController on _FingerprintControllerBase, Store {
  final _$valueAtom = Atom(name: '_FingerprintControllerBase.value');

  @override
  int get value {
    _$valueAtom.reportRead();
    return super.value;
  }

  @override
  set value(int value) {
    _$valueAtom.reportWrite(value, super.value, () {
      super.value = value;
    });
  }

  final _$_FingerprintControllerBaseActionController =
      ActionController(name: '_FingerprintControllerBase');

  @override
  void increment() {
    final _$actionInfo = _$_FingerprintControllerBaseActionController
        .startAction(name: '_FingerprintControllerBase.increment');
    try {
      return super.increment();
    } finally {
      _$_FingerprintControllerBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  String toString() {
    return '''
value: ${value}
    ''';
  }
}
