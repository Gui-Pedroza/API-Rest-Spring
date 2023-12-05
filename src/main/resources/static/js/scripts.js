const API_URL = 'http://localhost:8090/api-product/products'

function listar() {
  const ul = document.querySelector('ul#lista-todos')
  fetch(API_URL)
    .then(response => response.json())
    .then(itens => {      
      createProductList(itens, ul)
    })
    .catch(err => console.log("Erro ao listar produtos:", err))
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
      document.querySelector('div#produto-criado').innerHTML = `Produto salvo: ${data.name} valor: ${data.value}`
      console.log('Produto criado:', data);
    })
    .catch(error => {
      console.error('Erro:', error);
    });

}

let divResultado = document.querySelector('div#resultado-id')
function pesquisaPorId() {
  const id = document.querySelector('input#id-pesquisa').value
  axios(API_URL + '/id/' + id)
    .then(response => response.data)
    .then(data => showSingleResult(data))
    .catch('Deu ruim: ')
}

function pesquisaPorNome() {
  const nome = document.querySelector('input#nome-pesquisa').value
  axios(API_URL + '/name/' + nome)
  .then(response => response.data)
  .then(data => showSingleResult(data))
}

function clearList(ul) {
  ul.innerHTML = ''
}

function showSingleResult(result) {
  const divResultado = document.querySelector('div#resultado-id')
  divResultado.innerHTML = `<strong>Produto encontrado</strong>: Nome: ${result.name} | Valor: R$ ${result.value}`
}

function createProductList(products, ul) {
  ul.innerHTML = ''
  products.forEach(product => {
    const li = document.createElement('li')
    li.innerHTML = `Nome produto: ${product.name}<br> Preço: ${product.value}`
    ul.appendChild(li)    
  });
}
