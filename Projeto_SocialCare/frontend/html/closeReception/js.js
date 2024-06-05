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