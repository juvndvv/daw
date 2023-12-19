'use strict';

export function writeUserCookies(user) {
  document.cookie = "user=" + JSON.stringify(user) + "; path=/";
}

export function readUserCookies() {
  // Lee la cookie
  const cookies = document.cookie.split(";");

  let user = {};

  // Recorre las cookies
  cookies.forEach((cookie) => {
    const key = cookie.split("=")[0].trim();
    const value = cookie.split("=")[1].trim();

    if (key === "user") {
      user = JSON.parse(value);
    }
  });

  return user;
}

export function deleteUserCookies() {
  // Lee las cookies
  const cookies = document.cookie.split(";");

  // Recorre las cookies
  cookies.forEach((cookie) => {
    const key = cookie.split("=")[0].trim();

    // Elimina la cookie
    document.cookie = key + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
  });
}

export function userCookieExist() {
  const cookies = document.cookie.split(";");

  let userCookieExist = false;

  cookies.forEach((cookie) => {
    const key = cookie.split("=")[0].trim();

    if (key === "user") {
      userCookieExist = true;
    }
  });

  return userCookieExist;
}
