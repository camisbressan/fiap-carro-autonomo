package br.com.fiap.carro.autonomo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.carro.autonomo.dto.CarroDTO;
import br.com.fiap.carro.autonomo.dto.CreateCarroDTO;
import br.com.fiap.carro.autonomo.entity.Carro;
import br.com.fiap.carro.autonomo.repository.CarroRepository;
import br.com.fiap.carro.autonomo.service.CarroService;

@Service
public class CarroServiceImpl implements CarroService {

	private CarroRepository carroRepository;

	public CarroServiceImpl(CarroRepository carroRepository) {
		this.carroRepository = carroRepository;
	}

	@Override
	public List<CarroDTO> findAll() {
		List<Carro> transacoesList = carroRepository.findAll();
		return transacoesList.stream().map(CarroDTO::new).collect(Collectors.toList());
	}

	@Override
	public CarroDTO findById(Integer id) {
		return new CarroDTO(
				carroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}

	@Override
	public CarroDTO create(CreateCarroDTO createTransacaoDTO) {
		Carro carro = new Carro(createTransacaoDTO);
		carro.setStatus("Disponível");
		carro.setDataCriacao(new Date());
		carro.setDataModificacao(new Date());
		return new CarroDTO(carroRepository.save(carro));
	}

	@Override
	public CarroDTO update(Integer id, CreateCarroDTO createCarroDTO) {
		CarroDTO carroFind = findById(id);
		Carro carro = new Carro();
		carro.setId(id);
		carro.setMarca(createCarroDTO.getMarca());
		carro.setModelo(createCarroDTO.getModelo());
		carro.setAno(createCarroDTO.getAno());
		carro.setPlaca(createCarroDTO.getPlaca());
		carro.setStatus(createCarroDTO.getStatus());
		carro.setDataCriacao(Date.from(carroFind.getDataCriacao().toInstant()));
		carro.setDataModificacao(new Date());
		return new CarroDTO(carroRepository.save(carro));
	}

	@Override
	public void delete(Integer id) {
		carroRepository.deleteById(id);
	}

}
