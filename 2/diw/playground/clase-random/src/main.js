import "https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js";

// Boton 1
export function game() {
  // Creacion de la bola
  const $bola = $("<div class='bola'></div>");
  $("body").append($bola);

  // Creacion de del alien
  const $alien = $(
    "<div class='alien'><div class='plato'></div><div class='cabeza'></div></div>"
  );
  $("main").append($alien);

  // Listener para el raton
  $(document).mousemove(function (e) {
    // retrasar la bola
    setTimeout(() => {
      $(".bola").css({ left: e.pageX - 25, top: e.pageY - 25 });
      $(".bala").css({ left: e.pageX - 7.5, top: e.pageY - 2.5 });
    }, 20);
  });

  $("nav").on("mouseenter", () => {
    $(".bola").css({ display: "none" });
  });

  $("nav").on("mouseleave", () => {
    $(".bola").css({ display: "block" });
  });

  // Disparo
  const shoot = (e) => {
    let $bala = $("<div class='bala'></div>");
    $("body").append($bala);
    $bala.css({ left: e.pageX - 7.5, top: e.pageY - 2.5 });
    $bala.animate({ left: e.pageX - 7.5, top: 0 }, 500, function () {
      $bala.css({ top: "100vh" });
    });

    return $bala;
  };

  $(document).click((e) => {
    const $bala = shoot(e);
    setTimeout(() => {
      $bala.remove();
    }, 500);
  });
}

const $button1 = $("nav > button:nth-of-type(1)");

$button1.on("click", () => {
  game();
  // Desactivar boton
  $button1.attr("disabled", "disabled");
});

// Boton 2
const $button2 = $("nav > button:nth-of-type(2)");

$button2.on("click", () => {
  $("button[disabled=disabled]").removeAttr("disabled");
  // Eliminar alien
  $(".alien").remove();
});
