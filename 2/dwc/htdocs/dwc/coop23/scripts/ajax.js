import {
  drawErrors,
  drawCorrectForm,
  drawIndex,
  drawLoginForm,
  drawRegistrationForm,
} from "./dom.js";

import { writeUserCookies } from "./cookies.js";

const URL = "php/coop.php";

// articles
export function fetchAllArticles() {
  return new Promise((resolve, reject) => {
    fetch(`${URL}?opcion=AV`)
      .then((response) => response.json())
      .then((json) => {
        resolve(json);
      })
      .catch((error) => reject(error));
  });
}

// categories
export function fetchAllCategories() {
  return new Promise((resolve, reject) => {
    fetch(`${URL}?opcion=TC`)
      .then((response) => response.json())
      .then((json) => {
        // Inserta en la primera posicion del array la categoria "Todas"
        json.unshift({ id: "0", nombre: "Todos los articulos" });
        resolve(json);
      })
      .catch((error) => reject(error));
  });
}

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
        error.message = "Usuario no registrado";
        reject(error);
      });
  });
}

export function postUser(formData) {
  return new Promise((resolve, reject) => {
    formData.append("opcion", "RS");

    fetch(URL, {
      method: "POST",
      body: formData,
    })
      .then(() => {
        resolve();
      })
      .catch((error) => {
        console.log("error:", error);
        reject();
      });
  });
}

export function postArticle(formData) {
  return new Promise((resolve, reject) => {
    formData.append("opcion", "RA");

    fetch(URL, {
      method: "POST",
      body: formData,
    })
      .then((response) => response.text())
      .then((response) => {
        console.log(response);
        resolve();
      })
      .catch((error) => {
        console.log("error:", error);
        reject();
      });
  });
}

export function buyArticle(formData) {
  return new Promise((resolve, reject) => {
    formData.append("opcion", "CA");

    fetch(URL, {
      method: "POST",
      body: formData,
    })
      .then((response) => response.text())
      .then((response) => {
        console.log(response);
        resolve();
      })
      .catch((error) => {
        console.log("error:", error);
        reject();
      });
  });
}

export function deleteArticle(formData) {
  return new Promise((resolve, reject) => {
    formData.append("opcion", "BA");

    fetch(URL, {
      method: "POST",
      body: formData,
    })
      .then((response) => response.text())
      .then((response) => {
        console.log(response);
        resolve();
      })
      .catch((error) => {
        console.log("error:", error);
        reject();
      });
  });
}

export function loginRequest(loginForm, formData) {
  console.log("loginRequest");
  fetchUser(formData)
    .then((user) => {
      writeUserCookies(user);
      drawCorrectForm().then(() => drawIndex(user));
    })
    .catch((error) => {
      drawErrors(loginForm, {});
      drawErrors(loginForm, { password: error.message + ", redirigiendo..." });
      setTimeout(drawRegistrationForm, 3000);
    });
}

export function registerRequest(registerForm, formData) {
  fetchUser(formData)
    // Si el usuario existe
    .then(() => {
      drawErrors(registerForm, { email: "El email ya existe" });
    })

    // Si el usuario no existe
    .catch(() => {
      console.log("Registrando...");
      postUser(formData)
        .then(drawCorrectForm)
        .then(drawLoginForm)
        .catch(() => {
          drawErrors(registerForm, {
            "password-repeat": "Ocurrio un error en el servidor",
          });
        });
    });
}
