package br.com.uds.vendaacai.service;

import br.com.uds.vendaacai.constants.URLConstants;
import br.com.uds.vendaacai.model.Adicional;
import br.com.uds.vendaacai.model.AdicionalPedido;
import br.com.uds.vendaacai.model.Pedido;
import br.com.uds.vendaacai.model.dto.AdicionalDTO;
import br.com.uds.vendaacai.model.dto.LinkDTO;
import br.com.uds.vendaacai.model.dto.PedidoDTO;
import br.com.uds.vendaacai.model.dto.ResponseDTO;
import br.com.uds.vendaacai.model.dto.ResumoPedidoDTO;
import br.com.uds.vendaacai.model.dto.SaborDTO;
import br.com.uds.vendaacai.model.dto.TamanhoDTO;
import br.com.uds.vendaacai.repository.AdicionalRepository;
import br.com.uds.vendaacai.repository.PedidoRepository;
import br.com.uds.vendaacai.repository.SaborRepository;
import br.com.uds.vendaacai.repository.TamanhoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class PedidoService {

    private final TamanhoRepository tamanhoRepository;
    private final SaborRepository saborRepository;
    private final AdicionalRepository adicionalRepository;
    private final PedidoRepository pedidoRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PedidoService(TamanhoRepository tamanhoRepository, SaborRepository saborRepository,
                         AdicionalRepository adicionalRepository, PedidoRepository pedidoRepository, ObjectMapper objectMapper) {
        this.tamanhoRepository = tamanhoRepository;
        this.saborRepository = saborRepository;
        this.adicionalRepository = adicionalRepository;
        this.pedidoRepository = pedidoRepository;
        this.objectMapper = objectMapper;
    }


    public ResponseDTO adicionarTamanho(PedidoDTO pedidoDTO, TamanhoDTO tamanhoDTO) {
        pedidoDTO.setTamanho(tamanhoDTO);
        calcularTempoPreparo(pedidoDTO, tamanhoDTO);
        calcularValorTotal(pedidoDTO, tamanhoDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setDados(pedidoDTO);
        responseDTO.getLinks().add(new LinkDTO("previous", URLConstants.URL_APP + URLConstants.PASSO1_LISTAR_TAMANHO));
        responseDTO.getLinks().add(new LinkDTO("next", URLConstants.URL_APP + URLConstants.PASSO1_LISTAR_SABOR));
        return responseDTO;
    }

    public ResponseDTO adicionarSabor(PedidoDTO pedidoDTO, SaborDTO saborDTO) {
        pedidoDTO.setSabor(saborDTO);
        calcularTempoPreparo(pedidoDTO, saborDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setDados(pedidoDTO);
        responseDTO.getLinks().add(new LinkDTO("previous", URLConstants.URL_APP + URLConstants.PASSO1_LISTAR_SABOR));

        return responseDTO;
    }

    public ResponseDTO adicionarAdicionais(PedidoDTO pedidoDTO, AdicionalDTO adicionalDTO) {
        pedidoDTO.getAdicionais().add(adicionalDTO);
        calcularTempoPreparo(pedidoDTO, adicionalDTO);
        calcularValorTotal(pedidoDTO, adicionalDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setDados(pedidoDTO);
        responseDTO.getLinks().add(new LinkDTO("self", URLConstants.URL_APP + URLConstants.PASSO2_ESCOLHER_ADICIONAL));
        responseDTO.getLinks().add(new LinkDTO("previous", URLConstants.URL_APP + URLConstants.PASSO2_LISTAR_ADICIONAIS));
        responseDTO.getLinks().add(new LinkDTO("next", URLConstants.URL_APP + URLConstants.PASSO3_RESUMO_PEDIDO));
        return responseDTO;
    }

    private void calcularTempoPreparo(PedidoDTO pedidoDTO, Object dto) {
        if (dto instanceof TamanhoDTO) {
            pedidoDTO.setTempoPreparoTotal(pedidoDTO.getTempoPreparoTotal() + ((TamanhoDTO) dto).getTempoPreparoAdicional());
        }
        if (dto instanceof SaborDTO) {
            pedidoDTO.setTempoPreparoTotal(pedidoDTO.getTempoPreparoTotal() + ((SaborDTO) dto).getTempoPreparoAdicional());
        }
        if (dto instanceof AdicionalDTO) {
            pedidoDTO.setTempoPreparoTotal(pedidoDTO.getTempoPreparoTotal() + ((AdicionalDTO) dto).getTempoPreparoAdicional());
        }
    }

    private void calcularValorTotal(PedidoDTO pedidoDTO, Object dto) {
        if (dto instanceof TamanhoDTO) {
            pedidoDTO.setValorTotal(pedidoDTO.getValorTotal().add(((TamanhoDTO) dto).getValor()));
        }
        if (dto instanceof AdicionalDTO) {
            pedidoDTO.setValorTotal(pedidoDTO.getValorTotal().add(((AdicionalDTO) dto).getValor()));
        }
    }

    private Pedido convertDTOToEntidade(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setIdCli(pedidoDTO.getIdentificacaoCliente());
        pedido.setVrTot(pedidoDTO.getValorTotal());
        pedido.setTmpPrpTot(pedidoDTO.getTempoPreparoTotal());
        pedido.setTamanho(this.tamanhoRepository.getOne(pedidoDTO.getTamanho().getId()));
        pedido.setSabor(this.saborRepository.getOne(pedidoDTO.getSabor().getId()));
        if (null != pedidoDTO.getAdicionais() && !pedidoDTO.getAdicionais().isEmpty()) {
            pedidoDTO.getAdicionais().forEach(a -> {
                pedido.adicionarAdicionais(a);
            });
        }
        return pedido;
    }

    public ResponseDTO salvarPedido(@Valid @NotNull PedidoDTO pedidoDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        ResumoPedidoDTO resumoPedidoDTO = new ResumoPedidoDTO();
        preencherResumoPedido(pedidoDTO,resumoPedidoDTO);
        responseDTO.setDados(resumoPedidoDTO);
        Pedido pedido = convertDTOToEntidade(pedidoDTO);
        this.pedidoRepository.save(pedido);
        return responseDTO;
    }

    private void preencherResumoPedido(PedidoDTO pedidoDTO, ResumoPedidoDTO resumoPedidoDTO) {
        resumoPedidoDTO.setTamanho(pedidoDTO.getTamanho().getDescricao());
        resumoPedidoDTO.setSabor(pedidoDTO.getSabor().getDescricao());
        resumoPedidoDTO.setValorTotal(pedidoDTO.getValorTotal());
        resumoPedidoDTO.setTempoPreparoTotal(pedidoDTO.getTempoPreparoTotal());

        if (null != pedidoDTO.getAdicionais() && !pedidoDTO.getAdicionais().isEmpty()) {
            pedidoDTO.getAdicionais().forEach(a -> {
                resumoPedidoDTO.setPersonalizacoes(resumoPedidoDTO.getPersonalizacoes().concat("|").concat(a.getDescricao()));
            });
        }
    }

}
