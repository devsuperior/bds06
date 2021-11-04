import { Router, Switch, Route, Redirect } from 'react-router-dom';
import history from 'utils/history';
import Navbar from 'components/Navbar';
import Home from 'pages/Home';
import Auth from 'pages/Home/Auth';

const Routes = function () {
   return (
      <Router history={history}>
         <Navbar />
         <Switch>
            <Redirect from="/" to="/auth" exact />
            <Route path="/" exact>
               <Home />
            </Route>
            <Redirect from="/auth" to="/auth/login" exact />
            <Route path="/auth">
               <Auth />
            </Route>
         </Switch>
      </Router>
   );
};

export default Routes;
