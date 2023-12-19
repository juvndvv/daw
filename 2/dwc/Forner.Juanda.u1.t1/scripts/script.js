
// Juan Daniel Forner Garriga. 2 DAW M
// Practica 1

const main = document.querySelector("main");

// Crear e insertar el nav antes del main dentro del body
const nav = document.createElement("nav");
document.querySelector("body")
    .insertBefore(nav, main);

// Crear e insertar botones. Elemento, atributo, contenido
const btnSaludar = document.createElement("button");
btnSaludar.setAttribute("id", "saludar");
btnSaludar.innerText = "Saludar";
nav.appendChild(btnSaludar);

const btnPreguntar = document.createElement("button");
btnPreguntar.setAttribute("id", "preguntar");
btnPreguntar.innerText = "Preguntar";
nav.appendChild(btnPreguntar);

// Listeners
btnSaludar.addEventListener("click", () => {
    const nombre = prompt("Hola, ¿como te llamas?");

    if (nombre) {
        const output = document.createElement("p");
        output.innerText = `Hola ${nombre}!`;

        main.innerHTML = "";
        main.appendChild(output);
    }
});

btnPreguntar.addEventListener("click", () => {
    const aceptado = confirm("¿Voy a aprobar el modulo de cliente?");

    const output = document.createElement("p");
    if (aceptado) {
        output.innerText = "Apruebas 100% seguro";
    } else {
        output.innerText = "No apruebas ni pagando"
    }

    main.innerHTML = "";
    main.appendChild(output);
});