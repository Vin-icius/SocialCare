document.addEventListener("DOMContentLoaded", carregarCategorias);

function carregarCategorias() {
    const API_URL = "http://localhost:8080/apis/citizen/manage-category-products";

    fetch(`${API_URL}/get-all-categories-product`, {
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
        const tabelaCategorias = document.getElementById("tabelaCategoria").getElementsByTagName('tbody')[0];
        tabelaCategorias.innerHTML = ''; // Limpa a tabela antes de adicionar os novos itens

        // Preenche a tabela com os dados dos produtos
        data.forEach(categoria => {
            const row = document.createElement("tr");
            row.innerHTML = `
            <td>${categoria.id}</td>
            <td>${categoria.nome}</td>`;
            tabelaCategorias.appendChild(row);
        });
    })
    .catch(error => {
        console.error('Erro:', error);
        alert("Erro ao carregar produtos");
    });

    
}