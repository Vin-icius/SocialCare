let products = [];
var cont = 0;
var cont2;

let vet = [];


document.addEventListener('DOMContentLoaded', function () {
    const productForm = document.getElementById('product-form');
    const addProductBtn = document.getElementById('add-product-btn');
    const addToListBtn = document.getElementById('add-to-list-btn');
    const productList = document.getElementById('product-list');
    const registrarDoacaoContainer = document.getElementById('registrar-doacao');
    const mostrarDoacoesContainer = document.getElementById('mostrar-doacoes');
    const deletarDoacoesContainer = document.getElementById('deletar-doacoes');
    const donationsList = document.getElementById('donationsList');
    const deletarTable = document.getElementById('deletar-table');
    
    // Event listeners para os botões da navbar
    document.getElementById('btn-registrar').addEventListener('click', function () {
        registrarDoacaoContainer.style.display = 'block';
        mostrarDoacoesContainer.style.display = 'none';
        deletarDoacoesContainer.style.display = 'none';
    });

    document.getElementById('btn-mostrar').addEventListener('click', function () {
        registrarDoacaoContainer.style.display = 'none';
        mostrarDoacoesContainer.style.display = 'block';
        deletarDoacoesContainer.style.display = 'none';
        mostrarDoacoes();
    });

    document.getElementById('btn-deletar').addEventListener('click', function () {
        registrarDoacaoContainer.style.display = 'none';
        mostrarDoacoesContainer.style.display = 'none';
        deletarDoacoesContainer.style.display = 'block';
        mostrarDoacoesParaDeletar();
    });

    addProductBtn.addEventListener('click', function () {
        productForm.classList.toggle('hidden');
    });

    addToListBtn.addEventListener('click', function () {
        var category = document.getElementById('category');
        var product = document.getElementById('product');
        var qtde = document.getElementById('qtde').value;

        category = category.options[category.selectedIndex].text;
        product = product.options[product.selectedIndex].text;

        if (category && product && qtde) {
            // Criar um objeto com os dados do produto

            // Adicionar o produto ao array products (se estiver usando)

            // Construir o elemento <li> com o botão para abrir modal
            var li = `
                <li id="${cont}">
                    <span>Categoria: ${category}</span><br>
                    <span>Produto: ${product}</span>
                    <span id="qtde${cont}">Quantidade: ${qtde}</span>
                    <button id="openModalBtn${cont}" class="open-modal-btn" data-id="${cont}">Abrir Modal</button>
                </li>`;

            category = document.getElementById('category').value;
            product = document.getElementById('product').value;
            console.log(typeof qtde)
            const productItem = { category, product, qtde };
            products.push(productItem); // Lembre-se de declarar products como um array em algum lugar do seu código

            // Adicionar o <li> ao array vet
            vet.push(li);

            // Construir a lista completa como uma string HTML
            var list2 = vet.join('');

            // Atualizar o conteúdo da lista no HTML
            productList.innerHTML = list2;

            // Incrementar o contador para o próximo ID
            cont++;

            // Limpar os campos do formulário após adicionar à lista
            document.getElementById('category').value = '';
            document.getElementById('product').value = '';
            document.getElementById('qtde').value = '';

            // Adicionar event listener para abrir o modal ao botão de cada item da lista
            // Você pode colocar este código após a atualização do productList.innerHTML
            document.querySelectorAll('.open-modal-btn').forEach(item => {
                item.addEventListener('click', function(event) {
                    abrirModal(event.target.getAttribute('data-id'));
                });
            });
        } else {
            alert('Preencha todos os campos do produto');
        }
    });
});



function abrirModal(id) {

    event.preventDefault();
    cont2=id;
    var modal = document.getElementById('myModal');
    modal.style.display = 'block';

    // Quando o usuário clica no botão fechar (×), fecha o modal
    var span = document.getElementsByClassName('close')[0];
    span.onclick = function() {
        modal.style.display = 'none';
    }
    // Quando o usuário clica fora do modal, fecha o modal
}


