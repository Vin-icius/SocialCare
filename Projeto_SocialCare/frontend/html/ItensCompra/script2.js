window.onload = function() {
    const itensList = document.getElementById('itensList');

    // Função para popular a lista de itens de compra
    function popularItensCompras() {
        fetch('http://localhost:8080/apis/citizen/manage-itens-compras/get-all-itens-compras')
            .then(response => response.json())
            .then(data => {
                // Limpar a lista antes de adicionar os novos itens
                itensList.innerHTML = '';
                data.forEach(item => {
                    const listItem = document.createElement('li');
                    listItem.classList.add('item');
                    listItem.innerHTML = `
                        <strong>Descrição:</strong> ${item.product.desc}<br>
                        <strong>Produto:</strong> ${item.product.nome}<br>
                        <strong>Unidade:</strong> ${item.uni.nome}<br>
                        <strong>Quantidade:</strong> ${item.quantity}<br>
                        <strong>Valor:</strong> ${item.value}<br>
                    `;
                    itensList.appendChild(listItem);
                });
            })
            .catch(error => console.error('Erro ao obter os itens de compra:', error));
    }

    // Chamar a função para popular a lista quando a página carregar
    popularItensCompras();
};
