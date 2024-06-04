document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('deleteFisicalPersonForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Evita que o formulário seja enviado normalmente

        // Captura o valor do campo CPF
        var cpf = document.getElementById('cpf').value;

        // Envia os dados para o servidor usando uma solicitação GET
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'http://localhost:8080/apis/FisicalPerson/delete-fisical-person?pesf_cpf=' + encodeURIComponent(cpf), true);

        xhr.onload = function() {
            if (xhr.status === 200) {
                alert('Pessoa Física excluída com sucesso.');
                // Limpar o formulário após o envio bem-sucedido, se necessário
                document.getElementById('deleteFisicalPersonForm').reset();
            } else {
                alert('Erro ao excluir pessoa física.');
            }
        };

        xhr.onerror = function() {
            alert('Erro ao excluir pessoa física.');
        };

        xhr.send();
    });
});
