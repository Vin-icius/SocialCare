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

    try {
        const response = await fetch('http://localhost:8080/apis/admin/add-user', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });

        const result = await response.json();
        if (response.ok) {
            message.textContent = 'Usuário cadastrado com sucesso!';
            message.style.color = 'green';
        } else {
            if (result.includes("Email inválido")) {
                emailError.textContent = result;
            } else if (result.includes("Senha inválida")) {
                passwordError.textContent = result;
            } else {
                message.textContent = `Erro: ${result}`;
                message.style.color = 'red';
            }
        }
    } catch (error) {
        message.textContent = `Erro: ${error.message}`;
        message.style.color = 'red';
    }
});