import './styles.css';

import Button from 'components/Button';

const Login = () => {
   return (
      <div className= "login-card">
         <h1>LOGIN</h1>
         <form>
            <div className="mb-4">
               <input
                  type="text"
                  className="form-control base-input login-input"
                  placeholder="Email"
                  name="username"
               />
            </div>
            <div className="mb-2">
               <input
                  type="password"
                  className="form-control base-input login-input"
                  placeholder="Senha"
                  name="password"
               />
            </div>
            <div className="login-submit">
               <Button text="Fazer login" />
            </div>
         </form>
      </div>
   );
};

export default Login;
