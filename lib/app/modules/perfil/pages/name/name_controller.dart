import 'package:mobx/mobx.dart';
import 'package:flutter_modular/flutter_modular.dart';

part 'name_controller.g.dart';

@Injectable()
class NameController = _NameControllerBase with _$NameController;

abstract class _NameControllerBase with Store {
  @observable
  int value = 0;

  @action
  void increment() {
    value++;
  }
}
