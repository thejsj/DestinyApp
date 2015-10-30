/**
 * Created by franzsilv1 on 10/30/2015.
 */
$(document).ready(function() {
    $(".mapDiv").click(function () {
        var selected = false;

        if (!$(this).find("div").hasClass("noDisplay")) {
            selected = true;
        }

        if (!selected) {
            $(this).addClass("selectedMapDiv");
            $(this).find("div").removeClass("noDisplay");
        } else {
            $(this).removeClass("selectedMapDiv");
            $(this).find("div").addClass("noDisplay");
        }
    });

    $(".mapDiv").hover(function() {
        $(this).css("border", "thin solid white");
    }, function() {
        if (!$(this).hasClass("selectedMapDiv")) {
            $(this).css("border", "none");
        }
    });
})