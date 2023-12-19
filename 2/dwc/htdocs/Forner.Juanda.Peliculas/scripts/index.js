import { fetchMoviesData } from "./peticiones.js";
import { renderMovies } from "./render.js";

let currPage = 1;

// Next page button
function handleNextPage() {
  currPage++;
  fetchMovies();
  window.scrollTo(0, 0);
}

document.querySelector("#next").addEventListener("click", handleNextPage);

// Previous page button
function handlePrevPage() {
  if (currPage === 1) return;
  currPage--;
  fetchMovies();
  window.scrollTo(0, 0);
}

document.querySelector("#prev").addEventListener("click", handlePrevPage);

// Fetch movies
function fetchMovies() {
  fetchMoviesData(currPage)
    .then((movies) => renderMovies(movies))
    .catch((error) => console.log(error));
}

fetchMovies();
