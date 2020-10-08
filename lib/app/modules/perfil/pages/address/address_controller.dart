import 'package:mobx/mobx.dart';
import 'package:flutter_modular/flutter_modular.dart';

part 'address_controller.g.dart';

@Injectable()
class AddressController = _AddressControllerBase with _$AddressController;

abstract class _AddressControllerBase with Store {
  @observable
  int value = 0;

  @action
  void increment() {
    value++;
  }
}
