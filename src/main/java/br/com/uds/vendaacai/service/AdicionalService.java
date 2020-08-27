package br.com.uds.vendaacai.service;

import br.com.uds.vendaacai.constants.URLConstants;
import br.com.uds.vendaacai.model.Adicional;
import br.com.uds.vendaacai.model.dto.AdicionalDTO;
import br.com.uds.vendaacai.model.dto.LinkDTO;
import br.com.uds.vendaacai.model.dto.ResponseDTO;
import br.com.uds.vendaacai.repository.AdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class AdicionalService {

    private AdicionalRepository adicionalRepository;

    @Autowired
    public AdicionalService(AdicionalRepository adicionalRepository){
        this.adicionalRepository = adicionalRepository;
    }

    public List<Adicional> buscarTodos() {
        return adicionalRepository.findAll();
    }

    public ResponseDTO listarTodos() {
        return converterEntidadeToDTO(buscarTodos());
    }

    private ResponseDTO converterEntidadeToDTO(List<Adicional> adicionais) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<AdicionalDTO> dtos = new ArrayList<>();
        adicionais.forEach(a -> {
            AdicionalDTO adicionalDTO = new AdicionalDTO();
            adicionalDTO.setId(a.getIdSeq());
            adicionalDTO.setDescricao(a.getDsDesc());
            adicionalDTO.setTempoPreparoAdicional(a.getDsTmpPrp());
            adicionalDTO.setValor(a.getDsVr());
            dtos.add(adicionalDTO);
        });
        responseDTO.setDados(dtos);
        responseDTO.getLinks().add(new LinkDTO("self", URLConstants.URL_APP + URLConstants.PASSO2_LISTAR_ADICIONAIS));
        responseDTO.getLinks().add(new LinkDTO("previous", URLConstants.URL_APP + URLConstants.PASSO1_ESCOLHER_SABOR));
        responseDTO.getLinks().add(new LinkDTO("next", URLConstants.URL_APP + URLConstants.PASSO2_ESCOLHER_ADICIONAL));
        return responseDTO;
    }
}
