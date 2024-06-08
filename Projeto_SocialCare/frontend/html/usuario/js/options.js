document.addEventListener('DOMContentLoaded', function() {
    const userLevel = localStorage.getItem('userLevel');
    const optionsList = document.getElementById('options-list');

    if (userLevel === '1') {
        const addOption = document.createElement('li');
        addOption.textContent = 'Cadastrar Usuário';
        addOption.addEventListener('click', () => {
            window.location.href = 'add.html';
        });

        const updateOption = document.createElement('li');
        updateOption.textContent = 'Alterar Cadastro de Usuário';
        updateOption.addEventListener('click', () => {
            window.location.href = 'update.html';
        });

        optionsList.appendChild(addOption);
        optionsList.appendChild(updateOption);
    } else if (userLevel === '2') {
        const addOption = document.createElement('li');
        addOption.textContent = 'Cadastrar Usuário';
        addOption.addEventListener('click', () => {
            window.location.href = 'add.html';
        });

        optionsList.appendChild(addOption);
    }
});