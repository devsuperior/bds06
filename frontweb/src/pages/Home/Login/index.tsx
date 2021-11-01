import './styles.css';

import { useForm } from 'react-hook-form';
import Button from 'components/Button';

type FormData = {
   username: string;
   password: string;
};

const Login = function () {

   const { register, handleSubmit } = useForm();
   
   const onSubmit = (formData: FormData) => {
      console.log(formData);
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
               />
            </div>
            <div className="mb-2">
               <input
                  type="password"
                  className="form-control base-input"
                  placeholder="Senha"
                  {...register('password')}
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
