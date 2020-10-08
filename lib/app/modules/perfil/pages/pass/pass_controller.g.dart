// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'pass_controller.dart';

// **************************************************************************
// InjectionGenerator
// **************************************************************************

final $PassController = BindInject(
  (i) => PassController(),
  singleton: true,
  lazy: true,
);

// **************************************************************************
// StoreGenerator
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, unnecessary_brace_in_string_interps, unnecessary_lambdas, prefer_expression_function_bodies, lines_longer_than_80_chars, avoid_as, avoid_annotating_with_dynamic

mixin _$PassController on _PassControllerBase, Store {
  final _$valueAtom = Atom(name: '_PassControllerBase.value');

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

  final _$_PassControllerBaseActionController =
      ActionController(name: '_PassControllerBase');

  @override
  void increment() {
    final _$actionInfo = _$_PassControllerBaseActionController.startAction(
        name: '_PassControllerBase.increment');
    try {
      return super.increment();
    } finally {
      _$_PassControllerBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  String toString() {
    return '''
value: ${value}
    ''';
  }
}
