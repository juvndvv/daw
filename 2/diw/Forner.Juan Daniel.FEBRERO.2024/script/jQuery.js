// AÑADE el código que creas NECESARIO de forma óptima

$(document).ready(function(){


// fecha formato MM AAAA añadiendo h3 al footer
/* con el método jQuery adecuado, asignar la fecha obtenida con la función Date() de javascript a un elemento HTML que has de incluir en el footer para tal efecto */
var meses = new Array ("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
var diaSem = new Array ("Domingo", "Lunes","Martes","Miércoles","Jueves","Viernes","Sábado");

var f = new Date();  //javascript

// fecha corta que se carga al inicio
var mesAnyo= meses[f.getMonth()] +  " " + f.getFullYear();
// fecha larga para la interaccion 
var fechaCompleta= diaSem[f.getDay()] +  ", " + f.getDate() + " de " + meses[f.getMonth()] +  " de " + f.getFullYear();


//***************       jQuery  *********************** */

/***************** FECHA INICIAL ******************** */
//1.	1 punto. Incluir la Fecha con un  h3 en el footer con formato MMMM AAAA. (fecha corta)

$("footer").append("<h3>" + mesAnyo + "</h3>");;

/***************** INTERACCIÓN FECHA corta/larga ******************** */
// 2.	4 puntos. 
$("footer h3").on("click", function(){
    $(this).text(fechaCompleta);
});


// Captura del evento sobre el icono "hamburguesa" para mostrar u ocultar el menú  en versión "mobile "
// 3.	4 puntos. 

$("nav > div").on("click", function(){
    $("nav ul").toggle();
});

// Acciones sobre figure
// •	5 puntos. interacción al situarse sobre "figure" y 

// •	5 puntos. interacción al salir

$("figure").on("mouseenter", function(){
    $(this).addClass("flexCentered");
    // desde abajo al centro
    $(this).find("figcaption")
    .animate({top: "0"}, 400);
});

$("figure").on("mouseleave", function(){
    $(this).find("figcaption").animate({top: "100%"}, 400).delay(400).removeClass("flexCentered");
}
);

// Acciones sobre figcaption. 
// Usar funciones transversales del DOM: padre, hermanos, siguiente, etc. 


// •	4 puntos. Evento click sobre figcaption. Se ha de obtener el contenido del h3 de esa section y se ha de asignar al h1 del header
$("figcaption").on("click", function(){
    console.log($(this).parent().siblings("h3").text());
    $("header h1").text($(this).parent().siblings("h3").text());


}
);

// •	4 puntos. Cuando se salga del figcaption, se repondrá el título inicial de la página
$("figcaption").on("mouseleave", function(){
    $("header h1").text("Diseño Interfaces WEB");
}
);
});