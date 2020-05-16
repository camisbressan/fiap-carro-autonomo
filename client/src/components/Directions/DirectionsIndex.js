import React, { Component } from "react";
import { compose, withProps } from "recompose";
import DirectionRenderComponent from "./DirectionRenderComponent";
import { G_API_URL } from "../../utility/constants";
import { createLocationObject } from "../../utility/helper";
//import DummyLocations from "../../utility/dummyLocations";
const { withScriptjs, withGoogleMap, GoogleMap } = require("react-google-maps");
class Directions extends Component {
  constructor(props) {
    super(props);
  this.state = {
    defaultZoom: 10,
    map: null,
    center: {
      lat: -23.5740998,
      lng: -46.6254161
    },
    origem: {
      lat: "-23.5740998",
      lng: "-46.6254161"
    },
    destino: {
      lat: "-23.6773149",
      lng: "-46.7043569"
    }
  };
};

componentWillReceiveProps(){
  this.setState({ origem: this.props.latLngOrigin, destino: this.props.latLngDestination});
}



  convertTolatLng = (latLng, title) => {
    return {
      latLng,
      title
    };
  };
 

  render() {
    let locationsList = {
      Origem: this.convertTolatLng(this.state.origem.lat + "," + this.state.origem.lng, "Origem"),
      Destino: this.convertTolatLng(this.state.destino.lat + "," + this.state.destino.lng, "Destino")
    }

    let directions = [{
      from: locationsList.Origem,
      to: locationsList.Destino,
      strokeColor: "#f68f54"
    }]


    let DummyLocations = directions.map(elem => {
      return createLocationObject(
        elem.from.latLng,
        elem.from.title,
        elem.to.latLng,
        elem.to.title,
        elem.strokeColor
      );
    });

    return (
      <GoogleMap
        defaultZoom={this.state.defaultZoom}
        center={this.state.center}
        defaultCenter={new window.google.maps.LatLng(this.state.lat, this.state.lng)}
      >
        {DummyLocations.map((elem, index) => {
          return (
            <DirectionRenderComponent
              key={index}
              index={index + 1}
              strokeColor={elem.strokeColor}
              from={elem.from}
              to={elem.to}
            />
          );
        })}
      </GoogleMap>
    );
  }
}

export default compose(
  withProps({
    googleMapURL: G_API_URL,
    loadingElement: <div style={{ height: `100%` }} />,
    containerElement: <div style={{ height: `600px` }} />,
    mapElement: <div style={{ height: `100%` }} />,
   // locationsList: this.locationsList
  }),
  withScriptjs,
  withGoogleMap
)(Directions);
