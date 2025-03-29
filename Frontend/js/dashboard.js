checkUser();

const params = new URLSearchParams(window.location.search);
const view = params.get("view");

// Only allowing logged in users to access
async function checkUser() {
  if (!(await verifyUser())) {
    redirectPage("login");
  }
}

// If no view, then equipment is taken as default
if (view == "infrastructure") writeEquipmentDetails(true);
else writeEquipmentDetails(false);

// Showing details of equipment/infrastructure
async function writeEquipmentDetails(infrastructure) {
  let responseJSON = null;

  if (infrastructure) responseJSON = await fetchData("public/infrastructure");
  else responseJSON = await fetchData("public/equipment");

  // Container for all Equipment
  outerContainer = document.querySelector(".equipmentContainer");

  responseJSON.forEach((equipment) => {
    let equipmentContainer = document.createElement("div");
    // equipmentContainer.setAttribute("data-id", equipment.id);
    equipmentContainer.classList.add("equipment", "container");

    let equipmentHead = document.createElement("div");
    equipmentHead.className = "headElements";
    equipmentHead.style.width = "400px";
    equipmentHead.style.justifyContent =
      userRole == "STUDENT" ? "center" : "space-between";

    let equipmentName = document.createElement("p");
    equipmentName.className = "generalTxt";
    equipmentName.textContent = equipment.name;

    if (userRole == "ADMIN") {
      // Update Equipment link
      let updateLink = document.createElement("a");
      updateLink.href = "#";
      updateLink.onclick = () => updateEquipment(equipment.id);

      // Update icon
      let updateIcon = document.createElement("i");
      updateIcon.classList.add("fa-regular", "fa-pen-to-square");
      updateIcon.id = "editBtn";

      updateLink.appendChild(updateIcon);

      // Delete Equipment link
      let deleteLink = document.createElement("a");
      deleteLink.href = "#";
      deleteLink.onclick = () => deleteEquipment(equipment.id);

      // Delete icon
      let deleteIcon = document.createElement("i");
      deleteIcon.classList.add("fa-regular", "fa-trash-can");
      deleteIcon.id = "deleteBtn";

      deleteLink.appendChild(deleteIcon);

      equipmentHead.appendChild(updateLink);
      equipmentHead.appendChild(equipmentName);
      equipmentHead.appendChild(deleteLink);
    } else equipmentHead.appendChild(equipmentName);

    let detailsContainer = document.createElement("div");
    detailsContainer.className = "detailsContainer";

    let detailsHeader = document.createElement("div");
    detailsHeader.className = "detailsHeader";

    detailsHeader.appendChild(
      Object.assign(document.createElement("p"), { textContent: "Title" })
    );
    detailsHeader.appendChild(
      Object.assign(document.createElement("p"), { textContent: "Info" })
    );

    detailsContainer.appendChild(detailsHeader);

    let keys = Object.keys(equipment);

    // Excluding Id & Name from detailsContainer
    for (let i = 2; i < keys.length; i++) {
      let details = document.createElement("div");
      details.className = "details";

      details.appendChild(
        Object.assign(document.createElement("p"), {
          textContent: `${formatText(keys[i])}:`,
        })
      );

      // Retaining formatting as entered by Admin except for:
      // Availability: UNDER_MAINTIANENCE -> Under Maintanence
      let info = equipment[keys[i]];
      if (keys[i] == "availabilityStatus")
        info = formatText(equipment[keys[i]]);

      details.appendChild(
        Object.assign(document.createElement("p"), {
          textContent: info,
        })
      );

      detailsContainer.appendChild(details);
    }

    equipmentContainer.appendChild(equipmentHead);
    equipmentContainer.appendChild(detailsContainer);

    // Adding Request creation input elements
    if (userRole == "STUDENT") {
      let appBtn = document.createElement("button");
      appBtn.className = "appBtn";
      appBtn.textContent = "Create Request";

      if (infrastructure) {
        let select = document.createElement("select");
        select.className = "inputTextBoxes";

        let start = new Date(`2000-01-01T${equipment.openingTime}`);
        let end = new Date(`2000-01-01T${equipment.closingTime}`);

        // 1 hr Time Slots between opening and closing time to book from
        while (start < end) {
          let nextHour = new Date(start);
          nextHour.setHours(start.getHours() + 1); // Add 1 hour

          if (nextHour > end) break;

          let option = document.createElement("option");
          let slotLabel = `${formatTime(start)} - ${formatTime(nextHour)}`;
          option.value = formatTime(start);
          option.textContent = slotLabel;

          select.appendChild(option);
          start = nextHour; // Move to the next slot
        }

        equipmentContainer.appendChild(select);

        appBtn.onclick = () => createInfraRequest(equipment.id, select.value);
      } else {
        let quantity = document.createElement("input");
        quantity.className = "inputTextBoxes";
        quantity.type = "number";
        quantity.placeholder = "Request Quantity";

        let duration = document.createElement("input");
        duration.className = "inputTextBoxes";
        duration.placeholder = "Request Duration";

        equipmentContainer.appendChild(quantity);
        equipmentContainer.appendChild(duration);

        appBtn.onclick = () =>
          createEquipmentRequest(equipment.id, quantity.value, duration.value);
      }

      equipmentContainer.appendChild(appBtn);
    }

    outerContainer.appendChild(equipmentContainer);
  });

  // Button to add Equipment/Infra for ADMIN
  if (userRole == "ADMIN") {
    let addButton = document.createElement("button");
    addButton.className = "floatingBtn";
    addButton.textContent = "+";
    addButton.onclick = () => addEquipment();
    document.body.appendChild(addButton);
  }
}

