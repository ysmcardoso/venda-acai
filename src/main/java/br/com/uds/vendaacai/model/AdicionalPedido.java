package br.com.uds.vendaacai.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
public class AdicionalPedido implements Serializable {

    @Id
    @Column(name = "id_seq", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSeq;

    @Column(name="ds_desc", nullable = false)
    private String dsDesc;

    @Column(name="ds_vr", nullable = false)
    private BigDecimal dsVr;

    @Column(name="ds_tmp_prp", nullable = false)
    private Integer dsTmpPrp;

    @ManyToOne
    @JoinColumn(name = "id_seq_ped")
    private Pedido pedido;
}
