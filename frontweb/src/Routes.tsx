import { BrowserRouter, Switch, Route } from 'react-router-dom';

import Navbar from 'components/Navbar';
import Home from 'pages/Home';
import Catalog from 'pages/Catalog';

const Routes = function () {
   return (
      <BrowserRouter>
         <Navbar />
         <Switch>
            <Route path="/" exact>
               <Home />
            </Route>
            <Route path="/movies" exact>
               <Catalog />
            </Route>
         </Switch>
      </BrowserRouter>
   );
};

export default Routes;
