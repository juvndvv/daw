'use strict';

// Juan Daniel Forner
// Practica 2

const main = document.querySelector("main");

const resultSection = document.createElement("section");
main.appendChild(resultSection);

const nav = document.createElement("nav");
document.querySelector("body").insertBefore(nav, main);

const insertaBoton = (id, innerText) => {
    let btn = document.createElement("button");
    btn.setAttribute("id", id);
    btn.innerText = innerText;
    nav.appendChild(btn);
    return btn;
}
// Pestaña operadores
const btnOperadores = insertaBoton("operadores", "Operadores");

btnOperadores.addEventListener("click", () => {
    resultSection.className = "";

    // Pedir numeros
    let a = parseInt(prompt("Introduce un numero:"));
    let b = parseInt(prompt("Introduce otro numero"));

    // Si son numeros validos mostramos numeros suma y resta
    if (!isNaN(a) && !isNaN(b)) {
        let numerosP = document.createElement("p");
        numerosP.innerText = `Los numeros introducidos son: ${a} y ${b}`;

        let sumaP = document.createElement("p");
        sumaP.innerText = `La suma de ${a} y ${b} es ${a + b}`;

        let restaP = document.createElement("p");
        restaP.innerText = `La resta de ${a} y ${b} es ${a - b}`;

        let multiplicacionP = document.createElement("p");
        multiplicacionP.innerText = `La multiplicacion de ${a} y ${b} es ${a * b}`;

        let divisionP = document.createElement("p");
        divisionP.innerText = `La division de ${a} y ${b} es ${a / b}`;

        let moduloP = document.createElement("p");
        moduloP.innerText = `El modulo de ${a} y ${b} es ${a % b}`;

        let incrementoPA = document.createElement("p");
        incrementoPA.innerText = `El incremento de ${a} es ${a + 1}`;

        let incrementoPB = document.createElement("p");
        incrementoPB.innerText = `El incremento de ${b} es ${b + 1}`;

        let decrementoPA = document.createElement("p");
        decrementoPA.innerText = `El decremento de ${a} es ${a - 1}`;

        let decrementoPB = document.createElement("p");
        decrementoPB.innerText = `El decremento de ${b} es ${b - 1}`;

        resultSection.innerHTML = "";
        resultSection.appendChild(numerosP);
        resultSection.appendChild(sumaP);
        resultSection.appendChild(restaP);
        resultSection.appendChild(multiplicacionP);
        resultSection.appendChild(divisionP);
        resultSection.appendChild(moduloP);
        resultSection.appendChild(incrementoPA);
        resultSection.appendChild(incrementoPB);
        resultSection.appendChild(decrementoPA);
        resultSection.appendChild(decrementoPB);

    } else {
        resultSection.innerHTML = "<p>Uno de los numeros introducidos no es correcto</p>"
    }
});

// Pestaña mayor
const btnMayor = insertaBoton("mayor", "Mayor");

btnMayor.addEventListener("click", () => {
    resultSection.className = "";

    let a = parseInt(prompt("Introduce un primer numero:"));
    let b = parseInt(prompt("Introduce un segundo numero"));
    let c = parseInt(prompt("Introduce un tercer numero:"));

    if (!isNaN(a) && !isNaN(b) && !isNaN(c)) {
        let numerosP = document.createElement("p");
        numerosP.innerText = `Los numeros introducidos son: ${a}, ${b} y ${c}`;

        let mayorP = document.createElement("p");
        mayorP.innerText = `El mayor es: ${Math.max(a, b, c)}`;

        resultSection.innerHTML = "";
        resultSection.appendChild(numerosP);
        resultSection.appendChild(mayorP);

    } else {
        resultSection.innerHTML = "<p>Uno de los numeros introducidos no es correcto</p>"
    }
});

// Pestaña multiplicar
const btnMultiplicar = insertaBoton("multiplicar", "Multiplicar");

btnMultiplicar.addEventListener("click", () => {
    resultSection.className = "";

    let a = parseInt(prompt("Introduce un numero:"));

    if (!isNaN(a)) {
        resultSection.innerHTML = "";

        for (let i = 1; i < 11; i++) {
            let currMult = document.createElement("p");
            currMult.innerText = `${a}x${i}=${a * i}`;
            resultSection.appendChild(currMult);
        }

    } else {
        resultSection.innerHTML = "<p>No has introducido un numero...</p>"
    }
});

// Pestaña seleccion iteración
const btnSeleccionIteracion = insertaBoton("seleccionIteracion", "Selección e iteración");

