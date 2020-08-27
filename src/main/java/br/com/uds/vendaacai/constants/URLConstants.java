package br.com.uds.vendaacai.constants;

public interface URLConstants {

    String URL_APP = "localhost:8080/venda-acai";
    String PASSO1_IDENTIFICACAO = "/passo1/0/identificarCliente";
    String PASSO1_LISTAR_TAMANHO = "/passo1/1/listarTamanho";
    String PASSO1_ESCOLHER_TAMANHO = "/passo1/2/escolherTamanho";
    String PASSO1_LISTAR_SABOR = "/passo1/3/listarSabor";
    String PASSO1_ESCOLHER_SABOR = "/passo1/4/escolherSabor";
    String PASSO2_LISTAR_ADICIONAIS = "/passo2/1/listarAdicionais";
    String PASSO2_ESCOLHER_ADICIONAL = "/passo2/2/escolherAdicional";
    String PASSO3_RESUMO_PEDIDO = "/passo3/detalhePedido";
}
