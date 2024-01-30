$(document).ready(function(){
var count=1;
var j=0;
    var i=0;
$("div").each(function (i) 
            {       
                $(this).css({"display": "none"});
            });


  $("button").click(function(){
    /*$("#div1").delay("slow").fadeIn();
    $("#div2").delay("fast").fadeIn();
    $("#div3").delay(1000*count++).fadeIn();
    $("#div4").delay(1000*count++).fadeIn();
    $("#div5").delay(1000*count++).fadeIn();*/

    $("div").each(function (i) 
      {       
        j=i+1;
        $(this).stop(true, false);
        $(this).delay(j*500).fadeIn(j*500).animate({left: '+=250px'}, 500);
      });
  });

});