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

export function drawErrors(form, errors) {
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
