package br.com.fiap.carro.autonomo.service;

import java.util.List;

import br.com.fiap.carro.autonomo.dto.ViagemDTO;
import br.com.fiap.carro.autonomo.dto.CreateViagemDTO;


public interface ViagemService {

	List<ViagemDTO> findAll();

	ViagemDTO findById(Integer id);

	ViagemDTO findByIdViagemIdCarro(Integer idViagem, Integer idCarro);
	
	List<ViagemDTO> findByIdCarro(Integer idCarro);
	
	ViagemDTO create(CreateViagemDTO createViagemDTO);

	ViagemDTO update(Integer id, CreateViagemDTO createViagemDTO);
	
	void delete(Integer id);

}
