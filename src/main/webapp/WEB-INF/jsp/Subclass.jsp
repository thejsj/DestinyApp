<%@include file="/WEB-INF/Includes/header.jsp"%>

<script>
    window.onload = function() {
        $("#classTab").addClass("selectedNav");
    }
</script>

<div id="subclassContainer">
    <div id="subclassInfo" class="block autoMargin">
        <br/>
        <div id="subclassInfoTable" class="block autoMargin">
            <table>
                <tr>
                    <td>
                        <img class="subclassLogo" src="${subclassObj.getImageLink()}" height="75">
                    </td>
                    <td>
                        <h2 class="subclassTitle">${subclassObj.getSubclassName()}</h2>
                        <br/>
                        <h4 class="subTitle">(${subclassObj.getBurn().getBurnName()} ${subclassObj.getGuardianClass().getClassName()} subclass)</h4>
                    </td>
                </tr>
            </table>
            <hr>
        </div>


        <p>${subclassObj.getDescription()}</p>
    </div>



    <div id="subclassTable" class="block autoMargin">
        <br/>
        <p><b>Ability tree</b></p>
        <table>
            <tr>
                <c:forEach items="${firstCol}" var="ability" varStatus="loop">
                    <td>
                        <div class="abilityCircle">
                            <div class="abilityIcon">
                                <img src="${iconList1.get(loop.index)}" height="40">
                            </div>
                            <div class="abilityText">
                                <b>${ability.getAbilityName()}</b>
                                <br/>
                                ${ability.getDescription()}
                            </div>
                        </div>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach items="${secondCol}" var="ability" varStatus="loop">
                    <td>
                        <div class="abilityCircle">
                            <div class="abilityIcon">
                                <img src="${iconList1.get(loop.index)}" height="40">
                            </div>
                            <div class="abilityText">
                                <b>${ability.getAbilityName()}</b>
                                <br/>
                                ${ability.getDescription()}
                            </div>
                        </div>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach items="${thirdCol}" var="ability" varStatus="loop">
                    <td>
                        <div class="abilityCircle">
                            <div class="abilityIcon">
                                <img src="${iconList1.get(loop.index)}" height="40">
                            </div>
                            <div class="abilityText">
                                <b>${ability.getAbilityName()}</b>
                                <br/>
                                ${ability.getDescription()}
                            </div>
                        </div>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <td></td>
                <c:forEach items="${fourthCol}" var="ability" varStatus="loop">
                    <td>
                        <div class="abilityCircle">
                            <div class="abilityIcon">
                                <img src="${iconList2.get(loop.index)}" height="40">
                             </div>
                            <div class="abilityText">
                                <b>${ability.getAbilityName()}</b>
                                <br/>
                                ${ability.getDescription()}
                            </div>
                        </div>
                    </td>
                </c:forEach>
            </tr>
        </table>
    </div>
</div>

<br/>
<br/>

<%@include file="/WEB-INF/Includes/footer.jsp"%>