// Função para alternar entre as abas
document.querySelectorAll('.tab').forEach(tab => {
    tab.addEventListener('click', function() {
        document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
        document.querySelectorAll('.tab-content').forEach(c => c.classList.remove('active'));
        tab.classList.add('active');
        document.getElementById(tab.dataset.tab).classList.add('active');
    });
});

// Função para buscar Pessoa Física pelo CPF
document.getElementById('buscarPessoa').addEventListener('click', function() {
    var cpf = document.getElementById('cpfAdd').value.replace(/[^\d]/g, ''); // Remove não números

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
            document.getElementById('addActionResponse').innerText = 'Erro ao buscar pessoa física: ' + error.message;
        });
});

// Função para adicionar Ação
document.getElementById('addActionForm').addEventListener('submit', function(event) {
    event.preventDefault();

    var formData = new FormData();
    formData.append('cpf', document.getElementById('cpfAdd').value.replace(/[^\d]/g, '')); // Remove não números
    formData.append('dtacao', document.getElementById('dtacao').value);
    formData.append('descricao', document.getElementById('descricao').value);
    formData.append('motivo', document.getElementById('motivo').value);
    formData.append('idade', document.getElementById('idade').value);

    // Validando idade
    if (formData.get('idade') <= 0) {
        document.getElementById('addActionResponse').innerText = 'Idade inválida. A idade deve ser maior que zero.';
        return;
    }

    // Validando data da ação
    var dtacao = new Date(document.getElementById('dtacao').value);
    var hoje = new Date();
    hoje.setHours(0, 0, 0, 0); // Zera as horas para comparar só a data
    if (dtacao > hoje) {
        document.getElementById('addActionResponse').innerText = 'Data inválida. A data da ação não pode ser no futuro.';
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
                document.getElementById('addActionResponse').innerText = data;
            } else {
                // Se a resposta for JSON
                if (data.message && data.message.includes('Pessoa física não está ativa')) {
                    document.getElementById('addActionResponse').innerText = 'Erro: Pessoa física não está ativa.';
                } else {
                    document.getElementById('addActionResponse').innerText = 'Ação inserida com sucesso.';
                }
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            document.getElementById('addActionResponse').innerText = 'Erro ao inserir ação: ' + error.message;
        });
});

// Função para formatar o CPF no formulário de adicionar
document.getElementById('cpfAdd').addEventListener('input', function() {
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

// Função para deletar Ação
document.getElementById('deleteActionForm').addEventListener('submit', function(event) {
    event.preventDefault();

    var cpf = document.getElementById('cpfDelete').value.replace(/[^\d]/g, ''); // Remove não números do CPF

    fetch('http://localhost:8080/apis/CloseReception/delete-action?cpf=' + encodeURIComponent(cpf), {
        method: 'GET'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao enviar requisição.');
            }
            return response.text();
        })
        .then(data => {
            document.getElementById('deleteActionResponse').innerText = data;
        })
        .catch(error => {
            console.error('Erro:', error);
            document.getElementById('deleteActionResponse').innerText = 'Erro ao excluir ação: ' + error.message;
        });
});

// Função para formatar o CPF no formulário de deletar
document.getElementById('cpfDelete').addEventListener('input', function() {
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
