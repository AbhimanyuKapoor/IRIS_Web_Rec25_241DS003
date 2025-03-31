checkUser();

const params = new URLSearchParams(window.location.search);
const equipmentId = params.get("id");
const view = params.get("view");

// Only allows logged in users to access page
async function checkUser() {
  if (await verifyUser()) {
    if (userRole != "ADMIN") {
      redirectPage("dashboard");
    } else {
      // If view == null, it is the default view (ie: Add Equipment/Infrastructure)
      if (view == "infrastructure") writeInfraDetails();
      else if (view == "equipment") writeEquipmentDetails();
    }
  } else {
    redirectPage("login");
  }
}

// Writing data of the existing Equipment to be edited
async function writeEquipmentDetails() {
  headText = document.querySelector(".generalTxt");
  appBtn = document.querySelector(".appBtn");

  if (equipmentId != null) {
    headText.textContent = "Update Equipment";
    appBtn.textContent = "Update Equipment";

    equipmentDetails = document.querySelectorAll(".inputTextBoxes");

    // Admin can update Availability Status and Quantity only
    for (let i = 0; i < 3; i++) {
      equipmentDetails[i].disabled = true;
      equipmentDetails[i].classList.add("disabled");
    }

    responseJSON = await fetchData(`public/equipment/${equipmentId}`);

    // Setting existing Equipment Details
    equipmentDetails[0].value = responseJSON.name;
    equipmentDetails[1].value = responseJSON.category;
    equipmentDetails[2].value = responseJSON.condition;
    equipmentDetails[3].value = responseJSON.quantity;
    equipmentDetails[4].value = responseJSON.availabilityStatus;
  }
}

async function writeInfraDetails() {
  headText = document.querySelector(".generalTxt");
  appBtn = document.querySelector(".appBtn");

  if (equipmentId != null) {
    headText.textContent = "Update Infrastructure";
    appBtn.textContent = "Update Infrastructure";

    infraDetails = document.querySelectorAll(".inputTextBoxes");

    // Admin can update Availability Status and Quantity only
    for (let i = 0; i < 6; i++) {
      infraDetails[i].disabled = true;
      infraDetails[i].classList.add("disabled");

      if (i == 2) i++;
    }

    responseJSON = await fetchData(`public/infrastructure/${equipmentId}`);

    // Setting existing Infrastructure Details
    infraDetails[0].value = responseJSON.name;
    infraDetails[1].value = responseJSON.location;
    infraDetails[2].value = responseJSON.capacity;
    infraDetails[3].value = responseJSON.quantity;
    infraDetails[4].value = responseJSON.openingTime;
    infraDetails[5].value = responseJSON.closingTime;
    infraDetails[6].value = responseJSON.availabilityStatus;
  }
}

function addEditEquipment() {
  if (equipmentId == null) addEquipment();
  else updateEquipment();
}

function addEditInfrastructure() {
  if (equipmentId == null) addInfrastructure();
  else updateInfrastructure();
}

async function addEquipment() {
  equipmentDetails = document.querySelectorAll(".inputTextBoxes");

  let responseJSON = null;

  responseJSON = await putData(
    "POST",
    "admin/equipment",
    JSON.stringify({
      name: equipmentDetails[0].value,
      category: equipmentDetails[1].value,
      condition: equipmentDetails[2].value,
      quantity: equipmentDetails[3].value,
      availabilityStatus: equipmentDetails[4].value,
    })
  );

  if (responseJSON != null) {
    showInfo("Equipment added successfully");

    equipmentDetails.forEach((element) => {
      element.value = "";
    });
    equipmentDetails[4].value = "AVAILABLE";

    setTimeout(() => {
      redirectPage("dashboard");
    }, 3000);
  }
}

async function addInfrastructure() {
  infrastructureDetails = document.querySelectorAll(".inputTextBoxes");

  let responseJSON = null;

  responseJSON = await putData(
    "POST",
    "admin/infrastructure",
    JSON.stringify({
      name: infrastructureDetails[0].value,
      location: infrastructureDetails[1].value,
      capacity: infrastructureDetails[2].value,
      quantity: infrastructureDetails[3].value,
      openingTime: infrastructureDetails[4].value,
      closingTime: infrastructureDetails[5].value,
      availabilityStatus: infrastructureDetails[6].value,
    })
  );

  if (responseJSON != null) {
    showInfo("Infrastructure added successfully");

    infrastructureDetails.forEach((element) => {
      element.value = "";
    });
    infrastructureDetails[6].value = "AVAILABLE";

    setTimeout(() => {
      redirectPage("infrastructure");
    }, 3000);
  }
}

async function updateEquipment() {
  equipmentDetails = document.querySelectorAll(".inputTextBoxes");

  let responseJSON = null;

  responseJSON = await putData(
    "PATCH",
    `admin/equipment/${equipmentId}`,
    JSON.stringify({
      quantity: equipmentDetails[3].value,
      availabilityStatus: equipmentDetails[4].value,
    })
  );

  if (responseJSON != null) {
    showInfo("Equipment updated successfully");

    setTimeout(() => {
      redirectPage("dashboard");
    }, 3000);
  }
}

async function updateInfrastructure() {
  infraDetails = document.querySelectorAll(".inputTextBoxes");

  let responseJSON = null;

  responseJSON = await putData(
    "PATCH",
    `admin/infrastructure/${equipmentId}`,
    JSON.stringify({
      quantity: infraDetails[3].value,
      availabilityStatus: infraDetails[6].value,
    })
  );

  if (responseJSON != null) {
    showInfo("Infrastructure updated successfully");

    setTimeout(() => {
      window.location.href = "dashboard.html?view=infrastructure";
    }, 3000);
  }
}
