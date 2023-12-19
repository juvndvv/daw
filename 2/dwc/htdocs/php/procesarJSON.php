<?php
  // -------  cabeceras indicando que se envian datos JSON.
  header('Content-Type: application/json');
  header('Cache-Control: no-cache, must-revalidate');
  header('Expires: Mon, 26 Jul 1997 05:00:00 GMT');
  
  // -------  en el archivo dwcArticuloslibros.sql está la creación del usuario la bd y las tablas en MySQL.
  sleep(3);
  $servidor = "localhost";   // Configuración BD
  $basedatos = "ajaxdwc";
  $usuario = "dwc";
  $password = "dwc";
  
  // -------   Crear la conexión al servidor y ejecutar la consulta.
  $conexion= new mysqli($servidor, $usuario, $password) or die(mysqli_error($conexion));
  ;
  mysqli_query($conexion,"SET NAMES 'utf8'");
   mysqli_select_db($conexion,$basedatos) or die(mysqli_error($conexion));


  $sql="select f.nombre, a.idarticulo, a.descripcion, a.precioventa, a.stockminimo, a.proveedor
       from articulos a, familias f where f.idfamilia = a.familia order by a.idarticulo, a.familia";     

  $resultados=mysqli_query($conexion,$sql) or die(mysqli_error($conexion));

  $datos=null;
  while ( $fila = mysqli_fetch_array($resultados))
  {
      $datos[]=$fila;                    // Almacenar en un array cada filas del recordset.
  }
  echo json_encode($datos);              // función de PHP que convierte a formato JSON el array.
  
  mysqli_close($conexion);
?>

