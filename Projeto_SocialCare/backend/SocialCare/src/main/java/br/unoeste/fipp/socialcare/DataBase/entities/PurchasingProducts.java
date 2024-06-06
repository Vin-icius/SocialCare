package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;


@Entity
@Table(name="itens_compra")
public class PurchasingProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itc_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "compra_com_id",nullable = false)
    private Compra compra;
    @ManyToOne
    @JoinColumn(name = "estoque_produto_com_id",nullable = false)
    private Storage product;
    @ManyToOne
    @JoinColumn(name="estoque_unidade_uni_id")
    private Storage uni;

    @Column(name = "itc_quantidade")
    private Double quantity;

    @Column(name = "itc_valor")
    private Double value;

    public PurchasingProducts()
    {
        this(0L,null,null,null,null,null);
    }
    public PurchasingProducts(Long id, Compra compra, Storage product, Storage uni, Double quantity, Double value) {
        this.id = id;
        this.compra = compra;
        this.product = product;
        this.uni = uni;
        this.quantity = quantity;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Storage getProduct() {
        return product;
    }

    public void setProduct(Storage product) {
        this.product = product;
    }

    public Storage getUni() {
        return uni;
    }

    public void setUni(Storage uni) {
        this.uni = uni;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
