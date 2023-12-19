<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Videoclub online</title>
  <link rel="stylesheet" href="./css/styles.css">
</head>
<body>
<header>
  <img src="./img/palomitas-de-maiz.png" alt="">
  <h1>Videoclub online</h1>
</header>
<main>
  <form action="resultado.jsp" method="post">
    <section class="texto" id="nombre">
      <h3>Nombre de la pelicula:</h3>
      <input type="text" name="nombre">
    </section>
    <section  class="texto" id="numero">
      <h3>Numero de dias:</h3>
      <input type="number" name="dias">
    </section>
    <section class="columna" id="edad">
      <h3>Edad cliente:</h3>
      <div class="opciones">
        <div class="opcion-edad">
          <input type="radio" name="edad" value="menos7">
          <p>Menos de 7 años</p>
        </div>

        <div class="opcion-edad">
          <input type="radio" name="edad" value="menos14">
          <p>Menos de 14 años</p>
        </div>

        <div class="opcion-edad">
          <input type="radio" name="edad" value="menos18">
          <p>Menos de 18 años</p>
        </div>
        <div class="opcion-edad">
          <input type="radio" name="edad" value="mayor18">
          <p>Más de 18 años</p>
        </div>
      </div>
    </section>
    <section class="columna" id="pago">
      <h3>Forma de pago:</h3>
      <div class="opciones">
        <select name="forma-pago" id="">
          <option value="visa">Visa</option>
          <option value="cheque">Cheque</option>
          <option value="contado">Contado</option>
          <option value="metalico">Metalico</option>
        </select>
      </div>
    </section>
    <section id="extras">
      <h3>Especificaciones extras:</h3>
      <textarea name="extras"></textarea>
    </section>
    <section id="botonera">
      <button type="submit" value="send" id="send-btn">Enviar petición</button>
      <button type="reset" id="clear-btn">Borrar formulario</button>
    </section>
  </form>
</main>
</body>

</html>