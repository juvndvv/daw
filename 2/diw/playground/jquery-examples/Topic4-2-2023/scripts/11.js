        $(function () {
            var texto = $("p.primero").text();
            alert(texto);
            $("p.segundo").html(texto);
            $("p.tercero").html($("p.primero").html());
            /* $("p.segundo").html($("p.primero").text()); */
        });