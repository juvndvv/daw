`use strict`;

// Juan Daniel Forner Garriga
// Unidad 3

import { insertElement, clearWindow } from './dom.js';
import { str, firstTo, lastTo, exist, pop, positionsOfO, charInfo, existInStr, str2, wordInfo, str3, encripta } from './string.js';
import { convertToInt, convertToFloat, getIVA, getBasePrice } from './number.js';
import { getDate, askQuestion, msToTime, actualTime, isNotValidDate, birthdayDays, formatBirthdayData } from './date.js';


// Inserta la barra de navegacion
document.querySelector(`body`).insertBefore(
    document.createElement(`nav`),
    document.querySelector(`main`)
);

// Botones de la barra de navegacion
const string = insertElement(`button`, `String`, `nav`);
string.addEventListener(`click`, stringLst);

const numberBtn = insertElement(`button`, `Number`, `nav`);
numberBtn.addEventListener(`click`, numberLst);

const mathBtn = insertElement(`button`, `Math`, `nav`);
mathBtn.addEventListener(`click`, mathLst);

const dateBtn = insertElement(`button`, `Date`, `nav`);
dateBtn.addEventListener(`click`, dateLst);

const navigatorBtn = insertElement(`button`, `Navigator`, `nav`);
navigatorBtn.addEventListener(`click`, navigatorLst);

const historyBtn = insertElement(`button`, `History`, `nav`);
historyBtn.addEventListener(`click`, historyLst);

const locationBtn = insertElement(`button`, `Location`, `nav`);
locationBtn.addEventListener(`click`, locationLst);

const screenBtn = insertElement(`button`, `Screen`, `nav`);
screenBtn.addEventListener(`click`, screenLst);

const windowDocument1Btn = insertElement(`button`, `Ventanas`, `nav`);
windowDocument1Btn.addEventListener(`click`, windowDocument1Lst);

const windowDocument2Btn = insertElement(`button`, `Maquina escribir`, `nav`);
windowDocument2Btn.addEventListener(`click`, windowDocument2Lst);

const windowDocument3Btn = insertElement(`button`, `Esquina centro`, `nav`);
windowDocument3Btn.addEventListener(`click`, windowDocument3Lst);


// Listeners de los botones de la barra de navegacion
function stringLst() {
    clearWindow();

    // Apartado: metodos cadena 
    insertElement(`h3`, `SHIFT TO THE LEFT! SHIFT TO THE RIGHT! POP UP, PUSH DOWN, BYTE, BYTE, BYTE!`, `main`);
    insertElement(`p`, `Primer TO: ${firstTo}`, `main`);

    insertElement(`p`, `Introduce una palabra:`, `main`);
    const input = insertElement(`input`, ``, `main`);
    const s = insertElement(`section`, ``, `main`);
    input.addEventListener(`input`, (e) => {
        if (e.target.value === ``) {
            s.textContent = `Palabra no valida`;
            return;
        }
        const exist = existInStr(e.target.value);
        s.textContent = `Existe: ${exist ? `Si` : `No`}`;
    });

    insertElement(`p`, `Ultimo TO: ${lastTo}`, `main`);
    insertElement(`p`, `Existe TO: ${exist}`, `main`);
    insertElement(`p`, `POP: ${pop}`, `main`);
    insertElement(`p`, `Posiciones de O: ${positionsOfO()}`, `main`);

    insertElement(`p`, `Introduce una posicion:`, `main`)
    const input1 = insertElement(`input`, ``, `main`);
    const s1 = insertElement(`section`, ``, `main`);
    input1.addEventListener(`input`, (e) => {
        if (e.target.value < 0 || e.target.value > str.length || isNaN(e.target.value) || e.target.value === ``) {
            s1.textContent = `Posicion no valida`;
            return;
        }
        result = charInfo(e.target.value);
        s1.textContent = `Caracter: '${result.char}', Codigo: ${result.code}`;
    });

    insertElement(`h3`, `User,error:,Replace,user,and,press,any,key,to,continue,`, `main`);
    insertElement(`p`, `Resultado: ${str2}`, `main`);

    insertElement(`p`, `Introduce una palabra:`, `main`);
    const input2 = insertElement(`input`, ``, `main`);
    const s2 = insertElement(`section`, ``, `main`);
    input2.addEventListener(`input`, (e) => {
        if (e.target.value === ``) {
            s2.textContent = `Palabra no valida`;
            return;
        }
        result = wordInfo(e.target.value);
        s2.textContent = `Palabra: '${result.word}', Longitud: ${result.length}, Inversa: ${result.reverse}`;
    });

    // Apartado: encripta
    insertElement(`h3`, str3, `main`);
    insertElement(`p`, `Resultado: ${encripta(str3)}`, `main`);
}

