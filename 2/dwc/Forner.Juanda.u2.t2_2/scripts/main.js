`use strict`;

function between(x, a, b) {
    return x >= a && x <= b;
}

function createParagraph(text) {
    const p = document.createElement('p');
    p.textContent = text;
    return p;
}

function createHeading(text) {
    const h = document.createElement('h2');
    h.textContent = text;
    return h;
}

function doOperations() {
    let a = parseInt(prompt(`Introduce un numero`));
    let b = parseInt(prompt(`Introduce otro numero`));

    if (isNaN(a) || isNaN(b)) {
        return `<p>No es un numero</p>`;
    }

    return `
        <p>Los numeros introducidos son ${a} y ${b}</p>
        <p>La suma de ${a} y ${b} es ${a + b}</p>
        <p>La resta de ${a} y ${b} es ${a - b}</p>
        <p>La multiplicacion de ${a} y ${b} es ${a * b}</p>
        <p>La división de ${a} y ${b} es ${a / b}</p>
        <p>El modulo de ${a} y ${b} es ${a % b}</p>
        <p>El incremento de ${a} es ${a + 1}</p>
        <p>El incremento de ${b} es ${b + 1}</p>
        <p>El decremento de ${a} es ${a - 1}</p>
        <p>El decremento de ${b} es ${b - 1}</p>`;
}

function getBiggerNumber() {
    let a = parseInt(prompt(`Introduce un primer numero:`));
    let b = parseInt(prompt(`Introduce un segundo numero:`));
    let c = parseInt(prompt(`Introduce un tercer numero:`));

    if (isNaN(a) || isNaN(b) || isNaN(c)) {
        return `<p>Uno de los numeros introducidos no es correcto</p>`;
    }

    return `
        <p>Los numeros introducidos son: ${a}, ${b} y ${c}</p>
        <p>El mayor es: ${Math.max(a, b, c)}</p>`;
}

function getMultiplicationTable() {
    let a = parseInt(prompt(`Introduce un numero`));

    if (isNaN(a)) {
        return `<p>No has introducido un numero...</p>`;
    }

    let multiplicationTable = ``;
    for (let i = 0; i < 11; i++) {
        multiplicationTable += `<p>${a}x${i}=${a * i}</p>`;
    }

    return multiplicationTable;
}

function selectionIteration() {
    return triangle() +
        invertedTriangle() +
        pairNumbers();
}

function triangle() {
    let triangle = ``;
    for (let i = 9; i > -1; i--) {
        let currLine = `<p>`;
        for (let j = i; j > -1; j--) {
            currLine += j;
        }
        currLine += `</p>`;
        triangle += currLine;
    }

    return triangle;
}

function invertedTriangle() {
    let invertedTriangle = ``;
    for (let i = 0; i < 10; i++) {
        let currLine = `<p>`;
        for (let j = 0; j < i + 1; j++) {
            currLine += j;
        }
        currLine += `</p>`;
        invertedTriangle += currLine;
    }

    return invertedTriangle;
}

function pairNumbers() {
    let numbers = ``;

    let currLine = `<p>`;
    let currNumbers = 0;
    for (let i = 1; i < 101; i++) {
        if (i % 2 === 0) {
            if (currNumbers !== 0) {
                currLine += `, ${i}`;
                currNumbers++;
            } else {
                currLine += `${i}`;
                currNumbers++;
            }
        }

        // Si ya tiene 10 insertamos la linea
        if (currNumbers === 10) {
            currLine += `</p>`;
            numbers += currLine;
            currLine = `<p>`;
            currNumbers = 0;
        }
    }

    return numbers;
}

function randomNumber() {
    let generated = Math.floor(Math.random() * 51);
    let a = parseInt(prompt(`Intenta adivinar un numero entre 1 y 50. Introduce un numero`));

    let intentos = 1;
    while (a !== generated) {
        if (!between(a, 1, 50) || isNaN(a)) {
            a = prompt(`Numero no valido, introduce otro`)
            continue;
        }

        if (generated < a) {
            a = parseInt(prompt(`El numero es menor`));

        } else if (generated > a) {
            a = parseInt(prompt(`El numero es mayor`));
        }

        intentos++;
    }

    return `<p>Numero acertado!!! El numero era ${generated} (${intentos} intentos)</p>`;
}

