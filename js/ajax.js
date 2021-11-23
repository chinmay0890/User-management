console.log("This is login page");
function preventBack() { window.history.forward(); }
  setTimeout("preventBack()", 0);
  window.onunload = function () { null };

//-----------LOGIN PAGE-----------------------

//LOGIN - SHOW PASSWORD
function myFunction() {
  var x = document.getElementById("password");
  if (x.type === "password") {
    x.type = "text";
  }
  else {
    x.type = "password";
  }
}

//LOGIN - LOGIN BUTTON HANDLER
function buttonclickhandler() {
  console.log("you have clicked the button");
  var email = document.getElementById('email').value;
  //VALIDATING EMAIL
  if(validateEmail(email)){
    console.log("Email ID is valid");
  }
  else{
    window.alert("invalid email pattern");
  }
  var d = { email: email };
  console.log(d);
  const xh = new XMLHttpRequest();
  xh.open("POST", "http://localhost:8080/employee/getEmailDetails", true)
  xh.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xh.onload = function () {
    var emp = JSON.parse(xh.responseText);
    console.log(emp);
    localStorage.setItem("login_id", emp.id);

    if (email === "") {
      document.getElementById("emptyemail").style.display = "block";
    }
    else {
      document.getElementById("emptyemail").style.display = "none";
      var pwd = document.getElementById('password').value;
      
      if (pwd.length === 0) {
        document.getElementById("emptypassword").style.display = "block";
      }
      else {
        document.getElementById("emptypassword").style.display = "none";
        console.log("email:", email, " , password:", pwd);
        var data = {
          email: email,
          pwd: pwd
        };
        console.log(data);

        if (emp.status == 1) {
          const xhr = new XMLHttpRequest();
          xhr.open('POST', 'http://localhost:8080/employee/login', true);
          xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
          xhr.onload = function () {
            var data1 = xhr.responseText;
            console.log(data1);

            if (data1 === "user not found") {
              document.getElementById("invaliduser").style.display = "block";
              document.getElementById("email").value = "";
              document.getElementById("password").value = "";
            }
            else {
              document.getElementById("invaliduser").style.display = "none";
            }

            if (data1 === "incorrect password") {
              document.getElementById("incorrectpassword").style.display = "block";
              document.getElementById("password").value = "";
            }
            else {
              document.getElementById("incorrectpassword").style.display = "none"
            }

            if (data1 === "successfully logged in") {
              alert("Login successfully");
              if (emp.role == 1) {
                window.location.href = "dash_superadmin.html";
              }
              else if (emp.role == 2) {
                window.location.href = "dash_subadmin_edit.html";
              }
              else if (emp.role == 3) {
                window.location.href = "dash_subadmin_verify.html";
              }
              else {
                window.location.href = "dash_employee.html";
              }
            }
          }

          xhr.send(JSON.stringify(data));
        }
        else {
          window.alert("Verification is under progress");
        }
      }

    }


  }
  xh.send(JSON.stringify(d));
}


//VALIDATION
function validateEmail(email){
  const pattern = new RegExp('^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$');
  return pattern.test(email); 
}

function validatePassword(pwd){
  const pattern = new RegExp('^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&-+=()])(?=\\S+$).{8, 20}$');
  return pattern.test(pwd);
}

// function space()




//LOGIN - SIGNUP BUTTON HANDLER
function signupbtnhandler() {
  window.location.href = "signup.html";
}



//---------FORGOT PASSWORD PAGE-------------------

//FORGOT PASSWORD - SUBMIT BUTTON HANDLER
function submitbtnhandler() {
  console.log("you have clicked the submit button")
  var email = document.getElementById("confirmemail").value;
  if (email === "") {
    document.getElementById("emptyemail").style.display = "block";
    var empty = document.getElementById("emptyemail");
    var confirm = document.getElementById("confirmemail");
    confirm.addEventListener("input", function () { empty.style.display = "none" })
  }
  else {
    var newpwd = document.getElementById("new").value;
    if (newpwd.length === 0) {
      document.getElementById("emptypassword").style.display = "block";
      var empty = document.getElementById("emptypassword");
      var confirm = document.getElementById("new");
      confirm.addEventListener("input", function () { empty.style.display = "none" })
    }
    else {
      var repeat = document.getElementById("repeat").value;
      if (repeat.length === 0) {
        document.getElementById("emptyrepeat").style.display = "block";
        var empty = document.getElementById("emptyrepeat");
        var confirm = document.getElementById("repeat");
        confirm.addEventListener("input", function () { empty.style.display = "none" })
      }
      else {
        if (newpwd.localeCompare(repeat) != 0) {
          document.getElementById("match").style.display = "block";
          document.getElementById("new").value = "";
          document.getElementById("repeat").value = "";
          var empty1 = document.getElementById("match");
          var new2 = document.getElementById("new");
          new2.addEventListener("input", function () { empty1.style.display = "none" });
        }
        else {
          document.getElementById("match").style.display = "none";
          var data = {
            email: email,
            pwd: newpwd
          };
          console.log(data);
          const xhr = new XMLHttpRequest();
          xhr.open("POST", "http://localhost:8080/employee/forgotPassword", true);
          xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
          xhr.onload = function () {
            var data1 = xhr.responseText;
            if (data1 === "Incorrect email") {
              document.getElementById("incorrectemail").style.display = "block";
              document.getElementById("confirmemail").value = "";
              document.getElementById("new").value = "";
              document.getElementById("repeat").value = "";
            }
            else {
              document.getElementById("incorrectemail").style.display = "none";
            }
            if (data1 === "password successfully updated") {
              document.getElementById("updated").style.display = "block";
              document.getElementById("confirmemail").type = "hidden";
              document.getElementById("new").type = "hidden";
              document.getElementById("repeat").type = "hidden";
              document.getElementById("cancel").type = "hidden";
              document.getElementById("submit").type = "hidden";
              document.getElementById("lgn").style.display = "block";
            }


          }
          xhr.send(JSON.stringify(data));
        }
      }
    }
  }
}

//FORGOT PASSWORD - LOGIN BUTTON HANDLER
function lgnbuttonclickhandler() {
  window.location.href = "login.html";
}

//FORGOT PASSWORD - CANCEL BUTTON HANDLER
function cancelbtnhandler() {
  console.log("you have clicked the cancel button");
  window.location.href = "login.html";
}

//LOGIN - CHROME BUTTONS
function noBack() {
  window.history.forward();
}