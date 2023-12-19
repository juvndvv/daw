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

export function postArticle(sellForm, formData) {
    // TODO: implementar petición de venta
  }