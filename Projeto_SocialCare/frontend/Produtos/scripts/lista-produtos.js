function carregarProdutos() {
    const API_URL = "http://localhost:8080/apis/citizen/manage-products";

    fetch(`${API_URL}/get-all-products`, {
        headers: {
            'Accept': 'application/json',
        },
        method: 'GET'
    })
    .then(resp => {
        if (!resp.ok) {
            throw new Error('Erro na requisição: ' + resp.statusText);
        }
        return resp.json();
    })
    .then(data => {
        const tabelaProdutos = document.getElementById("tabelaProdutos");
        tabelaProdutos.innerHTML = ''; // Limpa a tabela antes de adicionar os novos itens

        // Cria o cabeçalho da tabela
        const headerRow = document.createElement("tr");
        headerRow.innerHTML = "<th>ID</th><th>Nome</th><th>Categoria ID</th><th>Categoria Nome</th>";
        tabelaProdutos.appendChild(headerRow);

        // Preenche a tabela com os dados dos produtos
        data.forEach(produto => {
            const row = document.createElement("tr");
            row.innerHTML = `<td>${produto.id}</td><td>${produto.nome}</td><td>${produto.categoryProduct.id}</td><td>${produto.categoryProduct.nome}</td>`;
            tabelaProdutos.appendChild(row);
        });
    })
    .catch(error => {
        console.error('Erro:', error);
        alert("Erro ao carregar produtos");
    });
}

// Chama a função carregarProdutos quando a página é carregada
document.addEventListener("DOMContentLoaded", carregarProdutos);
