import './styles.css';

import { useHistory } from 'react-router';
import { useForm } from 'react-hook-form';
import { requestLogin } from 'utils/requests';
import { saveAuthData } from 'utils/storage';
import Button from 'components/Button';
import { useContext } from 'react';
import { AuthContext } from 'AuthContext';
import { getTokenData } from 'utils/auth';

/**
 * Tipo de dados para formulário de login
 */
type FormData = {
   username: string;
   password: string;
};

/**
 * Componente "Login"
 */
const Login = function () {
   /**
    * Hook da dependência `react` para usar o contexto global já criado
    */
   const { setAuthContextData } = useContext(AuthContext);

   /**
    * Hook da dependência `react-hook-form` para trabalhar com formulários
    * */
   const { register, handleSubmit } = useForm();

   /**
    * Hook da dependência `react-router` para trabalhar com redirecionamento de rotas
    */
   const history = useHistory();

   /**
    * onSubmit: função executada ao clicar com o mouse ou ao teclar enter
    * --> requisição de login ao backend
    * */
   const onSubmit = (formData: FormData) => {
      requestLogin(formData)
         .then((response) => {
            /* Salvando os dados de autenticação no localStorage */
            saveAuthData(response.data);

            /* Salvando os dados de autenticação no contexto global da aplicação */
            setAuthContextData({
               authenticated: true,
               tokenData: getTokenData(),
            });

            /* Login com sucesso: uma nova rota é empilhada */
            history.push('/movies');
         })
         .catch((error) => {
            console.log('ERRO! ', error);
         });
   };

   return (
      <div className="login-card">
         <h1>LOGIN</h1>
         <form onSubmit={handleSubmit(onSubmit)}>
            <div className="mb-4">
               <input
                  type="text"
                  className="form-control base-input"
                  placeholder="E-mail"
                  {...register('username')}
                  name="username"
               />
            </div>
            <div className="mb-2">
               <input
                  type="password"
                  className="form-control base-input"
                  placeholder="Senha"
                  {...register('password')}
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
