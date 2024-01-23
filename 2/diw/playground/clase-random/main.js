function drawDate() {
  const today = new Date();

  const year = today.getFullYear();
  const month = today.getMonth() + 1;
  const date = today.getDate();

  $("footer > p:last-child").text(`${date}/${month}/${year}`);
}

function init() {
  drawDate();
}

init();
