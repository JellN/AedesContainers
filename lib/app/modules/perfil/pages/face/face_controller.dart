import 'package:mobx/mobx.dart';
import 'package:flutter_modular/flutter_modular.dart';

part 'face_controller.g.dart';

@Injectable()
class FaceController = _FaceControllerBase with _$FaceController;

abstract class _FaceControllerBase with Store {
  @observable
  int value = 0;

  @action
  void increment() {
    value++;
  }
}