function salvar()
{
    event.preventDefault();

    var modal = document.getElementById('myModal');

    var numero= document.getElementById("numeroDigitado").value;
    const qtde = document.getElementById("qtde"+cont2);

    if(numero<0)
        numero=1;
    
    list=`Quantidade: ${numero}`;
    qtde.innerHTML=list;

   
    products[cont2].qtde =`${numero}`;
    modal.style.display = 'none';

    console.log(cont2)
    console.log(products[cont2])
}




function registrarDoacao()
{
    const donor = document.getElementById('donor').value;
    const obs = document.getElementById('obs').value;
    const donationData = document.getElementById('donationData').value;
    const unidade = document.getElementById('unidade').value;

    const URL = `http://localhost:8080/apis/register-donation/register-donation3?donor=${donor}&obs=${obs}&donationData=${donationData}&unidade=${unidade}`;
    //const URL = "http://localhost:8080/apis/register-donation/register-donation3";

    // Dados dos produtos (exemplo)

    
    console.log(products)
    // Configuração da requisição   
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(products)  // Convertendo os dados para JSON
    };
    
    // Realizando a requisição usando fetch
    fetch(URL, requestOptions)
        .then(response => {
            return response.text();
        })
        .then(result => {
            cont = 0;
            console.log('Resposta do servidor:', result);  // Exibe a mensagem de sucesso
            // Faça o que for necessário com a resposta, se precisar
        })
        .catch(error => {
            console.error('Erro ao registrar doação:', error.message);
            // Trate o erro como necessário
        });
    

}

document.addEventListener("DOMContentLoaded", function() {
    getUnities();
});

function getUnities() {
    const URL = "http://localhost:8080/apis/register-donation/get-all-unities";
    const tag = document.getElementById("unidade");

    fetch(URL, {
        method: 'GET'
    })
    .then(response => {
        return response.json(); // Processar os dados recebidos (assumindo que seja JSON)
    })
    .then(json => {
        var list="";
        console.log(json);
        json.forEach(element => {
            list += `<option value="${element.id}">${element.nome}</option>\n;`
        });
        tag.innerHTML=list;
    })
    .catch(error => {
        console.error('Erro:', error);
    });
}

document.addEventListener("DOMContentLoaded", function() {
    getCategories();
});

function getCategories() {
    const URL = "http://localhost:8080/apis/register-donation/get-all-categories-product";
    const tag = document.getElementById("category");

    fetch(URL, {
        method: 'GET'
    })
    .then(response => {
        return response.json(); // Processar os dados recebidos (assumindo que seja JSON)
    })
    .then(json => {
        var list="";
        console.log(json);
        json.forEach(element => {
            list += `<option value="${element.id}">${element.nome}</option>\n;`
        });
        tag.innerHTML=list;
    })
    .catch(error => {
        console.error('Erro:', error);
    });
}

document.addEventListener("DOMContentLoaded", function() {
    getProducts();
});

function getProducts() {
    const URL = "http://localhost:8080/apis/register-donation/get-all-products";
    const tag = document.getElementById("product");

    fetch(URL, {
        method: 'GET'
    })
    .then(response => {
        return response.json(); // Processar os dados recebidos (assumindo que seja JSON)
    })
    .then(json => {
        var list="";
        console.log(json);
        json.forEach(element => {
            list += `<option value="${element.id}">${element.nome}</option>\n;`
        });
        tag.innerHTML=list;
    })
    .catch(error => {
        console.error('Erro:', error);
    });
}

