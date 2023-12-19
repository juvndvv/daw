<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/formulario1.css">
    <script defer src="scripts/main.js"></script>
    <title>Compra electrodomesticos</title>
</head>

<body>
    <main>
        <section id="formulario">
            <div class="button-wrapper">
                <button id="previous" class="nav-btn limit">&lt;</button>
            </div>
            <form action="formulario2.jsp" method="post">
                <section id="productos" class="entrada">
                    <h3>Productos</h3>
                    <div class="wrapper">
                        <section class="producto">
                            <input type="checkbox" name="productos" value="television" id="television">
                            <label for="television">Television</label>
                            <img src="img/tv.jpeg" alt="Televisión" height="50px">
                        </section>
                        <section class="producto">
                            <input type="checkbox" name="productos" value="cadena-musica" id="cadena-musica">
                            <label for="cadena-musica">Cadena de musica</label>
                            <img src="img/cadena.jpeg" alt="" height="50px">
                        </section>
                        <section class="producto">
                            <input type="checkbox" name="productos" value="frigorifico" id="frigorifico">
                            <label for="frigorifico">Frigorifico</label>
                            <img src="img/nevera.jpeg" alt="" height="50px">
                        </section>
                        <section class="producto">
                            <input type="checkbox" name="productos" value="vitroceramica" id="vitroceramica">
                            <label for="vitroceramica">Vitroceramica</label>
                            <img src="img/vitroceramica.jpeg" alt="" height="50px">
                        </section>
                    </div>
                </section>
                <section id="pago" class="entrada">
                    <h3><label for="forma-pago">Forma de pago</label></h3>
                    <div class="wrapper">
                        <select name="forma-pago" id="forma-pago">
                            <option value="visa">Visa</option>
                            <option value="contado">Contado</option>
                            <option value="cheque">Cheque</option>
                        </select>
                    </div>
                </section>
                <section id="envio-section" class="entrada">
                    <h3>Forma de envio</h3>
                    <div class="wrapper">
                        <section class="forma-envio">
                            <input type="radio" name="envio" id="recogida" value="recogida">
                            <label for="recogida">Recogida en tienda</label>
                        </section>
                        <section class="forma-envio">
                            <input type="radio" name="envio" id="envio" value="reparto">
                            <label for="envio">Enviar a casa</label>
                        </section>
                    </div>
                </section>
                <section id="caracteristicas-especiales-section">
                    <h3><label for="caracteristicas">Caracteristicas especiales</label></h3>
                    <div class="wrapper">
                        <textarea name="caracteristicas" id="caracteristicas"></textarea>
                    </div>
                </section>
                <section id="datos-personales" class="entrada">
                    <h3>Datos personales</h3>
                    <div class="wrapper">
                        <section class="texto">
                            <label for="nombre">Nombre</label>
                            <input type="text" name="nombre" id="nombre">
                        </section>
                        <section class="texto">
                            <label for="apellidos">Apellidos</label>
                            <input type="text" name="apellidos" id="apellidos">
                        </section>
                        <section class="texto">
                            <label for="dni">Dni</label>
                            <input type="text" name="dni" id="dni">
                        </section>
                        <section class="texto">
                            <label for="telefono">Telefono</label>
                            <input type="text" name="telefono" id="telefono">
                        </section>
                    </div>
                </section>
                <section id="submit-button" class="entrada">
                    <h3>Enviar datos</h3>
                    <div class="wrapper">
                        <button type="submit">Enviar</button>
                    </div>
                </section>
            </form>
            <div class="button-wrapper">
                <button id="next" class="nav-btn">></button>
            </div>
        </section>
    </main>
</body>

</html>