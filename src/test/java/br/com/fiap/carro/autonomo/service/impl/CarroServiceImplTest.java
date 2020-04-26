package br.com.fiap.carro.autonomo.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.fiap.carro.autonomo.dto.CarroDTO;
import br.com.fiap.carro.autonomo.dto.CreateCarroDTO;
import br.com.fiap.carro.autonomo.entity.Carro;
import br.com.fiap.carro.autonomo.repository.CarroRepository;
import br.com.fiap.carro.autonomo.service.CarroService;

@RunWith(SpringRunner.class)
@SpringBootTest
class CarroServiceImplTest {

	@Test
	public void testFindAll() {
		CarroRepository carroRepository = mock(CarroRepository.class);
		List<Carro> carroList = new ArrayList<Carro>();
		carroList.add(new Carro());
		carroList.add(new Carro());
		carroList.add(new Carro());
		when(carroRepository.findAll()).thenReturn(carroList);

		CarroService carroService = new CarroServiceImpl(carroRepository);

		assertEquals(carroService.findAll().size(), 3);
	}

	
	@Test
	public void testFindById() {
		CarroRepository carroRepository = mock(CarroRepository.class);
		Carro carro = gerarCarro();
		Optional<Carro> carroOptional = Optional.of(carro);

		when(carroRepository.findById(1)).thenReturn(carroOptional);
		CarroService carroService = new CarroServiceImpl(carroRepository);

		assertEquals(carroService.findById(1).getId(), carro.getId());
		assertEquals(carroService.findById(1).getMarca(), carro.getMarca());
		assertEquals(carroService.findById(1).getModelo(), carro.getModelo());
		assertEquals(carroService.findById(1).getAno(), carro.getAno());
		assertEquals(carroService.findById(1).getPlaca(), carro.getPlaca());
		assertEquals(carroService.findById(1).getStatus(), carro.getStatus());
	}

	@Test
	public void testCreate() {
		CarroRepository carroRepository = mock(CarroRepository.class);
		when(carroRepository.save(Mockito.any(Carro.class))).thenReturn(gerarCarro());

		CarroService carroService = new CarroServiceImpl(carroRepository);
		CarroDTO carroRetorno = carroService.create(gerarCreateCarroDTO());

		assertNotNull(carroRetorno);	
		assertEquals(carroRetorno.getMarca(), gerarCarro().getMarca());
		assertEquals(carroRetorno.getModelo(), gerarCarro().getModelo());
		assertEquals(carroRetorno.getAno(), gerarCarro().getAno());
		assertEquals(carroRetorno.getPlaca(), gerarCarro().getPlaca());
		assertEquals(carroRetorno.getStatus(), gerarCarro().getStatus());
	}
	
	@Test
	public void testUpdate() {
		CarroRepository carroRepository = mock(CarroRepository.class);
		Carro carro = gerarCarro();
		Optional<Carro> carroOptional = Optional.of(carro);

		when(carroRepository.findById(carro.getId())).thenReturn(carroOptional);
		when(carroRepository.save(Mockito.any(Carro.class))).thenReturn(carro);

		CarroService carroService = new CarroServiceImpl(carroRepository);
		CarroDTO carroRetorno = carroService.update(carro.getId(), gerarCreateCarroDTO());

		assertNotNull(carroRetorno);
		assertEquals(carroRetorno.getMarca(), gerarCarro().getMarca());
		assertEquals(carroRetorno.getModelo(), gerarCarro().getModelo());
		assertEquals(carroRetorno.getAno(), gerarCarro().getAno());
		assertEquals(carroRetorno.getPlaca(), gerarCarro().getPlaca());
		assertEquals(carroRetorno.getStatus(), gerarCarro().getStatus());
	}

	@Test
	public void testDelete() {
		CarroRepository carroRepository = mock(CarroRepository.class);
		carroRepository.deleteById(any());
		verify(carroRepository, times(1)).deleteById(any());
	}

	public Carro gerarCarro() {
		Carro carro = new Carro();
		carro.setId(999999999);
		carro.setMarca("marca");
		carro.setModelo("modelo");
		carro.setAno(1234);
		carro.setPlaca("placa");
		carro.setStatus("status");
		carro.setDataCriacao(new Date());
		carro.setDataModificacao(new Date());
		return carro;
	}

	public CreateCarroDTO gerarCreateCarroDTO() {
		CreateCarroDTO carro = new CreateCarroDTO();
		carro.setMarca("marca");
		carro.setModelo("modelo");
		carro.setAno(1234);
		carro.setPlaca("placa");
		carro.setStatus("status");
		return carro;
	}

}
