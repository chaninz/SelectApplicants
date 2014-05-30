<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--<nav class="top-bar" data-options="is_hover:false">-->
<nav class="top-bar" data-topbar data-options="is_hover: false">
    <ul class="title-area">
        <!-- Title Area -->
        <li class="name">
            <!--                        <h1><a href="Home">WIP Camp</a></h1>-->
            <a href="Home"><img src="images/tiny-logo.png" /></a>
        </li>
        <!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
        <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
    </ul>

    <section class="top-bar-section">
        <c:choose>
            <c:when test="${!empty user}">
                <!-- Right Nav Section -->
                <ul class="right">
                    <!--                    <li><img src="images/tiny-logo.png" /></li>-->
                    <li class="has-form">
                        <form action="Search" method="post">
                            <div class="row collapse">

                                <div class="small-9 columns">
                                    <input type="text" name="keyword" style="height: 2.1em" pattern="^(?!%$).+" />
                                </div>
                                <div class="small-3 columns">
                                    <input class="button expand" type="submit" value="Search"/>
                                </div>

                            </div>
                        </form>
                    </li>
                    <c:choose>
                        <c:when test="${user.type == 1}">
                            <li class="divider"></li>
                            <li class="has-dropdown"><a href="#">Admin</a>
                                <ul class="dropdown">
                                    <!--                    <li><label>Dropdown Level 1 Label</label></li>-->
                                    <li><a href="ViewUsers"><i class="fa fa-group"></i> Users</a></li>
                                    <li><a href="ViewQuestions"><i class="fa fa-question-circle"></i> Questions</a></li>
                                    <li><a href="Summary"><i class="fa fa-list-ol"></i> Summary</a></li>
                                    <li><a href="Progress"><i class="fa fa-bar-chart-o"></i> Progress</a></li>
                                    <li class="divider"></li>
                                </ul>
                            </li>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${user.type == 2}">
                            <li class="divider"></li>
                            <li class="has-dropdown"><a href="#">Manage</a>
                                <ul class="dropdown">
                                    <li><a href="Summary"><i class="fa fa-list-ol"></i> Summary</a></li>
                                    <li><a href="Progress"><i class="fa fa-bar-chart-o"></i> Progress</a></li>
                                    <li class="divider"></li>
                                </ul>
                            </li>
                        </c:when>
                    </c:choose>

                    <!--Dropdown for current user setting-->
                    <li class="divider"></li>
                    <li class="has-dropdown"><a href="#"><i class="fa fa-user"></i> ${user.displayname}</a>
                        <ul class="dropdown">
                            <li><a href="EditProfile"><i class="fa fa-pencil-square-o"></i> Edit Profile</a></li>
                            <li><a href="Logout"><i class="fa fa-sign-out"></i> Sign Out</a></li>
                            <li class="divider"></li>
                        </ul>
                    </li>
                </ul>
            </c:when>
        </c:choose>
    </section>
</nav>