package br.com.fiap.carro.autonomo.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.fiap.carro.autonomo.dto.CreateViagemDTO;
import br.com.fiap.carro.autonomo.dto.ViagemDTO;
import br.com.fiap.carro.autonomo.entity.Viagem;
import br.com.fiap.carro.autonomo.repository.ViagemRepository;
import br.com.fiap.carro.autonomo.service.ViagemService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ViagemServiceImplTest {

	@Test
	public void testFindAll() {
		ViagemRepository viagemRepository = mock(ViagemRepository.class);
		List<Viagem> viagemList = new ArrayList<Viagem>();
		viagemList.add(new Viagem());
		viagemList.add(new Viagem());
		viagemList.add(new Viagem());
		when(viagemRepository.findAll()).thenReturn(viagemList);

		ViagemService viagemService = new ViagemServiceImpl(viagemRepository);

		assertEquals(viagemService.findAll().size(), 3);
	}

	
	@Test
	public void testFindById() {
		ViagemRepository viagemRepository = mock(ViagemRepository.class);
		Viagem viagem = gerarViagem();
		Optional<Viagem> viagemOptional = Optional.of(viagem);

		when(viagemRepository.findById(1)).thenReturn(viagemOptional);
		ViagemService viagemService = new ViagemServiceImpl(viagemRepository);

		assertEquals(viagemService.findById(1).getId(), viagem.getId());
		assertEquals(viagemService.findById(1).getIdCarro(), viagem.getIdCarro());
		assertEquals(viagemService.findById(1).getLatitudeOrigemUsuario(), viagem.getLatitudeOrigemUsuario());
		assertEquals(viagemService.findById(1).getLongitudeOrigemUsuario(), viagem.getLongitudeOrigemUsuario());
		assertEquals(viagemService.findById(1).getLatitudeDestinoUsuario(), viagem.getLatitudeDestinoUsuario());
		assertEquals(viagemService.findById(1).getLongitudeDestinoUsuario(), viagem.getLongitudeDestinoUsuario());
		assertEquals(viagemService.findById(1).getStatus(), viagem.getStatus());
		assertEquals(viagemService.findById(1).getDistanciaPercorrida(), viagem.getDistanciaPercorrida());
		assertEquals(viagemService.findById(1).getValor(), viagem.getValor());
	}

	@Test
	public void testCreate() {
		ViagemRepository viagemRepository = mock(ViagemRepository.class);
		when(viagemRepository.save(Mockito.any(Viagem.class))).thenReturn(gerarViagem());

		ViagemService viagemService = new ViagemServiceImpl(viagemRepository);
		ViagemDTO viagemRetorno = viagemService.create(gerarCreateViagemDTO());

		assertNotNull(viagemRetorno);	
		assertEquals(viagemRetorno.getId(), gerarViagem().getId());
		assertEquals(viagemRetorno.getIdCarro(), gerarViagem().getIdCarro());
		assertEquals(viagemRetorno.getLatitudeOrigemUsuario(), gerarViagem().getLatitudeOrigemUsuario());
		assertEquals(viagemRetorno.getLongitudeOrigemUsuario(), gerarViagem().getLongitudeOrigemUsuario());
		assertEquals(viagemRetorno.getLatitudeDestinoUsuario(), gerarViagem().getLatitudeDestinoUsuario());
		assertEquals(viagemRetorno.getLongitudeDestinoUsuario(), gerarViagem().getLongitudeDestinoUsuario());
		assertEquals(viagemRetorno.getStatus(), gerarViagem().getStatus());
		assertEquals(viagemRetorno.getDistanciaPercorrida(), gerarViagem().getDistanciaPercorrida());
		assertEquals(viagemRetorno.getValor(), gerarViagem().getValor());

	}
	
	@Test
	public void testUpdate() {
		ViagemRepository viagemRepository = mock(ViagemRepository.class);
		Viagem viagem = gerarViagem();
		Optional<Viagem> viagemOptional = Optional.of(viagem);

		when(viagemRepository.findById(viagem.getId())).thenReturn(viagemOptional);
		when(viagemRepository.save(Mockito.any(Viagem.class))).thenReturn(viagem);

		ViagemService viagemService = new ViagemServiceImpl(viagemRepository);
		ViagemDTO viagemRetorno = viagemService.update(viagem.getId(), gerarCreateViagemDTO());

		assertNotNull(viagemRetorno);
		assertEquals(viagemRetorno.getId(), gerarViagem().getId());
		assertEquals(viagemRetorno.getIdCarro(), gerarViagem().getIdCarro());
		assertEquals(viagemRetorno.getLatitudeOrigemUsuario(), gerarViagem().getLatitudeOrigemUsuario());
		assertEquals(viagemRetorno.getLongitudeOrigemUsuario(), gerarViagem().getLongitudeOrigemUsuario());
		assertEquals(viagemRetorno.getLatitudeDestinoUsuario(), gerarViagem().getLatitudeDestinoUsuario());
		assertEquals(viagemRetorno.getLongitudeDestinoUsuario(), gerarViagem().getLongitudeDestinoUsuario());
		assertEquals(viagemRetorno.getStatus(), gerarViagem().getStatus());
		assertEquals(viagemRetorno.getDistanciaPercorrida(), gerarViagem().getDistanciaPercorrida());
		assertEquals(viagemRetorno.getValor(), gerarViagem().getValor());
	}

	@Test
	public void testDelete() {
		ViagemRepository viagemRepository = mock(ViagemRepository.class);
		viagemRepository.deleteById(any());
		verify(viagemRepository, times(1)).deleteById(any());
	}

	public Viagem gerarViagem() {
		Viagem viagem = new Viagem();
		viagem.setId(999999999);
		viagem.setIdCarro(999999999);
		viagem.setLatitudeOrigemUsuario(new BigDecimal(99999.99));
		viagem.setLongitudeOrigemUsuario(new BigDecimal(99999.99));
		viagem.setLatitudeDestinoUsuario(new BigDecimal(99999.99));
		viagem.setLongitudeDestinoUsuario(new BigDecimal(99999.99));
		viagem.setStatus("status");
		viagem.setDistanciaPercorrida(new BigDecimal(99.99));
		viagem.setValor(new BigDecimal(99.99));
		viagem.setDataCriacao(new Date());
		viagem.setDataModificacao(new Date());
		return viagem;
	}

	public CreateViagemDTO gerarCreateViagemDTO() {
		CreateViagemDTO viagem = new CreateViagemDTO();
		viagem.setIdCarro(999999999);
		viagem.setLatitudeOrigemUsuario(new BigDecimal(99999.99));
		viagem.setLongitudeOrigemUsuario(new BigDecimal(99999.99));
		viagem.setLatitudeDestinoUsuario(new BigDecimal(99999.99));
		viagem.setLongitudeDestinoUsuario(new BigDecimal(99999.99));
		viagem.setStatus("status");
		viagem.setDistanciaPercorrida(new BigDecimal(99.99));
		viagem.setValor(new BigDecimal(99.99));
		viagem.setStatus("status");
		return viagem;
	}

}