function numberLst() {
    clearWindow();

    // Apartado: conversion a entero
    insertElement(`h3`, `Conversion a entero`, `main`);
    const list1 = [`14`, `6.65`, `7asd`, 12.78, true];
    const enteros = convertToInt(list1);

    list1.forEach((item, index) => {
        insertElement(`p`, `${item} -> ${enteros[index]}`, `main`);
    });

    // Apartado: conversion a float
    insertElement(`h3`, `Conversion a float`, `main`);
    const list2 = [`14`, `6.65`, `7.13asd12`];
    const floats = convertToFloat(list2);

    list2.forEach((item, index) => {
        insertElement(`p`, `${item} -> ${floats[index]}`, `main`);
    });

    // Apartado: precio sin iva
    insertElement(`h3`, `IVA`, `main`);
    insertElement(`p`, `Introduce un precio sin IVA:`, `main`);
    const input1 = insertElement(`input`, ``, `main`);
    const s1 = insertElement(`section`, ``, `main`);
    input1.addEventListener(`input`, (e) => {
        if (isNaN(e.target.value) || e.target.value === ``) {
            s1.textContent = `Precio no valido`;
            return;
        }
        const precioConIVA = getIVA(e.target.value);
        const IVA = precioConIVA - e.target.value;
        s1.textContent = `Precio: ${e.target.value} IVA (21%) = ${IVA.toFixed(2)}€ Precio con IVA = ${precioConIVA.toFixed(2)}€`;
    });

    // Apartado: precio con iva
    insertElement(`h3`, `Precio sin IVA`, `main`);
    insertElement(`p`, `Introduce un precio con IVA:`, `main`);
    const input2 = insertElement(`input`, ``, `main`);
    const s2 = insertElement(`section`, ``, `main`);
    input2.addEventListener(`input`, (e) => {
        if (isNaN(e.target.value) || e.target.value === ``) {
            s2.textContent = `Precio no valido`;
            return;
        }
        const precioSinIVA = getBasePrice(e.target.value);
        const IVA = e.target.value - precioSinIVA;
        s2.textContent = `Precio: ${e.target.value} IVA (21%) = ${IVA.toFixed(2)}€ Precio sin IVA = ${precioSinIVA.toFixed(2)}€`;
    });
}

function mathLst() {
    clearWindow();

    insertElement('p', `PI: ${Math.PI}`, `main`);
    insertElement('p', `Entero mas cercano a 5.67: ${Math.round(5.67)}`, `main`);
    insertElement('p', `Entero mas cercano a 5.37: ${Math.round(5.37)}`, `main`);
    insertElement('p', `Maximo entero menor o igual a 5.87: ${Math.floor(5.87)}`, `main`);
    insertElement(`p`, `Minimo entero mayor o igual a 6.17: ${Math.ceil(6.17)}`, `main`);
    insertElement(`p`, `Minimo de 12, 43, 56, 7, 12: ${Math.min(12, 43, 56, 7, 12)}`, `main`);
    insertElement(`p`, `Maximo de 12, 43, 56, 7, 12: ${Math.max(12, 43, 56, 7, 12)}`, `main`);
    insertElement(`p`, `Valor absoluto de -34: ${Math.abs(-34)}`, `main`);
    insertElement(`p`, `Raiz cuadrada de 81: ${Math.sqrt(81)}`, `main`);
}

