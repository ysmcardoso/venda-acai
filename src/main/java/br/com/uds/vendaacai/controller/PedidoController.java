package br.com.uds.vendaacai.controller;

import br.com.uds.vendaacai.model.dto.AdicionalDTO;
import br.com.uds.vendaacai.model.dto.IdenticacaoDTO;
import br.com.uds.vendaacai.model.dto.LinkDTO;
import br.com.uds.vendaacai.model.dto.PedidoDTO;
import br.com.uds.vendaacai.model.dto.ResponseDTO;
import br.com.uds.vendaacai.model.dto.SaborDTO;
import br.com.uds.vendaacai.model.dto.TamanhoDTO;
import br.com.uds.vendaacai.service.AdicionalService;
import br.com.uds.vendaacai.service.PedidoService;
import br.com.uds.vendaacai.service.SaborService;
import br.com.uds.vendaacai.service.TamanhoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.uds.vendaacai.constants.URLConstants.PASSO1_ESCOLHER_SABOR;
import static br.com.uds.vendaacai.constants.URLConstants.PASSO1_ESCOLHER_TAMANHO;
import static br.com.uds.vendaacai.constants.URLConstants.PASSO1_IDENTIFICACAO;
import static br.com.uds.vendaacai.constants.URLConstants.PASSO1_LISTAR_SABOR;
import static br.com.uds.vendaacai.constants.URLConstants.PASSO1_LISTAR_TAMANHO;
import static br.com.uds.vendaacai.constants.URLConstants.PASSO2_ESCOLHER_ADICIONAL;
import static br.com.uds.vendaacai.constants.URLConstants.PASSO2_LISTAR_ADICIONAIS;
import static br.com.uds.vendaacai.constants.URLConstants.PASSO3_RESUMO_PEDIDO;
import static br.com.uds.vendaacai.constants.URLConstants.URL_APP;

@Log4j2
@Api("Api para pedidos de açaí")
@RestController
@RequestMapping("pedido")
public class PedidoController {

    private TamanhoService tamanhoService;
    private SaborService saborService;
    private AdicionalService adicionalService;
    private PedidoService pedidoService;
    private PedidoDTO pedidoDTO;

    @Autowired
    public PedidoController(TamanhoService tamanhoService, SaborService saborService, PedidoService pedidoService, AdicionalService adicionalService) {
        this.tamanhoService = tamanhoService;
        this.saborService = saborService;
        this.pedidoService = pedidoService;
        this.adicionalService = adicionalService;
        pedidoDTO = new PedidoDTO();
    }

    @ApiOperation(value = "Informar identificação do cliente do pedido")
    @PostMapping(value = PASSO1_IDENTIFICACAO, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> identificarCliente(@RequestBody IdenticacaoDTO identicacaoDTO) {
        pedidoDTO.setIdentificacaoCliente(identicacaoDTO.getCpf());
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.getLinks().add(new LinkDTO("next",URL_APP + PASSO1_LISTAR_TAMANHO));
        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation(value = "Lista os tamanhos de açaí disponíveis")
    @GetMapping(value = PASSO1_LISTAR_TAMANHO, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> listarTamanhos() {
        ResponseDTO responseDTO = tamanhoService.listarTodos();
        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation(value = "Escolher o tamanho do açaí")
    @PostMapping(value = PASSO1_ESCOLHER_TAMANHO, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> escolherTamanho(@RequestBody TamanhoDTO tamanhoDTO) {
        ResponseDTO responseDTO = pedidoService.adicionarTamanho(pedidoDTO, tamanhoDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation(value = "Lista os sabores de açaí disponíveis")
    @GetMapping(value = PASSO1_LISTAR_SABOR, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> listarSabores() {
        ResponseDTO responseDTO = saborService.listarTodos();
        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation(value = "Escolher o sabor do açaí")
    @PostMapping(value = PASSO1_ESCOLHER_SABOR, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> escolherSabor(@RequestBody SaborDTO saborDTO) {
        ResponseDTO responseDTO = pedidoService.adicionarSabor(pedidoDTO, saborDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation(value = "Lista os adicionais disponíveis")
    @GetMapping(value = PASSO2_LISTAR_ADICIONAIS, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> listarAdicionais() {
        ResponseDTO responseDTO = adicionalService.listarTodos();
        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation(value = "Escolher os adicionais")
    @PostMapping(value = PASSO2_ESCOLHER_ADICIONAL, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> escolherAdicional(@RequestBody AdicionalDTO adicionalDTO) {
        ResponseDTO responseDTO = pedidoService.adicionarAdicionais(pedidoDTO, adicionalDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation(value = "Detalhamento do pedido")
    @GetMapping(value = PASSO3_RESUMO_PEDIDO, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> detalhePedido() {
        ResponseDTO responseDTO = pedidoService.salvarPedido(pedidoDTO);
        return ResponseEntity.ok(responseDTO);
    }

}
