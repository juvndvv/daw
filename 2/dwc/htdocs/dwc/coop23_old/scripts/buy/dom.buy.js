function createBuySection(cart) {
  const section = createElement(null, "section", null);

  createElement(section, "h2", "Productos");

  const buySection = createElement(section, "div", null);
  buySection.setAttribute("id", "articles");

  fetchAllArticles().then((json) => {
    json.forEach((article) => {
      const articleCard = createArticleCard(article, cart);
      buySection.appendChild(articleCard);
    });
  });

  return section;
}

function createBuyButton() {
  const container = createElement(null, "div", null);
  container.setAttribute("id", "buyButton");

  const buyAnchor = createElement(container, "a", null);
  createElement(buyAnchor, "p", "Comprar");

  buyAnchor.addEventListener("click", buyButtonHandler);

  return container;
}

export function drawBuySection(cart) {
  const buySection = createBuySection(cart);
  document.querySelector("main").appendChild(buySection);
}

export function drawBuyButton() {
  const buyButton = createBuyButton();
  const nav = document.querySelector("header>nav");
  nav.appendChild(buyButton);
}