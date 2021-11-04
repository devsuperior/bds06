import './styles.css';

import { ReactComponent as BannerImg } from 'assets/images/banner-img.svg';
import Login from './Login';
import { Switch, Route } from 'react-router';

const Auth = function () {
   return (
      <div className="auth">
         <div className="auth-container">
            <div className="banner">
               <div className="banner-content">
                  <h1>Avalie Filmes</h1>
                  <p>Diga o que você achou do seu filme favorito</p>
               </div>
               <div className="banner-img">
                  <BannerImg />
               </div>
            </div>

            <div className="form-container">
               <Switch>
                  <Route path="/auth/login" exact>
                     <Login />
                  </Route>

                  <Route path="/auth/signup" exact>
                     <h1>Signup</h1>
                  </Route>
               </Switch>
            </div>
         </div>
      </div>
   );
};

export default Auth;
