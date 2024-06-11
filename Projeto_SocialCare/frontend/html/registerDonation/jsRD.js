document.addEventListener('DOMContentLoaded', function () {
    const productForm = document.getElementById('product-form');
    const addProductBtn = document.getElementById('add-product-btn');
    const addToListBtn = document.getElementById('add-to-list-btn');
    const productList = document.getElementById('product-list');
    const donationForm = document.getElementById('donation-form');

    let products = [];

    addProductBtn.addEventListener('click', function () {
        productForm.classList.toggle('hidden');
    });

    addToListBtn.addEventListener('click', function () {
        const category = document.getElementById('category').value;
        const product = document.getElementById('product').value;
        const qtde = document.getElementById('qtde').value;

        if (category && product && qtde) {
            const productItem = { category, product, qtde };
            products.push(productItem);

            const li = document.createElement('li');
            li.textContent = `Categoria: ${category}, Produto: ${product}, Quantidade: ${qtde}`;
            productList.appendChild(li);

            // Limpar os campos
            document.getElementById('category').value = '';
            document.getElementById('product').value = '';
            document.getElementById('qtde').value = '';
        } else {
            alert('Preencha todos os campos do produto');
        }
    });

    donationForm.addEventListener('submit', async function (event) {
        event.preventDefault();

        const donor = document.getElementById('donor').value;
        const obs = document.getElementById('obs').value;
        const donationData = document.getElementById('donationData').value;
        const donationTime = document.getElementById('donationTime').value;
        const unidade = document.getElementById('unidade').value;

        const baseData = {
            donor,
            obs,
            donationData,
            donationTime,
            unidade,
        };

        for (const productItem of products) {
            const data = new URLSearchParams(baseData);
            data.append('category', productItem.category);
            data.append('product', productItem.product);
            data.append('qtde', productItem.qtde);

            try {
                const response = await fetch('http://localhost:8080/apis/register-donation', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: data,
                });

                if (!response.ok) {
                    throw new Error('Erro ao registrar doação');
                }
            } catch (error) {
                alert(error.message);
                return;
            }
        }

        alert('Doação registrada com sucesso');
        donationForm.reset();
        productList.innerHTML = '';
        products = [];
    });
});