document.addEventListener("DOMContentLoaded", carregarProdutos);

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
        const tabelaProdutos = document.getElementById("tabelaProdutos").getElementsByTagName('tbody')[0];
        tabelaProdutos.innerHTML = ''; // Limpa a tabela antes de adicionar os novos itens

        // Preenche a tabela com os dados dos produtos
        data.forEach(produto => {
            const row = document.createElement("tr");
            row.innerHTML = `
            <td>${produto.id}</td>
            <td>${produto.nome}</td>
            <td>${produto.categoryProduct.id}</td>
            <td>${produto.categoryProduct.nome}</td>
            <td>
                <button class="btn-delete" onclick="excluirProduto(${produto.id})">Excluir</button>
                <button class="btn-edit" onclick="mostrarFormEdicao(${produto.id}, '${produto.nome}', ${produto.categoryProduct.id}, '${produto.categoryProduct.nome}')">Alterar</button>
            </td>`;
            tabelaProdutos.appendChild(row);
        });
    })
    .catch(error => {
        console.error('Erro:', error);
        alert("Erro ao carregar produtos");
    });
}

function excluirProduto(id) {
    const API_URL = "http://localhost:8080/apis/citizen/manage-products/delete-product";
    
    var params = new URLSearchParams({
        pro_id: id
    });

    fetch(`${API_URL}?${params.toString()}`, {
        method: 'GET'
    })
    .then(resp => {
        if (!resp.ok) {
            throw new Error('Erro na requisição: ' + resp.statusText);
        }
        return resp.text();
    })
    .then(text => {
        alert("Produto excluído com sucesso");
        carregarProdutos(); // Recarrega a lista de produtos
    })
    .catch(error => {
        console.error('Erro:', error);
        alert("Erro ao excluir produto");
    });
}

function alterarProduto(id, nome, catId) {
    const API_URL = "http://localhost:8080/apis/citizen/manage-products/update-product";
    
    var params = new URLSearchParams({
        pro_id: id,
        pro_nome: nome,
        cat_id: catId
    });

    fetch(`${API_URL}?${params.toString()}`, {
        method: 'POST'
    })
    .then(resp => {
        if (!resp.ok) {
            throw new Error('Erro na requisição: ' + resp.statusText);
        }
        return resp.text();
    })
    .then(text => {
        alert("Produto alterado com sucesso");
        document.getElementById('formEdicao').style.display = 'none'; // Esconde o formulário de edição
        document.querySelector('.container').style.display = 'block'; // Mostra a tabela de produtos
        carregarProdutos(); // Recarrega a lista de produtos
    })
    .catch(error => {
        console.error('Erro:', error);
        alert("Erro ao alterar produto");
    });
}

function mostrarFormEdicao(id, nome, catId, catNome) {
    const formEdicao = document.getElementById('formEdicao');
    formEdicao.style.display = 'block';
    document.querySelector('.container').style.display = 'none'; // Esconde a tabela de produtos

    document.getElementById('edit_pro_id').value = id;
    document.getElementById('edit_pro_nome').value = nome;
    document.getElementById('edit_cat_id').value = catId;
    document.getElementById('edit_cat_nome').value = catNome;
}

function salvarAlteracao(event) {
    event.preventDefault(); // Prevent the form from submitting the traditional way
    var id = document.getElementById('edit_pro_id').value;
    var nome = document.getElementById('edit_pro_nome').value;
    var catId = document.getElementById('edit_cat_id').value;

    alterarProduto(id, nome, catId);
}