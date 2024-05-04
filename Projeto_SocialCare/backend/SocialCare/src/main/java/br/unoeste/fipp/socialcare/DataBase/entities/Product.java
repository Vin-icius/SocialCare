package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="produto")
public class Product {

    //Atributos proprios da tabela de produtos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pro_id")
    private Long id;
    @Column(name="pro_nome")
    private String nome;

    //Foreign Key
    @ManyToOne
    @JoinColumn(name="categoria_cat_id", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private CategoryProduct categoryProduct;

    public Product() {
        this(0L,"",null);
    }

    public Product(Long id, String nome, CategoryProduct categoryProduct) {
        this.id = id;
        this.nome = nome;
        this.categoryProduct = categoryProduct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryProduct getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(CategoryProduct categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
