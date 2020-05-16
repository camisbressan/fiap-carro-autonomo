
import React, { Component } from 'react'
import Car from './Car';
import './App.css';
import Directions from "../components/Directions/DirectionsIndex";
import LocationSearchInput from '../components/LocationSearchInput';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cars: [],
      loading: true,
      baseUrl: "http://localhost:8080/v1/",
      user: "fiapcarroautonomo",
      password: "password",
      accessToken: "",
      carScreen: false,
      carIdSelected: "",
      adress: "",
      latLngOrigin: "",
      latLngDestination: "",
      result: "",
      showMap: false,
      trip: ""
    };

    this.setGeolocationOrigin = this.setGeolocationOrigin.bind(this);
    this.setGeolocationDestination = this.setGeolocationDestination.bind(this);
  }



  componentWillMount() {
    this.postAuth();
  }

  postAuth = () => {
    let { user, password } = this.state
    fetch(this.state.baseUrl + "authenticate", {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        usuario: user,
        senha: password,
      }),
    }).then(function (response) {
      return response.json();
    }).then((data) => {
      this.setState({ accessToken: data.token }, this.getCars)
    }).catch(
      error => {
        console.log(error)
        this.setState({ result: "Erro ao gerar o token, tente novamente mais tarde" })
      }
    );;
  }

  getCars = () => {
    fetch(this.state.baseUrl + "carro", {
      method: 'GET',
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Authorization': 'Bearer ' + this.state.accessToken,
      },
    }).then((response) => response.json()
      .then((result) => {
        let cars = result.filter(car => car.status === "Disponível");
        this.setState({ cars, loading: false, carIdSelected: cars[0].id })
      })
    ).catch(
      error => {
        console.log(error)
        this.setState({ result: "Erro ao buscar informações de carros, tente novamente mais tarde" })
      }
    );
  }

  requestTrip = () => {
    let { latLngDestination, latLngOrigin, baseUrl, accessToken, carIdSelected } = this.state
    let date = new Date().toLocaleString();
    date = date.split("/").join("-");

    fetch(baseUrl + "viagem", {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + accessToken,
      },
      body: JSON.stringify({
        distanciaPercorrida: "0",
        horaFim: "",
        horaInicio: "",
        idCarro: carIdSelected.toString(),
        latitudeDestinoUsuario: latLngOrigin.lat,
        latitudeOrigemUsuario: latLngOrigin.lng,
        longitudeDestinoUsuario: latLngDestination.lat,
        longitudeOrigemUsuario: latLngDestination.lng,
        status: "Solicitada",
        valor: 0
      }),
    }).then(function (response) {
      return response.json();
    }).then((data) => {
      this.setState({ trip: data }, this.showMap);
    }).catch(
      error => {
        console.log(error)
        this.setState({ result: "Erro ao solicitar viagem, tente novamente mais tarde" })
      }
    );;

  }

  finishTrip = () => {
    let { latLngDestination, latLngOrigin, baseUrl, accessToken, carIdSelected } = this.state
    let date = new Date().toLocaleString();
    date = date.split("/").join("-");

    fetch(baseUrl + "viagem", {
      method: 'PUT',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + accessToken,
      },
      body: JSON.stringify({
        distanciaPercorrida: "0",
        horaFim: "",
        horaInicio: "",
        idCarro: carIdSelected.toString(),
        latitudeDestinoUsuario: latLngOrigin.lat,
        latitudeOrigemUsuario: latLngOrigin.lng,
        longitudeDestinoUsuario: latLngDestination.lat,
        longitudeOrigemUsuario: latLngDestination.lng,
        status: "Finalizada",
        valor: 0
      }),
    }).then(function (response) {
      return response.json();
    }).then((data) => {
      this.showMap();
    }).catch(
      error => {
        console.log(error)
        this.setState({ result: "Erro ao solicitar viagem, tente novamente mais tarde" }, this.showMap())
      }
    );;
  }

  handleCarChange = (event) => {
    this.setState({ carIdSelected: event.target.value });
  }

  carScreen = () => {
    this.setState({ carScreen: !this.state.carScreen });
  };

  showMap = () => {
    this.setState({ showMap: !this.state.showMap });
  };

  setGeolocationOrigin = (latLngOrigin) => {
    this.setState({
      latLngOrigin
    })
  }

  setGeolocationDestination = (latLngDestination) => {
    this.setState({
      latLngDestination
    })
  }

  render() {


    let { cars, accessToken, baseUrl } = this.state

    return (
      <div className="app">

        {this.state.carScreen === true ?
          <Car
            show={this.carScreen}
            baseUrl={baseUrl}
            accessToken={accessToken}
          /> :
          <div className="content">
            <h2>Inicie sua viagem.</h2>
            {cars.length > 0 ? (
              <>
                <label className="item">Escolha um veículo:</label>
                <select className="item" value={this.state.carIdSelected} onChange={this.handleCarChange}>
                  {cars.map((car) => {
                    return (
                      <option key={car.id} value={car.id}>{car.marca} {car.modelo}</option>
                    )
                  })}
                </select>

                <label className="item">Origem:</label>
                <LocationSearchInput setGeolocationCallback={this.setGeolocationOrigin} />

                <label className="item">Destino:</label>
                <LocationSearchInput setGeolocationCallback={this.setGeolocationDestination} />

              </>
            ) : <h4 style={{ color: "red" }} className="item">Sem veículos disponíveis</h4>}

            <a className="item" style={{ color: "blue", justifyContent: "flex-end" }} onClick={this.carScreen}>Deseja cadastrar um veiculo?</a>
            <h4>{this.state.resut}</h4>
            <div className="mt10 end">              
              <button className="w50" onClick={this.requestTrip}>Iniciar viagem</button>
            </div>
          </div>
        }
        <div>
          {this.state.showMap ? (
            <>
              <Directions
                latLngOrigin={this.state.latLngOrigin}
                latLngDestination={this.state.latLngDestination}
              />

              <div className="mt10 end">
                <button className="w50" onClick={this.finishTrip}>Finalizar Viagem</button>
              </div>
            </>
          ) : null
          }
        </div>
      </div>

    );
  }

}

export default App;