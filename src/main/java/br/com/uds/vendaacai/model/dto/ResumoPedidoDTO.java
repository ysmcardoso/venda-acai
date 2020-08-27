package br.com.uds.vendaacai.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ResumoPedidoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tamanho;
    private String sabor;
    private String personalizacoes;
    private Integer tempoPreparoTotal;
    private BigDecimal valorTotal;

    public String getPersonalizacoes() {
        return personalizacoes == null ? new String() : personalizacoes;
    }
}
