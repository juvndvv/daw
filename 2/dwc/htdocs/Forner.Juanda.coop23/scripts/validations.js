'use strict';

export function validateRegistrationForm(formData) {
  const errores = {};

  validateEmail(formData, errores);
  validateCorrectPasword(formData, errores);
  validateName(formData, errores);
  validateSurname(formData, errores);
  validatePassword(formData, errores);
  validateMadatory(formData, errores);

  return errores;
}

export function validateLoginForm(formData) {
  let errores = {};

  validateEmail(formData, errores);
  validatePassword(formData, errores);
  validateMadatory(formData, errores);

  return errores;
}

export function validatePostArticleForm(formData) {
  let errores = {};

  validateArticleName(formData, errores);
  validateArticlePhoto(formData, errores);
  validatePrice(formData, errores);
  validateMadatory(formData, errores);

  return errores;
}

// Funciones auxiliares
function validatePassword(formData, errors) {
  const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{5,10}$/;
  const password = formData.get("password");

  let correctPasswd = true;
  if (!passwordRegex.test(password)) {
    errors["password"] =
      "La contraseña debe tener entre 5 y 10 caracteres alfanumericos, un digito y un caracter";
    correctPasswd = false;
  }

  if (correctPasswd && formData.get("password-repeat")) {
    const passwordRepeat = formData.get("password-repeat");
    if (!passwordRegex.test(passwordRepeat)) {
      errors["password-repeat"] =
        "La contraseña debe tener entre 5 y 10 caracteres alfanumericos, un digito y un caracter";
    }
  }
}

function validateArticleName(formData, errors) {
  const nameRegex = /^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜñÑ\s]{4,30}$/;
  const name = formData.get("nombre");

  if (!nameRegex.test(name)) {
    errors["nombre"] =
      "El nombre debe tener entre 4 y 30 caracteres alfanumericos";
  }
}

function validatePrice(formData, errors) {
  const priceRegex = /^\d{1,4}(\.\d{1,2})?$/;
  const price = formData.get("precio");

  if (!priceRegex.test(price)) {
    errors["precio"] = "El precio debe ser un número positivo con dos decimales";
  }
}

function validateArticlePhoto(formData, errors) {
  if (formData.get("imagen").name === "") {
    errors["imagen"] = "Debe subir una foto";
  }
}

function validateName(formData, errors) {
  const nameRegex = /^[a-zA-Z0-9\s]{2,20}$/;
  const name = formData.get("nombre");

  if (!nameRegex.test(name)) {
    errors["nombre"] =
      "El nombre debe tener entre 2 y 20 caracteres alfanumericos";
  }
}

function validateSurname(formData, errors) {
  const surnameRegex = /^[a-zA-Z0-9\s]{2,30}$/;
  const surname = formData.get("apellidos");

  if (!surnameRegex.test(surname)) {
    errors["apellidos"] =
      "El apellido debe tener entre 2 y 30 caracteres alfanumericos";
  }
}

function validateMadatory(formData, errors) {
  formData.forEach((value, key) => {
    if (value === "") {
      console.log(key, value);
      errors[key] = "El campo no puede estar vacío";
    }
  });
}

function validateEmail(formData, errors) {
  const emailRegex = /\S+@\S+\.\S+/;
  const email = formData.get("email");

  if (!emailRegex.test(email)) {
    errors["email"] = "El email no es válido";
  }
}

function validateCorrectPasword(formData, errors) {
  const password = formData.get("password");
  const password2 = formData.get("password-repeat");

  if (password !== password2) {
    errors["password"] = "Las contraseñas no coinciden";
  }
}
