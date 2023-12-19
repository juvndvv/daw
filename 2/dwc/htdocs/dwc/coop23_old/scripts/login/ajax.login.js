export function loginRequest(loginForm, formData) {
  fetchUser(formData)
    .then((user) => {
      writeUserCookies(user);
      drawCorrectForm().then(() => drawIndex(user));
    })
    .catch((error) => {
      drawErrors(loginForm, {});
      drawErrors(loginForm, { password: error.message });
    });
}
