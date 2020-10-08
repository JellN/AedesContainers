import 'package:app/app/repositories/auth/auth_repository.dart';
import 'package:app/app/services/auth/auth_service.dart';
import 'app_widget.dart';
import 'app_controller.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'package:flutter/material.dart';
import 'interfaces/auth/auth_interface.dart';
import 'modules/login/login_module.dart';
import 'modules/splash/splash_page.dart';
import 'modules/perfil/perfil_module.dart';
import 'modules/start/start_module.dart';

class AppModule extends MainModule {
  @override
  List<Bind> get binds => [
        $AppController,
        Bind((i) => AuthService()),
        Bind<IAuthRepository>((i) => AuthRepository()),
      ];

  @override
  List<ModularRouter> get routers => [
        ModularRouter('/', child: (_, args) => SplashPage(), transition: TransitionType.fadeIn),
        ModularRouter('/start', module: StartModule(), transition: TransitionType.fadeIn),
        ModularRouter('/login', module: LoginModule()),
        ModularRouter('/perfil', module: PerfilModule()),
      ];

  @override
  Widget get bootstrap => AppWidget();

  static Inject get to => Inject<AppModule>.of();
}
