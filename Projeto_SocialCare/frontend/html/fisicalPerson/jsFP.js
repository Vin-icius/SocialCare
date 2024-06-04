document.getElementById('addFisicalPersonForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita que o formulário seja enviado normalmente

    // Captura os valores dos campos do formulário
    var nome = document.getElementById('nome').value;
    var logradouro = document.getElementById('logradouro').value;
    var bairro = document.getElementById('bairro').value;
    var cep = document.getElementById('cep').value;
    var email = document.getElementById('email').value;
    var cpf = document.getElementById('cpf').value;
    var rg = document.getElementById('rg').value;
    var dtnascimento = document.getElementById('dtnascimento').value;
    var cidadeId = document.getElementById('cidade').value;
    var generoId = document.getElementById('genero').value;

    // Verifica se a data de nascimento é futura
    var today = new Date();
    var birthDate = new Date(dtnascimento);
    if (birthDate > today) {
        alert('A data de nascimento não pode ser futura.');
        return;
    }

    // Cria um objeto com os dados do formulário
    var pessoa = {
        nome: nome,
        logradouro: logradouro,
        bairro: bairro,
        cep: cep,
        email: email,
        cpf: cpf,
        ativo: true,
        rg: rg,
        dtnascimento: dtnascimento,
        cidade: { id: cidadeId },
        genero: { id: generoId }
    };

    // Envia os dados para o servidor usando uma solicitação POST
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8080/apis/FisicalPerson/add-fisical-person', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onload = function() {
        if (xhr.status === 200) {
            alert('Pessoa inserida com sucesso.');
            // Limpar o formulário após o envio bem-sucedido, se necessário
            document.getElementById('addFisicalPersonForm').reset();
        } else {
            alert('Erro ao cadastrar pessoa física.');
        }
    };

    xhr.onerror = function() {
        alert('Erro ao cadastrar pessoa física.');
    };

    xhr.send(JSON.stringify(pessoa));
});

// Função para preencher as opções do campo de seleção de gênero
function populateGenders() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/apis/citizen/get-all-genders', true);

    xhr.onload = function() {
        if (xhr.status === 200) {
            var genders = JSON.parse(xhr.responseText);
            var select = document.getElementById('genero');
            genders.forEach(function(gender) {
                var option = document.createElement('option');
                option.value = gender.id;
                option.textContent = gender.nome;
                select.appendChild(option);
            });
        } else {
            alert('Erro ao carregar os gêneros.');
        }
    };

    xhr.onerror = function() {
        alert('Erro ao carregar os gêneros.');
    };

    xhr.send();
}

// Função para preencher as opções do campo de seleção de cidade
function populateCities() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/apis/admin/get-all-cities', true);

    xhr.onload = function() {
        if (xhr.status === 200) {
            var cities = JSON.parse(xhr.responseText);
            var select = document.getElementById('cidade');
            cities.forEach(function(city) {
                var option = document.createElement('option');
                option.value = city.id;
                option.textContent = city.nome;
                select.appendChild(option);
            });
        } else {
            alert('Erro ao carregar as cidades.');
        }
    };

    xhr.onerror = function() {
        alert('Erro ao carregar as cidades.');
    };

    xhr.send();
}

// Chamar a função para preencher as opções do campo de seleção de gênero
populateGenders();

// Chamar a função para preencher as opções do campo de seleção de cidade
populateCities();
