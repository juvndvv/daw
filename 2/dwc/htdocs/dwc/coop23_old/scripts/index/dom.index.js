export function drawIndex(user = null) {
  // Lee las cookies del usuario
  if (!user) {
    user = readUserCookies();
  }

  // Crea el carrito TODO: implementar leer cookies del carrito
  const cart = [];

  // Limpia el main
  document.querySelector("main").innerHTML = "";

  // Limpiar botones de registro y login
  document.getElementById("loginButton").remove();
  document.getElementById("registerButton").remove();

  // Dibuja el boton de compra y venta
  drawBuyAndSellButton();

  // Dibuja la información del usuario
  drawUserInfo(user);

  // Dibuja el carrito
  drawCart(cart);

  // Dibuja el botón de logout
  drawLogoutButton();

  // Dibuja la sección de compra
  drawBuySection();
}