btnSeleccionIteracion.addEventListener("click", () => {
    resultSection.innerHTML = "";
    resultSection.className = "";

    // Muestra el triangulo
    for (let i = 9; i > -1; i--) {
        let currP = document.createElement("p");
        for (let j = i; j > -1; j--) {
            currP.innerText += j;
        }
        resultSection.appendChild(currP);
    }

    // Muestra el triangulo invertido
    for (let i = 0; i < 10; i++) {
        let currP = document.createElement("p");
        for (let j = 0; j < i + 1; j++) {
            currP.innerText += j;
        }

        resultSection.appendChild(currP);
    }

    // Calcular los numeros pares del 1 al 100 y mostrar 10 por fila;
    let currP = document.createElement("p");
    let currNumbers = 0;
    for (let i = 1; i < 101; i++) {
        if (i % 2 === 0) {
            if (currNumbers !== 0) {
                currP.innerText += `, ${i}`;
                currNumbers++;
            } else {
                currP.innerText += `${i}`;
                currNumbers++;
            }
        }

        // Si ya tiene 10 insertamos la linea
        if (currNumbers === 10) {
            resultSection.appendChild(currP);
            currP = document.createElement("p");
            currNumbers = 0;
        }
    }

    currP.innerText = currP.innerText.slice(0, -2);
    resultSection.appendChild(currP);
});

// Pestaña aleatorio
const btnAleatorio = insertaBoton("aleatorio", "Aleatorio");

btnAleatorio.addEventListener("click", () => {
    resultSection.innerHTML = "";
    resultSection.className = "";

    let aleatorio = Math.floor(Math.random() * (51 - 1) + 1);
    console.log(aleatorio);
    let a = parseInt(prompt("Intenta adivinar un numero entre 1 y 50. Introduce un numero:"));

    let intentos = 1;
    while (a !== aleatorio) {
        if (aleatorio < a) {
            a = parseInt(prompt("El numero es menor"));
            intentos++;

        } else if (aleatorio > a && !isNaN(a)) {
            a = parseInt(prompt("El numero es mayor"));
            intentos++;

        } else {
            a = parseInt(prompt("No has introducido un numero"));
        } 
    }

    let resultadoP = document.createElement("p");
    resultadoP.innerText = `Enhorabuena! El numero era ${a}! (Intentos: ${intentos})`;
    resultSection.appendChild(resultadoP);
});


// Bonoloto
const generarNumerosBonoloto = () => {
    // n1, n2, n3, n4, n5, n6, complementario, reintegro
    let numeros = []

    // Combinaciones y complementario
    while (numeros.length != 7) {
        let currNumero = Math.floor(Math.random() * 49 + 1);

        // Si no se contiene se añade
        if (!numeros.includes(currNumero)) {
            numeros.push(currNumero);
        }
    }

    // Reintegro
    numeros.push(Math.floor(Math.random() * 10));
    return numeros;
}

/*
 [0]: Array de numeros premiados
 [1]: Complementario
 [2]: Reintegro
 [3]: Reintegro generado
 [4]: Resultado
*/
const jugarBonoloto = (numerosSeleccionados, complementarioSeleccionado) => {
    numerosSeleccionados.sort((a, b) => { return a - b });

    // Generar reintegro
    let reintegroGenerado = Math.floor(Math.random() * 10);

    // Obtener los numeros premiados
    let resultados = generarNumerosBonoloto();

    let numerosGanadores = resultados.slice(0, 6).sort((a, b) => { return a - b });
    let complementarioGanador = resultados[6];
    let reintegroGanador = resultados[7];

    // Comparar numeros premiados
    let acertados = 0;
    numerosGanadores.forEach((numeroGanador) => {
        let acertado = false;
        numerosSeleccionados.forEach((numero) => {
            if (numeroGanador == numero) {
                acertado = true;
                acertados++;
                document.getElementById(numero).className = "acertado";
            }
        });

        if (!acertado) {
            document.getElementById(numeroGanador).className = "fallado";
        }
    });

    // Comprobar complementario
    let complementarioAcertado = false;
    if (complementarioSeleccionado === complementarioGanador) {
        document.getElementById(complementarioSeleccionado).className = "complementario";
        complementarioAcertado = true;
    }

    // Comprobar reintegro
    let reintegroAcertado = false;
    if (reintegroGenerado === reintegroGanador) {
        reintegroAcertado = true;
    }

    // Mostrar resultado
    let resultado = "";
    resultado = acertados;

    if (complementarioAcertado) {
        resultado += ` + C`;
    }

    if (reintegroAcertado) {
        resultado += ` + R`;
    }

    // 

    return [numerosGanadores, complementarioGanador, reintegroGanador, reintegroGenerado, resultado]
}

