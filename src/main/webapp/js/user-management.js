function editCandidate(id) {
    console.log(`clicked id ${id}`)
    document.querySelectorAll(".candidate").forEach((candidate) => {
        if (candidate.dataset.userId === id.toString()) {
            console.table(candidate)
            document.querySelector(".edit-dialog").style.display = ""
            document.querySelector("#edit-id").value = candidate.dataset.userId
            document.querySelector("#edit-names").value = candidate.dataset.userNames
            document.querySelector("#edit-surnames").value = candidate.dataset.userSurnames
            document.querySelector("#edit-admin").value = candidate.dataset.userAdmin
            document.querySelector("#edit-age").value = candidate.dataset.userAge
            document.querySelector("#edit-region").value = candidate.dataset.userRegion
        }
    })
}