function maskCpf(input) {
    let cpf = input.value.replace(/\D/g, ''); // Remove todos os caracteres não numéricos
    if (cpf.length > 0) {
        cpf = cpf.replace(/^(\d{3})(\d)/, '$1.$2'); // Adiciona o primeiro ponto
        cpf = cpf.replace(/^(\d{3})\.(\d{3})(\d)/, '$1.$2.$3'); // Adiciona o segundo ponto
        cpf = cpf.replace(/^(\d{3})\.(\d{3})\.(\d{3})(\d{1,2}).*/, '$1.$2.$3-$4'); // Adiciona o traço e os últimos dígitos
    }
    input.value = cpf;

    // Validação do CPF
    let errorSpan = document.getElementById('cpf-error');
    if (!isValidCPF(cpf) && cpf.length === 14) {
        errorSpan.textContent = 'CPF inválido';
    } else {
        errorSpan.textContent = '';
    }
}

function isValidCPF(cpf) {
    cpf = cpf.replace(/[^\d]+/g, '');    
    if (cpf == '') return false; 
    // Elimina CPFs invalidos conhecidos
    if (cpf.length != 11 || 
        cpf == "00000000000" || 
        cpf == "11111111111" || 
        cpf == "22222222222" || 
        cpf == "33333333333" || 
        cpf == "44444444444" || 
        cpf == "55555555555" || 
        cpf == "66666666666" || 
        cpf == "77777777777" || 
        cpf == "88888888888" || 
        cpf == "99999999999")
            return false;       
    // Valida 1o digito	
    let add = 0;    
    for (let i=0; i < 9; i ++)      
        add += parseInt(cpf.charAt(i)) * (10 - i);  
        let rev = 11 - (add % 11);  
        if (rev == 10 || rev == 11)     
            rev = 0;    
        if (rev != parseInt(cpf.charAt(9)))     
            return false;       
    // Valida 2o digito 
    add = 0;    
    for (let i = 0; i < 10; i ++)       
        add += parseInt(cpf.charAt(i)) * (11 - i);  
    rev = 11 - (add % 11);  
    if (rev == 10 || rev == 11) 
        rev = 0;    
    if (rev != parseInt(cpf.charAt(10)))
        return false;       
    return true;   
}


function mostrarDoacoes() {
    const URL = "http://localhost:8080/apis/register-donation/get-all-donations";

    fetch(URL, {
        method: 'GET'
    })
    .then(response => {
        return response.json(); // Processar os dados recebidos (assumindo que seja JSON)
    })
    .then(json => {
        donationsList.innerHTML = '';

        json.forEach(donation => {
            const tr = document.createElement('tr');

            tr.innerHTML = `
                <td>${donation.id}</td>
                <td>${donation.pessoa.nome}</td>
                <td>${donation.observacao}</td>
            `;
            donationsList.appendChild(tr);
        });
    })
    .catch(error => {
        console.error('Erro:', error);
    });
}



function mostrarDoacoesParaDeletar() {
    const URL = "http://localhost:8080/apis/register-donation/get-all-donations";

    fetch(URL, {
        method: 'GET'
    })
    .then(response => {
        return response.json(); // Processar os dados recebidos (assumindo que seja JSON)
    })
    .then(json => {

        console.log(json)
        deletarTabela.innerHTML = '';
        json.forEach(donation => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${donation.id}</td>
                <td>${donation.pessoa.nome}</td>
                <td>${donation.observacao}</td>
                <td><button class="delete-btn" data-id="${donation.id}">Deletar</button></td>
            `;
            deletarTabela.appendChild(tr);
        });

        document.querySelectorAll('.delete-btn').forEach(button => {
            button.addEventListener('click', function(event) {
                const donationId = event.target.getAttribute('data-id');
                deletarDoacao(donationId);
            });
        });
    })
    .catch(error => {
        console.error('Erro:', error);
    });
}


function deletarDoacao(donationId) {
    const URL = `http://localhost:8080/apis/register-donation/delete-donation?id=${donationId}`;
    fetch(URL, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        alert('Doação deletada com sucesso!');
        mostrarDoacoesParaDeletar();
    })
    .catch(error => {
        console.error('Houve um problema com a operação fetch:', error);
    });
}