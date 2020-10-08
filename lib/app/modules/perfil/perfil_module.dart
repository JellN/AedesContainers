import 'package:app/app/modules/perfil/pages/address/address_page.dart';
import 'package:app/app/modules/perfil/pages/email/email_page.dart';
import 'package:app/app/modules/perfil/pages/face/face_page.dart';
import 'package:app/app/modules/perfil/pages/fingerprint/fingerprint_page.dart';
import 'package:app/app/modules/perfil/pages/name/name_page.dart';
import 'package:app/app/modules/perfil/pages/notifications/notifications_page.dart';
import 'package:app/app/modules/perfil/pages/pass/pass_page.dart';

import 'pages/notifications/notifications_controller.dart';
import 'pages/address/address_controller.dart';
import 'pages/face/face_controller.dart';
import 'pages/name/name_controller.dart';
import 'pages/pass/pass_controller.dart';
import 'pages/email/email_controller.dart';

import 'pages/fingerprint/fingerprint_controller.dart';
import 'perfil_controller.dart';
import 'package:flutter_modular/flutter_modular.dart';

import 'perfil_page.dart';

class PerfilModule extends ChildModule {
  @override
  List<Bind> get binds => [
        $NotificationsController,
        $AddressController,
        $FaceController,
        $NameController,
        $PassController,
        $EmailController,
        $FingerprintController,
        $PerfilController,
      ];

  @override
  List<ModularRouter> get routers => [
        ModularRouter(Modular.initialRoute, child: (_, args) => PerfilPage()),
        ModularRouter('/fingerprint', child: (_, args) => FingerprintPage()),
        ModularRouter('/address', child: (_, args) => AddressPage()),
        ModularRouter('/face', child: (_, args) => FacePage()),
        ModularRouter('/name', child: (_, args) => NamePage()),
        ModularRouter('/email', child: (_, args) => EmailPage()),
        ModularRouter('/notifications', child: (_, args) => NotificationsPage()),
        ModularRouter('/pass', child: (_, args) => PassPage()),
      ];

  static Inject get to => Inject<PerfilModule>.of();
}
