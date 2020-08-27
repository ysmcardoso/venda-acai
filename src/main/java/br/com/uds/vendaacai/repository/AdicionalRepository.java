package br.com.uds.vendaacai.repository;

import br.com.uds.vendaacai.model.Adicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdicionalRepository extends JpaRepository<Adicional,Integer> {
}
