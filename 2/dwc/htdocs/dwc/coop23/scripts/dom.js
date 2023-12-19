'use strict';

import {
  registrationFormHandler,
  loginFormHandler,
  loginButtonHandler,
  registerButtonHandler,
  logoutButtonHandler,
  buyButtonSectionHandler,
  sellButtonHandler,
  categoryButtonHandler,
  postArticleHandler,
  buyButtonHandler,
  deleteButtonHandler,
} from "./handlers.js";
import { fetchAllArticles, fetchAllCategories } from "./ajax.js";
import { readUserCookies } from "./cookies.js";
import { ARTICLES } from "./consts.js";

// Create functions
function createLoginForm() {
  const loginForm = createElement(null, "form", null);
  loginForm.setAttribute("id", "loginForm");

  // Titulo
  createElement(loginForm, "h2", "Iniciar sesión");

  // Campo email
  const emailField = createElement(loginForm, "div", null);

  const emailLabel = createElement(emailField, "label", "Correo electrónico");
  emailLabel.setAttribute("for", "email");

  const emailInput = createElement(emailField, "input", null);
  emailInput.setAttribute("type", "text");
  emailInput.setAttribute("name", "email");
  emailInput.setAttribute("id", "email");
  emailInput.setAttribute("placeholder", "Correo electrónico");

  // Campo contraseña
  const passwordField = createElement(loginForm, "div", null);

  const passwordLabel = createElement(passwordField, "label", "Contraseña");
  passwordLabel.setAttribute("for", "password");

  const passwordInput = createElement(passwordField, "input", null);
  passwordInput.setAttribute("type", "password");
  passwordInput.setAttribute("name", "password");
  passwordInput.setAttribute("id", "password");
  passwordInput.setAttribute("placeholder", "Contraseña");

  // Submit
  createElement(loginForm, "button", "Iniciar sesión");

  loginForm.addEventListener("submit", loginFormHandler);

  return loginForm;
}

function createRegistrationForm() {
  const registrationForm = createElement(null, "form", null);
  registrationForm.setAttribute("id", "registrationForm");
  registrationForm.setAttribute("enctype", "multipart/form-data");

  // Titulo
  createElement(registrationForm, "h2", "Registro");

  // Campo nombre
  const nombreField = createElement(registrationForm, "div", null);

  const nombreLabel = createElement(nombreField, "label", "Nombre");
  nombreLabel.setAttribute("for", "nombre");

  const nombreInput = createElement(nombreField, "input", null);
  nombreInput.setAttribute("type", "text");
  nombreInput.setAttribute("name", "nombre");
  nombreInput.setAttribute("id", "nombre");
  nombreInput.setAttribute("placeholder", "Nombre");

  // Campo apellido
  const apellidoField = createElement(registrationForm, "div", null);

  const apellidoLabel = createElement(apellidoField, "label", "Apellido");
  apellidoLabel.setAttribute("for", "apellidos");

  const apellidoInput = createElement(apellidoField, "input", null);
  apellidoInput.setAttribute("type", "text");
  apellidoInput.setAttribute("name", "apellidos");
  apellidoInput.setAttribute("id", "apellidos");
  apellidoInput.setAttribute("placeholder", "Apellido");

  // Campo email
  const emailField = createElement(registrationForm, "div", null);

  const emailLabel = createElement(emailField, "label", "Correo electrónico");
  emailLabel.setAttribute("for", "email");

  const emailInput = createElement(emailField, "input", null);
  emailInput.setAttribute("type", "text");
  emailInput.setAttribute("name", "email");
  emailInput.setAttribute("id", "email");
  emailInput.setAttribute("placeholder", "Correo electrónico");

  // Campo contraseña
  const passwordField = createElement(registrationForm, "div", null);

  const passwordLabel = createElement(passwordField, "label", "Contraseña");
  passwordLabel.setAttribute("for", "password");

  const passwordInput = createElement(passwordField, "input", null);
  passwordInput.setAttribute("type", "password");
  passwordInput.setAttribute("name", "password");
  passwordInput.setAttribute("id", "password");
  passwordInput.setAttribute("placeholder", "Contraseña");

  // Campo repetir contraseña
  const passwordRepeatField = createElement(registrationForm, "div", null);

  const passwordRepeatLabel = createElement(
    passwordRepeatField,
    "label",
    "Repetir contraseña"
  );
  passwordRepeatLabel.setAttribute("for", "password-repeat");

  const passwordRepeatInput = createElement(passwordRepeatField, "input", null);
  passwordRepeatInput.setAttribute("type", "password");
  passwordRepeatInput.setAttribute("name", "password-repeat");
  passwordRepeatInput.setAttribute("id", "password-repeat");
  passwordRepeatInput.setAttribute("placeholder", "Repetir contraseña");

  // Campo foto
  const fotoField = createElement(registrationForm, "div", null);

  const fotoLabel = createElement(fotoField, "label", "Foto");
  fotoLabel.setAttribute("for", "foto");

  const fotoPreview = createElement(fotoField, "img", null);
  fotoPreview.setAttribute("id", "foto-preview");
  fotoPreview.setAttribute("src", "img/user.png");

  const fotoInput = createElement(fotoField, "input", null);
  fotoInput.setAttribute("accept", "image/*");
  fotoInput.setAttribute("type", "file");
  fotoInput.setAttribute("name", "foto");
  fotoInput.setAttribute("id", "foto");

  fotoInput.addEventListener("change", (event) => {
    console.log(event.target.files);
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.addEventListener("load", () => {
      fotoPreview.setAttribute("src", reader.result);
    });

    reader.readAsDataURL(file);
  });

  // Submit
  createElement(registrationForm, "button", "Registrarse");

  registrationForm.addEventListener("submit", registrationFormHandler);

  return registrationForm;
}

