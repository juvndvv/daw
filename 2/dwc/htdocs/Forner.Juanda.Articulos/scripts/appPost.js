
// author: Juan Daniel Forner Garriga
`use strict`;

// Constantes de la aplicación
const OPTIONS = {
    articles: `TA`,
    families: `TF`,
    providers: `TP`,
    articlesByFamily: `AF`,
    articlesByProvider: `AP`,
    familyImage: `FF`
}

const TITLES = {
    TA: `Todos los articulos`,
    TF: `Todas las familias`,
    TP: `Todos los proveedores`
}

const TABLE_HEADERS = {
    idarticulo: `ID Artículo`,
    idproveedor: `ID Proveedor`,
    idfamilia: `ID Familia`,
    nombre: `Nombre`,
    familia: `Familia`,
    proveedor: `Proveedor`,
    precioventa: `Precio de venta`,
    descripcion: `Descripción`,
    imagen: `Imagen`
}

const IMG_FOLDER = `img/`;

// Document object model functions
function createElement(parent, element, content) {
    const newElement = document.createElement(element);
    newElement.innerHTML = content;

    if (parent !== null) {
        parent.appendChild(newElement);
    }

    return newElement;
}

// Funciones 
function makePOSTRequest(params, callback){
    const URL = `/php/consultarArticulos.php`;

    var xhr = new XMLHttpRequest();
    xhr.open('POST', URL);

    // Cambiar el encabezado para indicar que estás enviando datos codificados de formulario
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    // Llamar a una función cuando la solicitud cambie de estado
    xhr.addEventListener('readystatechange', () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.responseText);
            const json = JSON.parse(xhr.responseText) ?? [];
            callback(json);
        }
    });
    
    xhr.send(new URLSearchParams(params));
}

function createTable(json) {
    // Si no hay resultados, crea una tabla con un mensaje
    if (json.length === 0) {
        const table = document.createElement(`table`);
        table.id = `result`;
        table.createTBody().insertRow().insertCell().textContent = `No hay resultados`;
        return table;
    }

    // Crea la tabla
    const table = document.createElement(`table`);
    table.id = `result`;

    // Crea la cabecera de la tabla con la mitad de las columnas por necesidad de la respuesta
    let columns = Object.keys(json[0]);
    columns = columns.splice(columns.length / 2, columns.length);

    const mappedColumns = columns.map(column => TABLE_HEADERS[column]);

    // Crea la cabecera
    const header = table.createTHead();
    const row = header.insertRow();

    // Añade las columnas a la cabecera
    mappedColumns.forEach(columna => {
        const ceil = row.insertCell();
        ceil.innerHTML = columna;
    });

    // Crea el cuerpo de la tabla
    const body = table.createTBody();

    // Añade las filas a la tabla
    json.forEach(element => {
        const row = body.insertRow();
        columns.forEach(column => {
            const cell = row.insertCell();
            const mappedValue = mapValueIfNecessary(column, element[column]);

            // Se ve el tipo de dato para saber si es un elemento html o un texto
            if (typeof mappedValue === `object`) {
                cell.appendChild(mappedValue);
            } else {
                cell.textContent = mappedValue
            }
        });
    });

    return table;
}

function mapValueIfNecessary(column, value) {
    switch (column) {
        case `familia`:
            return FAMILIES[value];
        case `proveedor`:
            return PROVIDERS[value];
        case `precioventa`:
            return `${value} €`;
        case 'imagen':
            const img = createElement(null, `img`, null);
            img.setAttribute(`src`, IMG_FOLDER + value);
            img.setAttribute(`alt`, `Imagen de familia`);
            return img;
        default:
            return value;
    }
}

function drawTable(json) {
    // Elimina la tabla anterior si existe
    const previousTable = document.getElementById(`result`);
    if (previousTable) {
        previousTable.remove();
    }

    // Crea la nueva tabla y la añade al DOM
    const table = createTable(json);
    table.id = `result`;
    resultSection.appendChild(table);
}

