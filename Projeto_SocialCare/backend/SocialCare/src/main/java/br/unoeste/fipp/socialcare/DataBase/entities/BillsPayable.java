package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;

import java.security.Timestamp;
import java.util.Date;

@Entity
@Table(name="contas_pagar")
public class BillsPayable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cpg_id")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name="pessoa_pes_id", nullable = false)
//    private Person person;

//    @ManyToOne
//    @JoinColumn(name="compra_com_id", nullable = false)
//    private Purchase purchase;

    @Column(name="cpg_dtemissao", nullable = false)
    private Date dtEmissao;

//    @Column(name="cpg_numparc", nullable = false)
//    private int numParc;

    @Column(name="cpg_dtvencto", nullable = false)
    private Date dtVencto;

    @Column(name="cpg_valor", nullable = false)
    private double valor;

    @Column(name="cpg_descricao")
    private String descricao;

    @Column(name="cpg_dtpagto")
    private Date dtPagto;

    @Column(name="cpg_valorpago")
    private double valorPago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Purchase getPurchase() {
//        return purchase;
//    }
//
//    public void setPurchase(Purchase purchase) {
//        this.purchase = purchase;
//    }

    public Date getDtEmissao() {
        return dtEmissao;
    }

    public void setDtEmissao(Date dtEmissao) {
        this.dtEmissao = dtEmissao;
    }

//    public int getNumParc() {
//        return numParc;
//    }
//
//    public void setNumParc(int numParc) {
//        this.numParc = numParc;
//    }

    public Date getDtVencto() {
        return dtVencto;
    }

    public void setDtVencto(Date dtVencto) {
        this.dtVencto = dtVencto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDtPagto() {
        return dtPagto;
    }

    public void setDtPagto(Date dtPagto) {
        this.dtPagto = dtPagto;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }
}
