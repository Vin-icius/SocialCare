document.getElementById('submitBtn').addEventListener('click', async function() {

    const data = {
        donor:document.getElementById('donor').value,
        category:document.getElementById('category').value,
        product:document.getElementById('product').value,
        obs:document.getElementById('obs').value,
        donationDate:document.getElementById('donationDate').value,
        donationTime:document.getElementById('donationTime').value
    };


    const formData = new FormData();
    formData.append('donor', data.donor);
    formData.append('category', data.category);
    formData.append('product', data.product);
    formData.append('obs', data.obs);
    formData.append('donationDate', data.donationDate);
    formData.append('donationTime', data.donationTime);

    
    try {
        const response = await fetch('http://localhost:8080/apis/registerDonation', {
            method: 'POST',
            body: formData
        });

        const result = await response.json();
        
        if (response.ok) {
            document.getElementById('message').innerText = 'Doação registrada com sucesso!';
        } else {
            document.getElementById('message').innerText = `Erro: ${result.message}`;
        }
    } catch (error) {
        document.getElementById('message').innerText = `Erro de rede: ${error.message}`;
    }
});