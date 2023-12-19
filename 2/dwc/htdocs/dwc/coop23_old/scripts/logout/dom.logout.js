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

export function drawLogoutButton() {
    const nav = document.querySelector("header>nav");
  
    const logoutButton = createLogoutButton();
    nav.appendChild(logoutButton);
  }