checkUser();

const infrastructure =
  window.location.pathname == "/equipment-requests.html" ? false : true;

async function checkUser() {
  if (!(await verifyUser())) {
    redirectPage("login");
  } else {
    if (infrastructure) writeInfraRequestDetails();
    else writeEquipmentRequestDetails();
  }
}

async function writeEquipmentRequestDetails() {
  let responseJSON = null;

  if (userRole == "ADMIN")
    responseJSON = await fetchData("admin/equipment-request");
  else responseJSON = await fetchData("student/equipment-request");

  // Container for all details
  detailsContainer = document.querySelector(".detailsContainer");

  if (userRole == "ADMIN") {
    document
      .querySelector(".detailsHeader")
      .appendChild(
        Object.assign(document.createElement("p"), { textContent: "Actions" })
      );
  }

  responseJSON.forEach((request, index) => {
    let details = document.createElement("div");
    details.className = "details";

    let select = document.createElement("select");
    select.className = "requestInput";
    select.style.marginBottom = "0px";

    if (userRole == "STUDENT") select.disabled = true;

    const options = ["PENDING", "APPROVED", "REJECTED"];
    options.forEach((status) => {
      const option = document.createElement("option");
      option.value = status;
      option.textContent = formatText(status);
      select.appendChild(option);
    });

    select.value = request.requestStatus;
    details.appendChild(select);

    let properties;

    if (userRole == "ADMIN") {
      properties = ["equipmentDto.name", "quantity", "duration"];
    } else {
      properties = [
        "equipmentDto.name",
        "quantity",
        "duration",
        "comments",
        "instructions",
      ];
    }

    properties.forEach((property) => {
      let detail = document.createElement("p");
      detail.className = "detailsTxt";
      detail.textContent = property
        .split(".")
        .reduce((obj, key) => obj?.[key], request);

      details.appendChild(detail);
    });

    if (userRole == "ADMIN") {
      let comments = document.createElement("input");
      comments.placeholder = "Comments";
      comments.className = "requestInputSmall";

      let instructions = document.createElement("input");
      instructions.placeholder = "Instructions";
      instructions.className = "requestInputSmall";

      details.appendChild(comments);
      details.appendChild(instructions);
    }

    if (userRole == "ADMIN") {
      let updateBtn = document.createElement("button");
      updateBtn.onclick = () => updateEquipmentRequest(request.id, index);
      updateBtn.classList.add("requestBtn");
      updateBtn.textContent = "Update";
      details.appendChild(updateBtn);
    }

    detailsContainer.appendChild(details);
  });
}

async function writeInfraRequestDetails() {
  let responseJSON = null;

  if (userRole == "ADMIN")
    responseJSON = await fetchData("admin/infrastructure-request");
  else responseJSON = await fetchData("student/infrastructure-request");

  // Container for all details
  detailsContainer = document.querySelector(".detailsContainer");

  responseJSON.forEach((request, index) => {
    let details = document.createElement("div");
    details.className = "details";

    let select = document.createElement("select");
    select.className = "requestInput";
    select.style.marginBottom = "0px";

    if (userRole == "STUDENT") select.disabled = true;

    const options = ["PENDING", "APPROVED", "REJECTED", "CANCELLED"];
    options.forEach((status) => {
      const option = document.createElement("option");
      option.value = status;
      option.textContent = formatText(status);

      if (status == "CANCELLED") option.disabled = true;

      select.appendChild(option);
    });

    select.value = request.requestStatus;
    details.appendChild(select);

    let properties = ["infrastructureDto.name", "requestedOn", "requestedFor"];

    properties.forEach((property) => {
      let detail = document.createElement("p");
      detail.className = "detailsTxt";

      if (property == "requestedFor") {
        let start = new Date(`2000-01-01T${request.requestedFor}`);
        let end = new Date(start);
        end.setHours(start.getHours() + 1);

        detail.textContent = `${formatTime(start)} - ${formatTime(end)}`;
      } else {
        detail.textContent = property
          .split(".")
          .reduce((obj, key) => obj?.[key], request);
      }

      details.appendChild(detail);
    });

    let requestBtn = document.createElement("button");
    requestBtn.className = "requestBtn";
    requestBtn.onclick = () => updateInfraRequest(request.id, index);

    if (userRole == "ADMIN") {
      requestBtn.textContent = "Update";
    } else {
      requestBtn.textContent = "Cancel";

      if (request.requestStatus != "APPROVED") {
        requestBtn.classList.add("disabled");
        requestBtn.disabled = true;
      }
    }

    details.appendChild(requestBtn);
    detailsContainer.appendChild(details);
  });
}

async function updateEquipmentRequest(equipmentRequestId, index) {
  requestStatusDetails = document.querySelectorAll(".requestInput");
  requestDetails = document.querySelectorAll(".requestInputSmall");

  if (requestStatusDetails[index].value == "PENDING")
    raiseErrors("Cannot Update Status to Pending");
  else {
    showInfo("Updating Request");

    let responseJSON = null;

    responseJSON = await putData(
      "PATCH",
      `admin/equipment-request/${equipmentRequestId}`,
      JSON.stringify({
        requestStatus: requestStatusDetails[index].value,
        comments: requestDetails[2 * index].value,
        instructions: requestDetails[2 * index + 1].value,
      })
    );

    if (responseJSON != null) {
      showInfo("Request updated successfully");
      setTimeout(() => {
        redirectPage("equipment-requests");
      }, 3000);
    }
  }
}

async function updateInfraRequest(infrastructureRequestId, index) {
  requestStatusDetails = document.querySelectorAll(".requestInput");

  let path;
  let status;

  // Student can only cancel the Approved Request
  if (userRole == "ADMIN") {
    status = requestStatusDetails[index].value;
    path = `admin/infrastructure-request/${infrastructureRequestId}`;
  } else {
    status = "CANCELLED";
    path = `student/infrastructure-request/${infrastructureRequestId}`;
  }

  if (requestStatusDetails[index].value == "PENDING")
    raiseErrors("Cannot Update Status to Pending");
  else {
    // ADMIN updation sends the Mail, needs some buffer
    if (userRole == "ADMIN") showInfo("Updating Request");

    let responseJSON = null;

    responseJSON = await putData(
      "PATCH",
      path,
      JSON.stringify({
        requestStatus: status,
      })
    );

    if (responseJSON != null) {
      showInfo("Request updated successfully");
      setTimeout(() => {
        redirectPage("infrastructure-requests");
      }, 3000);
    }
  }
}
