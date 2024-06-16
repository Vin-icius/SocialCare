// Função para alternar entre os formulários
function showForm(formId) {
    const forms = document.querySelectorAll('.form-container');
    forms.forEach(form => form.style.display = 'none');
    document.getElementById(formId).style.display = 'block';
}

// Função para preencher as opções do campo de seleção de gênero
function populateGenders() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/apis/citizen/get-all-genders', true);

    xhr.onload = function() {
        if (xhr.status === 200) {
            var genders = JSON.parse(xhr.responseText);
            document.querySelectorAll('.genero-select').forEach(select => {
                select.innerHTML = '';
                genders.forEach(function(gender) {
                    var option = document.createElement('option');
                    option.value = gender.id;
                    option.textContent = gender.nome;
                    select.appendChild(option);
                });
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
            document.querySelectorAll('.cidade-select').forEach(select => {
                select.innerHTML = '';
                cities.forEach(function(city) {
                    var option = document.createElement('option');
                    option.value = city.id;
                    option.textContent = city.nome;
                    select.appendChild(option);
                });
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

// Função para enviar dados para o servidor
function sendData(url, data, successMessage, errorMessage) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onload = function() {
        if (xhr.status === 200) {
            alert(successMessage);
        } else {
            alert(errorMessage);
        }
    };

    xhr.onerror = function() {
        alert(errorMessage);
    };

    xhr.send(JSON.stringify(data));
}

// Função para remover a máscara do CPF
function removeCpfMask(cpf) {
    return cpf.replace(/\D/g, '');
}

// Função para adicionar a máscara do CPF
function applyCpfMask(cpf) {
    return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
}

// Função para validar data de nascimento
function isFutureDate(date) {
    const today = new Date();
    const inputDate = new Date(date);
    return inputDate > today;
}

// Função para buscar dados da pessoa pelo CPF
function getPersonByCpf(cpf, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/apis/FisicalPerson/get-fisical-person?pesf_cpf=' + encodeURIComponent(cpf), true);

    xhr.onload = function() {
        if (xhr.status === 200) {
            console.log("Raw response:", xhr.responseText);  // Log the raw response
            try {
                var person = JSON.parse(xhr.responseText);
                callback(person);
            } catch (e) {
                console.error("Failed to parse JSON response for person by CPF:", xhr.responseText);
                alert('Erro ao buscar dados da pessoa.');
            }
        } else {
            alert('Erro ao buscar dados da pessoa.');
        }
    };

    xhr.onerror = function() {
        alert('Erro ao buscar dados da pessoa.');
    };

    xhr.send();
}

// Adicionar máscara de CPF nos campos de CPF
document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('input[id^="cpf"]').forEach(input => {
        input.addEventListener('input', function() {
            this.value = applyCpfMask(removeCpfMask(this.value));
        });
    });

    // Manipuladores de evento para os formulários
    document.getElementById('addFisicalPersonForm').addEventListener('submit', function(event) {
        event.preventDefault();
        var dtnascimento = document.getElementById('dtnascimentoAdd').value;
        if (isFutureDate(dtnascimento)) {
            alert('A data de nascimento não pode ser futura.');
            return;
        }
        var pessoa = {
            nome: document.getElementById('nomeAdd').value,
            logradouro: document.getElementById('logradouroAdd').value,
            bairro: document.getElementById('bairroAdd').value,
            cep: document.getElementById('cepAdd').value,
            email: document.getElementById('emailAdd').value,
            cpf: removeCpfMask(document.getElementById('cpfAdd').value),
            rg: document.getElementById('rgAdd').value,
            dtnascimento: dtnascimento,
            cidade: { id: document.getElementById('cidadeAdd').value },
            genero: { id: document.getElementById('generoAdd').value }
        };
        sendData('http://localhost:8080/apis/FisicalPerson/add-fisical-person', pessoa, 'Pessoa inserida com sucesso.', 'Erro ao cadastrar pessoa física.');
    });

    document.getElementById('updateFisicalPersonForm').addEventListener('submit', function(event) {
        event.preventDefault();
        var dtnascimento = document.getElementById('dtnascimentoUpdate').value;
        if (isFutureDate(dtnascimento)) {
            alert('A data de nascimento não pode ser futura.');
            return;
        }
        var pessoa = {
            cpf: removeCpfMask(document.getElementById('cpfUpdate').value),
            nome: document.getElementById('nomeUpdate').value,
            logradouro: document.getElementById('logradouroUpdate').value,
            bairro: document.getElementById('bairroUpdate').value,
            cep: document.getElementById('cepUpdate').value,
            email: document.getElementById('emailUpdate').value,
            rg: document.getElementById('rgUpdate').value,
            dtnascimento: dtnascimento,
            cidade: { id: document.getElementById('cidadeUpdate').value },
            genero: { id: document.getElementById('generoUpdate').value }
        };
        sendData('http://localhost:8080/apis/FisicalPerson/update-fisical-person', pessoa, 'Pessoa atualizada com sucesso.', 'Erro ao atualizar pessoa física.');
    });

    document.getElementById('deleteFisicalPersonForm').addEventListener('submit', function(event) {
        event.preventDefault();
        var cpf = removeCpfMask(document.getElementById('cpfDelete').value);
        getPersonByCpf(cpf, function(person) {
            var confirmation = confirm(`Tem certeza que deseja excluir a seguinte pessoa?\n\nNome: ${person.nome}\nLogradouro: ${person.logradouro}\nBairro: ${person.bairro}\nCEP: ${person.cep}\nEmail: ${person.email}\nCPF: ${applyCpfMask(person.cpf)}\nRG: ${person.rg}\nData de Nascimento: ${person.dtnascimento}\nCidade: ${person.cidade.nome}\nGênero: ${person.genero.nome}`);
            if (confirmation) {
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'http://localhost:8080/apis/FisicalPerson/delete-fisical-person?pesf_cpf=' + encodeURIComponent(cpf), true);

                xhr.onload = function() {
                    if (xhr.status === 200) {
                        alert('Pessoa Física excluída com sucesso.');
                        document.getElementById('deleteFisicalPersonForm').reset();
                    } else {
                        alert('Erro ao excluir pessoa física.');
                    }
                };

                xhr.onerror = function() {
                    alert('Erro ao excluir pessoa física.');
                };

                xhr.send();
            }
        });
    });

    // Inicializar campos de seleção de gênero e cidade
    populateGenders();
    populateCities();
});
