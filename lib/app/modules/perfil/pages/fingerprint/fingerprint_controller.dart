import 'package:mobx/mobx.dart';
import 'package:flutter_modular/flutter_modular.dart';

part 'fingerprint_controller.g.dart';

@Injectable()
class FingerprintController = _FingerprintControllerBase
    with _$FingerprintController;

abstract class _FingerprintControllerBase with Store {
  @observable
  int value = 0;

  @action
  void increment() {
    value++;
  }
}