function createLogginButton() {
  const container = createElement(null, "div", null);
  container.setAttribute("id", "loginButton");

  const loginAnchor = createElement(container, "a", null);

  // Icono
  loginAnchor.innerHTML = `   <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24">
                                    <path fill="#ffffff"
                                        d="M5.85 17.1q1.275-.975 2.85-1.538T12 15q1.725 0 3.3.563t2.85 1.537q.875-1.025 1.363-2.325T20 12q0-3.325-2.337-5.663T12 4Q8.675 4 6.337 6.337T4 12q0 1.475.488 2.775T5.85 17.1ZM12 13q-1.475 0-2.488-1.012T8.5 9.5q0-1.475 1.012-2.488T12 6q1.475 0 2.488 1.012T15.5 9.5q0 1.475-1.012 2.488T12 13Zm0 9q-2.075 0-3.9-.788t-3.175-2.137q-1.35-1.35-2.137-3.175T2 12q0-2.075.788-3.9t2.137-3.175q1.35-1.35 3.175-2.137T12 2q2.075 0 3.9.788t3.175 2.137q1.35 1.35 2.138 3.175T22 12q0 2.075-.788 3.9t-2.137 3.175q-1.35 1.35-3.175 2.138T12 22Z" />
                                </svg>`;

  loginAnchor.addEventListener("click", loginButtonHandler);

  // Texto
  createElement(loginAnchor, "p", "Iniciar sesión");

  return container;
}

function createRegisterButton() {
  const container = createElement(null, "div", null);
  container.setAttribute("id", "registerButton");

  const registerAnchor = createElement(container, "a", null);

  // Icono
  registerAnchor.innerHTML = `<svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24">
                                    <path fill="#ffffff"
                                        d="M4 21q-.425 0-.713-.288T3 20q0-.425.288-.713T4 19h16q.425 0 .713.288T21 20q0 .425-.288.713T20 21H4Zm4-4q-.425 0-.713-.288T7 16q0-.425.288-.713T8 15h8q.425 0 .713.288T17 16q0 .425-.288.713T16 17H8Zm-4-4q-.425 0-.713-.288T3 12q0-.425.288-.713T4 11h16q.425 0 .713.288T21 12q0 .425-.288.713T20 13H4Zm4-4q-.425 0-.713-.288T7 8q0-.425.288-.713T8 7h8q.425 0 .713.288T17 8q0 .425-.288.713T16 9H8ZM4 5q-.425 0-.713-.288T3 4q0-.425.288-.713T4 3h16q.425 0 .713.288T21 4q0 .425-.288.713T20 5H4Z" />
                                </svg>`;

  registerAnchor.addEventListener("click", registerButtonHandler);

  // Texto
  createElement(registerAnchor, "p", "Registrarse");

  return container;
}

