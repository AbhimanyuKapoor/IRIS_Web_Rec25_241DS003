const serverUrl = "http://localhost:8080/";
let userDetails = null;
let userRole = null;
let jwtToken = null;

async function redirectPage(location) {
  if (location != "signup" && location != "login") {
    if (await verifyUser()) {
      if (location == "infrastructure") {
        window.location.href = "dashboard.html?view=infrastructure";
      } else {
        window.location.href = `${location}.html`;
      }
    } else window.location.href = "login.html";
  } else window.location.href = `${location}.html`;
}

async function login() {
  loginData = document.querySelectorAll(".inputTextBoxes");

  responseJSON = await authUser(
    "login",
    JSON.stringify({
      email: loginData[0].value.toLowerCase(),
      password: loginData[1].value,
    })
  );

  if (responseJSON != null) {
    localStorage.setItem("jwtToken", responseJSON.token);
    redirectPage("dashboard");
  }
}

async function signup() {
  signupData = document.querySelectorAll(".inputTextBoxes");

  responseJSON = await authUser(
    "signup",
    JSON.stringify({
      name: signupData[0].value,
      email: signupData[1].value.toLowerCase(),
      password: signupData[2].value,
      branch: signupData[3].value,
    })
  );

  if (responseJSON != null) {
    localStorage.setItem("jwtToken", responseJSON.token);
    redirectPage("dashboard");
  }
}

function logout() {
  localStorage.clear();
  setTimeout(() => {
    // Slight delay to avoid race conditions
    redirectPage("login");
  }, 10);
}

async function verifyUser() {
  jwtToken = localStorage.getItem("jwtToken");

  if (jwtToken != null) {
    response = await fetch(`${serverUrl}user/role`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        "Content-type": "application/json",
      },
    });

    if (response.ok) {
      userRole = await response.text();
      return true;
    } else return false;
  } else {
    return false;
  }
}

async function authUser(endpoint, payload) {
  response = await fetch(`${serverUrl}auth/${endpoint}`, {
    method: "POST",
    headers: {
      "Content-type": "application/json",
    },
    body: payload,
  });

  responseJSON = await response.json();

  if (!response.ok) {
    raiseErrors(responseJSON.message);
  } else return responseJSON;
}

function raiseErrors(message) {
  alert(message);
}

function showInfo(message) {
  const infoMessage = document.createElement("div");
  infoMessage.className = "toast";
  infoMessage.id = "infoMessage";
  infoMessage.textContent = message;
  document.body.appendChild(infoMessage);

  // Remove after 2.8 sec
  setTimeout(() => infoMessage.remove(), 2800);
}
