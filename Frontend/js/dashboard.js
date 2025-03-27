checkUser();

// Only allowing logged in users to access
async function checkUser() {
  if (!(await verifyUser())) {
    redirectPage("login");
  }
}
