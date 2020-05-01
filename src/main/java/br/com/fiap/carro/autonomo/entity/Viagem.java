package br.com.fiap.carro.autonomo.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import br.com.fiap.carro.autonomo.dto.CreateViagemDTO;

@Entity
@Table(name = "TB_VIAGEM")
public class Viagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "id_carro")
	private Integer idCarro;
	
	@Column(name = "latitude_origem_usuario")
	private BigDecimal latitudeOrigemUsuario;

	@Column(name = "longitude_origem_usuario")
	private BigDecimal longitudeOrigemUsuario;
	
	@Column(name = "latitude_destino_usuario")
	private BigDecimal latitudeDestinoUsuario;

	@Column(name = "longitude_destino_usuario")
	private BigDecimal longitudeDestinoUsuario;

	@Column(name = "status")
	private String status;

	@Column(name = "distancia_percorrida")
	private BigDecimal distanciaPercorrida;

	@Column(name = "valor")
	private BigDecimal valor;
	
	@Column(name = "data_criacao")
	@CreatedDate
	private Date dataCriacao;

	@Column(name = "data_atualizacao")
	@LastModifiedDate
	private Date dataModificacao;

	public Viagem() {
	}	

	public Viagem(CreateViagemDTO createViagemDTO) {
		this.idCarro = createViagemDTO.getIdCarro();
		this.latitudeOrigemUsuario = createViagemDTO.getLatitudeOrigemUsuario();
		this.longitudeOrigemUsuario = createViagemDTO.getLongitudeOrigemUsuario();
		this.latitudeDestinoUsuario = createViagemDTO.getLatitudeDestinoUsuario();
		this.longitudeDestinoUsuario = createViagemDTO.getLongitudeDestinoUsuario();
		this.status = createViagemDTO.getStatus();
		this.distanciaPercorrida = createViagemDTO.getDistanciaPercorrida();
		this.valor = createViagemDTO.getValor();
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

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

}
