checkUser();

// Only allows logged in users to access page
async function checkUser() {
  if (await verifyUser()) {
    if (userRole != "ADMIN") redirectPage("dashboard");
  } else {
    redirectPage("login");
  }
}

async function addEquipment() {
  equipmentDetails = document.querySelectorAll(".inputTextBoxes");

  var responseJSON = null;

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

  var responseJSON = null;

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
      redirectPage("dashboard");
    }, 3000);
  }
}
