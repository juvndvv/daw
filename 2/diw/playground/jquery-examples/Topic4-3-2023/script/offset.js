/* Tema4-3 15.js*/
$(function () {
    var offs;
    var i;
    var mensaje;
    $("p").on("click", function () {
        offs = $(this).offset();
        offs.left += 50;
        $(this).offset({
            top: offs.top,
            left: offs.left
        });
        mensaje= "(" + i + ") >>  offset:  left: " + offs.left + ", top: " + offs.top;
        $(this).find("span").text(mensaje);


    });

    $("p").each(function (i) {
        offs = $(this).offset();
        $(this).find("span").html("(" + i + ") >>  offset:  left: " + offs.left + ", top: " + offs.top);
    });
});
