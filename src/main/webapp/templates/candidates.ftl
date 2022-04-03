<#-- @ftlvariable name="candidates" type="java.util.List<com.github.ebassani.electionmachine.data.model.User>" -->
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Candidates</title>
</head>
<body>



<#list candidates as candidate>
    <div class="candidate" data-user-id="${candidate.id}">
        <span>${candidate.names}</span>
        <span>${candidate.surnames}</span>
        <span>${candidate.age}</span>
        <span>${candidate.region}</span>
        <div class="button">Edit</div>
        <div class="button">Delete</div>
    </div>
</#list>

</body>
</html>