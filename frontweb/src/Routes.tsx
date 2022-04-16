import Navbar from 'components/Navbar';
import { Redirect, Route, Router, Switch  } from 'react-router-dom';
import MovieDetails from 'pages/MovieDetails';
import Movies from 'pages/Movies';
import Auth from 'pages/Admin/Auth';
import history from 'util/history';

const Routes = () => {
  return (
    <Router history={history}>
      <Navbar />
      <Switch>
        <Redirect from="/" to="/admin/auth/login" exact/>
        <Route path="/" exact>
          <Auth />
        </Route>
        <Route path="/movies" exact>
          <Movies />
        </Route>
        <Route path="/movies/:movieId">
          <MovieDetails />
        </Route>
        <Redirect from="/admin/auth" to="/admin/auth/login" exact/>
        <Route path="/admin/auth">
          <Auth/>
        </Route>
      </Switch>
    </Router>
  );
}

export default Routes;
