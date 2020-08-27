package br.com.uds.vendaacai.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Object dados;
    private List<LinkDTO> links;

    public ResponseDTO() {
        links = new ArrayList<>();
    }
}