function dateLst() {
    clearWindow();

    // Apartado: mostrar fecha y tiempos
    insertElement(`h3`, getDate(), `main`);
    insertElement(`p`, `Pulse aqui para responder dos preguntas:`, `main`);
    const button = insertElement(`button`, ``, `main`);
    button.textContent = `Preguntas`;
    const s = insertElement(`section`, ``, `main`);
    s.id = `time`;
    button.addEventListener(`click`, () => {
        const time1 = askQuestion(`¿Cual es el nombre de tu madre?`);
        const time2 = askQuestion(`¿Cual es tu comida favorita?`);

        const time = msToTime(time1 + time2);
        insertElement(`p`, `Has tardado ${time.minutes} minutos, ${time.seconds} segundos y ${time.milliseconds} milisegundos`, `#time`);
    });

    insertElement(`h3`, `Tiempo`, `main`);
    const time = actualTime();
    insertElement(`p`, `Año: ${time.year}`, `main`);
    insertElement(`p`, `Mes: ${time.month}`, `main`);
    insertElement(`p`, `Dia del mes: ${time.dayOfMonth}`, `main`);
    insertElement(`p`, `Dia de la semana: ${time.dayOfWeek}`, `main`);
    insertElement(`p`, `Hora: ${time.hour}`, `main`);
    insertElement(`p`, `Minutos: ${time.minutes}`, `main`);
    insertElement(`p`, `Milisegundos: ${time.miliseconds}`, `main`);
    insertElement(`p`, `Milisegundos desde el 1 de enero de 1970: ${time.msFromEpoch}`, `main`);

    // Apartado: mostrar los dias que quedan o han pasado de un cumpleaños y la edad que tiene
    insertElement(`h3`, `Cumpleaños`, `main`);
    insertElement(`p`, `Introduce tu fecha de nacimiento`, `main`);
    const input = insertElement(`input`, ``, `main`);
    input.type = `date`;
    const s2 = insertElement(`section`, ``, `main`);
    s2.id = `fechaResultado`;
    input.addEventListener(`input`, (e) => {
        // Checkea si la fecha es valida
        if (isNotValidDate(e.target.value)) {
            s2.textContent = `Fecha no valida`;
            return;
        }

        // Crea la fecha, obtiene los datos y muestra el formateo
        const birthday = new Date(e.target.value);
        const time = birthdayDays(birthday);
        document.querySelector(`#fechaResultado`).innerHTML = ``;
        insertElement(`p`, formatBirthdayData(time), `#fechaResultado`);
    });
}

function navigatorLst() {
    clearWindow();
    insertElement(`h3`, `Demostracion navigator`, `main`);
    insertElement(`p`, `Nombre del navegador: ${navigator.appCodeName}`, `main`);
    insertElement(`p`, `Version del navegador: ${navigator.appVersion}`, `main`);
    insertElement(`p`, `Idioma del navegador: ${navigator.language}`, `main`);
    insertElement(`p`, `Plataforma: ${navigator.platform}`, `main`);
    insertElement(`p`, `Motor del navegador: ${navigator.product}`, `main`);
    insertElement(`p`, `User-agent: ${navigator.userAgent}`, `main`);
    insertElement(`p`, `Online: ${navigator.onLine}`, `main`);
    insertElement(`p`, `Java activo: ${navigator.javaEnabled()}`, `main`);
    insertElement(`p`, `Cookies activas: ${navigator.cookieEnabled}`, `main`);
}

function historyLst() {
    clearWindow();

    insertElement(`p`, `Longitud del historial: ${history.length}`, `main`);

    const backBtn = insertElement(`button`, `Atras`, `main`);
    backBtn.setAttribute(`onclick`, `history.back()`);
    
    const forwardBtn = insertElement(`button`, `Adelante`, `main`);
    forwardBtn.setAttribute(`onclick`, `history.forward()`);
}

function locationLst() {
    clearWindow();
    insertElement(`h3`, `Demostracion location`, `main`);
    insertElement(`p`, `Protocolo: ${location.protocol}`, `main`);
    insertElement(`p`, `Nombre del host: ${location.host}`, `main`);
    insertElement(`p`, `Nombre del host: ${location.hostname}`, `main`);
    insertElement(`p`, `Puerto: ${location.port}`, `main`);
    insertElement(`p`, `Ruta: ${location.pathname}`, `main`);
    insertElement(`p`, `Parametros: ${location.search}`, `main`);
    insertElement(`p`, `Hash: ${location.hash}`, `main`);
}

