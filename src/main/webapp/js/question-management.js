function getQuestion(id,question) {
    document.getElementById("question").value = question
    document.getElementById("q_id").value = id
    toVisible("edit")
}

function delQuestion(id) {
    document.getElementById("id").value = id
    toVisible("delete")
}

function toHidden(id){
    document.getElementById(id).classList.add("hidden")
}

function toVisible(id){
    document.getElementById(id).classList.remove("hidden")
}