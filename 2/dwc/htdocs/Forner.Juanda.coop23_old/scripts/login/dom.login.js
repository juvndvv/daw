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

export function drawLoginForm() {
  const container = document.querySelector("main");
  container.innerHTML = "";

  const loginForm = createLoginForm();
  container.appendChild(loginForm);

  return loginForm;
}

export function drawLoginButton() {
  const loginButton = createLogginButton();
  const nav = document.querySelector("header>nav");
  nav.appendChild(loginButton);
}
