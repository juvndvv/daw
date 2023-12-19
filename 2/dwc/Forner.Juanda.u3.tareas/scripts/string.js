`use strict`;

// Parte 1
export const str = `SHIFT TO THE LEFT! SHIFT TO THE RIGHT! POP UP, PUSH DOWN, BYTE, BYTE, BYTE!`;

export const firstTo = str.indexOf('TO');

export const lastTo = str.lastIndexOf('TO');

export const exist = str.includes('TO');

export const pop = str.search('POP');

export function positionsOfO() {
    const o = [];
    let index = str.indexOf('O');
    while (index !== -1) {
        o.push(index);
        index = str.indexOf('O', index + 1);
    }
    return o;
}

export function charInfo(pos) {
    return {
        char: str.charAt(pos),
        code: str.charCodeAt(pos)
    }
}

export function existInStr(word) {
    return str.includes(word);
}

// Parte 2
export const str2 = 'User,error:,Replace,user,and,press,any,key,to,continue,'.replace(/,/g, ' ');  // g = global

// Parte 3
export function wordInfo(word) {
    return {
        length: word.length,
        word,
        reverse: word.split('').reverse().join('')
    }
}

// Parte 4
export const str3 = `La criptografía (griego “oculto” y “escribir”, literalmente “escritura oculta”): ciencia de cifrar<br>y descifrar información. Se emplea permitir un intercambio de mensajes que solo puedan<br>ser leídos por personas a las que van dirigidos y que poseen los medios para descifrarlos.<br>Una técnica sencilla de cifrado, criptografía clásica, es la sustitución: cambio de significado<br>de los elementos básicos del mensaje, las letras, los dígitos o los símbolos.`;
export function encripta(str) {
    const map = new Map([
        [`a`, `1`],
        [`e`, `3`],
        [`i`, `3`],
        [`o`, `4`],
        [`u`, `5`],
        [`A`, `1`],
        [`E`, `3`],
        [`I`, `3`],
        [`O`, `4`],
        [`U`, `5`]
    ]);

    return str.split(``).map(char => map.has(char) ? map.get(char) : char).join(``);
}