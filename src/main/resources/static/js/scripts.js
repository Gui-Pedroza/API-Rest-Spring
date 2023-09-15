const API_URL = 'http://localhost:8090/api-product/products'
function listar() {
    const mostraLista = document.querySelector('ul#lista')
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

function criarProduto() {    
    const produto = {
        name: document.querySelector('input#nome').value,
        value: document.querySelector('input#valor').value
    }
    fetch(API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(produto)
    })
    .then(response => {
        if (!response.ok) {
          throw new Error('Erro ao criar o produto');
        }
        return response.json(); 
      })
      .then(data => {
        TODO: // mostrar na página o produto que foi criado mas to com sono e preguiça
        document.querySelector('div#produto-criado').innerHTML = `Produto salvo: ${data.name} valor: ${data.value}`
        console.log('Produto criado:', data);
      })
      .catch(error => {
        console.error('Erro:', error);
      });
    
}
