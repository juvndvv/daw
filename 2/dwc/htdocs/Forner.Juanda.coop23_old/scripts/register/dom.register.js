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

export function drawRegistrationForm() {
  const container = document.querySelector("main");
  container.innerHTML = "";

  const registrationForm = createRegistrationForm();
  container.appendChild(registrationForm);

  registrationForm.addEventListener("submit", registrationFormHandler);
}

export function drawRegisterButton() {
  const registerButton = createRegisterButton();
  const nav = document.querySelector("header>nav");
  nav.appendChild(registerButton);
}
