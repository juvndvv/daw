export function sellFormHandler(event) {
  event.preventDefault();

  const sellForm = event.target;

  // Crea el objeto FormData con los datos del formulario
  const formData = new FormData(sellForm);

  // Se hace trim a los datos del formulario
  formData.forEach((value, key) => {
    if (key === "foto") return;
    formData.set(key, value.trim());
  });

  // Valida los datos del formulario
  const errores = validateSellForm(formData);

  // Si hay errores, los muestra
  if (Object.keys(errores).length > 0) {
    drawErrors(sellForm, {});
    drawErrors(sellForm, errores);
  } else {
    postArticle(sellForm, formData);
  }
}

export function sellButtonHandler() {
    // Limpia el main
    document.querySelector("main").innerHTML = "";
  
    // Dibuja la seccion de venta
    drawSellSection();
  }