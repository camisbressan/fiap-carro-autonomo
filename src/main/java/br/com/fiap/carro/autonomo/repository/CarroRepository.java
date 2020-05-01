package br.com.fiap.carro.autonomo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.carro.autonomo.entity.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {

}
