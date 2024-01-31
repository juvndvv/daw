// Interactividad jQuery.Puntos 18/100
// import "https://code.jquery.com/jquery-3.7.1.slim.js";

// jQuery 1. Inserta las dimensiones del viewport en el título
function dibujaDimensiones() {
  $("header > div > h3").text($(window).width() + "x" + $(window).height());
}

$(window).resize(dibujaDimensiones);
dibujaDimensiones();

// jQuery 2. Contador de acciones menú (click y mouseenter)
const $contadorMenu = $("nav > h4 > span");

$("nav > button").on("click mouseenter", () => {
  $contadorMenu.text(parseInt($contadorMenu.text()) + 1);
});

// jQuery 3.1 Contador de acciones main (click)
const $contadorMain = $("main > h2 > span");

$("main > section").on("click", () => {
  $contadorMain.text(parseInt($contadorMain.text()) + 1);
});

// jQuery 3.2 Contador de acciones main de los futuros elementos (click y mouseenter)
$(document).on("click mouseenter", "main > section", () => {
  $contadorMain.text(parseInt($contadorMain.text()) + 1);
});
