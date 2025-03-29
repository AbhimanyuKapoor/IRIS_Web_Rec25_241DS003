// For POST, PUT & PATCH Requests
async function putData(action, endpoint, payload) {
  response = await fetch(serverUrl + endpoint, {
    method: action,
    headers: {
      Authorization: `Bearer ${jwtToken}`,
      "Content-type": "application/json",
    },
    body: payload,
  });

  responseJSON = await response.json();

  if (!response.ok) {
    raiseErrors(responseJSON.message);
  } else return responseJSON;
}

// For GET Requests
async function fetchData(endpoint) {
  response = await fetch(serverUrl + endpoint, {
    method: "GET",
    headers: {
      Authorization: `Bearer ${jwtToken}`,
    },
  });

  responseJSON = await response.json();

  if (!response.ok) {
    raiseErrors(responseJSON.message);
  } else return responseJSON;
}

// For DELETE Requests
async function deleteData(endpoint) {
  response = await fetch(serverUrl + endpoint, {
    method: "DELETE",
    headers: {
      Authorization: `Bearer ${jwtToken}`,
    },
  });

  if (!response.ok) {
    raiseErrors(responseJSON.message);
  } else return responseJSON;
}
