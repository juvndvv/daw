function drawUserInfo(user) {
  // Dibuja el nombre la imagen y el nombre
  const container = createElement(null, "div", null);
  container.setAttribute("id", "username");

  createElement(container, "p", user.nombre + " " + user.apellidos);

  const image = createElement(container, "img", null);
  image.setAttribute("src", "php/socios/" + user.foto);
  image.setAttribute("alt", user.nombre);

  document.querySelector("header>nav").appendChild(container);
}
