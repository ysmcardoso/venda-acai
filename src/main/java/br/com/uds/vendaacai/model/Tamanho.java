package br.com.uds.vendaacai.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
public class Tamanho implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_seq", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSeq;

    @Column(name = "ds_desc", nullable = false)
    private String dsDesc;

    @Column(name = "ds_tam", nullable = false)
    private String dsTam;

    @Column(name="ds_vr", nullable = false)
    private BigDecimal dsVr;

    @Column(name="ds_tmp_prp", nullable = false)
    private Integer dsTmpPrp;

}
