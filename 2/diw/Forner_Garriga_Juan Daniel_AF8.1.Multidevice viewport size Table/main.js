function showCurrentViewportSize() {
  $("main > section > p > span:first-child").text($(window).width());
  $("main > section > p > span:last-child").text($(window).height());
}

$(window).resize(() => {
  showCurrentViewportSize();
});

function showToday() {
  const fecha = new Date();
  $("footer > p:first-child").text(
    `${fecha.getDate()}/${
      fecha.getMonth() + 1 < 10
        ? "0" + (fecha.getMonth() + 1)
        : fecha.getMonth() + 1
    }/${fecha.getFullYear()}`
  );
}

function init() {
  showCurrentViewportSize();
  showToday();
}

init();
