import { drawIndex, drawRegisterAndLoginButton } from "./dom.js";
import { userCookieExist } from "./cookies.js";

drawRegisterAndLoginButton();

if (userCookieExist()) {
  drawIndex();
}
