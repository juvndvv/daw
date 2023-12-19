export function validateRegistrationForm(formData) {
  const errores = {};

  validateEmail(formData, errores);
  validateCorrectPasword(formData, errores);
  validateMadatory(formData, errores);

  return errores;
}

export function validateLoginForm(formData) {
  let errores = {};

  validateEmail(formData, errores);
  validateMadatory(formData, errores);

  return errores;
}

export function validateSellForm(formData) {
  // TODO: implementar validación de formulario de venta
}

// Funciones auxiliares
function validateMadatory(formData, errors) {
  formData.forEach((value, key) => {
    if (value === "") {
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
