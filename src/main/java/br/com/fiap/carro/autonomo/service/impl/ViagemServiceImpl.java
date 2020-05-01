package br.com.fiap.carro.autonomo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.carro.autonomo.dto.CreateViagemDTO;
import br.com.fiap.carro.autonomo.dto.ViagemDTO;
import br.com.fiap.carro.autonomo.entity.Viagem;
import br.com.fiap.carro.autonomo.repository.ViagemRepository;
import br.com.fiap.carro.autonomo.service.ViagemService;

@Service
public class ViagemServiceImpl implements ViagemService {

	private ViagemRepository viagemRepository;

	public ViagemServiceImpl(ViagemRepository viagemRepository) {
		this.viagemRepository = viagemRepository;
	}

	@Override
	public List<ViagemDTO> findAll() {
		List<Viagem> transacoesList = viagemRepository.findAll();
		return transacoesList.stream().map(ViagemDTO::new).collect(Collectors.toList());
	}

	@Override
	public ViagemDTO findById(Integer id) {
		return new ViagemDTO(
				viagemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}
	
	@Override
	public ViagemDTO findByIdViagemIdCarro(Integer idViagem, Integer idCarro) {
		return new ViagemDTO(
				viagemRepository.findByIdViagemIdCarro(idViagem, idCarro).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}

	@Override
	public List<ViagemDTO> findByIdCarro(Integer id) {
		List<Viagem> transacoesList = viagemRepository.findByIdCarro(id);
		return transacoesList.stream().map(ViagemDTO::new).collect(Collectors.toList());
	}

	@Override
	public ViagemDTO create(CreateViagemDTO createTransacaoDTO) {
		Viagem viagem = new Viagem(createTransacaoDTO);
		viagem.setStatus("Disponível");
		viagem.setDataCriacao(new Date());
		viagem.setDataModificacao(new Date());
		return new ViagemDTO(viagemRepository.save(viagem));
	}

	@Override
	public ViagemDTO update(Integer id, CreateViagemDTO createViagemDTO) {
		ViagemDTO viagemFind = findById(id);
		Viagem viagem = new Viagem();
		viagem.setId(id);
		viagem.setIdCarro(createViagemDTO.getIdCarro());
		viagem.setLatitudeOrigemUsuario(createViagemDTO.getLatitudeOrigemUsuario());
		viagem.setLongitudeOrigemUsuario(createViagemDTO.getLongitudeOrigemUsuario());
		viagem.setLatitudeDestinoUsuario(createViagemDTO.getLatitudeDestinoUsuario());
		viagem.setLongitudeDestinoUsuario(createViagemDTO.getLongitudeDestinoUsuario());
		viagem.setStatus(createViagemDTO.getStatus());
		viagem.setDistanciaPercorrida(createViagemDTO.getDistanciaPercorrida());
		viagem.setValor(createViagemDTO.getValor());
		viagem.setDataCriacao(Date.from(viagemFind.getDataCriacao().toInstant()));
		viagem.setDataModificacao(new Date());
		return new ViagemDTO(viagemRepository.save(viagem));
	}
	
	@Override
	public void delete(Integer id) {
		viagemRepository.deleteById(id);
	}

}
