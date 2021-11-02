import './styles.css';

import { useForm } from 'react-hook-form';
import Button from 'components/Button';
import { requestLogin } from 'utils/requests';

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
   /* Biblioteca React Hook Form */
   const { register, handleSubmit } = useForm();

   /* onSubmit: ação executada ao clicar com o mouse ou ao teclar enter
    * --> requisição de login */
   const onSubmit = (formData: FormData) => {
      requestLogin(formData)
         .then((response) => {
            console.log('SUCESSO! ', response);
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
                  placeholder="Email"
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
