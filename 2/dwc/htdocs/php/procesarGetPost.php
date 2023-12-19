<?php
// Cabeceras para no hacer cache de los datos.
header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 26 Jul 1997 05:00:00 GMT');
// pausa para mostrar un gif animado 
sleep(4);
echo "<br>";

if (isset($_GET['nombre']) && isset($_GET['apellidos'])) {
    // si se recibe el parámetro nombre mediante GET
    echo "Saludos desde el servidor: Hola {$_GET['nombre']} {$_GET['apellidos']}.";
} else if (isset($_POST['nombre']) && isset($_POST['apellidos'])) {
    // si se recibe el parámetro nombre mediante POST
    echo "Saludos desde el servidor: Hola {$_POST['nombre']} {$_POST['apellidos']}.";
} else {
    echo "No se recibieron datos válidos.";
}

// Mostramos la fecha y hora del servidor web.
echo "<br> <br> La fecha y hora del Servidor Web: ";
echo date("j/n/Y G:i:s");
?>




