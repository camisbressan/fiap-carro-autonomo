import {BrowserRouter, Switch, Route} from 'react-router-dom';

//Importar as páginas
import App from './views/App';
import Car from './views/Car';
import Viagem from './views/Viagem';

//Criar o componentes com as rotas
function Routes(){
    return(
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={App} />
                <Route path="/carro" component={Car} />
                <Route path="/viagem" component={Viagem} />
            </Switch>        
        </BrowserRouter>
    );
};

export default Routes;