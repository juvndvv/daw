// Interactividad jQuery.Puntos 18/100
import "https://code.jquery.com/jquery-3.7.1.min.js";

// jQuery 1. Inserta las dimensiones del viewport en el título
function dibujaDimensiones() {
  $("header > div > h3").text($(window).width() + "x" + $(window).height());
}

$(window).resize(dibujaDimensiones);
dibujaDimensiones();

// jQuery 2. Contador de acciones menú (click y mouseenter)
let contadorMenu = 0;
const $contadorMenu = $("nav > h4 > span");
$contadorMenu.text(contadorMenu);

$("nav > button").on("click mouseenter", () => {
  $contadorMenu.text(++contadorMenu);
});

// jQuery 3.1 Contador de acciones main (click)
let contadorMain = 0;
const $contadorMain = $("main > h2 > span");
$contadorMain.text(contadorMain);

$("main > section").on("click", () => {
  $contadorMain.text(++contadorMain);
});

// jQuery 3.2 Contador de acciones main de los futuros elementos (click y mouseenter)
$("main > section").on("click mouseenter", "> *", () => {
  $contadorMain.text(++contadorMain);
});