function createLogoutButton() {
  const container = createElement(null, "div", null);
  container.setAttribute("id", "logoutButton");

  const logoutAnchor = createElement(container, "a", null);

  // Icono
  logoutAnchor.innerHTML = `  <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24">
                                    <g fill="none" stroke="#ffffff" stroke-width="2">
                                        <path stroke-linecap="round"
                                            d="M8 16c0 2.828 0 4.243.879 5.121C9.757 22 11.172 22 14 22h1c2.828 0 4.243 0 5.121-.879C21 20.243 21 18.828 21 16V8c0-2.828 0-4.243-.879-5.121C19.243 2 17.828 2 15 2h-1c-2.828 0-4.243 0-5.121.879C8 3.757 8 5.172 8 8" />
                                        <path
                                            d="M8 19.5c-2.357 0-3.536 0-4.268-.732C3 18.035 3 16.857 3 14.5v-5c0-2.357 0-3.536.732-4.268C4.464 4.5 5.643 4.5 8 4.5"
                                            opacity=".5" />
                                        <path stroke-linecap="round" stroke-linejoin="round"
                                            d="M6 12h9m0 0l-2.5 2.5M15 12l-2.5-2.5" />
                                    </g>
                                </svg>`;

  logoutAnchor.addEventListener("click", logoutButtonHandler);

  return container;
}

function createBuyButton() {
  const container = createElement(null, "div", null);
  container.setAttribute("id", "buyButton");

  const buyAnchor = createElement(container, "a", null);
  createElement(buyAnchor, "p", "Comprar");

  buyAnchor.addEventListener("click", buyButtonSectionHandler);

  return container;
}

function createSellButton() {
  const container = createElement(null, "div", null);
  container.setAttribute("id", "sellButton");

  const sellAnchor = createElement(container, "a", null);
  createElement(sellAnchor, "p", "Vender");

  sellAnchor.addEventListener("click", sellButtonHandler);

  return container;
}

function createBuySection() {
  const section = createElement(null, "section", null);
  section.setAttribute("id", "buySection");

  createElement(section, "h2", "Todos los artículos");

  const articlesSection = createElement(section, "div", null);
  articlesSection.setAttribute("id", "articles");

  // Limpia los articulos
  ARTICLES.length = 0;

  // Carga los articulos
  fetchAllArticles()
    .then((json) => {
      json.forEach((article) => {
        ARTICLES.push(article);
      });
    })
    .then(() => {
      drawArticles(ARTICLES);
    });

  // Crea aside de filtros
  const aside = createElement(document.querySelector("main"), "aside", null);
  aside.setAttribute("id", "filters");

  // Carga las categorias y las dibuja
  fetchAllCategories().then((categories) => {
    categories.forEach((category) => {
      const categoryButton = createCategoryButton(category);
      if (category.id === "0") {
        categoryButton.classList.add("active");
      }
      aside.appendChild(categoryButton);
    });
  });

  return section;
}

function createCategoryButton(category) {
  const button = createElement(null, "button", category.nombre);
  button.addEventListener("click", (event) => {
    categoryButtonHandler(event, category);
  });
  return button;
}

function createArticleCard(article) {
  const card = createElement(null, "article", null);
  card.setAttribute("class", "article");

  // Imagen
  const image = createElement(card, "img", null);
  image.setAttribute("src", "php/archivos/" + article.imagen.trim());
  image.setAttribute("alt", article.nombre);

  // Nombre
  createElement(card, "h3", article.nombre);

  // Descripcion
  createElement(card, "p", article.descripcion);

  // Precio
  createElement(card, "p", article.precio);

  // Boton
  const user = readUserCookies();

  const buttonContainer = createElement(card, "div", null);
  if (user.id === article.vendedor) {
    createElement(buttonContainer, "button", "Modificar");
    const eliminarButton = createElement(buttonContainer, "button", "Eliminar");
    eliminarButton.classList.add("delete");
    eliminarButton.addEventListener("click", () => {
      deleteButtonHandler(article);
    });
  } else {
    const button = createElement(buttonContainer, "button", "Comprar");
    button.addEventListener("click", () => {
      buyButtonHandler(article);
    });
  }

  return card;
}

