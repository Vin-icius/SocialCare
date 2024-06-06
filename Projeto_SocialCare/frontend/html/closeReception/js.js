function date() {
    var dtacao = document.getElementById('dtacao').value;
    var today = new Date();
    var selectedDate = new Date(dtacao);

    if (selectedDate > today) {
        alert('A data de ação não pode ser futura.');
        return false; // Cancela o envio do formulário
    }

    return true; // Permite o envio do formulário
}

fetch('http://localhost:8080/apis/CloseReception/add-action', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify({
        cpf: 'valor_do_cpf',
        dtacao: 'valor_da_data_de_acao',
        descricao: 'valor_da_descricao'
        // outros campos, se houver
    })

}).then(response => {
    if (!response.ok) {
        throw new Error('Erro ao enviar requisição.');
    }
    return response.json();
}).then(data => {
    console.log('Resposta do servidor:', data);
}).catch(error => {
    console.error('Erro:', error);
});
