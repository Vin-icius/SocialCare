// Função para carregar as cidades no select ao carregar a página
// Função para preencher as opções do campo de seleção de cidade
function openParam() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/apis/admin/get-all-cities', true);

    xhr.onload = function() {
        if (xhr.status === 200) {
            var cities = JSON.parse(xhr.responseText);
            var select = document.getElementById('cidade_cid_id');
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

// Função para adicionar uma nova parametrização
function addParam(event) {
    event.preventDefault();  // Previne o comportamento padrão de submissão do formulário
    
    const URL = "http://localhost:8080/apis/parametrization/add-param";
    const parForm = document.getElementById("formCadPar");
    
    fetch(URL, {
        method: 'POST',
        body: new FormData(parForm)
    })
    .then(resp => {
        if (!resp.ok) {
            throw new Error(`HTTP error! status: ${resp.status}`);
        }
        return resp.text();
    })
    .then(text => {
        console.log("Resposta do servidor:", text);
    })
    .catch(error => {
        console.error("Erro ao adicionar parametrização:", error);
    });
}

// Adiciona o event listener ao formulário para prevenir o comportamento padrão ao submeter
document.getElementById("formCadPar").addEventListener("submit", addParam);

// Carrega as cidades no select ao carregar a página
document.addEventListener("DOMContentLoaded", openParam);