function screenLst() {
    clearWindow();
    insertElement(`h3`, `Demostracion screen`, `main`);
    insertElement(`p`, `Altura: ${screen.height}`, `main`);
    insertElement(`p`, `Anchura: ${screen.width}`, `main`);
    insertElement(`p`, `Altura disponible: ${screen.availHeight}`, `main`);
    insertElement(`p`, `Anchura disponible: ${screen.availWidth}`, `main`);
    insertElement(`p`, `Profundidad de color: ${screen.colorDepth}`, `main`);
    insertElement(`p`, `Profundidad de color disponible: ${screen.pixelDepth}`, `main`);
}

function windowDocument1Lst() {
    clearWindow();

    let ventana;

    const openBtn = insertElement(`button`, `Abrir`, `main`);
    openBtn.addEventListener(`click`, () => {
        ventana = window.open(``, ``, `width=200,height=200,top=0,left=0`);
    });

    const closeBtn = insertElement(`button`, `Cerrar`, `main`);
    closeBtn.addEventListener(`click`, () => {
        if (ventana) { ventana.close(); }
    });
}

function windowDocument2Lst() {
    clearWindow();

    insertElement(`p`, `Introduce una frase o palabra:`, `main`);
    const input = insertElement(`input`, ``, `main`);
    const button = insertElement(`button`, `Enviar`, `main`);
    const info = insertElement(`section`, ``, `main`);
    info.id = `info`;

    const result = insertElement(`section`, ``, `main`);
    result.id = `maquinaEscribir`;
    button.addEventListener(`click`, () => {
        let str = input.value;
        result.innerHTML = ``;
        if (str === ``) {
            info.textContent = `La cadena no puede estar vacia`;
            return;
        }

        info.textContent = ``;

        // Simula una maquina de escribir
        const p = insertElement(`p`, ``, `#maquinaEscribir`);
        let i = 0;
        const interval = setInterval(() => {
            p.textContent += str[i++];
            if (i === str.length) { clearInterval(interval); }
        }, 100);
    });

    input.addEventListener(`keyup`, (e) => {
        if (e.keyCode === 13) { button.click(); }
    });
}

function windowDocument3Lst() {
    clearWindow();

    const WIDTH = 500;
    const HEIGHT = 500;

    // Apartado: abre una ventana en la esquina superior izquierda y la va ampliando hasta completar la pantalla
    const esquinaBtn = insertElement(`button`, `Esquina`, `main`);
    esquinaBtn.addEventListener(`click`, () => {
        let ventana = window.open(``, ``, `width=${WIDTH},height=${HEIGHT},top=0,left=0`);
        console.log(`Maximo disponible: ${screen.width}x${screen.availHeight}`);
        let intervalo = setInterval(() => {
            if (ventana.closed) { console.info("Ventana cerrada. Fin de redimension"); clearInterval(intervalo) }
            
            try {
    
                let x = ventana.outerWidth < screen.width ? 10 : 0;
                let y = ventana.outerHeight < screen.availHeight ? 10 : 0;
                
                if (x === 0 && y === 0) { console.log("Fin redimension"); clearInterval(intervalo) }

                ventana.resizeBy(x, y);
            } catch (error) {
                console.error("Ha ocurrido un error. Fin de redimension", error);
                clearInterval(intervalo);
            }
        }, 100);
    });

    // Apartado: abre una ventana en el centro y la va ampliando por todos los lados por igual
    const centroBtn = insertElement(`button`, `Centro`, `main`);
    centroBtn.addEventListener(`click`, () => {
        const X = (screen.width / 2) - (WIDTH / 2);
        const Y = (screen.height / 2) - (HEIGHT / 2);
        let ventana = window.open(``, ``, `width=${WIDTH},height=${HEIGHT},top=${Y},left=${X}`);
            const interval = setInterval(() => {
            if (ventana.closed) { console.info("Ventana cerrada. Fin de redimension"); clearInterval(interval) }

            try {
                let x = ventana.outerWidth < screen.width ? 10 : 0;
                let y = ventana.outerHeight < screen.availHeight ? 10 : 0;
                
                if (x === 0 && y === 0) { console.log("Fin redimension"); clearInterval(interval) }

                ventana.resizeBy(x, y);
                ventana.moveBy(-x / 2, -y / 2);
            } catch (error) {
                console.error("Ha ocurrido un error. Fin de redimension", error);
                clearInterval(interval)
            }
        }, 100);
    });
}