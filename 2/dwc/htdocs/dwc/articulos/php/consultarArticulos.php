
<?php
  // --  cabeceras indicando que se envian datos JSON.
  header('Content-Type: application/json');
  header('Cache-Control: no-cache, must-revalidate');
  header('Expires: Mon, 26 Jul 1997 05:00:00 GMT');
  
  // -- en el archivo dwecarticulos2.sql están la bd y las tablas en MySQL.
  $servidor = "localhost";   // Configuración BD
  $basedatos = "ajaxdwc";
  $usuario = "dwc";
  $password = "dwc";
  
  // --  Crear la conexión al servidor y ejecutar la consulta.
  $conexion= new mysqli($servidor, $usuario, $password) or die(mysqli_error($conexion));
  mysqli_query($conexion, "SET NAMES 'utf8'");
  mysqli_select_db($conexion, $basedatos) or die(mysqli_error($conexion));
 
 
 // -- párametro opción para determinar la select a realizar -------
  if (isset($_GET['opcion'])) 
      $opc=$_GET['opcion'];
  else
     if (isset($_POST['opcion'])) //si se recibe el parámetro nombre mediante POST
        $opc=$_POST['opcion'];
 
 // -- pámetro familia para realizar la consulta de artículos por familia       
   if (isset($_GET['familia'])) //si se recibe el parámetro nombre mediante GET
      $fam=$_GET['familia'];
  else
     if (isset($_POST['familia'])) //si se recibe el parámetro nombre mediante POST
        $fam=$_POST['familia'];
 
 // -- parámetro proveedor para realizar la consulta de articulos por proveedor       
   if (isset($_GET['proveedor'])) //si se recibe el parámetro nombre mediante GET
      $pro=$_GET['proveedor'];
   else
     if (isset($_POST['proveedor'])) //si se recibe el parámetro nombre mediante POST
        $pro=$_POST['proveedor'];
   

// -- selección de la consulta sql a realizar --------  
// -- TA = todos los artículos; TF = todas las familias; TP = todos los proveedores;
// -- AF = artículos por familia; AP = artículos por proveedor; FF = imagen familia
switch ($opc) {
    case "TA":  
         $sql="select idarticulo, familia, descripcion, precioventa, proveedor from articulos order by idarticulo";     
         break;
    case "TF":
         $sql="select idfamilia, nombre, imagen from familias order by nombre";     
         break;
    case "TP":
         $sql="select idproveedor, nombre from proveedores order by idproveedor";     
         break;
    case "AF":
// -- $sql="select idarticulo, familia, descripcion, precioventa, proveedor from articulos where familia = '".$fam."'";
        $sql="SELECT idarticulo, familia, descripcion, precioventa, proveedor
        FROM articulos WHERE familia=(SELECT idfamilia FROM familias WHERE nombre='".$fam."')";
         break;
    case "AP":
// -- $sql="select idarticulo, familia, descripcion, precioventa, proveedor from articulos where proveedor = '".$pro."'";
        $sql="SELECT idarticulo, familia, descripcion, precioventa, proveedor
        FROM articulos WHERE proveedor=(SELECT idproveedor FROM proveedores WHERE nombre='".$pro."')";
         break;
    case "FF":
        $sql="select imagen from familias where nombre='".$fam."'";
        break;
}

  $resultados=mysqli_query($conexion, $sql) or die(mysqli_error($conexion));
  $datos=null;
  while ( $fila = mysqli_fetch_array($resultados))
  {
      $datos[]=$fila;         //-- Almacenar en un array cada filas del recordset.
  }
  echo json_encode($datos);   //-- función de PHP que convierte a formato JSON el array.
  
  mysqli_close($conexion);
?>

