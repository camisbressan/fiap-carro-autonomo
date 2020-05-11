import React, { Component } from 'react';
import './App.css';

class App extends Component {

  state = {
    cars: [],
    loading: true,
    baseUrl: "http://localhost:8080/v1/",
    user: "fiapcarroautonomo",
    password: "password", 
    accessToken: ""
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
      console.log(data.token);
      this.setState({ accessToken: data.token }, this.getCars)
    });
  }

  getCars = () => {
    fetch(this.state.baseUrl + "carro", {
      method: 'GET',
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Authorization': 'Bearer ' + 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmaWFwY2Fycm9hdXRvbm9tbyIsImV4cCI6MTU4OTM4NTgyMCwiaWF0IjoxNTg5MjA1ODIwfQ.HwMSAg-zdYr5yBZH8PG7dttkCE53H3nLQ4Y-X0wV462hsja9NnIgMFYHMXogYZUTP0Mko__glRqhEAxMreRm0Q',
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

  handleChange = (event) => {
    this.setState({ text: event.target.value });
  }

  postComments = () => {
    let { text } = this.state

    fetch(this.state.baseUrl, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        text: text,
      }),
    }).then(function (response) {
      return response.json();
    }).then((data) => {
      this.setState({ loading: false, text: "" }, this.getComments)
    });
  }

  playerSound = (id) => {
    
      let audio = new Audio("../../audio/comment_31.wav");
      console.log(audio);
      audio.play();
  }

  render() {
    const { comments } = this.state

    return (
      <div className="app center">    
        <div className="content">
          <h4>Inicie sua via</h4>
          </div>
      </div>
    );
  }
}

export default App;
