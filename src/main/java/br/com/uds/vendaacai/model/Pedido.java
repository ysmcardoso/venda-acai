package br.com.uds.vendaacai.model;

import br.com.uds.vendaacai.model.dto.AdicionalDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Pedido implements Serializable {

    @Id
    @Column(name = "id_seq", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSeq;

    @Column(name = "id_cli", nullable = false)
    private String idCli;

    @Column(name = "vr_tot", nullable = false)
    private BigDecimal vrTot;

    @Column(name = "tmp_prp_tot", nullable = false)
    private Integer tmpPrpTot;

    @OneToOne
    @JoinColumn(name = "id_seq_tam", nullable = false)
    private Tamanho tamanho;

    @OneToOne
    @JoinColumn(name = "id_seq_sab", nullable = false)
    private Sabor sabor;

    @OneToMany(mappedBy = "pedido", orphanRemoval = true)
    private List<AdicionalPedido> adicionais;

    public Pedido() {
        this.adicionais = new ArrayList<>();
    }

    public void adicionarAdicionais(AdicionalDTO adicionalDTO) {
        AdicionalPedido adicionalPedido = new AdicionalPedido();
        adicionalPedido.setDsDesc(adicionalDTO.getDescricao());
        adicionalPedido.setDsVr(adicionalDTO.getValor());
        adicionalPedido.setDsTmpPrp(adicionalDTO.getTempoPreparoAdicional());
        adicionalPedido.setPedido(this);
        adicionais.add(adicionalPedido);
    }

}
