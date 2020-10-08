import 'package:mobx/mobx.dart';
import 'package:flutter_modular/flutter_modular.dart';

part 'email_controller.g.dart';

@Injectable()
class EmailController = _EmailControllerBase with _$EmailController;

abstract class _EmailControllerBase with Store {
  @observable
  int value = 0;

  @action
  void increment() {
    value++;
  }
}
