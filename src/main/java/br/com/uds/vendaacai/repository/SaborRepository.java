package br.com.uds.vendaacai.repository;

import br.com.uds.vendaacai.model.Sabor;
import br.com.uds.vendaacai.model.Tamanho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaborRepository extends JpaRepository<Sabor, Integer> {

}
