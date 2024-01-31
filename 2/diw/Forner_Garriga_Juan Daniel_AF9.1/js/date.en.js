// fecha en el lugar y formato que aparece en las capturas de pantalla
// Puntos 2/100

// jQuery 4. Fecha en el footer
function setDate() {
  const daysOfWeek = [
    "Sunday",
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
  ];
  const monthsOfYear = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
  ];
  const today = new Date();
  const dayOfWeek = daysOfWeek[today.getDay()];
  const dayOfMonth = today.getDate();
  const monthOfYear = monthsOfYear[today.getMonth()];
  const year = today.getFullYear();

  $("footer > p > span").text(
    `${dayOfWeek} - ${monthOfYear} ${dayOfMonth}, ${year}`
  );
}

setDate();
