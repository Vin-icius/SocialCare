async function fetchUsers() {
    const response = await fetch('http://localhost:8080/apis/admin/get-all-users');
    const users = await response.json();
    const userList = document.getElementById('user-list');
    userList.innerHTML = ''; // Clear the list before populating
    users.forEach(user => {
        const li = document.createElement('li');
        li.textContent = `${user.id} - ${user.email}`;
        li.addEventListener('click', () => loadUser(user));
        userList.appendChild(li);
    });
}

function loadUser(user) {
    document.getElementById('user-id').value = user.id;
    const emailField = document.getElementById('email');
    emailField.value = user.email;
    emailField.disabled = user.email !== 'admin@admin';
    document.getElementById('access-level').value = user.level;
    document.getElementById('active').value = user.active;
    document.getElementById('password').value = '';
}

document.getElementById('update-user-form').addEventListener('submit', async function(event) {
    event.preventDefault();

    const userId = document.getElementById('user-id').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const accessLevel = document.getElementById('access-level').value;
    const active = document.getElementById('active').value;
    const message = document.getElementById('message');
    const result = document.getElementById('result');

    message.textContent = '';
    result.textContent = '';

    const user = {
        id: userId,
        email: email,
        password: password,
        level: parseInt(accessLevel),
        active: active === 'true'
    };

    try {
        const response = await fetch('http://localhost:8080/apis/admin/update-user', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });

        if (response.ok) {
            result.textContent = 'Usuário atualizado com sucesso!';
            result.style.color = 'green';
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

window.onload = fetchUsers;
