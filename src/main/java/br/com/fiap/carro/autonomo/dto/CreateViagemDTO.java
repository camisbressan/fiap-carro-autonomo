package br.com.fiap.carro.autonomo.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CreateViagemDTO {

	private Integer id;

	private Integer idCarro;

	private BigDecimal latitudeOrigemUsuario;

	private BigDecimal longitudeOrigemUsuario;
	
	private BigDecimal latitudeDestinoUsuario;

	private BigDecimal longitudeDestinoUsuario;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private ZonedDateTime horaInicio;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private ZonedDateTime horaFim;

	private String status;

	private BigDecimal distanciaPercorrida;

	private BigDecimal valor;

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

	public ZonedDateTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(ZonedDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public ZonedDateTime getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(ZonedDateTime horaFim) {
		this.horaFim = horaFim;
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

}
