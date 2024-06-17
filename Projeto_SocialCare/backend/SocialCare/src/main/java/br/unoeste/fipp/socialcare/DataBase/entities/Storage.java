package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="estoque")
public class Storage {
    //Foreign Key
    @ManyToOne
    @JoinColumn(name="unidade_uni_id", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Unity unity;

    //Foreign Key
    @ManyToOne
    @JoinColumn(name="produto_pro_id", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Product product;

    //Atributo proprio da tabela Estoque
    @Column(name="est_quantidade")
    private int quantidade;
    @Id
    private Long id;

    //Contructors
    public Storage(Unity unity, Product product, int quantidade) {
        this.unity = unity;
        this.product = product;
        this.quantidade = quantidade;
    }

    public Storage(){
        this(null, null, 0);
    }

    //Getters and Setters
    public Unity getUnity() {
        return unity;
    }

    public void setUnity(Unity unity) {
        this.unity = unity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getEst_quantidade() {
        return quantidade;
    }

    public void setEst_quantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}