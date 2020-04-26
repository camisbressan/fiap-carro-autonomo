package br.com.fiap.carro.autonomo.dto;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.fiap.carro.autonomo.entity.Carro;

public class CarroDTO {

	private Integer id;

    private String marca;
	
    private String modelo;
    
    private Integer ano;
	
    private String placa;
	
    private String status;
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private ZonedDateTime dataCriacao;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private ZonedDateTime dataAtualizacao;
    
    public CarroDTO(){}  

	public CarroDTO(Integer id, String marca, String modelo, Integer ano, String placa, String status,
			ZonedDateTime dataCriacao, ZonedDateTime dataAtualizacao) {
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.placa = placa;
		this.status = status;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
	}

	public CarroDTO(CreateCarroDTO transacaoDTO, Integer id) {
		this.id = id;
		this.marca = transacaoDTO.getMarca();
		this.modelo = transacaoDTO.getModelo();
		this.ano = transacaoDTO.getAno();
		this.placa = transacaoDTO.getPlaca();
		this.status = transacaoDTO.getStatus();
	}

	public CarroDTO(Carro carro) {
		this.id = carro.getId();
		this.marca = carro.getMarca();
		this.modelo = carro.getModelo();
		this.ano = carro.getAno();
		this.placa = carro.getPlaca();
		this.status = carro.getStatus();
		this.dataCriacao = convertToZonedDateTime(carro.getDataCriacao());
		this.dataAtualizacao = convertToZonedDateTime(carro.getDataModificacao());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    private ZonedDateTime convertToZonedDateTime(Date data) {
        if(data != null){
            return ZonedDateTime.ofInstant(data.toInstant(), ZoneOffset.systemDefault());
        } else {
            return null;
        }
    }

    public ZonedDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(ZonedDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public ZonedDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(ZonedDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
    
}
