package br.com.zaniboni.mscartoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zaniboni.mscartoes.entities.ClienteCartao;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {
    List<ClienteCartao> findByCpf(String cpf);
}
