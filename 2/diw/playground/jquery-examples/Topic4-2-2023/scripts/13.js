      $(function() 
	  {
		var $obj = $("<p>Test 3</p>");
		var parrafo = document.createElement("p");
		parrafo.appendChild(document.createTextNode("Test 4"));
		
        $(".inner").before("<p>Test</p>", "<p>Test2</p>", $obj, parrafo);
		$('main').after($('h2'));
      });	

	     /*     $(function() 
    {
    var $obj = $("<p>Test 3</p>");
    var parrafo = document.createElement("p");
    parrafo.appendChild(document.createTextNode("Test 4"));
    
        $(".inner").before("<p>Test</p>", "<p>Test2</p>", $obj, parrafo);
    $('.container').after($('h2'));
      });*/