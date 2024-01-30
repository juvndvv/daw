$(function () {
    $("form + p").append($("input").map(function () {
        var valor= $(this).val();
        return valor;
    }).get().join(", "));
});

// .map() recorre los inputs añadidos al conjunto, toma los valores,los encadena con","
// y añade la cadena encadenada al párrafo que hay a continuación del formulario "form + p"