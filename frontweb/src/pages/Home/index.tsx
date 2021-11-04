import { Switch, Route } from 'react-router';
import PrivateRoute from 'components/PrivateRoute';
import Auth from './Auth';
import Catalog from 'pages/Home/Catalog';

const Home = () => {
   return (
      {
         /*<Switch>
         <Route path="/auth" exact>
            <Auth />
         </Route>
         <PrivateRoute path="/movies">
            <Catalog />
         </PrivateRoute>
      </Switch>*/
      } && <h1>teste</h1>
   );
};

export default Home;
