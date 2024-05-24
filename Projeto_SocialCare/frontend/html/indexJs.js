function openParam() {
    const URL="http://localhost:8080/apis/parametrization/get-param";
    var parForm = document.getElementById("formIndex");
    
    fetch(URL, {
        method: 'GET', body: new FormData(parForm)
    })
        .then(resp=>{
            return resp.json()
            .then(json=>{

                let list;
                for (let data of json)
                {
                    console.log(data);
                    list+=`
                            <img src="${data.logoP}" alt="Logo lar dos meninos">
                            <header>
                                <h1>${data.fantasia} - ${data.logradouro}</h1>
                            </header>
                            <section>
                                <h2>Quem Somos</h2>
                                <p>Somos uma ONG dedicada a ajudar os mais necessitados e promover a solidariedade na comunidade.</p>
                            </section>
                            <section>
                                <h2>Operações</h2>
                                <p>Aqui você pode encontrar informações sobre as atividades e projetos que realizamos para ajudar quem mais precisa.</p>
                            </section>
                            <section>
                                <h2>Como Ajudar</h2>
                                <p>Se você deseja contribuir com nossa causa, entre em contato para saber como ajudar financeiramente, como voluntário ou doando alimentos e roupas.</p>
                                <p>${data.email}</p>
                            </section>
                            <footer>
                                <p>&copy; 2021 ONG ${data.fantasia}. Todos os direitos reservados.</p>
                            </footer>`
                }
                
                list+=`<br><br>`
                tag.innerHTML=list;
            })
        })
        .catch(Err=>{
            console.error="Erro"+Err;
        })
}
function verifyParam(){
    const URL="http://localhost:8080/apis/parametrization/verify-param";

    fetch(URL, {
        method: 'GET', body: new FormData(formIndex),
    })
        .then(resp=> {
            openParam();
            return resp.text();
        })
        .then(text=> {
            console.log("Deu certo!");
        }).catch(error=> {
            addParam();
            console.error(error);
        });
}