<%@include file="/WEB-INF/Includes/header.jsp"%>
<script>
    window.onload = function() {
        $("#locationTab").addClass("selectedNav");
    }
</script>
<%--<br/>--%>

<div id="planetInfoContainer">
    <div id="planetInfo" class="bodyWidth block autoMargin">
        <h3 class="centerText titleSpacing">${currPlanet.getPlanetName()}</h3>
        <hr>
        <p>Some planet information here, maybe, then below do divs to show map/zones/etc</p>

        <c:if test="${!currPlanet.getPlanetName().equals('Mercury')}">
        <div class="mapDiv block autoMargin centerText">
            <table class="classDivTable autoMargin">
                <tr>
                    <td width="40"><img src="../../Images/map.png" height="40"></td>
                    <td class="marginLeft shortCell">Map</td>
                </tr>
            </table>


            <div class="noDisplay">
                <p class="subText centerText">(click to enlarge)</p>
                <a href="/spring/map?plnt=${currPlanet.getPlanetId()}">
                    <img src="${currPlanet.getMapLink()}" width="350">
                </a>
                <br/>
            </div>
         </div>
        </c:if>

    </div>
</div>



<br/>
<%@include file="/WEB-INF/Includes/footer.jsp"%>