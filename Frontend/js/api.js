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
