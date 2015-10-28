/**
 * Created by franzsilv1 on 10/27/2015.
 */
$(document).ready(function () {

    $(".classDiv").click(function () {
        var selected = false;

        if (!$(this).find("div").hasClass("noDisplay")) {
            selected = true;
        }

        if (!selected) {
            $(this).addClass("selectedClassDiv");
            $(this).find("div").removeClass("noDisplay");
        } else {
            $(this).removeClass("selectedClassDiv");
            $(this).find("div").addClass("noDisplay");
        }
    });

    $(".classDiv").hover(function() {
        $(this).css("border", "thin solid white");
    }, function() {
        if (!$(this).hasClass("selectedClassDiv")) {
            $(this).css("border", "none");
        }
    });
});