$(document).ready(function () {
    var jump=250;
var bW=$("body").innerWidth();
var oW=$("div").outerWidth();
console.log("bW= " + bW + ",  oW= " + oW);
            //.color-primary-0 
            $("div").each(function (i) {                    
                    $(this).addClass("bcolor-primary-"+i);
                });            
            var count = 1;

            // executes only once the eventclick over the paragraph
            $("ul").one("click", function () {
                $(this).animate({
                    fontSize: "+=6px"
                });
            });

    function totheright(thisobj) {
        var aleft = thisobj.css("left");
        console.log("left = " + aleft);
        if (aleft + oW < bW - jump) {
            thisobj.animate({ left: '+=' + jump + 'px' }, 500);
        }
        else {
            thisobj.animate({ right: '0px' }, 100, function () { thisobj.animate({ left: '-=' + jump + 'px' }, 400); });
        }
        // NO funciona.. REVISAR
    }


            $("button").on("click", function () {
                /*$("#div1").delay("slow").fadeIn();
                $("#div2").delay("fast").fadeIn();
                $("#div3").delay(1000*count++).fadeIn();
                $("#div4").delay(1000*count++).fadeIn();
                $("#div5").delay(1000*count++).fadeIn();*/

                /* with .each() id no longer neccesary */
                var j;
                $("div").each(function (i) {
                    j = i + 1;
                    $(this).stop(true, false).fadeOut();
                   // $(this).delay(i * 500).fadeIn(j * 500).totheright($(this));
                    $(this).delay(i * 500).fadeIn(j * 500).animate({
                        left: '+=' + jump + 'px'
                    }, 500);
                });
            });

                $("div").on("click", function () {
                    $(this).stop(true, false).animate({
                        opacity: '0'
                    }, 500).delay(500).animate({
                        left:  '-=' + jump + 'px', 
                        opacity: "1"
                    }, 500);
                });


        });
