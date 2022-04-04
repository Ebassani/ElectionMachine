let overlay = document.querySelector(".overlay")

function editCandidate(id) {
    console.log(`clicked id ${id}`)
    document.querySelectorAll(".candidate").forEach((candidate) => {
        if (candidate.dataset.userId === id.toString()) {
            console.table(candidate)
            overlay.style.display = ""
            document.querySelector(".dialog--edit").style.display = ""
            document.querySelector("#edit-id").value = candidate.dataset.userId
            document.querySelector("#edit-names").value = candidate.dataset.userNames
            document.querySelector("#edit-surnames").value = candidate.dataset.userSurnames
            document.querySelector("#edit-admin").checked = candidate.dataset.userAdmin === "true"
            document.querySelector("#edit-age").value = candidate.dataset.userAge
            document.querySelector("#edit-region").value = candidate.dataset.userRegion
        }
    })
}

function deleteCandidate(id) {
    console.log(`clicked id ${id}`)
    document.querySelectorAll(".candidate").forEach((candidate) => {
        if (candidate.dataset.userId === id.toString()) {
            console.table(candidate)
            overlay.style.display = ""
            document.querySelector(".dialog--delete").style.display = ""
            document.querySelector("#delete-id").value = candidate.dataset.userId
            document.querySelector("#delete-name").innerHTML =
                candidate.dataset.userNames + " " + candidate.dataset.userSurnames
        }
    })
}