function createSellSection() {
  const sellForm = createElement(null, "form", null);
  sellForm.setAttribute("id", "articleForm");

  // Titulo
  createElement(sellForm, "h2", "Vender");

  // Vendedor
  const vendedorField = createElement(sellForm, "div", null);

  const vendedorLabel = createElement(vendedorField, "label", "Vendedor");
  vendedorLabel.setAttribute("for", "vendedor");

  const user = readUserCookies();
  const vendedorInput = createElement(vendedorField, "input", null);
  vendedorInput.setAttribute("type", "text");
  vendedorInput.setAttribute("name", "vendedor");
  vendedorInput.setAttribute("id", "vendedor");
  vendedorInput.setAttribute("value", `Vendedor: ${user.nombre}`);
  vendedorInput.setAttribute("readonly", true);

  // Categoria
  const categoriaField = createElement(sellForm, "div", null);

  const categoriaLabel = createElement(categoriaField, "label", "Categoria");
  categoriaLabel.setAttribute("for", "categoria");

  const categoriaInput = createElement(categoriaField, "select", null);
  categoriaInput.setAttribute("name", "categoria");
  categoriaInput.setAttribute("id", "categoria");

  // Nombre
  const nombreField = createElement(sellForm, "div", null);

  const nombreLabel = createElement(nombreField, "label", "Nombre");
  nombreLabel.setAttribute("for", "nombre");

  const nombreInput = createElement(nombreField, "input", null);
  nombreInput.setAttribute("type", "text");
  nombreInput.setAttribute("name", "nombre");
  nombreInput.setAttribute("id", "nombre");
  nombreInput.setAttribute("placeholder", "Nombre");

  // Descripcion
  const descripcionField = createElement(sellForm, "div", null);

  const descripcionLabel = createElement(
    descripcionField,
    "label",
    "Descripcion"
  );
  descripcionLabel.setAttribute("for", "descripcion");

  const descripcionInput = createElement(descripcionField, "textarea", null);
  descripcionInput.setAttribute("name", "descripcion");
  descripcionInput.setAttribute("id", "descripcion");
  descripcionInput.setAttribute("placeholder", "Descripcion");

  // Precio
  const precioField = createElement(sellForm, "div", null);

  const precioLabel = createElement(precioField, "label", "Precio");
  precioLabel.setAttribute("for", "precio");

  const precioInput = createElement(precioField, "input", null);
  precioInput.setAttribute("name", "precio");
  precioInput.setAttribute("id", "precio");
  precioInput.setAttribute("placeholder", "Precio");

  // Carga las categorias y las dibuja
  fetchAllCategories().then((categories) => {
    categories.forEach((category) => {
      if (category.id === "0") {
        return;
      }
      const option = createElement(categoriaInput, "option", category.nombre);
      option.setAttribute("value", category.id);
    });
  });

  // Imagen
  const imagenField = createElement(sellForm, "div", null);

  const imagenLabel = createElement(imagenField, "label", "Imagen");
  imagenLabel.setAttribute("for", "imagen");

  const imagenPreview = createElement(imagenField, "img", null);
  imagenPreview.setAttribute("id", "imagen-preview");
  imagenPreview.setAttribute("src", "img/user.png");

  const imagenInput = createElement(imagenField, "input", null);
  imagenInput.setAttribute("accept", "image/*");
  imagenInput.setAttribute("type", "file");
  imagenInput.setAttribute("name", "imagen");
  imagenInput.setAttribute("id", "imagen");

  imagenInput.addEventListener("change", (event) => {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.addEventListener("load", () => {
      imagenPreview.setAttribute("src", reader.result);
    });

    reader.readAsDataURL(file);
  });

  // Submit
  createElement(sellForm, "button", "Vender");

  sellForm.addEventListener("submit", postArticleHandler);

  return sellForm;
}

// Draw functions
export function drawLoginForm() {
  console.log("dibujando formulario de login");

  const container = document.querySelector("main");
  container.innerHTML = "";

  const loginForm = createLoginForm();
  container.appendChild(loginForm);

  return loginForm;
}

export function drawRegistrationForm() {
  console.log("dibujando formulario de registro");

  const container = document.querySelector("main");
  container.innerHTML = "";

  const registrationForm = createRegistrationForm();
  container.appendChild(registrationForm);

  registrationForm.addEventListener("submit", registrationFormHandler);
}

export function drawRegisterAndLoginButton() {
  console.log("dibujando botones de registro y login");

  const loginButton = createLogginButton();
  const registerButton = createRegisterButton();

  const nav = document.querySelector("header>nav");

  nav.appendChild(loginButton);
  nav.appendChild(registerButton);
}

export function drawLogoutButton() {
  console.log("dibujando boton de logout");

  const nav = document.querySelector("header>nav");

  const logoutButton = createLogoutButton();
  nav.appendChild(logoutButton);
}

