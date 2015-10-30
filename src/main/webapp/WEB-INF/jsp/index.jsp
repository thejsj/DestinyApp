<%@include file="/WEB-INF/Includes/header.jsp"%>
<script>
    window.onload = function() {
        $("#homeTab").addClass("selectedNav");
    }
</script>
        <br/>
        <div id="homeContainer">
            <div id="homeContent" class="block autoMargin centerText bodyWidth">
                <h3 class="centerText">Welcome to my DestinyDB Application</h3>
                <p class="centerText">Click on a tab above to get started.</p>
                <div id="homeLogo" class="block autoMargin">
                    <img src="../Images/destiny_logo.jpg" height="150">
                </div>
            </div>
        </div>

<%@include file="/WEB-INF/Includes/footer.jsp"%>

