export function registerRequest(registerForm, formData) {
  fetchUser(formData)
    // Si el usuario existe
    .then(() => {
      drawErrors(registerForm, { email: "El email ya existe" });
    })

    // Si el usuario no existe
    .catch(() => {
      postUser(formData)
        .then(drawCorrectForm)
        .then(drawLoginForm)
        .catch(() => {
          drawErrors(registerForm, {
            "password-repeat": "Ocurrio un error en el servidor",
          });
        });
    });
}
