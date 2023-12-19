export function logoutButtonHandler() {
  deleteUserCookies();

  // Elimina el boton de logout y el nombre de usuario
  document.getElementById("logoutButton").remove();
  document.getElementById("username").remove();

  // Limpia el main
  document.querySelector("main").innerHTML = "";

  // Elimina los botones de compra y venta
  document.getElementById("buyButton").remove();
  document.getElementById("sellButton").remove();

  // Dibuja los botones de registro y login
  drawRegisterAndLoginButton();
}

export function buyButtonHandler() {
  // Limpia el main
  document.querySelector("main").innerHTML = "";

  // Dibuja la seccion de compra
  drawBuySection();
}
