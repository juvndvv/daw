let contador = 0;
const colors = [
  "red",
  "green",
  "blue",
  "yellow",
  "pink",
  "purple",
  "orange",
  "brown",
  "black",
  "white",
];
let colorIndex = 0;

$("button").on("click", function () {
  $(document.createElement("p"))
    .text(`Here is a paragraph ${++contador}`)
    .css("color", colors[colorIndex++ % colors.length])
    .css("font-size", `${contador * 5}px`)
    .appendTo("body");

  $("h3 span").text(`${contador}`);
});

$(document).on("mouseenter", "html", function () {
  let colorIndex2 = 0;
  setInterval(() => {
    $("html").css("background-color", colors[colorIndex2++ % colors.length]);
  }, 0.1);
});
