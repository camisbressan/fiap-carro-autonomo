import React, { Component } from 'react';
import './Car.css';

class Car extends Component {

  state = {
    year: "",
    brand: "",
    model: "",
    plaque: "",
    stats: "Disponível"
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
    
    fetch("http://localhost:8080/v1/" + "carro", {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmaWFwY2Fycm9hdXRvbm9tbyIsImV4cCI6MTU4OTM4NTgyMCwiaWF0IjoxNTg5MjA1ODIwfQ.HwMSAg-zdYr5yBZH8PG7dttkCE53H3nLQ4Y-X0wV462hsja9NnIgMFYHMXogYZUTP0Mko__glRqhEAxMreRm0Q',
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
      console.log(data);
      
    });
  }

  render() {

    let { baseUrl, accessToken } = this.props;
    console.log(baseUrl);
    return (
      <div className="container center">
        <h4>Cadastre um carro novo veículo</h4>

        <label className="item">Ano</label>
        <input className="item" type="text" value={this.state.year} onChange={this.handleYearChange} />

        <label className="item">Marca</label>
        <input className="item" type="text" value={this.state.brand} onChange={this.handleBrandChange} />

        <label className="item">Modelo</label>
        <input className="item" type="text" value={this.state.model} onChange={this.handleModelChange} />

        <label className="item">Placa</label>
        <input className="item" type="text" value={this.state.plaque} onChange={this.handlePlaqueChange} />

        <label className="item">Status</label>
        <select className="item" value={this.state.stats} onChange={this.handleStateChange}>
          <option value="Disponível">Disponível</option>
          <option value="Ocupado">Ocupado</option>
        </select>

        <button className="w80 mt10" onClick={this.btnCreateCar}>Salvar</button>
      </div>
    );
  }
}

export default Car;
