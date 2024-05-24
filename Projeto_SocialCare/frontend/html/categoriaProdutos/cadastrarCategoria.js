function cadastrarCategoria(event) {
    event.preventDefault(); 
    const URL = "http://localhost:8080/apis/citizen/manage-category-products/add-category-product";
    
    var cat_nome = document.getElementById("cat_nome").value;

    var params = new URLSearchParams({
        cat_nome : cat_nome
        
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
        document.getElementById("pro_nome").value = "";
    })
    .catch(error => {
        console.error('Erro:', error);
        alert("Erro ao inserir produto");
    });
}