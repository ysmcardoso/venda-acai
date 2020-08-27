package br.com.uds.vendaacai.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LinkDTO implements Serializable {

    private String rel;
    private String href;

    public LinkDTO(String rel, String href) {
        this.rel = rel;
        this.href = href;
    }
}
