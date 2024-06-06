package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;


@Table(name="itens_doacao")
public class DonationItens {

    //Foreign Key
    @ManyToOne
    @JoinColumn(name="produto_pro_id")
    private Product produto;
    @ManyToOne
    @JoinColumn(name="doacao_doa_id")
    private Donation doacao;

    @Column(name = "itd_quantidade")
    private double quantidade;

    public DonationItens(Product produto, Donation doacao, double quantidade) {
        this.produto = produto;
        this.doacao = doacao;
        this.quantidade = quantidade;
    }

    public DonationItens() {
        this(null,null,0.0);
    }

    public Product getProduto() {
        return produto;
    }

    public void setProduto(Product produto) {
        this.produto = produto;
    }

    public Donation getDoacao() {
        return doacao;
    }

    public void setDoacao(Donation doacao) {
        this.doacao = doacao;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}
