package br.com.uds.vendaacai.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TamanhoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer id;
    @NotNull
    private String descricao;
    @NotNull
    private String tamanho;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private Integer tempoPreparoAdicional;
}
