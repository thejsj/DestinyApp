<%@include file="/WEB-INF/Includes/header.jsp"%>
<script>
    window.onload = function() {
        $("#locationTab").addClass("selectedNav");
    }
</script>

<div id="locationContainer">
    <div id="locationContent" class="block autoMargin bodyWidth">
        <h3 class="titleSpacing centerText">Locations</h3>

        <c:forEach items="${planetList}" var="planet">
            <div class="planetSelect block autoMargin centerText">

                <a href="#">
                    <c:choose>
                        <c:when test="${planet.getPlanetName() != 'Saturn (The Dreadnaught)'}">
                            <h3 class="titleSpacing">${planet.getPlanetName()}</h3>
                        </c:when>
                        <c:otherwise>
                            <h3 class="titleSpacing">The Dreadnaught</h3>
                        </c:otherwise>
                    </c:choose>
                    <img class="planetPicture" src="${planet.getImageLink()}" width="350">
                </a>


            </div>
            <br/>
        </c:forEach>

    </div>
</div>

<br/>
<%@include file="/WEB-INF/Includes/footer.jsp"%>