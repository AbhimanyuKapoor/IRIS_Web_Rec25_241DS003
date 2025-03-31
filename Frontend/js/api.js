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
