export function registerButtonHandler() {
  drawRegistrationForm();
}

export function registrationFormHandler(event) {
  event.preventDefault();
  const registerForm = event.target;

  // Crea el objeto FormData con los datos del formulario
  const formData = new FormData(registerForm);

  // Se hace trim a los datos del formulario
  formData.forEach((value, key) => {
    if (key === "foto") return;
    formData.set(key, value.trim());
  });

  // Elimina le campo foto si no se ha seleccionado ninguna
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
