
document.querySelector(`body`).insertBefore(
    document.createElement(`nav`),
    document.querySelector(`main`)
);

const arrayBtn = insertElement(`button`, `Array`, `nav`);

arrayBtn.addEventListener(`click`, () => {
    console.clear();

    const ORIGINAL = [0, 1, 2, 3, 4];
    console.warn(`ARRAY ORIGINAL:`, ORIGINAL);

    const array1 = ORIGINAL.concat(5, 6, 7, 8, 9, [10, 11]);
    console.log(`[concat] Array 1:`, array1);

    const array2 = [...ORIGINAL].join(` `);
    console.log(`[join] Array 2:`, array2);

    const array3 = [...ORIGINAL];
    console.log(`[pop] Array 3:`, array3);
    console.log(`Se quita:`, array3.pop());
    console.log(`Despues:`, array3);

    const array4 = [...ORIGINAL]
    const ARRAY4_LEN = array4.push(5, 6, 7, 8, 9, [10, 11]);
    console.log(`[push] Array 4:`, array4);
    console.log(`Longitud:`, ARRAY4_LEN);

    const array5 = [...ORIGINAL];
    console.log(`[shift] Array 5:`, array5);
    console.log(`Se quita:`, array5.shift());
    console.log(`Despues:`, array5);

    const array6 = [...ORIGINAL]
    const ARRAY6_LEN = array6.unshift(5, 6, 7, 8, 9, [10, 11]);
    console.log(`[unshift] Array 6:`, array6);
    console.log(`Longitud:`, ARRAY6_LEN);

    const array7 = [...ORIGINAL].slice(1, 3);
    console.log(`[slice] Array 7:`, array7);

    const array8 = [...ORIGINAL];
    console.log(`[splice] Array 8:`, array8);
    console.log(`Se quita:`, array8.splice(1, 2));
    console.log(`Despues:`, array8);

    const array9 = [...ORIGINAL].reverse();
    console.log(`[reverse] Array 9:`, array9);

    const array10 = [...ORIGINAL].sort((a, b) => b - a);
    console.log(`[sort] Array 10:`, array10);

    const ORIGINAL2 = [1, `patata`, 3.3, `4`, true];
    console.warn(`ARRAY ORIGINAL 2:`, ORIGINAL2);

    const array11 = [];
    ORIGINAL2.forEach((e) => {
        if (!isNaN(e)) { array11.push(Number(e)) }
    });

    console.log(`[forEach] Array 11:`, array11);

    const array12 = ORIGINAL2.filter(e => !isNaN(e)).map(Number);
    console.log(`[map + filter] Array 12:`, array12);

    const ORIGINAL3 = [`perro`, `gato`, `loro`, `periquito`, `leon`];
    console.warn(`ARRAY ORIGINAL 3:`, ORIGINAL3);

    const array13 = ORIGINAL3.map(e => e.length);
    console.log(`[map] Array 13:`, array13);

    const array14 = ORIGINAL3.filter(e => e.startsWith(`p`));
    console.log(`[filter] Array 14:`, array14);
});

const funcionesBtn = insertElement(`button`, `Funciones`, `nav`);

funcionesBtn.addEventListener(`click`, () => {
    console.clear();

    function mayorLongitud(str1, str2) {
        if (typeof str1 !== `string` || typeof str2 !== `string`) {
            console.error("Uno de los parametros introducidos no es un string");
            return;
        }

        if (str1.length > str2.length)
            console.log(`La cadena mas larga es: ${str1}`);

        else if (str1.length < str2.length)
            console.log("La cadena mas larga es: " + str2);

        else
            console.log("Las cadenas son iguales");

    }

    mayorLongitud(`tarara`, `los pinos con la pinada`);
    mayorLongitud(`los pinos con la pinada`, `tarara`);
    mayorLongitud(`tarara`, `tarara`);
    mayorLongitud(2, `tarara`);

    function suma(...numeros) {
        let suma = 0;

        for (let i = 0; i < numeros.length; i++) {
            if (typeof numeros[i] !== `number`) {
                console.error("Uno de los parametros introducidos no es un numero");
                return;
            }

            suma += numeros[i];
        }

        console.log(`Suma de los parametros:`, suma);
    }

    suma()
    suma(1, 2, 3, 4)
    suma(1, 2, 3, 4, 5)
    suma(1, 2, 3, 4, 5, "a");
    suma(1, 2, 3, 4, 5, true);

    function mostrarArgumentos() {
        console.log("Número total de argumentos: " + arguments.length);

        for (let i = 0; i < arguments.length; i++) {
            console.log("Argumento " + (i + 1) + ": " + arguments[i]);
        }
    }

    mostrarArgumentos();
    mostrarArgumentos(1, "Hola", true);
    mostrarArgumentos("JavaScript", 42, [1, 2, 3], { key: "value" });

    function muestraProducto(nombre = `Producto generico`, precio = 100, impuesto = 21) {
        try {
            nombre = String(nombre);
            precio = Number(precio);
            impuesto = Number(impuesto);

        } catch (error) {
            console.error("Uno de los parametros introducidos no es valido");
            return;
        }

        console.log(`Nombre: ${nombre}\nPrecio total: ${Math.floor(precio * (1 + impuesto / 100))}`);
    }

    muestraProducto();
    muestraProducto(undefined, undefined, undefined);
    muestraProducto(undefined, undefined, 10);
    muestraProducto(undefined, 1000, undefined);
    muestraProducto(`Patatas`, 2, 10);
    muestraProducto(`Patatas`, 2, 10, 5);
    muestraProducto(`Patatas`, `2`, 10);
    muestraProducto(`Patatas`, 2, `10`);
    muestraProducto(`Patatas`, 2, `10`);
    muestraProducto(undefined, `2`, `10`);
});

const objetosBtn = insertElement(`button`, `Objetos`, `nav`);

objetosBtn.addEventListener(`click`, () => {
    console.clear();


    const objeto1 = {
        nombre: `Juan`,
        apellidos: `Garcia`,
        edad: 25,
        sexo: `H`,
        "likes rock": true
    };

    console.log(`Persona 1:`, objeto1);
    console.log("Nombre:", objeto1.nombre);
    console.log("Apellidos:", objeto1.apellidos);
    console.log("Edad:", objeto1[`edad`]);
    console.log("Sexo:", objeto1.sexo);
    console.log("Le gusta el rock:", objeto1[`likes rock`]);

    delete objeto1.edad;
    console.log(`Persona 1:`, objeto1);

    let fruta = prompt("¿Qué fruta comprar?", "Manzana");

    let bolsa = {
        [fruta]: 5
    };

    alert(bolsa[fruta]);
});