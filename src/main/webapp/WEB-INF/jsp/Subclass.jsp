<%@include file="/WEB-INF/Includes/header.jsp"%>

<script>
    window.onload = function() {
        $("#classTab").addClass("selectedNav");
    }
</script>

<table>
    <tr>
        <c:forEach begin="1" end="8" varStatus="loop">
            <td>
                <div class="abilityCircle">
                    <div class="abilityText">

                    </div>
                </div>
            </td>
        </c:forEach>
    </tr>
    <tr>
        <c:forEach begin="1" end="8" varStatus="loop">
            <td>
                <div class="abilityCircle">
                    <div class="abilityText">

                    </div>
                </div>
            </td>
        </c:forEach>
    </tr>
    <tr>
        <c:forEach begin="1" end="8" varStatus="loop">
            <td>
                <div class="abilityCircle">
                    <div class="abilityText">

                    </div>
                </div>
            </td>
        </c:forEach>
    </tr>
    <tr>
        <td></td>
        <c:forEach begin="1" end="3" varStatus="loop">
            <td>
                <div class="abilityCircle">
                    <div class="abilityText">

                    </div>
                </div>
            </td>
        </c:forEach>
    </tr>
</table>


<%@include file="/WEB-INF/Includes/footer.jsp"%>