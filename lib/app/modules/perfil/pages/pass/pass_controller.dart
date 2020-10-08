import 'package:mobx/mobx.dart';
import 'package:flutter_modular/flutter_modular.dart';

part 'pass_controller.g.dart';

@Injectable()
class PassController = _PassControllerBase with _$PassController;

abstract class _PassControllerBase with Store {
  @observable
  int value = 0;

  @action
  void increment() {
    value++;
  }
}
