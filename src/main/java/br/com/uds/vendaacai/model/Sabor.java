package br.com.uds.vendaacai.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Sabor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_seq", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSeq;

    @Column(name = "ds_desc", nullable = false)
    private String dsDesc;

    @Column(name="ds_tmp_adc",nullable = false)
    private Integer dsTmpAdc;
}
