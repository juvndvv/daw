function createSellSection() {
  const section = createElement(null, "section", null);

  createElement(section, "h2", "Vender");

  const sellForm = createElement(section, "form", null);
  sellForm.setAttribute("id", "articleForm");

  // TODO: introducir aqui los campos del formulario de venta

  return section;
}

function createSellButton() {
  const container = createElement(null, "div", null);
  container.setAttribute("id", "sellButton");

  const sellAnchor = createElement(container, "a", null);
  createElement(sellAnchor, "p", "Vender");

  sellAnchor.addEventListener("click", sellButtonHandler);

  return container;
}

export function drawSellSection() {
  const sellSection = createSellSection();
  document.querySelector("main").appendChild(sellSection);
}

export function drawSellButton() {
  const sellButton = createSellButton();
  const nav = document.querySelector("header>nav");
  nav.appendChild(sellButton);
}
