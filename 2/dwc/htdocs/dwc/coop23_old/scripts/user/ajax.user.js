export function fetchUser(formData) {
  return new Promise((resolve, reject) => {
    formData.append("opcion", "SR");
    fetch(URL, {
      method: "post",
      body: formData,
    })
      .then((response) => {
        formData.delete("opcion");
        return response.json();
      })
      .then((json) => {
        resolve(json[0]);
      })
      .catch((error) => {
        error.message = "Email o contraseña incorrectos";
        reject(error);
      });
  });
}

export function postUser(formData) {
  formData.forEach((value, key) => {
    console.log(key, value);
  });
  return new Promise((resolve, reject) => {
    formData.append("opcion", "RS");

    fetch(URL, {
      method: "POST",
      body: formData,
    })
      .then(resolve)
      .catch(reject);
  });
}
