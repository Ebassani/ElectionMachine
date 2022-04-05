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
    <form method="post" action="/user-management">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="id" id="edit-id">
        <label for="edit-names">Names</label><input type="text" name="names" id="edit-names">
        <label for="edit-surnames">Surnames</label><input type="text" name="surnames" id="edit-surnames">
        <label for="edit-region"></label><select name="region" id="edit-region">
            <#list regions as region>
                <option value="${region.region}">${region.region}</option>
            </#list>
        </select>
        <label for="edit-age">Age</label><input type="text" name="age" id="edit-age">
        <span>Candidate</span>
        <label for="edit-admin" hidden>Admin</label><input type="checkbox" name="admin" id="edit-admin">
        <span>Admin</span>
        <input type="submit" value="Save">
    </form>
</div>

<div class="dialog dialog--delete" style="display: none">
    <span>Are you sure you want to delete <span id="delete-name"></span>?</span>
    <form action="/user-management" method="post">
        <input type="hidden" name="action" value="delete">
        <input type="hidden" name="id" id="delete-id">
        <input type="button" name="cancel" value="Cancel">
        <input type="submit" name="confirm" value="Confirm">
    </form>
</div>

<div class="create-dialog" style="display: none">

</div>

<div class="candidates">
    <#list users as user>
        <div class="candidate" data-user-id="${user.id}" data-user-names="${user.names}"
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
            <div class="button button__edit" onclick="editCandidate(${user.id})">Edit</div>
            <div class="button button__delete" onclick="deleteCandidate(${user.id})">Delete</div>
        </div>
    </#list>
</div>

<script src="../js/user-management.js"></script>
</body>
</html>