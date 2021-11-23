console.log("Welcome to dashboard");
    const html = document.getElementById("welcome");
    var n = localStorage.getItem("name");
    console.log(n);
    html.innerHTML = n;
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/employees", true)
    xhr.getResponseHeader("Content-Type", "application/json");
    xhr.onload = function () {
        employeedata = JSON.parse(xhr.responseText);
        console.log(employeedata);
        console.log("email:", employeedata[0].email);
        loadtabledata(employeedata);
    }
    xhr.send();


    function loadtabledata(employeedata) {
        const tablebody = document.getElementById("tabledata");
        let dataHTML = "";
        for (let employee of employeedata) {
            dataHTML += "<tr><td>" + employee.id + "</td><td>" + employee.name + "</td><td>" + employee.dob + "</td><td>" + employee.age + "</td><td>" + employee.mob + "</td><td>" + employee.presentAddress + "</td><td>" + employee.permanentAddress + "</td><td>" + employee.email + "</td><td><button  onclick='update("+employee.id+")'>edit</button></td></tr>"
        }
        console.log(dataHTML);
        tablebody.innerHTML = dataHTML;
    }

    function update(id){
        localStorage.setItem("id2",id);
        window.location.href="updateadmin.html"; 
    }

    function logoutbtnhandler(){
        window.location.href="login.html";
    }