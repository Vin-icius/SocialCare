document.getElementById('buscarPessoa').addEventListener('click', function() {
    var cpf = document.getElementById('cpf').value.replace(/[^\d]/g, ''); // Remove não números

    fetch('http://localhost:8080/apis/FisicalPerson/get-fisical-person?pesf_cpf=' + cpf)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao buscar pessoa física.');
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('nome').innerText = 'Nome: ' + data.nome;
            document.getElementById('endereco').innerText = 'Endereço: ' + data.logradouro;
            document.getElementById('dadosPessoa').style.display = 'block';
        })
        .catch(error => {
            console.error('Erro:', error);
            document.getElementById('response').innerText = 'Erro ao buscar pessoa física: ' + error.message;
        });
});

document.getElementById('addActionForm').addEventListener('submit', function(event) {
    event.preventDefault();

    var formData = new FormData();
    formData.append('cpf', document.getElementById('cpf').value.replace(/[^\d]/g, '')); // Remove não números
    formData.append('dtacao', document.getElementById('dtacao').value);
    formData.append('descricao', document.getElementById('descricao').value);
    formData.append('motivo', document.getElementById('motivo').value);
    formData.append('idade', document.getElementById('idade').value);

    // Validando idade
    if (formData.get('idade') <= 0) {
        document.getElementById('response').innerText = 'Idade inválida. A idade deve ser maior que zero.';
        return;
    }

    fetch('http://localhost:8080/apis/CloseReception/add-action', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao enviar requisição.');
            }
            // Verifica o tipo de conteúdo da resposta
            const contentType = response.headers.get('content-type');
            if (contentType && contentType.indexOf('application/json') !== -1) {
                return response.json(); // Retorna o JSON se o conteúdo for JSON
            } else {
                return response.text(); // Retorna o texto se o conteúdo não for JSON
            }
        })
        .then(data => {
            if (typeof data === 'string') {
                // Se a resposta for texto (erro interno, por exemplo)
                document.getElementById('response').innerText = data;
            } else {
                // Se a resposta for JSON
                if (data.message && data.message.includes('Pessoa física não está ativa')) {
                    document.getElementById('response').innerText = 'Erro: Pessoa física não está ativa.';
                } else {
                    document.getElementById('response').innerText = 'Ação inserida com sucesso.';
                }
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            document.getElementById('response').innerText = 'Erro ao inserir ação: ' + error.message;
        });
});

// Adiciona formatação do CPF (XXX.XXX.XXX-XX)
document.getElementById('cpf').addEventListener('input', function() {
    let cpf = this.value.replace(/\D/g, ''); // Remove não números
    if (cpf.length > 3 && cpf.length <= 6) {
        cpf = cpf.replace(/(\d{3})(\d{1,3})/, '$1.$2');
    } else if (cpf.length > 6 && cpf.length <= 9) {
        cpf = cpf.replace(/(\d{3})(\d{3})(\d{1,3})/, '$1.$2.$3');
    } else if (cpf.length > 9 && cpf.length <= 11) {
        cpf = cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{1,2})/, '$1.$2.$3-$4');
    }
    this.value = cpf;
});
