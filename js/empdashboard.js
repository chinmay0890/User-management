console.log("Welcome to dashboard");
const html = document.getElementById("welcome1");
var n = localStorage.getItem("name");
var i = localStorage.getItem("id3");
console.log(n);
html.innerHTML = n;
const xhr = new XMLHttpRequest();
xhr.open("GET", "http://localhost:8080/employee/"+i, true)
xhr.getResponseHeader("Content-Type", "application/json");
xhr.onload = function () {
    employee = JSON.parse(xhr.responseText);
    console.log(employee);
    console.log("email:", employee.email);
    loadtabledata(employee);
}
xhr.send();


function loadtabledata(employee) {
    const tablebody = document.getElementById("tabledata");
    let dataHTML = "";
    dataHTML += "<tr><td>" + employee.id + "</td><td>" + employee.name + "</td><td>" + employee.dob + "</td><td>" + employee.age + "</td><td>" + employee.mob + "</td><td>" + employee.presentAddress + "</td><td>" + employee.permanentAddress + "</td><td>" + employee.email + "</td></tr>"
    //console.log(dataHTML);
    tablebody.innerHTML = dataHTML;
}

function update() {
    
    window.location.href = "updateemp.html";

}

function out(){
    window.location.href="login.html"
}