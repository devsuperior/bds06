import { Router, Switch, Route, Redirect } from 'react-router-dom';
import PrivateRoute from 'components/PrivateRoute';
import history from 'utils/history';
import Navbar from 'components/Navbar';
import Auth from 'pages/Home/Auth';
import Catalog from 'pages/Home/Catalog';
import MovieDetails from 'pages/Home/MovieDetails';

const Routes = function () {
   return (
      <Router history={history}>
         <Navbar />
         <Switch>
            <Redirect from="/" to="/movies" exact />
            {/*<Route path="/" exact>
               <Home />
   </Route>*/}

            <Redirect from="/auth" to="/auth/login" exact />
            <Route path="/auth">
               <Auth />
            </Route>

            <PrivateRoute path="/movies" exact>
               <Catalog />
            </PrivateRoute>

            <PrivateRoute path="/movies/:movieId">
               <MovieDetails />
            </PrivateRoute>
         </Switch>
      </Router>
   );
};

export default Routes;
