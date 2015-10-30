<%@include file="/WEB-INF/Includes/header.jsp"%>
<script>
    window.onload = function() {
        $("#locationTab").addClass("selectedNav");
    }
</script>
<br/>

<div id="bigMapContainer">
    <div class="block autoMargin" style="width: 800px">
        <h2 class="titleSpacing centerText">${currPlanet.getPlanetName()} Map</h2>
        <img class="subclassLogo" src="${currPlanet.getMapLink()}" width="800">
    </div>
</div>
<br/>
<br/>
<%@include file="/WEB-INF/Includes/footer.jsp"%>