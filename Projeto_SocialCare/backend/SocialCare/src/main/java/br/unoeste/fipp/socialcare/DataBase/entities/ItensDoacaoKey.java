package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class ItensDoacaoKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "estoque_produto_pro_id", nullable = false)
    // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Product id_produto;
    @ManyToOne
    @JoinColumn(name = "doacao_doa_id", nullable = false)
    // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Donation id_doacao;
    @ManyToOne
    @JoinColumn(name = "estoque_unidade_uni_id", nullable = false)
    // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Unity id_unidade;

    public ItensDoacaoKey() {
        this(null, null,null);
    }

    public ItensDoacaoKey(Product id_produto, Donation id_doacao, Unity id_unidade) {
        this.id_produto = id_produto;
        this.id_doacao = id_doacao;
        this.id_unidade = id_unidade;
    }

    public Product getId_produto() {
        return id_produto;
    }

    public void setId_produto(Product id_produto) {
        this.id_produto = id_produto;
    }

    public Donation getId_doacao() {
        return id_doacao;
    }

    public void setId_doacao(Donation id_doacao) {
        this.id_doacao = id_doacao;
    }

    public Unity getId_unidade() {
        return id_unidade;
    }

    public void setId_unidade(Unity id_unidade) {
        this.id_unidade = id_unidade;
    }
}
