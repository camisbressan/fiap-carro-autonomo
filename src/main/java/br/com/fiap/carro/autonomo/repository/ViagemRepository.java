package br.com.fiap.carro.autonomo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap.carro.autonomo.entity.Viagem;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Integer> {

    @Query("SELECT v FROM Viagem v WHERE v.id=?1 AND v.idCarro=?2")
    Optional<Viagem> findByIdViagemIdCarro(Integer idViagem, Integer idCarro);

    @Query("SELECT v FROM Viagem v WHERE v.idCarro=?1")
    List<Viagem> findByIdCarro(Integer idCarro);
    
}
