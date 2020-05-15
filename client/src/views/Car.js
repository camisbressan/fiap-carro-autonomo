import React, { Component } from 'react';
import './Car.css';

class Car extends Component {
  constructor(props) {
    super(props);
    this.state = {
      year: "",
      brand: "",
      model: "",
      plaque: "",
      stats: "Disponível",
      result: ""
    };
  };

  handleYearChange = (event) => {
    this.setState({ year: event.target.value });
  }

  handleBrandChange = (event) => {
    this.setState({ brand: event.target.value });
  }

  handleModelChange = (event) => {
    this.setState({ model: event.target.value });
  }

  handlePlaqueChange = (event) => {
    this.setState({ plaque: event.target.value });
  }

  handleStateChange = (event) => {

    console.log(event.target.value);
    this.setState({ stats: event.target.value });
  }



  btnCreateCar = () => {
    let { year, brand, model, plaque, stats } = this.state

    fetch(this.props.baseUrl + "carro", {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + this.props.accessToken,
      },
      body: JSON.stringify({
        ano: year,
        marca: brand,
        modelo: model,
        placa: plaque,
        status: stats,

      }),
    }).then(function (response) {
      return response.json();
    }).then((data) => {
      this.setState({ result: "Carro cadastrado com sucesso" });
    }).catch(
      error => {
        this.setState({ result: "Tivemos um problema, tente novamente mais tarde!" });
        console.log(error)
      }
    );;
  }

  btnSair = () => {
    this.props.show();
  }

  render() {

    let { baseUrl, accessToken } = this.props;
    console.log(baseUrl);
    return (
      <div className="container center">
        <h2>Cadastre um carro novo veículo</h2>

        <label>Ano</label>
        <input type="text" value={this.state.year} onChange={this.handleYearChange} />

        <label>Marca</label>
        <input type="text" value={this.state.brand} onChange={this.handleBrandChange} />

        <label>Modelo</label>
        <input type="text" value={this.state.model} onChange={this.handleModelChange} />

        <label>Placa</label>
        <input type="text" value={this.state.plaque} onChange={this.handlePlaqueChange} />

        <label>Status</label>
        <select value={this.state.stats} onChange={this.handleStateChange}>
          <option value="Disponível">Disponível</option>
          <option value="Ocupado">Ocupado</option>
        </select>
        <h4 style={{ color: "blue" }}>{this.state.result}</h4>
        <div className="w100">
          <button className="w50 mt10" onClick={this.btnSair}>Sair</button>
          <button className="w50 mt10" onClick={this.btnCreateCar}>Salvar</button>
        </div>
      </div>
    );
  }
}

export default Car;
