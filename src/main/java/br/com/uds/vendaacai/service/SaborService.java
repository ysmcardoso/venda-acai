package br.com.uds.vendaacai.service;

import br.com.uds.vendaacai.constants.URLConstants;
import br.com.uds.vendaacai.model.Sabor;
import br.com.uds.vendaacai.model.Tamanho;
import br.com.uds.vendaacai.model.dto.LinkDTO;
import br.com.uds.vendaacai.model.dto.ResponseDTO;
import br.com.uds.vendaacai.model.dto.SaborDTO;
import br.com.uds.vendaacai.model.dto.TamanhoDTO;
import br.com.uds.vendaacai.repository.SaborRepository;
import br.com.uds.vendaacai.util.LinkUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class SaborService {

    private SaborRepository repository;

    public SaborService(SaborRepository repository) {
        this.repository = repository;
    }

    public List<Sabor> findAll() {
        return repository.findAll();
    }

    public ResponseDTO listarTodos() {
        return converterEntidadeToDTO(findAll());
    }

    private ResponseDTO converterEntidadeToDTO(List<Sabor> sabores) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<SaborDTO> dtos = new ArrayList<>();
        sabores.forEach(s -> {
            SaborDTO saborDTO = new SaborDTO();
            saborDTO.setId(s.getIdSeq());
            saborDTO.setDescricao(s.getDsDesc());
            saborDTO.setTempoPreparoAdicional(s.getDsTmpAdc());
            dtos.add(saborDTO);
        });
        responseDTO.setDados(dtos);
        responseDTO.getLinks().add(new LinkDTO("previous", URLConstants.URL_APP + URLConstants.PASSO1_ESCOLHER_TAMANHO));
        responseDTO.getLinks().add(new LinkDTO("next",URLConstants.URL_APP + URLConstants.PASSO1_ESCOLHER_SABOR));
        return responseDTO;
    }
}
