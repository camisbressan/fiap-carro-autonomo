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

import br.com.fiap.carro.autonomo.dto.CarroDTO;
import br.com.fiap.carro.autonomo.dto.CreateCarroDTO;
import br.com.fiap.carro.autonomo.service.impl.CarroServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Carro")
@RequestMapping("/v1/carro")
public class CarroController {

	private final CarroServiceImpl service;

	public CarroController(CarroServiceImpl service) {
		this.service = service;
	}

	@ApiOperation(value = "Lista todos os carros")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Carros listados com sucesso"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@GetMapping
	public List<CarroDTO> getAll() {
		return service.findAll();
	}

	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Obtém um carro")
	@ApiResponses({ @ApiResponse(code = 200, message = "Carro encontrado"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@GetMapping("{id}")
	public CarroDTO findById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Cria um carro")
	@ApiResponses({ @ApiResponse(code = 201, message = "Carro criado com sucesso"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CarroDTO create(@RequestBody @Valid CreateCarroDTO createTransacaoDTO) {
		return service.create(createTransacaoDTO);
	}

	@ApiOperation(value = "Atualiza um carro")
	@ApiResponses({ @ApiResponse(code = 200, message = "Carro atualizado com sucesso"),
			@ApiResponse(code = 201, message = "Aluno criado com sucesso"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@PutMapping("{id}")
	public ResponseEntity<CarroDTO> update(@PathVariable Integer id, @RequestBody CreateCarroDTO createTransacaoDTO) {
		return ResponseEntity.ok(service.update(id, createTransacaoDTO));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Exclui um carro")
	@ApiResponses({ @ApiResponse(code = 204, message = "Exclusão com sucesso de um carro"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}
