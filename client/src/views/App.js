import React, { Component } from 'react'
import Car from './Car';
import './App.css';

class App extends Component {

  state = {
    cars: [],
    loading: true,
    baseUrl: "http://localhost:8080/v1/",
    user: "fiapcarroautonomo",
    password: "password",
    accessToken: "",
    carScreen: false
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
      .then((cars) => {
        console.log(cars);
        this.setState({ cars, loading: false })
      })
    ).catch(
      error => console.log(error)
    );
  }

  carScreen = () => {
    this.setState({carScreen: true });
  };

  render() {

    return (
      <div className="app center">
        {this.state.carScreen === true ?
        <Car /> :
        <div className="content">
          <h4>Carro Autônomo.</h4>
          <button onClick={this.carScreen}>Cadastrar veículo</button>
        </div>
        }
      </div>
    );
  }
}

export default App;
