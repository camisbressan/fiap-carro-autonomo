package br.com.fiap.carro.autonomo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap.carro.autonomo.entity.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {

    @Query("SELECT c FROM Carro c WHERE c.status=?1")
    List<Carro> findAllByStatus(String status);
	
}
