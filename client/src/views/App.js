
import React, { Component } from 'react'
import Car from './Car';
import './App.css';
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
      carIdSelected: undefined,
      adress: "",
      latLngOrigin: "",
      latLngDestination: "",
      result: ""
    };

    this.setGeolocationOrigin = this.setGeolocationOrigin.bind(this);
    this.setGeolocationDestination = this.setGeolocationDestination.bind(this);
  }



  componentWillMount() {
    this.postAuth();
  }

  postAuth = () => {
    let { user, password } = this.state
    console.log(this.state.baseUrl + "authenticate")
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
      console.log('gerando token ' + data.token)
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
        console.log(cars)
        this.setState({ cars, loading: false })
      })
    ).catch(
      error => {
        console.log(error)
        this.setState({ result: "Erro ao buscar informações de carros, tente novamente mais tarde" })
      }
    );
  }

  requestTrip = () => {
    let { latLngDestination, latLngOrigin, baseUrl, accessToken } = this.state
    let date = new Date().toLocaleString();

    console.log(baseUrl + "viagem")
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
        horaInicio: date,
        idCarro: this.state.carIdSelected,
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
      console.log(data);
    }).catch(
      error => {
        console.log(error)
        this.setState({ result: "Erro ao solicitar viagem, tente novamente mais tarde" })
      }
    );;

  }

  handleCarChange = (event) => {
    console.log(event.target.value);
    this.setState({ carIdSelected: event.target.value });
  }

  carScreen = () => {
    this.setState({ carScreen: !this.state.carScreen });
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

            <div className="mt10" style={{ justifyContent: "flex-end" }} >
              <h4>{this.state.resut}</h4>
              <button className="w50" onClick={this.requestTrip}>Iniciar viagem</button>
            </div>
          </div>
        }
      </div>

    );
  }

}

export default App;
/*import React, { Component } from 'react'
import Car from './Car';
import Map from '../components/Directions/Map';
import './App.css';

class App extends Component {

  state = {
    cars: [],
    loading: true,
    baseUrl: "http://localhost:8080/v1/",
    user: "fiapcarroautonomo",
    password: "password",
    accessToken: "",
    carScreen: false,
    carIdSelected: undefined
  };

  componentWillMount() {
    this.postAuth();
  }

  postAuth = () => {
    let { user, password } = this.state
    console.log(this.state.baseUrl + "authenticate")
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
      console.log('gerando token ' + data.token)
      this.setState({ accessToken: data.token }, this.getCars)
    });
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
        console.log(cars)
        this.setState({ cars, loading: false })
      })
    ).catch(
      error => console.log(error)
    );
  }

  handleCarChange = (event) => {
    this.setState({ carIdSelected: event.target.value });
  }

  carScreen = () => {
    this.setState({ carScreen: true });
  };

  render() {

    let { cars } = this.state

    return (
      <div className="app center">
        <div style={{ margin: '100px' }}>
				<Map
					google={this.props.google}
					center={{lat: -23.5740998, lng: -46.6254161}}
					height='300px'
					zoom={15}
				/>
			</div>
        {this.state.carScreen === true ?
          <Car /> :
          <div className="content">
            <h4>Inicie sua viagem.</h4>


            {cars.length > 0 ? (
              <>
                <label className="item">Escolha um veículo:</label>
                <select className="item" value={this.state.carIdSelected} onChange={this.handleCarChange}>
                  {cars.map((car) => {
                    return (
                      <option key={car.id} value="Disponível">{car.marca} {car.modelo}</option>
                    )
                  })}
                </select>

                <label className="item">Origem:</label>
                <input className="item" type="text" value={this.state.plaque} onChange={this.handlePlaqueChange} />

                <label className="item">Destino:</label>
                <input className="item" type="text" value={this.state.plaque} onChange={this.handlePlaqueChange} />

              </>
            ) : <label className="item">Sem veículos disponíveis</label>}
            <button onClick={this.carScreen}>Cadastrar veículo</button>
          </div>
        }
      </div>
    );
  }
}

export default App;
*/