export function drawBuyAndSellButton() {
  console.log("dibujando botones de compra y venta");

  const buyButton = createBuyButton();
  const sellButton = createSellButton();

  const nav = document.querySelector("header>nav");

  nav.appendChild(buyButton);
  nav.appendChild(sellButton);
}

export function drawIndex(user = null) {
  console.log("iniciando de sesion");

  // Si no hay usuario, lo leemos de las cookies
  if (!user) {
    user = readUserCookies();
  }

  // Limpia el main
  document.querySelector("main").innerHTML = "";

  // Limpiar botones de registro y login
  document.getElementById("loginButton").remove();
  document.getElementById("registerButton").remove();

  // Dibuja el boton de compra y venta
  drawBuyAndSellButton();

  // Dibuja el nombre la imagen y el nombre
  const container = createElement(null, "div", null);
  container.setAttribute("id", "username");

  createElement(container, "p", user.nombre + " " + user.apellidos);

  const image = createElement(container, "img", null);
  image.setAttribute("src", "php/socios/" + user.foto);
  image.setAttribute("alt", user.nombre);

  document.querySelector("header>nav").appendChild(container);

  // Dibuja el botón de logout
  drawLogoutButton();

  // Dibuja la sección de compra
  drawBuySection();
}

export function drawBuySection() {
  console.log("dibujando seccion de compra");

  const buySection = createBuySection();
  document.querySelector("main").appendChild(buySection);
}

export function drawSellSection() {
  console.log("dibujando seccion de venta");

  const sellSection = createSellSection();
  document.querySelector("main").appendChild(sellSection);
}

export function drawArticles(articles, page = 1) {
  // Obtiene la paginación
  const pagination = document.getElementById("pagination");

  if (!pagination) {
    // Crea aside paginacion
    const aside = createElement(
      document.querySelector("main>section"),
      "aside",
      null
    );
    aside.setAttribute("id", "pagination");

    // Crea los botones de paginacion
    const totalPages = Math.ceil(articles.length / 3);

    for (let i = 1; i <= totalPages; i++) {
      const button = createElement(aside, "button", i);

      if (i === page) {
        button.classList.add("active");
      }

      button.addEventListener("click", () => {
        drawArticles(articles, i);
      });
    }

  } else {
    // Actualiza pagina activa
    const prevActive = document.querySelector("#pagination button.active");
    prevActive.classList.remove("active");

    document
      .querySelector(`#pagination button:nth-child(${page})`)
      .classList.add("active");
  }

  // Crea los articulos
  const articlesContainer = document.getElementById("articles");

  // Limpia los articulos
  document.getElementById("articles").innerHTML = "";

  if (articles.length === 0) {
    createElement(articlesContainer, "p", "No hay articulos");
  }

  // Dibuja los articulos
  const start = (page - 1) * 3;
  const end = start + 3;

  for (let i = start; i < end; i++) {
    const article = articles[i];
    if (!article) {
      break;
    }
    const articleCard = createArticleCard(article);
    articlesContainer.appendChild(articleCard);
  }
}

export function drawCorrectForm() {
  return new Promise((resolve) => {
    // Elimina el contenido del formulario
    const formElements = document.querySelectorAll("form > *");
    formElements.forEach((element) =>
      element.classList.add("remove-animation")
    );
    console.log("Eliminando formulario");

    // Marcar correcto en el formulario
    const form = document.querySelector("form");
    form.classList.add("correct");

    setTimeout(() => {
      resolve();
    }, 1000);
  });
}

export function drawErrors(form, errors) {
  console.log("dibujando errores");

  const inputs = form.querySelectorAll("input");

  inputs.forEach((campo) => {
    // Obtenemos el error
    const error = errors[campo.name];

    // Obtenemos el padre del campo
    const contenedor = campo.parentElement;

    // Si hay error añadimos clase error, sino la quitamos
    if (error) {
      contenedor.classList.add("error");
      const pError =
        contenedor.querySelector("p") ?? createElement(contenedor, "p", null);
      pError.textContent = error;
    } else {
      contenedor.classList.remove("error");
      const pError =
        contenedor.querySelector("p") ?? createElement(contenedor, "p", null);
      pError.remove();
    }
  });
}

// Funciones auxiliares
export function createElement(parent, element, content) {
  const newElement = document.createElement(element);

  if (content) {
    newElement.innerHTML = content;
  }
  if (parent) {
    parent.appendChild(newElement);
  }

  return newElement;
}
