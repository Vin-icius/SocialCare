document.getElementById('user-form').addEventListener('submit', async function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const emailError = document.getElementById('email-error');
    const passwordError = document.getElementById('password-error');
    const message = document.getElementById('message');

    emailError.textContent = '';
    passwordError.textContent = '';
    message.textContent = '';

    const user = {
        email: email,
        password: password,
        active: true
    };

    const userLevel = localStorage.getItem('userLevel');
    const url = userLevel === '1' ? 'http://localhost:8080/apis/admin/add-user' : 'http://localhost:8080/apis/citizen/add-user';

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });

        if (response.ok) {
            const result = await response.json();
            message.textContent = 'Usuário cadastrado com sucesso!';
            message.style.color = 'green';
        } else {
            const error = await response.text();
            if (error.includes("Email inválido")) {
                emailError.textContent = error;
            } else if (error.includes("Senha inválida")) {
                passwordError.textContent = error;
            } else {
                message.textContent = `Erro: ${error}`;
                message.style.color = 'red';
            }
        }
    } catch (error) {
        message.textContent = `Erro: ${error.message}`;
        message.style.color = 'red';
    }
});