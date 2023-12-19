export function drawCorrectForm() {
  return new Promise((resolve) => {
    // Elimina el contenido del formulario
    const formElements = document.querySelectorAll("form > *");
    formElements.forEach((element) =>
      element.classList.add("remove-animation")
    );

    // Marcar correcto en el formulario
    const form = document.querySelector("form");
    form.classList.add("correct");

    setTimeout(() => {
      resolve();
    }, 1000);
  });
}
