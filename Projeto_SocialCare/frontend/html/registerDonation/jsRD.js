document.getElementById('submitBtn').addEventListener('click', async function() {
    const donor = document.getElementById('donor').value;
    const category = document.getElementById('category').value;
    const product = document.getElementById('product').value;
    const donationDate = document.getElementById('donationDate').value;
    const donationTime = document.getElementById('donationTime').value;

    const data = {
        donor,
        category,
        product,
        donationDate,
        donationTime
    };

    try {
        const response = await fetch('http://localhost:8080/registerDonation', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
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