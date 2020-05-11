import {BrowserRouter, Switch, Route} from 'react-router-dom';

//Importar as páginas
import Main from './views/App';
import SobreEmpresa from './views/Carro';
import Contato from './views/Viagem';

//Criar o componentes com as rotas
function Routes(){
    return(
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={App} />
                <Route path="/carro" component={Carro} />
                <Route path="/viagem" component={Viagem} />
            </Switch>        
        </BrowserRouter>
    );
};

export default Routes;