<%@include file="/WEB-INF/Includes/header.jsp"%>

<script>
    window.onload = function() {
        $("#classTab").addClass("selectedNav");
    }
</script>

<div id="classTab">
    <div id="classDivContainer">
        <h3 class="centerText titleSpacing">Classes and Subclasses</h3>
        <c:forEach items="${classList}" var="clazz">
            <div class="classDiv block autoMargin centerText">
                <table class="classDivTable autoMargin">
                    <tr>
                        <td width="40"><img src="${clazz.getImageLink()}" height="40" width="40"></td>
                        <td class="floatLeft marginLeft">${clazz.getClassName()}</td>
                    </tr>
                </table>
                <div class="subclassDiv noDisplay" >
                    <p class="subclassDesc">${clazz.getDescription()}</p>
                    <p class="subclassDesc bodySpacing"><b>Subclasses:</b></p>

                    <c:forEach items="${subclassList}" var="subclass">
                        <c:if test="${subclass.getGuardianClass().getClassId() == clazz.getClassId()}">
                            <table class="classDivTable autoMargin">
                                <tr>
                                    <td width="35"><img src="${subclass.getImageLink()}" height="35" width="35"></td>
                                    <td class="floatLeft marginLeft">
                                        <a href="/spring/subclass">${subclass.getSubclassName()}</a>
                                    </td>
                                </tr>
                            </table>
                            <br/>
                        </c:if>
                    </c:forEach>
                </div>

            </div>
        </c:forEach>
        <br/>
    </div>
</div>

<%@include file="/WEB-INF/Includes/footer.jsp"%>