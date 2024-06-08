document.getElementById('login-form').addEventListener('submit', async function(event) {
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
        password: password
    };

    try {
        const response = await fetch('http://localhost:8080/apis/security/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });

        if (response.ok) {
            const token = await response.text();
            message.textContent = 'Login bem-sucedido!';
            message.style.color = 'green';

            const payload = JSON.parse(atob(token.split('.')[1]));
            const userLevel = payload.nivel;
            localStorage.setItem('token', token);
            localStorage.setItem('userLevel', userLevel);

            if (email === 'admin@admin') {
                setTimeout(() => {
                    window.location.href = 'update.html';
                }, 1000);
            } else {
                setTimeout(() => {
                    window.location.href = 'options.html';
                }, 1000);
            }
        } else {
            const error = await response.text();
            message.textContent = `Erro: ${error}`;
            message.style.color = 'red';
        }
    } catch (error) {
        message.textContent = `Erro: ${error.message}`;
        message.style.color = 'red';
    }
});
