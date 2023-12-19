import {
  drawErrors,
  drawRegistrationForm,
  drawLoginForm,
  drawRegisterAndLoginButton,
  drawBuySection,
  drawSellSection,
  drawArticles,
  drawCorrectForm,
} from "./dom.js";
import {
  validateRegistrationForm,
  validateLoginForm,
  validatePostArticleForm,
} from "./validations.js";
import { deleteUserCookies, readUserCookies } from "./cookies.js";
import {
  buyArticle,
  deleteArticle,
  loginRequest,
  postArticle,
  registerRequest,
} from "./ajax.js";
import { ARTICLES } from "./consts.js";

// Handlers botones
export function registerButtonHandler() {
  drawRegistrationForm();
}

export function loginButtonHandler() {
  drawLoginForm();
}

export function logoutButtonHandler() {
  deleteUserCookies();

  ARTICLES.length = 0;

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

export function buyButtonSectionHandler() {
  console.log("buy button pressed");

  // Limpia el main
  document.querySelector("main").innerHTML = "";

  // Dibuja la seccion de compra
  drawBuySection();
}

export function sellButtonHandler() {
  console.log("sell button pressed");

  // Limpia el main
  document.querySelector("main").innerHTML = "";

  // Dibuja la seccion de venta
  drawSellSection();
}

export function buyButtonHandler(article) {
  // Crea el objeto FormData con los datos del formulario
  const formData = new FormData();
  formData.set("idarticulo", article.id);

  buyArticle(formData)
    .then(() => {
      alert("Articulo comprado correctamente");
    })
    .then(buyButtonSectionHandler);
}

export function deleteButtonHandler(article) {
  // Pregunta al usuario si quiere eliminar el articulo
  const confirmDelete = confirm("¿Estas seguro de que quieres eliminarlo?");
  if (!confirmDelete) {
    return;
  }

  // Crea el objeto FormData con los datos del formulario
  const formData = new FormData();
  formData.set("idarticulo", article.id);

  // Elimina el articulo
  deleteArticle(formData)
    .then(buyButtonSectionHandler);
}

export function categoryButtonHandler(event, category) {
  if (event.target.classList.contains("active")) {
    return;
  }

  // Quita la clase active del boton activo
  document.querySelector(".active").classList.remove("active");

  // Pone la clase active al boton pulsado
  event.target.classList.add("active");

  if (category.id === "0") {
    drawArticles(ARTICLES);
    document.querySelector("main>section>h2").textContent =
      "Todos los artículos";
    return;
  }

  const filteredArticles = ARTICLES.filter((article) => {
    return article.categoria === category.id;
  });

  drawArticles(filteredArticles);

  document.querySelector("main>section>h2").textContent = category.nombre;
}

// Handlers formularios
export function registrationFormHandler(event) {
  event.preventDefault();
  const registerForm = event.target;

  // Crea el objeto FormData con los datos del formulario
  const formData = new FormData(registerForm);

  // Muestra los datos del formulario
  formData.forEach((value, key) => {
    console.log(key, value);
  });

  // Quita de form data foto si no se ha subido
  console.log(formData.get("foto").name);
  if (formData.get("foto").name === "") {
    formData.delete("foto");
  }

  // Valida los datos del formulario
  const errores = validateRegistrationForm(formData);

  // Si hay errores, los muestra
  if (Object.keys(errores).length > 0) {
    drawErrors(registerForm, {});
    drawErrors(registerForm, errores);
  } else {
    registerRequest(registerForm, formData);
  }
}

export function loginFormHandler(event) {
  event.preventDefault();
  const loginForm = event.target;

  // Crea el objeto FormData con los datos del formulario
  const formData = new FormData(loginForm);
  formData.append("opcion", "SR");

  // Valida los datos del formulario
  const errores = validateLoginForm(formData);

  console.log("errores:", errores);
  // Si hay errores, los muestra
  if (Object.keys(errores).length > 0) {
    drawErrors(loginForm, {});
    drawErrors(loginForm, errores);
  } else {
    loginRequest(loginForm, formData);
  }
}

export function postArticleHandler(event) {
  event.preventDefault();
  const postArticleForm = event.target;

  // Crea el objeto FormData con los datos del formulario
  const formData = new FormData(postArticleForm);
  formData.set("vendedor", readUserCookies().id);

  // Valida los datos del formulario
  const errores = validatePostArticleForm(formData);

  // Si hay errores, los muestra
  if (Object.keys(errores).length > 0) {
    drawErrors(postArticleForm, {});
    drawErrors(postArticleForm, errores);
  } else {
    postArticle(formData).then(drawCorrectForm).then(buyButtonSectionHandler);
  }
}
