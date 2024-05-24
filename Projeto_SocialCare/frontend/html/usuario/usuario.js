function validarFormulario() {
    var cpfInput = document.getElementById('cpf_cad');
    var emailInput = document.getElementById('email_cad');
    var cpf = cpfInput.value;
    var email = emailInput.value;

    if (!validarCPF(cpf)) {
        alert("CPF inválido. Por favor, insira um CPF válido.");
        cpfInput.focus();
        return false;
    }

    if (!validarEmail(email)) {
        alert("E-mail inválido. Por favor, insira um e-mail válido.");
        emailInput.focus();
        return false;
    }

    return true;
}

function validarEmail(email) {
    var re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}

function formatarCPF(cpfInput) {
    var cpf = cpfInput.value.replace(/\D/g, '');
    cpf = cpf.replace(/^(\d{3})(\d)/, '$1.$2');
    cpf = cpf.replace(/^(\d{3})\.(\d{3})(\d)/, '$1.$2.$3');
    cpf = cpf.replace(/^(\d{3})\.(\d{3})\.(\d{3})(\d)/, '$1.$2.$3-$4');
    cpfInput.value = cpf;
}

function validarCPF(cpf) {
    cpf = cpf.replace(/\D/g, '');
    if(cpf.length !== 11) return false;
  
    if (cpf == "00000000000" || 
        cpf == "11111111111" || 
        cpf == "22222222222" || 
        cpf == "33333333333" || 
        cpf == "44444444444" || 
        cpf == "55555555555" || 
        cpf == "66666666666" || 
        cpf == "77777777777" || 
        cpf == "88888888888" || 
        cpf == "99999999999")
            return false;       

    let add = 0;
    for (let i = 0; i < 9; i++)       
        add += parseInt(cpf.charAt(i)) * (10 - i);  
    let rev = 11 - (add % 11);  
    if (rev == 10 || rev == 11)     
        rev = 0;    
    if (rev != parseInt(cpf.charAt(9)))     
        return false;   
        
    add = 0;  
    for (let i = 0; i < 10; i++)        
        add += parseInt(cpf.charAt(i)) * (11 - i);  
    rev = 11 - (add % 11);  
    if (rev == 10 || rev == 11) 
        rev = 0;  
    if (rev != parseInt(cpf.charAt(10)))
        return false;   
    return true;   
}

function cadastrarPessoa() {
    const URL = "http://localhost:8080/apis/admin/add-person";
    var fdados = document.getElementById("fdados");
    var jsontext = JSON.stringify(Object.fromEntries(new FormData(fdados)));
    fetch(URL, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'POST', body: jsontext
    })
        .then(resp=> {
            return resp.text();
        })
        .then(text=> {
            alert(text);
        }).catch(error=> {
            console.error(error);
        });
}