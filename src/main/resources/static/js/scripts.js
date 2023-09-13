function listar() {
    const mostraLista = document.querySelector('ul#lista')
    const API_URL = 'http://localhost:8090/api-product/products'
    fetch(API_URL)
    .then(response => response.json())
    .then(itens => {
        mostraLista.innerHTML = ''
        for (item of itens) {
            const itemLista = document.createElement('li')
            itemLista.innerHTML = `Nome produto: ${item.name}<br> Preço: ${item.value}`
            mostraLista.appendChild(itemLista)
        }
    })
    .catch(err => console.log(err))
}