function drawTitle(title, family=null) {
    // Añade la imagen del titulo si corresponde
    if (family) {
        makePOSTRequest({opcion: OPTIONS.familyImage, familia: family}, json => {
            let imgSrc = ``;

            if (json.length !== 0) {
                imgSrc = IMG_FOLDER + json[0].imagen;
            }

            drawImage(imgSrc);
        });

    // Si no hay familia, elimina la imagen
    } else {
        drawImage(``);
    }

    // Elimina el titulo anterior si existe
    const previousTitle = document.getElementById(`title`);
    if (previousTitle) {
        previousTitle.remove();
    }

    // Si no hay titulo, no hace nada
    if (title === ``) {
        return;
    }

    // Crea el nuevo titulo y lo añade al DOM
    const h2 = createElement(titleSection, `h2`, title);
    h2.id = `title`;
}

function drawImage(src) {
    // Elimina la imagen anterior si existe
    const previousImage = document.getElementById(`familyImage`);
    if (previousImage) {
        previousImage.remove();
    }

    // Si no hay imagen, no hace nada
    if (src === ``) {
        return;
    }
    // Crea la nueva imagen y la añade al DOM
    const img = createElement(titleSection, `img`, ``);
    img.id = `familyImage`;
    img.src = src;
}

function drawSelect(json) {
    // Si no se obtiene respuesta no se hace nada
    if (json.length === 0) {
        return;
    }

    // Se elimina el contenido anterior
    secondParamSelect.innerHTML = ``;

    // Se crea la primera opcion
    const firstOption = createElement(secondParamSelect, `option`, `Selecciona una opcion`);
    firstOption.setAttribute("disabled", "");
    firstOption.setAttribute("selected", "");

    // Se crea una opcion para cada tipo
    json.forEach(element => {
        createElement(secondParamSelect, `option`, element.nombre);
    });

    // Se muestra el select
    secondParamSelect.style.display = `inline`;
}

// Funciones de inicialización de la aplicacion
function initFamilies() {
    makePOSTRequest({opcion: OPTIONS.families}, json => {
        if (json.length === 0) {
            return;
        }

        json.forEach(element => {
            FAMILIES[element.idfamilia] = element.nombre;
        });
    });
}

function initProviders() {
    makePOSTRequest({opcion: OPTIONS.providers}, json => {
        if (json.length === 0) {
            return;
        }

        json.forEach(element => {
            PROVIDERS[element.idproveedor] = element.nombre;
        });
    });
}

// Inicializa la pagina
const FAMILIES = {};
const PROVIDERS = {};

initFamilies();
initProviders();

// Se obtienen los elementos del DOM
const resultSection = document.getElementById(`resultSection`);
const titleSection = document.getElementById(`titleSection`);

// Evento del primer select
const firstParamSelect = document.getElementById(`firstParam`);

function firstParamSelectHandler(event) {
    // Se oculta el segundo select
    secondParamSelect.style.display = `none`;

    // Se obtiene el parametro del body
    const body = {};
    const firstParam = event.target.value;
    switch (firstParam) {
        case OPTIONS.articles:
            body.opcion = OPTIONS.articles;
            break;

        case OPTIONS.providers:
        case OPTIONS.articlesByProvider:
            body.opcion = OPTIONS.providers;
            break;

        case OPTIONS.families:
        case OPTIONS.articlesByFamily:
            body.opcion = OPTIONS.families;
            break;
    }

    // Se obtiene el callback correspondiente
    let callbackFunction = null;
    // [TA, TP, TF]
    if ([OPTIONS.articles, OPTIONS.providers, OPTIONS.families].includes(firstParam)) {
        drawTitle(TITLES[firstParam]);
        callbackFunction = drawTable;
    }
    
    // [AF, AP]
    else if ([OPTIONS.articlesByFamily, OPTIONS.articlesByProvider].includes(firstParam)) {
        callbackFunction = drawSelect;
    }
    
    makePOSTRequest(body, callbackFunction);
}

firstParamSelect.addEventListener(`change`, firstParamSelectHandler);

// Evento del segundo select
const secondParamSelect = document.getElementById(`secondParam`);

function secondParamSelectHandler(event) {
    const firstParam = firstParamSelect.value;
    const secondParam = event.target.value;
    

    const body = {};
    switch (firstParam) {
        case OPTIONS.articlesByFamily:
            body.opcion = OPTIONS.articlesByFamily;
            body.familia = secondParam;
            break;

        case OPTIONS.articlesByProvider:
            body.opcion = OPTIONS.articlesByProvider;
            body.proveedor = secondParam;
            break;
    }

    drawTitle(secondParam, body.familia ?? null);
    makePOSTRequest(body, drawTable);
}

secondParamSelect.addEventListener(`change`, secondParamSelectHandler);