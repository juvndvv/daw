// child nodes
console.log(document.body.childNodes[3].childNodes[1]);

// Get element by tag name
const h2Element = document.getElementsByTagName("h2");

console.log(h2Element[0]);
console.log(h2Element.length);

[...h2Element].forEach((e) => {
  console.log(e);
});

// Get element by class name
const reservas = document.getElementsByClassName("reservas");

[...reservas].forEach((reserva) => {
  console.log(reserva);
});

// Get element by id
console.log(document.getElementById("asturias"));

// Query selector all
console.log(document.querySelectorAll("li").length);
console.log(document.querySelectorAll("#catalunya li").length);
console.log(document.querySelectorAll(".agua").length);
console.log(document.querySelectorAll("#catalunya > .agua").length);

// Query selector
console.log(document.querySelector("li"));

// innerHTML
console.log(document.querySelector("#andalucia").innerHTML);

// textContent
console.log(document.querySelector("#andalucia").textContent);

// getAttribute
console.log(document.querySelector("script").getAttribute("src"));

// hasAttribute
if (document.querySelector("a").hasAttribute("target")) {
  console.log("El primer enlace tiene el atributo target");
} else {
  console.log("El primer enlace no tiene el atributo target");
}

// class list
console.log(document.querySelector("#andalucia").classList);
if (document.querySelector("#andalucia").classList.contains("pepe")) {
  document.querySelector("#andalucia").classList.remove("pepe");
}
console.log(document.querySelector("#andalucia").classList);
