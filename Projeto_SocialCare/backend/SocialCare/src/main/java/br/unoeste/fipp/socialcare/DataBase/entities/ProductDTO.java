package br.unoeste.fipp.socialcare.DataBase.entities;

public class ProductDTO {
    private Long category;
    private Long product;
    private int qtde;

    public ProductDTO(Long category, Long product, int qtde) {
        this.category = category;
        this.product = product;
        this.qtde = qtde;
    }

    public ProductDTO() {
        this(0L,0L,0);
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }
}