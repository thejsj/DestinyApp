/**
 * Created by franzsilv1 on 10/27/2015.
 */
$(document).ready(function() {

   $(".abilityCircle").hover(
       function() {
        $(this).find("div.abilityText").show();
   }, function() {
        $(this).find("div.abilityText").hide();
   });

});