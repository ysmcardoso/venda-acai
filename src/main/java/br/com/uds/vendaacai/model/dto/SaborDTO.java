package br.com.uds.vendaacai.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SaborDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer id;
    @NotNull
    private String descricao;
    @NotNull
    private Integer tempoPreparoAdicional;
}
