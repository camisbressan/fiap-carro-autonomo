package br.com.fiap.carro.autonomo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.carro.autonomo.dto.CreateViagemDTO;
import br.com.fiap.carro.autonomo.dto.ViagemDTO;
import br.com.fiap.carro.autonomo.service.impl.ViagemServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Viagem")
@RequestMapping("/v1/viagem")
public class ViagemController {

	private final ViagemServiceImpl service;

	public ViagemController(ViagemServiceImpl service) {
		this.service = service;
	}

	@ApiOperation(value = "Lista todas as viagens")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Viagens listadas com sucesso"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@GetMapping
	public List<ViagemDTO> getAll() {
		return service.findAll();
	}

	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Obtém uma viagem")
	@ApiResponses({ @ApiResponse(code = 200, message = "Viagem encontrada"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@GetMapping("{id}")
	public ViagemDTO findById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Consulta uma viagem pelo id da viagem e id do carro")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Viagens listados com sucesso"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@GetMapping("{id}/carro/{idCarro}")
	public ViagemDTO findByIdViagemIdCarro(@PathVariable Integer id, @PathVariable Integer idCarro) {
		return service.findByIdViagemIdCarro(id, idCarro);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Lista todas as viagens feitas por um carro")
	@ApiResponses({ @ApiResponse(code = 200, message = "Viagem encontrado"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@GetMapping("carro/{id}")
	public List<ViagemDTO> findByIdCarro(@PathVariable Integer id) {
		return service.findByIdCarro(id);
	}
	
	@ApiOperation(value = "Cria um viagem")
	@ApiResponses({ @ApiResponse(code = 201, message = "Viagem criada com sucesso"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ViagemDTO create(@RequestBody @Valid CreateViagemDTO createTransacaoDTO) {
		return service.create(createTransacaoDTO);
	}

	@ApiOperation(value = "Atualiza um viagem")
	@ApiResponses({ @ApiResponse(code = 200, message = "Viagem atualizada com sucesso"),
			@ApiResponse(code = 201, message = "Aluno criado com sucesso"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@PutMapping("{id}")
	public ResponseEntity<ViagemDTO> update(@PathVariable Integer id, @RequestBody CreateViagemDTO createTransacaoDTO) {
		return ResponseEntity.ok(service.update(id, createTransacaoDTO));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Exclui um viagem")
	@ApiResponses({ @ApiResponse(code = 204, message = "Exclusão com sucesso de um viagem"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}
