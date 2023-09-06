function listar() {
    const mostraLista = document.querySelector('div#lista')
    const API_URL = 'http://localhost:8090/api-product/products'
    fetch(API_URL)
    .then(response => response.json())
    .then(data => {
        mostraLista.innerHTML = data
    })
}