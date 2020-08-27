package br.com.uds.vendaacai.service;

import br.com.uds.vendaacai.constants.URLConstants;
import br.com.uds.vendaacai.model.Tamanho;
import br.com.uds.vendaacai.model.dto.ResponseDTO;
import br.com.uds.vendaacai.model.dto.TamanhoDTO;
import br.com.uds.vendaacai.repository.TamanhoRepository;
import br.com.uds.vendaacai.util.LinkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class TamanhoService {

    private TamanhoRepository repository;

    @Autowired
    public TamanhoService(TamanhoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Tamanho> buscarTodos() {
        return repository.findAll();
    }

    public ResponseDTO listarTodos() {
        return converterEntidadeToDTO(buscarTodos());
    }

    private ResponseDTO converterEntidadeToDTO(List<Tamanho> tamanhos) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<TamanhoDTO> dtos = new ArrayList<>();
        tamanhos.forEach(t -> {
            TamanhoDTO tamanhoDTO = new TamanhoDTO();
            tamanhoDTO.setId(t.getIdSeq());
            tamanhoDTO.setDescricao(t.getDsDesc());
            tamanhoDTO.setTamanho(t.getDsTam());
            tamanhoDTO.setValor(t.getDsVr());
            tamanhoDTO.setTempoPreparoAdicional(t.getDsTmpPrp());
            dtos.add(tamanhoDTO);
        });
        responseDTO.setDados(dtos);
        responseDTO.setLinks(LinkUtil.adicionarLinks(LinkUtil.montarURL("next", URLConstants.URL_APP + URLConstants.PASSO1_ESCOLHER_TAMANHO)));
        return responseDTO;
    }
}
