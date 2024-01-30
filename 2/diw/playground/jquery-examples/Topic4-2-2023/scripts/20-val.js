        /*
        El método .val() lo usaremos para obtener o establecer el valor de los campos de form_box.

        En este ejemplo el valos del counter se incrementa cada vez que se recarga (actualiza) la página 
        */
// $(document).ready(function() {
// 	var counter = 0;
// 	//alert("counter actual: " + counter);

// 	$("#counter").val(++counter);

// 	// $("#c1").on("click", function () { $("#counter").val(++counter); });
// 	// $("#c2").on("click", function () { $("#counter").val(--counter); });
// 	$("#c1").click(function () { 
// 		++counter;
// 		$("input:text").val(counter); 
// 	});
// 	$("#c2").click(function () { 
// 		--counter;
// 		$("input:text").val(counter); 
// 	});
// });



$(document).ready(function () {
	var counter = 0;
	$("#contador").val(counter);

	$("#c1").on("click", function (event) {
		event.preventDefault();
		$("#contador").val(++counter);
	});
	$("#c2").on("click", function (event) {
		event.preventDefault();
		$("#contador").val(--counter);
	});

			
	// $("input:text").val(counter);
// $("#b1").click(function () {
	//     $("input:text").val(++counter);
	// });
	// $("#b2").click(function () {
	//     $("input:text").val(--counter);
	// });
});

// Para select y checkbox podemos utilizar el método .val() de la siguiente forma:

// // Obtener el valor de un desplegable
// $('select.foo option:selected').val();

// // Igual que el anterior, pero más fácil
// $('select.foo').val();

// // Obtener el valor de un checkbox chequeado
// $('input:checkbox:checked').val();

// // Obtener el valor del radio botón seleccionado en un grupo
// $('input:radio[name=bar]:checked').val();

