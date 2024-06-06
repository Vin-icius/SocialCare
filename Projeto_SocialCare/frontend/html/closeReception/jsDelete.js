document.getElementById('deleteActionForm').addEventListener('submit', function(event) {
    event.preventDefault();

    var cpf = document.getElementById('cpf').value.replace(/[^\d]/g, ''); // Remove não números do CPF

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
            document.getElementById('response').innerText = data;
        })
        .catch(error => {
            console.error('Erro:', error);
            document.getElementById('response').innerText = 'Erro ao excluir ação: ' + error.message;
        });
});

// Adiciona formatação dinâmica do CPF (XXX.XXX.XXX-XX)
document.getElementById('cpf').addEventListener('input', function() {
    let cpf = this.value.replace(/[^\d]/g, ''); // Remove não números
    if (cpf.length > 3 && cpf.length <= 6) {
        cpf = cpf.replace(/(\d{3})(\d{1,3})/, '$1.$2');
    } else if (cpf.length > 6 && cpf.length <= 9) {
        cpf = cpf.replace(/(\d{3})(\d{3})(\d{1,3})/, '$1.$2.$3');
    } else if (cpf.length > 9 && cpf.length <= 11) {
        cpf = cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{1,2})/, '$1.$2.$3-$4');
    }
    this.value = cpf;
});
