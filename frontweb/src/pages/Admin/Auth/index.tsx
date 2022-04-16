import { ReactComponent as AuthImg } from 'assets/images/auth-image.svg';
import { Route, Switch } from 'react-router-dom';
import Login from './Login';

import './styles.css';

const Auth = () => {
    return(
        <div className="auth-container">
            <div className="auth-banner-container">
                <h1>Avalie Filmes</h1>
                <p>Diga o que você achou do seu filme favorito</p>
                <AuthImg/>
            </div>
            <div className="auth-form-container">
                <Switch>
                    <Route path="/admin/auth/login">
                        <Login/>
                    </Route>
                </Switch>
            </div>
        </div>
    )
}

export default Auth;