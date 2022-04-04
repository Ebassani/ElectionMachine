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

<div class="edit-dialog" style="display: none">
    <form method="post" action="/user-management">
        <input type="hidden" name="id" id="edit-id">
        <label for="edit-names">Names</label><input type="text" name="names" id="edit-names">
        <label for="edit-surnames">Surnames</label><input type="text" name="surnames" id="edit-surnames">
        <label for="edit-region">Region</label><input type="text" name="region" id="edit-region">
        <label for="edit-age">Age</label><input type="text" name="age" id="edit-age">
        <span>Candidate</span>
        <label for="edit-admin" hidden>Admin</label><input type="checkbox" name="admin" id="edit-admin">
        <span>Admin</span>
        <input type="submit" value="Save">
    </form>
</div>

<div class="delete-dialog" style="display: none">
    <#--    U sure u wanna delete?-->
</div>

<div class="create-dialog" style="display: none">

</div>

<#list users as user>
    <div class="candidate" data-user-id="${user.id}" data-user-names="${user.names}"
         data-user-surnames="${user.surnames}" data-user-admin="${user.admin?string("true", "false")}"
         data-user-age="${user.age}" data-user-region="${user.region}">

        <span>${user.names}</span>
        <span>${user.surnames}</span>
        <#if user.admin>
            <span>Admin</span>
        <#else>
            <span>Candidate</span>
        </#if>

        <span>${user.age}</span>
        <span>${user.region}</span>
        <div class="button" onclick="editCandidate(${user.id})">Edit</div>
        <div class="button">Delete</div>
    </div>
</#list>

<script src="../js/user-management.js"></script>
</body>
</html>