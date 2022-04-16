import { Redirect, Route } from 'react-router-dom';
import { isAuthenticated } from 'util/requests';

type Props = {
  children: React.ReactNode;
  path: string;
};

const PrivateRoute = ({ children, path }: Props) => {
  return (
    <Route
      path={path}
      render={({ location }) =>
        isAuthenticated() ? (
          children
        ) : (
          // se não estiver logado redireciona para o login passando como parâmetro o state que contém a rota informada / pretendida.
          <Redirect to={{ pathname: '/admin/auth/login', state: location.pathname }} />
        )
      }
    />
  );
};

export default PrivateRoute;