// Redirecting page for updating equipment/infrastructure
function updateEquipment(equipmentId) {
  if (view == "infrastructure")
    window.location.href = `add-edit-infrastructure.html?view=infrastructure&id=${equipmentId}`;
  else
    window.location.href = `add-edit-equipment.html?view=equipment&id=${equipmentId}`;
}

// Redirecting page for adding equipment/infrastructure
function addEquipment() {
  if (view == "infrastructure")
    window.location.href = `add-edit-infrastructure.html?view=infrastructure`;
  else window.location.href = `add-edit-equipment.html?view=equipment`;
}

// Deletion of equipment/infrastructure
async function deleteEquipment(equipmentId) {
  let endpoint;

  if (view == "infrastructure")
    endpoint = `admin/infrastructure/${equipmentId}`;
  else endpoint = `admin/equipment/${equipmentId}`;

  if (userRole == "ADMIN") {
    if (await deleteData(endpoint)) {
      showInfo("Removed successfully");

      setTimeout(() => {
        if (view == "infrastructure")
          window.location.href = "dashboard.html?view=infrastructure";
        else redirectPage("dashboard");
      }, 3000);
    }
  } else raiseErrors("Only Admin can perform this action");
}

// Dynamic Formatting of JSON Data
function formatText(key) {
  return key
    .replace(/_/g, " ") // Replaces underscores (_) with spaces
    .replace(/([a-z])([A-Z])/g, "$1 $2") // Inserts space before uppercase letters in camelCase
    .toLowerCase() // Convert everything to lowercase
    .replace(/\b\w/g, (char) => char.toUpperCase()); // Capitalize first letter of each word
}

function formatTime(date) {
  return date.toTimeString().substring(0, 5);
}

async function createInfraRequest(infraId, timeSlot) {
  let responseJSON = null;

  responseJSON = await putData(
    "POST",
    `student/infrastructure/${infraId}/infrastructure-request`,
    JSON.stringify({
      requestedFor: timeSlot,
    })
  );

  if (responseJSON != null) {
    showInfo("Request created successfully");
  }

  setTimeout(() => {
    redirectPage("infrastructure");
  }, 3000);
}

async function createEquipmentRequest(
  equipmentId,
  requestQuantity,
  requestDuration
) {
  let responseJSON = null;

  responseJSON = await putData(
    "POST",
    `student/equipment/${equipmentId}/equipment-request`,
    JSON.stringify({
      quantity: requestQuantity,
      duration: requestDuration,
    })
  );

  if (responseJSON != null) {
    showInfo("Request created successfully");
  }

  setTimeout(() => {
    redirectPage("dashboard");
  }, 3000);
}
