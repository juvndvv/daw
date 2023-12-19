function createArticleCard(article, cart) {
  const card = createElement(null, "article", null);
  card.setAttribute("class", "article");

  // Imagen
  const image = createElement(card, "img", null);
  image.setAttribute("src", "img/" + article.imagen.trim());
  image.setAttribute("alt", article.nombre);

  // Nombre
  createElement(card, "h3", article.nombre);

  // Descripcion
  createElement(card, "p", article.descripcion);

  // Precio
  createElement(card, "p", article.precio);

  // Boton
  const button = createElement(card, "button", "Comprar");
  button.setAttribute("data-id", article.id);

  // TODO: implementar añadir al carrito

  return card;
}