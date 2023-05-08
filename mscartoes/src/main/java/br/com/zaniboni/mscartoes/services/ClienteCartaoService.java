package br.com.zaniboni.mscartoes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.zaniboni.mscartoes.entities.ClienteCartao;
import br.com.zaniboni.mscartoes.repositories.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> listCartoesByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
