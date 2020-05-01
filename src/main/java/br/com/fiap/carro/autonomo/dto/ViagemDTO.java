package br.com.fiap.carro.autonomo.dto;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.fiap.carro.autonomo.entity.Viagem;

public class ViagemDTO {

	private Integer id;

	private Integer idCarro;

	private BigDecimal latitudeOrigemUsuario;

	private BigDecimal longitudeOrigemUsuario;
	
	private BigDecimal latitudeDestinoUsuario;

	private BigDecimal longitudeDestinoUsuario;

	private String status;

	private BigDecimal distanciaPercorrida;

	private BigDecimal valor;
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private ZonedDateTime dataCriacao;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private ZonedDateTime dataAtualizacao;
    
    public ViagemDTO(){}    
    
	public ViagemDTO(Integer id, Integer idCarro, BigDecimal latitudeOrigemUsuario, BigDecimal longitudeOrigemUsuario,
			BigDecimal latitudeDestinoUsuario, BigDecimal longitudeDestinoUsuario, ZonedDateTime horaInicio,
			ZonedDateTime horaFim, String status, BigDecimal distanciaPercorrida, BigDecimal valor,
			ZonedDateTime dataCriacao, ZonedDateTime dataAtualizacao) {
		this.id = id;
		this.idCarro = idCarro;
		this.latitudeOrigemUsuario = latitudeOrigemUsuario;
		this.longitudeOrigemUsuario = longitudeOrigemUsuario;
		this.latitudeDestinoUsuario = latitudeDestinoUsuario;
		this.longitudeDestinoUsuario = longitudeDestinoUsuario;
		this.status = status;
		this.distanciaPercorrida = distanciaPercorrida;
		this.valor = valor;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
	}

	public ViagemDTO(CreateViagemDTO viagemDTO, Integer id) {
		this.id = id;
		this.idCarro = viagemDTO.getIdCarro();
		this.latitudeOrigemUsuario = viagemDTO.getLatitudeOrigemUsuario();
		this.longitudeOrigemUsuario = viagemDTO.getLongitudeOrigemUsuario();
		this.latitudeDestinoUsuario = viagemDTO.getLatitudeDestinoUsuario();
		this.longitudeDestinoUsuario = viagemDTO.getLongitudeDestinoUsuario();
		this.status = viagemDTO.getStatus();
		this.distanciaPercorrida = viagemDTO.getDistanciaPercorrida();
		this.valor = viagemDTO.getValor();
	}

	public ViagemDTO(Viagem viagem) {
		this.id = viagem.getId();
		this.idCarro = viagem.getIdCarro();
		this.latitudeOrigemUsuario = viagem.getLatitudeOrigemUsuario();
		this.longitudeOrigemUsuario = viagem.getLongitudeOrigemUsuario();
		this.latitudeDestinoUsuario = viagem.getLatitudeDestinoUsuario();
		this.longitudeDestinoUsuario = viagem.getLongitudeDestinoUsuario();
		this.status = viagem.getStatus();
		this.distanciaPercorrida = viagem.getDistanciaPercorrida();
		this.valor = viagem.getValor();
		this.dataCriacao = convertToZonedDateTime(viagem.getDataCriacao());
		this.dataAtualizacao = convertToZonedDateTime(viagem.getDataModificacao());
	}


    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCarro() {
		return idCarro;
	}

	public void setIdCarro(Integer idCarro) {
		this.idCarro = idCarro;
	}

	public BigDecimal getLatitudeOrigemUsuario() {
		return latitudeOrigemUsuario;
	}

	public void setLatitudeOrigemUsuario(BigDecimal latitudeOrigemUsuario) {
		this.latitudeOrigemUsuario = latitudeOrigemUsuario;
	}

	public BigDecimal getLongitudeOrigemUsuario() {
		return longitudeOrigemUsuario;
	}

	public void setLongitudeOrigemUsuario(BigDecimal longitudeOrigemUsuario) {
		this.longitudeOrigemUsuario = longitudeOrigemUsuario;
	}

	public BigDecimal getLatitudeDestinoUsuario() {
		return latitudeDestinoUsuario;
	}

	public void setLatitudeDestinoUsuario(BigDecimal latitudeDestinoUsuario) {
		this.latitudeDestinoUsuario = latitudeDestinoUsuario;
	}

	public BigDecimal getLongitudeDestinoUsuario() {
		return longitudeDestinoUsuario;
	}

	public void setLongitudeDestinoUsuario(BigDecimal longitudeDestinoUsuario) {
		this.longitudeDestinoUsuario = longitudeDestinoUsuario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getDistanciaPercorrida() {
		return distanciaPercorrida;
	}

	public void setDistanciaPercorrida(BigDecimal distanciaPercorrida) {
		this.distanciaPercorrida = distanciaPercorrida;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
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
