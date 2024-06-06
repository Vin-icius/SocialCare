document.addEventListener('DOMContentLoaded', function () {
    const token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbiIsImlzcyI6ImxvY2FsaG9zdDo4MDgwIiwibml2ZWwiOiIxIiwiaWF0IjoxNzE3NzEzMzYyLCJleHAiOjE3MTc3MTQyNjJ9.xTiweeLXvfsr1x6ZX4EMOFldp9t34iaPsC9WPfAKLR8'; //localStorage.getItem('token');
    if (!token) {
        alert('Você precisa estar logado para acessar esta página.');
        window.location.href = 'login.html';
        return;
    }

    const apiUrl = 'http://localhost:8080/apis/admin';

    function setHeaders() {
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        };
    }

    async function fetchBills() {
        try {
            const headers = setHeaders();
            const response = await fetch(`${apiUrl}/get-all-bills-payables`, {
                method: 'GET',
                headers: headers
            });

            if (!response.ok) {
                throw new Error('Erro ao carregar contas a pagar');
            }

            const bills = await response.json();
            populateTable(bills);
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    }

    function formatDate(dateString) {
        const date = new Date(dateString);
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
    }

    function formatValue(value) {
        return parseFloat(value).toFixed(2).replace('.', ',');
    }

    function populateTable(bills) {
        const tbody = document.querySelector('#bills-table tbody');
        tbody.innerHTML = '';

        bills.forEach(bill => {
            const row = document.createElement('tr');

            row.innerHTML = `
                <td>${bill.id}</td>
                <td>${formatDate(bill.dtEmissao)}</td>
                <td>${formatDate(bill.dtVencto)}</td>
                <td>${formatValue(bill.valor)}</td>
                <td>${bill.descricao}</td>
                <td class="actions">
                    <button class="edit" onclick="editBill(${bill.id}, '${bill.pessoaPesId}', 'null', '${bill.dtEmissao}', '${bill.dtVencto}', ${bill.valor}, '${bill.descricao}')">Editar</button>
                    <button class="delete" onclick="deleteBill(${bill.id})">Excluir</button>
                </td>
            `;

            tbody.appendChild(row);
        });
    }

    function validateForm() {
        const dtEmissao = document.getElementById('dtEmissao').value;
        const dtVencto = document.getElementById('dtVencto').value;
        const valor = parseFloat(document.getElementById('valor').value);

        const today = new Date().toISOString().split('T')[0];
        if (dtEmissao > today) {
            alert('A data de emissão não pode ser maior que hoje.');
            return false;
        }

        if (dtVencto < dtEmissao) {
            alert('A data de vencimento não pode ser menor que a data de emissão.');
            return false;
        }

        if (valor <= 0) {
            alert('O valor deve ser positivo e diferente de zero.');
            return false;
        }

        return true;
    }

    document.getElementById('bill-form').addEventListener('submit', async function (event) {
        event.preventDefault();

        if (!validateForm()) {
            return;
        }

        const id = document.getElementById('bill-id').value;
        const pessoaPesId = document.getElementById('pessoaPesId').value;
        const compraComId = null //document.getElementById('compraComId').value;
        const dtEmissao = document.getElementById('dtEmissao').value;
        const dtVencto = document.getElementById('dtVencto').value;
        const valor = document.getElementById('valor').value;
        const descricao = document.getElementById('descricao').value;

        const bill = {
            id: id ? parseInt(id) : null,
            pessoaPesId: parseInt(pessoaPesId),
            compraComId: compraComId ? parseInt(compraComId) : null,
            dtEmissao: dtEmissao,
            dtVencto: dtVencto,
            valor: parseFloat(valor),
            descricao: descricao,
            dtPagto: null,
            valorPago: 0
        };

        try {
            const method = id ? 'PUT' : 'POST';
            const url = id ? `${apiUrl}/update-bills-payable` : `${apiUrl}/add-bills-payable`;

            const response = await fetch(url, {
                method: method,
                headers: setHeaders(),
                body: JSON.stringify(bill)
            });

            if (!response.ok) {
                throw new Error('Erro ao salvar conta a pagar');
            }

            alert('Conta a pagar salva com sucesso!');
            document.getElementById('bill-form').reset();
            document.getElementById('form-title').textContent = 'Adicionar Conta a Pagar';
            fetchBills();
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    });

    window.editBill = function (id, pessoaPesId, compraComId, dtEmissao, dtVencto, valor, descricao) {
        document.getElementById('bill-id').value = id;
        document.getElementById('pessoaPesId').value = pessoaPesId;
        //document.getElementById('compraComId').value = compraComId || '';
        document.getElementById('dtEmissao').value = dtEmissao.split('T')[0];
        document.getElementById('dtVencto').value = dtVencto.split('T')[0];
        document.getElementById('valor').value = valor.toFixed(2);
        document.getElementById('descricao').value = descricao;
        document.getElementById('form-title').textContent = 'Editar Conta a Pagar';
    };

    window.deleteBill = async function (id) {
        if (!confirm('Tem certeza que deseja excluir esta conta a pagar?')) {
            return;
        }

        try {
            const response = await fetch(`${apiUrl}/delete-bills-payable?cpg_id=${id}`, {
                method: 'DELETE',
                headers: setHeaders()
            });

            if (!response.ok) {
                throw new Error('Erro ao excluir conta a pagar');
            }

            alert('Conta a pagar excluída com sucesso!');
            fetchBills();
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    };

    window.filterGrid = function () {
        const searchTerm = document.getElementById('search-input').value.toLowerCase();
        const rows = document.querySelectorAll('#bills-table tbody tr');

        rows.forEach(row => {
            const id = row.cells[0].textContent;
            const descricao = row.cells[4].textContent.toLowerCase();
            const valor = row.cells[3].textContent.toLowerCase().replace(',', '.');

            if (id.includes(searchTerm) || descricao.includes(searchTerm) || valor.includes(searchTerm)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    };

    fetchBills();
});