function insertElement(type, content, parent) {
    let element = document.createElement(type);
    element.innerText = content;

    document.querySelector(parent).appendChild(element);
    return element
}

// Inserta la barra de navegacion
document.querySelector(`body`).insertBefore(
    document.createElement(`nav`),
    document.querySelector(`main`)
);

// Inserta la seccion de resultados
const resultSection = insertElement(`section`, ``, `main`);

// Inserta los botones en la barra de navegacion
const btnOperators = insertElement(`button`, `Operadores`, `nav`);
btnOperators.addEventListener(`click`, operatorsLst);

const btnBigger = insertElement(`button`, `Mayor`, `nav`);
btnBigger.addEventListener(`click`, biggerLst);

const btnMultiplication = insertElement(`button`, `Multiplicar`, `nav`);
btnMultiplication.addEventListener(`click`, multiplicationLst);

const btnSelection = insertElement(`button`, `Selección e iteración`, `nav`);
btnSelection.addEventListener(`click`, selectionLst);

const btnAleatorio = insertElement(`button`, `Aleatorio`, `nav`);
btnAleatorio.addEventListener(`click`, randomLst);

const btnBonoloto = insertElement(`button`, `Bonoloto`, `nav`);
btnBonoloto.addEventListener(`click`, lottoLst);

function clearWindow() {
    resultSection.innerHTML = ``;
    resultSection.className = ``;
}

function operatorsLst() {
    clearWindow();
    resultSection.innerHTML = doOperations();
}

function biggerLst() {
    clearWindow();
    resultSection.innerHTML = getBiggerNumber();
}

function multiplicationLst() {
    clearWindow();
    resultSection.innerHTML = getMultiplicationTable();
}

function selectionLst() {
    clearWindow();
    resultSection.innerHTML = selectionIteration();
}

function randomLst() {
    clearWindow();
    resultSection.innerHTML = randomNumber();
}

