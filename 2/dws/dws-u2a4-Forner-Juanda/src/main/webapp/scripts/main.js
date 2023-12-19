
// Funcionalidad de botones del formulario
var secciones = document.querySelectorAll("form > section");
var activa = 0;

secciones[activa].classList.add("activa");

var previousBtn = document.getElementById("previous");
var nextBtn = document.getElementById("next");

function prevPage() {
    secciones[activa].classList.remove("activa");
    activa--;
    secciones[activa].className = "activa";
}

function nextPage() {
    secciones[activa].classList.remove("activa");
    activa++;
    secciones[activa].className = "activa";
}

function checkPrevButton() {
    if (activa === 0) {
        previousBtn.classList.add("limit");
    } else {
        previousBtn.classList.remove("limit");
    }
} 

function checkNextButton() {
    console.log(activa);
    console.log(secciones.length -1);
    if (activa === secciones.length - 1) {
        nextBtn.classList.add("limit");
    } else {
        nextBtn.classList.remove("limit");
    }
}

previousBtn.addEventListener("click", () => {
    prevPage();
    checkNextButton();
    checkPrevButton();
});

nextBtn.addEventListener("click", () => {
    nextPage();
    checkNextButton();
    checkPrevButton();
});