import "https://code.jquery.com/jquery-3.7.1.min.js";
// Mantén todo el código jQuery de la fase 1 en el archivo AF7.1.fase1.js
// Modifícalo en función de los comentarios de la retroalimnentacion

// escribe aquí TODO lo nuevo requerido en esta fase
const DURATION = 500;

$(function () {
  // funciones varias
  //Para BOTÓN 4... ejemplo basado en Seccion_4_5_1_show_hide.html, pero con fadeIn y fadeOut
  //  this, en este caso, hace referencia a los span del título que aparece/desaparece
  // Para section 4

  // BOTÓN 6... Captura lo que se escribe en el input, y lo añade a una lista ordenada en el item 7 del main
  // Las dos acciones que enviarán el dato a la lista del item 7 son:
  //1. Pulsando tecla ENTER
  //2. Pulsando el botón 6

  function button6() {
    let $input = $("main > section:nth-of-type(6) > input").val();
    if ($input !== "") {
      $("main > section:nth-of-type(7) > ul").append("<li>" + $input + "</li>");
    }
  }

  // jQuery 1
  // carga inicial de la primera imagen (clonada) de la cabecera en el tercer item del main-GRID//

  $("header > img:nth-of-type(1)")
    .clone()
    .appendTo("main > section:nth-of-type(2)");

  // jQuery eventos de ratón sobre los BOTONES menu
  //1. al pulsar botón del ratón se aplique una claseque cambie el color y el tamaño de letra
  //2. al liberar el botón del ratón se restaure los valores iniciales

  $("button").on("mousedown", function () {
    $(this).addClass("buttonDown");
  });

  $("button").on("mouseup", function () {
    $(this).removeClass("buttonDown");
  });

  // Desarrolla una función única para TODOS LOS BOTONES que detecte:
  //1. qué botón se ha pulsado, .index()

  $("button").on("click", function () {
    let index = $(this).index();
    console.log("Botón pulsado: " + index);

    function button1() {
      $("main > section:nth-of-type(1)").append("<p>Texto añadido</p>");
    }

    function button2() {
      $("main > section:nth-of-type(2) > img").animate(
        {
          height: "toggle",
          opacity: "toggle",
        },
        DURATION
      );
    }

    function button3() {
      $("main > section:nth-of-type(3) > div:nth-of-type(2)").slideDown(
        DURATION
      );
    }

    function button4() {
      const $words = $("main > section:nth-of-type(4) > h2 > span");
      for (let i = 0 - 1; i < $words.length; i++) {
        $($words[i])
          .delay(($words.length - i) * DURATION)
          .fadeOut(DURATION);
      }
    }

    function button5() {
      $("main > section:nth-of-type(5) > h2").slideUp(DURATION);
      $("main > section:nth-of-type(5) > section")
        .delay(DURATION)
        .slideDown(DURATION);
    }

    function button7() {
      $("main > section:nth-of-type(7) > ul > li").remove();
    }

    function button8() {
      $("main > section:nth-of-type(8) > ol > li").appendTo(
        "main > section:nth-of-type(7) > ul"
      );
    }

    function button9() {
      $("main > section:nth-of-type(9) > div")
        .animate({ top: "-=20px", rotate: "+=90deg" }, 200, "swing")
        .animate({ top: "+=40px", rotate: "-=90deg" }, 400, "swing")
        .animate({ top: "-=20px", rotate: "+=90deg" }, 200, "swing");
    }

    // En función del boton pulsado, se ejecutará una función u otra
    switch (index) {
      case 1:
        button1();
        break;
      case 2:
        button2();
        break;
      case 3:
        button3();
        break;
      case 4:
        button4();
        break;
      case 5:
        button5();
        break;
      case 6:
        button6();
        break;
      case 7:
        button7();
        break;
      case 8:
        button8();
        break;
      case 9:
        button9();
        break;
    }
  });

  // jQuery SECTIONS ****************************************

  // Section 1
  // Al pulsar sobre cualquiera de los párrafos añadidos, se vaciará el contenido del item grid
  $("main > section:nth-of-type(1)").on("click", "p", function () {
    $("main > section:nth-of-type(1) > p").remove();
  });

  // Section 3 ... A. Cada vez que se pulse sobre esta section, aparecerá una cortina blanca semitransparente sobre esta section
  $("main > section:nth-of-type(3)").on("click", function () {
    $("main > section:nth-of-type(3) > div:nth-of-type(2)").slideDown(DURATION);
  });

  // Section 3 ... B. Al entrar en la 3er. section, desaparecerá la cortina con un fade
  $("main > section:nth-of-type(3)").on("mouseenter", function () {
    $("main > section:nth-of-type(3) > div:nth-of-type(2)").fadeOut(DURATION);
  });

  // Section 4 ...
  //click en la 4ª. section, section:nth-of-type(4) aparecerá de nuevo la frase desde la primera palabra
  $("main > section:nth-of-type(4)").on("click", function () {
    const $words = $("main > section:nth-of-type(4) > h2 > span");
    for (let i = 0; i < $words.length; i++) {
      $($words[i])
        .delay(i * DURATION)
        .fadeIn(DURATION);
    }
  });

  // Section 5... click sobre el h4
  $("main > section:nth-of-type(5) > section > h4").on("click", function () {
    $(this).parent().slideUp(DURATION);
    $("main > section:nth-of-type(5) > h2").delay(DURATION).slideDown(DURATION);
  });

  // Section 6...  Tecla "Enter" dentro del input
  $("main > section:nth-of-type(6) > input").on("keypress", function (e) {
    if (e.which === 13) {
      button6();
    }
  });

  // Section 7... doble click sobre un item de la lista,
  // 1. lo añade (appendTo) a la lista de la sección 8
  // 2. lo borra de esta lista
  $("main > section:nth-of-type(7) > ul").on("dblclick", "li", function () {
    $(this).appendTo("main > section:nth-of-type(8) > ol");
  });

  // Section 8...... doble click sobre un item de la lista,
  // 1. lo añade a la lista de la sección 7
  // 2. lo borra de esta lista
  $("main > section:nth-of-type(8) > ol").on("dblclick", "li", function () {
    $(this).appendTo("main > section:nth-of-type(7) > ul");
  });

  // ratón entra en sección 7 y 8
  // aparece footer correspondiente
  $("main > section:nth-of-type(7)").on("mouseenter", function () {
    $("main > section:nth-of-type(7) > footer").slideDown(DURATION);
  });

  $("main > section:nth-of-type(7)").on("mouseleave", function () {
    $("main > section:nth-of-type(7) > footer").slideUp(DURATION);
  });

  $("main > section:nth-of-type(8)").on("mouseenter", function () {
    $("main > section:nth-of-type(8) > footer").slideToggle(DURATION);
  });

  $("main > section:nth-of-type(8)").on("mouseleave", function () {
    $("main > section:nth-of-type(8) > footer").slideToggle(DURATION);
  });

  // BOTÓN 9... Interacción a voluntad usando la función animate()
  // de varias propiedades y elementos html, con reinicio y parada
});
