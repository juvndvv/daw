<?php
header('Content-Type: application/json');
header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 26 Jul 1997 05:00:00 GMT');

$servidor = "localhost";   // Configuración BD
$basedatos = "coop";
$usuario = "coop";
$password = "coop";

// --  Crear la conexión al servidor y ejecutar la consulta.
$conexion = mysqli_connect($servidor, $usuario, $password) or die(mysqli_error($conexion));
mysqli_query($conexion, "SET NAMES 'utf8'");
mysqli_select_db($conexion, $basedatos) or die(mysqli_error($conexion));

// --  párametro opción para determinar la select a realizar -------
if (isset($_GET['opcion']))
    $opc = $_GET['opcion'];
else if (isset($_POST['opcion']))
    $opc = $_POST['opcion'];

// -- parámetros socio para consulta socio registrado (SR) y registrar socio (RS)      
if (isset($_GET['email']))
    $ema = $_GET['email'];
else if (isset($_POST['email']))
    $ema = $_POST['email'];
if (isset($_GET['password']))
    $pas = $_GET['password'];
else if (isset($_POST['password']))
    $pas = $_POST['password'];
if (isset($_GET['nombre']))
    $nom = $_GET['nombre'];
else if (isset($_POST['nombre']))
    $nom = $_POST['nombre'];
if (isset($_GET['apellidos']))
    $ape = $_GET['apellidos'];
else if (isset($_POST['apellidos']))
    $ape = $_POST['apellidos'];

//-- parametros articulo para registrar articulo en venta (RA)
if (isset($_GET['categoria']))
    $cat = $_GET['categoria'];
else if (isset($_POST['categoria']))
    $cat = $_POST['categoria'];
if (isset($_GET['nombre']))
    $art = $_GET['nombre'];
else if (isset($_POST['nombre']))
    $art = $_POST['nombre'];
if (isset($_GET['descripcion']))
    $des = $_GET['descripcion'];
else if (isset($_POST['descripcion']))
    $des = $_POST['descripcion'];
if (isset($_GET['precio']))
    $pre = $_GET['precio'];
else if (isset($_POST['precio']))
    $pre = $_POST['precio'];
if (isset($_GET['imagen']))
    $img = $_GET['imagen'];
else if (isset($_POST['imagen']))
    $img = $_POST['imagen'];
if (isset($_GET['vendedor']))
    $ven = $_GET['vendedor'];
else if (isset($_POST['vendedor']))
    $ven = $_POST['vendedor'];

// -- parámetro id articulo comprado (CAV) cambiar
if (isset($_GET['idarticulo']))
    $ida = $_GET['idarticulo'];
else if (isset($_POST['idarticulo']))
    $ida = $_POST['idarticulo'];

switch ($opc) {
    case "ES": // Existe socio
        $sql = "select * from socios where email = '" . $ema . "'";
        break;

    case "RS": // Registro nuevo Socio
        $sql = "insert into socios (nombre, apellidos, email, password) 
         values ('" . $nom . "',' " . $ape . "','" . $ema . "', '" . $pas . " ')";
        break;

    case "SR": // consulta Socio Registrado
        $sql = "select * from socios where email = '" . $ema . "' and password = '" . $pas . "'";
        break;

    case "AV": // consulta Articulos en Venta
        $sql = "select * from articulos where estado = 'D'";
        break;

    default:
        break;
}

$datos = null;
try {
    $resultados = mysqli_query($conexion, $sql);

    // Si la opcion es registrar socio (RS) y la consulta es correcta, devolver ok=true
    if ($opc == "RS") {
        if ($resultados == true) {
            echo json_encode(["ok" => true]);
            exit;
        }
    }

    if (($resultados != null) && ($resultados != '')) {
        while ($fila = mysqli_fetch_array($resultados)) {
            $datos[] = $fila;
        }
    }
    echo json_encode($datos);
} catch (Exception $e) {
    echo false;
}
mysqli_close($conexion);
?>