function lottoLst() {
    clearWindow();
    resultSection.className = `bonoloto`;
    prepareGame();

    let selected = [];

    function numberLst(event) {
        let number = parseInt(event.target.innerText);
        if (selected.length != 7 && !event.target.className) {
            event.target.className = `seleccionado`;
            selected.push(number);

        } else if (event.target.className === `seleccionado`) {
            event.target.className = ``;
            selected.splice(selected.indexOf(event.target.innerText), 1);
        }

        // Cambia la guia segun los seleccionados
        if (selected.length < 6) {
            document.getElementById(`guide`).innerText = `Selecciona 6 numeros:`;

        } else if (selected.length === 6) {
            document.getElementById(`guide`).innerText = `Selecciona el complementario:`;

        } else {
            document.getElementById(`guide`).innerText = `Presione Jugar!`;
        }
    }

    function prepareGame() {
        // Seccion de juego
        let numbersColumn = document.createElement(`section`);
        numbersColumn.id = `game`;

        let title = document.createElement(`h1`);
        title.innerText = `Bonoloto`;

        let gameGuideP = document.createElement(`p`);
        gameGuideP.id = `guide`;
        gameGuideP.innerText = `Selecciona 6 numeros:`;

        let playBtn = document.createElement(`button`);
        playBtn.innerText = `Jugar`;
        playBtn.addEventListener(`click`, playLotto);

        numbersColumn.appendChild(title);
        numbersColumn.appendChild(gameGuideP);
        numbersColumn.appendChild(createLottoTable());
        numbersColumn.appendChild(playBtn);

        resultSection.appendChild(numbersColumn);

        // Añade listeners a los numeros
        document.querySelectorAll(`td`).forEach(number => number.addEventListener(`click`, numberLst));

        function playLotto() {
            if (selected.length === 7) {
                // Bloqueamos el juego
                document.querySelectorAll(`td`).forEach(
                    number => {
                        number.removeEventListener(`click`, numberLst);
                    });

                playBtn.removeEventListener(`click`, playLotto);

                // Establecemos el boton para resetear el juego
                playBtn.innerText = `Resetear`;
                playBtn.addEventListener(`click`, lottoLst);

                // Obtenemos los resultados y mostramos
                selected = [selected.slice(0, 6), selected[6], Math.floor(Math.random() * 10)];
                let winners = generateLottoNumbers();
                let result = getLottoResult(selected, winners);
                let report = createLottoReport(selected, winners, result);
                resultSection.appendChild(report);

                // Marcamos en la tabla los premiados
                document.querySelectorAll(`td`).forEach(
                    number => {
                        if (winners[0].includes(parseInt(number.innerText))) {
                            number.classList.add(`acertado`);
                        }
                    });

                // Marcamos en la tabla los fallados
                document.querySelectorAll(`td`).forEach(
                    number => {
                        if (selected[0].includes(parseInt(number.innerText)) && !winners[0].includes(parseInt(number.innerText))) {
                            number.classList.add(`fallado`);
                        }
                    });

                // Marcamos en la tabla el complementario
                document.querySelectorAll(`td`).forEach(
                    number => {
                        if (parseInt(number.innerText) === winners[1]) {
                            number.className = `complementario`;
                        }
                    });
            }
        }

        // Seccion de resultados
        let resultsColumn = document.createElement(`section`);
        resultSection.appendChild(resultsColumn);
    }

    function generateLottoNumbers() {
        let lottoNumbers = [];
        while (lottoNumbers.length < 6) {
            // Generamos un numero aleatorio entre 1 y 49
            let currNumber = Math.floor(Math.random() * 50);
            if (!lottoNumbers.includes(currNumber)) {
                lottoNumbers.push(currNumber);
            }
        }
        lottoNumbers.sort((a, b) => { return a - b })

        let complementary = Math.floor(Math.random() * 50);
        while (lottoNumbers.includes(complementary)) {
            complementary = Math.floor(Math.random() * 50);
        }

        let refund = Math.floor(Math.random() * 10);

        return [lottoNumbers, complementary, refund];
    }

    function createLottoTable() {
        // Crear la tabla y el cuerpo de la tabla
        const table = document.createElement(`table`);
        const tbody = document.createElement(`tbody`);

        let number = 1;
        for (let i = 0; i < 7; i++) {
            const row = document.createElement(`tr`);

            for (let j = 0; j < 7; j++) {
                const cell = document.createElement(`td`);
                cell.textContent = number;
                number++;

                row.appendChild(cell);
            }

            tbody.appendChild(row);
        }

        table.appendChild(tbody);

        return table;
    }

    function getLottoResult(selected, winners) {
        let correct = 0;
        for (let i = 0; i < 6; i++) {
            if (winners[0].includes(selected[0][i])) {
                correct++;
            }
        }

        return `${correct}${(selected[1] === winners[1]) ? `+ C` : ``}${(selected[2] === winners[2]) ? ` + R` : ``}`;
    }

    function createLottoReport(selected, winners, result) {
        const reportContainer = document.createElement(`section`);

        reportContainer.appendChild(createHeading('Informe de resultados'));
        reportContainer.appendChild(createParagraph(`Números seleccionados: ${selected[0].join(', ')}`));
        reportContainer.appendChild(createParagraph(`Complementario seleccionado: ${selected[1]}`));
        reportContainer.appendChild(createParagraph(`Reintegro generado: ${selected[2]}`));
        reportContainer.appendChild(createParagraph(`Números premiados: ${winners[0].join(', ')}`));
        reportContainer.appendChild(createParagraph(`Complementario premiado: ${winners[1]}`));
        reportContainer.appendChild(createParagraph(`Reintegro premiado: ${winners[2]}`));
        reportContainer.appendChild(createParagraph(`Aciertos: ${result}`));

        return reportContainer;
    }
}