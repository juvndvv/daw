function crearTablaInnerHTML() {
    let contador = 0;
    let tabla = "<table>";
    for (let i = 0; i < 10; i++) {
        tabla += "<tr>";
        for (let j = 0; j < 10; j++) {
            tabla += "<td>" + contador + "</td>";
            contador++;
        }
        tabla += "</tr>";
    }
    tabla += "</table>";
    document.querySelector("main").innerHTML = tabla;
}

function crearTablaCreateElement() {
    let tabla = document.createElement("table");
    let contador = 0;
    for (let i = 0; i < 10; i++) {
        let fila = document.createElement("tr");
        for (let j = 0; j < 10; j++) {
            let celda = document.createElement("td");
            celda.textContent = contador;
            fila.appendChild(celda);
            contador++;
        }
        tabla.appendChild(fila);
    }
    document.querySelector("main").appendChild(tabla);
}

function efectoRollOver() {
    document.querySelectorAll("td").forEach((td) => {
        td.addEventListener("mouseover", () => {
            td.style.backgroundColor = "green"
            td.style.cursor = "pointer"
        });
        td.addEventListener("mouseout", () => {
            td.style.backgroundColor = ""
        });
    });
}

window.addEventListener("load", () => {
    crearTablaCreateElement();
    efectoRollOver();
});