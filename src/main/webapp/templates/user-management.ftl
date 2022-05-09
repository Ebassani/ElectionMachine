<#-- @ftlvariable name="regions" type="java.util.List<com.github.ebassani.electionmachine.data.model.Region>" -->
<#-- @ftlvariable name="users" type="java.util.List<com.github.ebassani.electionmachine.data.model.User>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Candidates</title>
    <link rel="stylesheet" href="../style/user-management.css">
</head>
<body>

<div class="overlay" style="display: none" onclick="cancelModals()"></div>

<div class="dialog dialog--edit" style="display: none">
    <form method="post" action="/rest/users/update" accept-charset="UTF-8" onsubmit="setTimeout(() => {location.reload()}, 1000)">
        <#--        <input type="hidden" name="action" value="edit">-->
        <input type="hidden" name="id" id="edit-id">
        <label for="edit-names">Names:</label><input type="text" name="names" id="edit-names">
        <label for="edit-surnames">Surnames:</label><input type="text" name="surnames" id="edit-surnames">
        <label for="edit-region">Region:</label><select name="region" id="edit-region">
            <#list regions as region>
                <option value="${region.region}">${region.region}</option>
            </#list>
        </select>
        <label for="edit-age">Age:</label><input type="text" name="age" id="edit-age">
        <label for="edit-admin">Admin</label>
        <input id="edit-admin" name="admin" type="radio" value="true" checked>
        <label for="edit-candidate">Candidate</label>
        <input id="edit-candidate" name="admin" type="radio" value="false">
        <input class="button" type="submit" value="Save">
    </form>
</div>

<div class="dialog dialog--delete" style="display: none">
    <span>Are you sure you want to delete <span id="delete-name"></span>?</span>
    <form action="/rest/users/delete" method="post" accept-charset="UTF-8" onsubmit="setTimeout(() => {location.reload()}, 1000)">
        <input type="hidden" name="action" value="delete">
        <input type="hidden" name="id" id="delete-id">
        <input class="button" type="button" name="cancel" value="Cancel" onclick="cancelModals()">
        <input class="button" type="submit" name="confirm" value="Confirm">
    </form>
</div>

<div class="dialog dialog--create" style="display: none">
    <form method="post" action="/rest/users/add" accept-charset="UTF-8" onsubmit="setTimeout(() => {location.reload()}, 1000)">
        <input type="hidden" name="action" value="create">
        <label for="create-email">Email:</label><input type="email" name="email" id="create-email" required>
        <label for="create-password">Password:</label><input type="password" name="password" id="create-password"
                                                             required>
        <label for="create-names">Names:</label><input type="text" name="names" id="create-names" required>
        <label for="create-surnames">Surnames:</label><input type="text" name="surnames" id="create-surnames" required>
        <label for="create-region">Region:</label><select name="region" id="create-region">
            <#list regions as region>
                <option value="${region.region}">${region.region}</option>
            </#list>
        </select>
        <label for="create-age">Age:</label><input type="text" name="age" id="create-age" required>
        <label for="create-admin">Admin</label>
        <input id="create-admin" name="admin" type="radio" value="true" required>
        <label for="create-candidate">Candidate</label>
        <input id="create-candidate" name="admin" type="radio" value="false" checked required>
        <input class="button" type="submit" value="Create user">
    </form>
</div>

<div class="header">
    <div class="left">
        <a href="/user-management"><h1>Users</h1></a>
    </div>
    <div class="right">
        <a class="nav-element" href="/admQuestions.jsp">Questions</a>
        <a class="nav-element" href="/logout">Log out</a>
        <div class="button" onclick="createCandidate()">Create user</div>
    </div>
</div>

<div class="candidates">
    <#list users as user>
        <div class="candidate <#if user.admin>admin<#else>candidate</#if>" data-user-id="${user.id}"
             data-user-names="${user.names}"
             data-user-surnames="${user.surnames}" data-user-admin="${user.admin?string("true", "false")}"
             data-user-age="${user.age}" data-user-region="${user.region}">

            <span>Names: ${user.names}</span>
            <span>Surnames: ${user.surnames}</span>
            <#if user.admin>
                <span>Role: Admin</span>
            <#else>
                <span>Role: Candidate</span>
            </#if>
            <span>E-mail: ${user.email}</span>
            <span>Age: ${user.age}</span>
            <span>Region: ${user.region}</span>
            <div class="buttons">
                <div class="button button__edit" onclick="editCandidate(${user.id})">Edit</div>
                <div class="button button__delete" onclick="deleteCandidate(${user.id})">Delete</div>
            </div>
        </div>
    </#list>
</div>

<script src="../js/user-management.js"></script>
</body>
</html>