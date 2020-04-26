package br.com.fiap.carro.autonomo.service;

import java.util.List;

import br.com.fiap.carro.autonomo.dto.CarroDTO;
import br.com.fiap.carro.autonomo.dto.CreateCarroDTO;


public interface CarroService {

	List<CarroDTO> findAll();

	CarroDTO findById(Integer id);

	CarroDTO create(CreateCarroDTO createCarroDTO);

	CarroDTO update(Integer id, CreateCarroDTO createCarroDTO);

	void delete(Integer id);

}
