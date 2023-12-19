export function loginButtonHandler() {
  drawLoginForm();
}

export function loginFormHandler(event) {
  event.preventDefault();
  const loginForm = event.target;

  // Crea el objeto FormData con los datos del formulario
  const formData = new FormData(loginForm);

  // Se hace trim a los datos del formulario
  formData.forEach((value, key) => {
    formData.set(key, value.trim());
  });

  // SR = Sign in Request
  formData.append("opcion", "SR");

  // Valida los datos del formulario
  const errores = validateLoginForm(formData);

  // Si hay errores, los muestra
  if (Object.keys(errores).length > 0) {
    drawErrors(loginForm, {});
    drawErrors(loginForm, errores);
  } else {
    loginRequest(loginForm, formData);
  }
}