const crearTablaNumeros = () => {
    let tbody = document.createElement("tbody");

    let currNumber = 1;
    for (let i = 0; i < 7; i++) {
        let currRow = document.createElement("tr");
        for (let j = 0; j < 7; j++) {
            let currData = document.createElement("td");
            currData.id = currNumber;
            currData.innerText = currNumber++;
            currRow.appendChild(currData);
        }
        tbody.appendChild(currRow);
    }

    let tabla = document.createElement("table");
    tabla.appendChild(tbody);

    return tabla;
}
const seleccionarNumero = (event, numeros, guiaP) => {
    // Comprobar si se selecciona o no
    if (numeros.length != 7 && !event.target.className) {
        event.target.className = "seleccionado";
        numeros.push(parseInt(event.target.id));

    } else if (event.target.className === "seleccionado") {
        event.target.className = "";
        numeros.splice(numeros.indexOf(event.target.id), 1);
    }

    // Cambia la guia segun los seleccionados
    if (numeros.length < 6) {
        guiaP.innerText = "Selecciona 6 numeros:";

    } else if (numeros.length === 6) {
        guiaP.innerText = "Selecciona el complementario:";

    } else {
        guiaP.innerText = "Presione Jugar!";
    }
}

// Pestaña bonoloto
const btnBonoloto = insertaBoton("bonoloto", "Bonoloto");

btnBonoloto.addEventListener("click", () => {
    // Se prepara la seccion de resultados
    resultSection.innerHTML = "";
    resultSection.className = "bonoloto"

    // Dibuja columna del juego
    let columnaNumeros = document.createElement("section");
    columnaNumeros.className = "columna-numeros"
    resultSection.appendChild(columnaNumeros);

    // Columna de resultados
    let columnaResultados = document.createElement("section");
    columnaResultados.className = "hidden";
    resultSection.appendChild(columnaResultados);

    // Crea el titulo
    let titulo = document.createElement("h2");
    titulo.innerText = "Bonoloto";
    columnaNumeros.appendChild(titulo);

    // Crea la explicacion del juego
    let guiaP = document.createElement("p");
    guiaP.innerText = "Selecciona 6 numeros:";
    columnaNumeros.appendChild(guiaP);

    // Crea la tabla
    let tabla = crearTablaNumeros();
    columnaNumeros.appendChild(tabla);

    // Añade eventos a los numeros
    const numerosSeleccionados = [];
    document.querySelectorAll("td").forEach((numero) => {
        numero.addEventListener("click", (event) => {
            seleccionarNumero(event, numerosSeleccionados, guiaP);
        })
    });

    resultSection.appendChild(columnaResultados);

    // Boton enviar
    let btnSend = document.createElement("button");
    btnSend.innerText = "Jugar";

    btnSend.addEventListener("click", () => {
        if (numerosSeleccionados.length === 7) {
            btnSend.remove();

            document.querySelector(".bonoloto .columna-numeros p").remove();

            guiaP.innerText = "Resultados:"

            let complementarioSeleccionado = numerosSeleccionados.pop();

            let resultados = jugarBonoloto(numerosSeleccionados, complementarioSeleccionado);

            let numerosPremiados = resultados[0];
            let complementarioPremiado = resultados[1];
            let reintegroPremiado = resultados[2]
            let reintegroGenerado = resultados[3];
            let resultado = resultados[4];

            let seleccionadosP = document.createElement("p");
            seleccionadosP.innerText = `Numeros seleccionados: ${numerosSeleccionados[0]}, ${numerosSeleccionados[1]}, ${numerosSeleccionados[2]}, ${numerosSeleccionados[3]}, ${numerosSeleccionados[4]}, ${numerosSeleccionados[5]}`;

            let complementarioSeleccionadoP = document.createElement("p");
            complementarioSeleccionadoP.innerText = `Complementario seleccionado: ${complementarioSeleccionado}`;

            let reintegroGeneradoP = document.createElement("p");
            reintegroGeneradoP.innerText = `Reintegro generado automaticamente: ${reintegroGenerado}`;

            // Ganadores
            let numerosPremiadosP = document.createElement("p");
            numerosPremiadosP.innerText = `Premiados: ${numerosPremiados[0]}, ${numerosPremiados[1]}, ${numerosPremiados[2]}, ${numerosPremiados[3]}, ${numerosPremiados[4]}, ${numerosPremiados[5]}`;

            // Complementario
            let complementarioP = document.createElement("p");
            complementarioP.innerText = `Complementario: ${complementarioPremiado}`;

            // Reintegro
            let reintegroP = document.createElement("p");
            reintegroP.innerText = `Reintegro: ${reintegroPremiado}`;

            // Resultado
            let resultadoP = document.createElement("p");
            resultadoP.innerText = `Acertados: ${resultado}`;

            // Dibuja el informe
            columnaResultados.innerHTML = "<h2>Informe de resultado</h2>";

            columnaResultados.appendChild(seleccionadosP);
            columnaResultados.appendChild(complementarioSeleccionadoP);
            columnaResultados.appendChild(reintegroGeneradoP);
            columnaResultados.appendChild(document.createElement("hr"));

            columnaResultados.appendChild(numerosPremiadosP);
            columnaResultados.appendChild(complementarioP);
            columnaResultados.appendChild(reintegroP);
            columnaResultados.appendChild(document.createElement("hr"));

            columnaResultados.appendChild(resultadoP);
        }
    });

    columnaNumeros.appendChild(btnSend);
});

