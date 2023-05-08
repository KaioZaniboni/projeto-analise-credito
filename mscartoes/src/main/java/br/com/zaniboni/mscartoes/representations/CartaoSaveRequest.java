package br.com.zaniboni.mscartoes.representations;

import java.math.BigDecimal;

import br.com.zaniboni.mscartoes.entities.Cartao;
import br.com.zaniboni.mscartoes.enums.BandeiraCartao;
import lombok.Data;

@Data
public class CartaoSaveRequest {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel(){
        return new Cartao(nome, bandeira, renda, limite );
    }
}
