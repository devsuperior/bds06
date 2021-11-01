import { BrowserRouter, Switch, Route } from 'react-router-dom';

import Navbar from 'components/Navbar';
import Home from 'pages/Home';

const Routes = function () {
   return (
      <BrowserRouter>
         <Navbar />
         <Switch>
            <Route path="/" exact>
               <Home />
            </Route>
            <Route path="/movies" exact>
               <h1>Movies</h1>
            </Route>
         </Switch>
      </BrowserRouter>
   );
};

export default Routes;
