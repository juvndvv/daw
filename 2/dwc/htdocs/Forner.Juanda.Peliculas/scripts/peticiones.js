const URL = `https://api.themoviedb.org/3/movie/popular?api_key=192e0b9821564f26f52949758ea3c473&language=es-ES&page=`;

export function fetchMoviesData(page) {
  return new Promise((resolve, reject) => {
    fetch(`${URL}${page}`)
      .then(response => response.json())
      .then(data => {
        const movies = data.results.map(movie => {
          return {
            title: movietitle,
            poster: movie.poster_path,
            overview: movie.overview
          }
        });
        resolve(movies);
      })
      .catch(error => reject(error));
  });
}