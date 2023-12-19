<%--
  Created by IntelliJ IDEA.
  User: jdani
  Date: 27/09/2023
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login de usuarios</title>
</head>
<body>
    <form action="login" method="post">
        <h3>Formulario de login de usuarios</h3>
        <label>
            Nombre de usuario*:
            <input type="text" name="usuario" id="usuario"/>
        </label>
        <br>
        <label>
            Clave de acceso*:
            <input type="password" name="password" id="password" />
        </label>
        <p>Los campos marcados con un asterisco deben rellenarse de forma obligatoria</p>
        <button type="submit">Enviar</button>
        <button type="reset">Restablecer</button>
    </form>
</body>
</html>
