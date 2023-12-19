import { drawRegisterButton } from "./register/dom.register.js";
import { drawLoginButton } from "./login/dom.login.js";
import { drawIndex } from "./index/dom.index.js";

import { userCookieExist } from "./user/cookies.user.js";

drawRegisterButton();
drawLoginButton();

// Comprobar si el usuario esta logueado con cookies
if (userCookieExist()) {
  drawIndex();
}
