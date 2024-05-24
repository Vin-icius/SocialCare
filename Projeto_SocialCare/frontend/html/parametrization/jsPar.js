// Função para adicionar uma nova parametrização
function addParam(paramData) {
    const URL="http://localhost:8080/apis/parametrization/add-param";
    var parForm = document.getElementById("formCadPar");
    
    fetch(URL, {
        method: 'POST', body: new FormData(parForm),
    })
        .then(resp=> {
            return resp.text();
        })
        .then(text=> {
            console.log("Deu certo!");
        }).catch(error=> {
            console.error(error);
        });
}


//Onlaod da page para carregar cidades

function openParam()
{
    const URL="http://localhost:8080/apis/admin/get-all-cities";
    const tag = document.getElementById("cidade_cid_id_form");

    fetch(URL, {
        method: 'GET', body: new FormData(tag)
    })
        .then(resp=>{
            return resp.json()
            .then(json=>{

                let list=`<select id="cidade_cid_id" name="cidade_cid_id" required>`
                for (let data of json)
                {
                    console.log(data);
                    list+=`<option value="${data.id}">${data.nome}</option>`
                }
                
                list+=`</select><br><br>`
                tag.innerHTML=list;
            })
        })
        .catch(Err=>{
            console.error="Erro"+Err;
        })
}
