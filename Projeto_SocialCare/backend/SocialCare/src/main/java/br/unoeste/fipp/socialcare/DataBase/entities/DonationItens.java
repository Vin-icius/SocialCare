package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="itens_doacao")
public class DonationItens {
    //Entidade Fraca
    @EmbeddedId
    private ItensDoacaoKey id;

    @Column(name = "itd_quantidade")
    private double quantidade;

    public DonationItens(double quantidade) {

        this.quantidade = quantidade;
    }

    public DonationItens() {
        this(0.0);
    }


    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}
