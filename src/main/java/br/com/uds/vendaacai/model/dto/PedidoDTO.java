package br.com.uds.vendaacai.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PedidoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String identificacaoCliente;
    @NotNull
    private BigDecimal valorTotal = BigDecimal.ZERO;
    @NotNull
    private Integer tempoPreparoTotal = 0;
    @NotNull
    private TamanhoDTO tamanho;
    @NotNull
    private SaborDTO sabor;
    private List<AdicionalDTO> adicionais;

    public PedidoDTO() {
        adicionais = new ArrayList<>();
    }
}
