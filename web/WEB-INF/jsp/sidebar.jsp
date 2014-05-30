<div class="panel">
    <ul class="side-nav">
        <c:choose>
            <c:when test="${user.type == 1}">
                <li style="font-weight: bold;"><a href="ViewUsers"><i class="fa fa-group"></i> Users</a></li>
                <li>
                    <ul>
                        <li><a href="ViewUsers">All Users</a></li>
                        <li><a href="AddUser">Add New</a></li>
                    </ul>
                </li>
                <li class="divider"></li>
                <li style="font-weight: bold;"><a href="ViewQuestions"><i class="fa fa-question-circle"></i> Questions</a></li>
                <li>
                    <ul>
                        <li><a href="ViewQuestions">All Questions</a></li>
                        <li><a href="AddQuestion">Add New</a></li>
                    </ul>
                </li>
                <li class="divider"></li>
            </c:when>
        </c:choose>
        <li style="font-weight: bold;"><a href="Summary"><i class="fa fa-list-ol"></i> Summary</a></li>
        <li style="font-weight: bold;"><a href="Progress"><i class="fa fa-bar-chart-o"></i> Progress</a></li>
    </ul>
</div>