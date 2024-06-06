
        // Função para popular o seletor com os dados retornados pelo endpoint
        function popularSelector(endpoint, selector, fieldName) {
            fetch(endpoint)
                .then(response => response.json())
                .then(data => {
                    const selectElement = document.getElementById(selector);
                    data.forEach(item => {
                        const option = document.createElement('option');
                        option.value = item[fieldName]; // Usando o campo especificado
                        option.text = item[fieldName];
                        selectElement.appendChild(option);
                    });
                })
                .catch(error => console.error('Erro ao obter os dados:', error));
        }

        document.getElementById('addItemsForm').addEventListener('submit', function(event) {
            event.preventDefault(); // Evita o envio do formulário padrão

            // Obtém os valores dos campos de entrada
            const compraDesc = document.getElementById('compraDesc').value;
            const produtoNome = document.getElementById('produtoNome').value;
            const unidadeNome = document.getElementById('unidadeNome').value;
            const quantity = parseInt(document.getElementById('quantity').value);
            const value = parseFloat(document.getElementById('value').value);

            // Dados do objeto a ser enviado no corpo da requisição
            const requestBody = {
                compra: {
                    desc: compraDesc
                },
                product: {
                    product: {
                        nome: produtoNome
                    }
                },
                uni: {
                    unity: {
                        nome: unidadeNome
                    }
                },
                quantity: quantity,
                value: value
            };

            // Configurações da requisição
            const requestOptions = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestBody)
            };

            // URL do endpoint
            const url = 'http://localhost:8080/apis/citizen/manage-itens-compras/add-itens-compra';

            // Realizando a requisição
            fetch(url, requestOptions)
                .then(response => {
                    if (response.ok) {
                        return response.text();
                    }
                    throw new Error('Erro ao adicionar itens de compra');
                })
                .then(data => {
                    alert(data); // Mensagem de sucesso
                    // Limpar os campos do formulário após o sucesso
                    document.getElementById('compraDesc').value = '';
                    document.getElementById('produtoNome').value = '';
                    document.getElementById('unidadeNome').value = '';
                    document.getElementById('quantity').value = '';
                    document.getElementById('value').value = '';
                })
                .catch(error => {
                    console.error('Erro:', error.message); // Mensagem de erro
                });
        });

        // Populando os seletores com os dados retornados pelos endpoints
        popularSelector('http://localhost:8080/apis/citizen/manage-itens-compras/get-all-compras', 'compraDesc', 'desc');
        popularSelector('http://localhost:8080/apis/citizen/manage-itens-compras/get-produtos-estoque', 'produtoNome', 'nome');
        popularSelector('http://localhost:8080/apis/citizen/manage-itens-compras/get-unidades-estoque', 'unidadeNome', 'nome');
 