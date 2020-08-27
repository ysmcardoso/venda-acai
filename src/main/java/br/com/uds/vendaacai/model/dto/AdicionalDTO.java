package br.com.uds.vendaacai.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AdicionalDTO implements Serializable {

    @NotNull
    private Integer id;
    @NotNull
    private String descricao;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private Integer tempoPreparoAdicional;
}
