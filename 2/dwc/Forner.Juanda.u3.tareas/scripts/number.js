`use strict`;

export function convertToInt(l) {
    return l.map(e => parseInt(e));
}

export function convertToFloat(l) {
    return l.map(e => parseFloat(e));
}

export function getIVA(price) {
    if (isNaN(price)) {
        throw new Error(`El precio no es un numero`);
    }

    return price * 1.21;
}

export function getBasePrice(price) {
    if (isNaN(price)) {
        throw new Error(`El precio no es un numero`);
    }

    const basePrice = price / 1.21;

    return basePrice;
}

