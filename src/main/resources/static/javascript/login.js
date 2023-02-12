const form = document.getElementById("login-form");
const usernameInput = document.getElementById("username");
const passwordInput = document.getElementById("password");
const signUpButton = document.getElementById("sign-up-button");

form.addEventListener("submit", event => {
  event.preventDefault();

  const username = usernameInput.value;
  const password = passwordInput.value;

  if (!username || !password) {
    alert("Username and password are required.");
    return;
  }

  alert(`Login successful!\nUsername: ${username}`);
});

signUpButton.addEventListener("click", () => {
  alert("Redirecting to sign up page...");
});
