function cadastrarProduto(event) {
    event.preventDefault(); // Prevent the form from submitting the traditional way
    const URL = "http://localhost:8080/apis/citizen/manage-products/add-products";
    
    var catId = document.getElementById("cat_id").value;
    var proNome = document.getElementById("pro_nome").value;

    var params = new URLSearchParams({
        pro_nome: proNome,
        cat_id: catId
    });

    fetch(URL + "?" + params.toString(), {
        method: 'POST'
    })
    .then(resp => {
        if (!resp.ok) {
            throw new Error('Erro na requisição: ' + resp.statusText);
        }
        return resp.text();
    })
    .then(text => {
        alert("Inserido com sucesso");
    })
    .catch(error => {
        console.error('Erro:', error);
        alert("Erro ao inserir produto");
    });
}




