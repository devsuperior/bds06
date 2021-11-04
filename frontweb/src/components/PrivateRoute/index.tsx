import { Redirect, Route } from 'react-router-dom';
import { isAuthenticated, Role } from 'utils/auth';

type Props = {
   path: string;
   children: React.ReactNode;
   roles?: Role[];
};

const PrivateRoute = (props: Props) => {
   const { path, children, roles = [] } = props;

   return (
      <Route
         path={path}
         render={({ location }) =>
            !isAuthenticated() ? (
               <Redirect
                  to={{
                     pathname: '/auth/login',
                     state: { from: location },
                  }}
               />
            ) : (
               children
            )
         }
      />
   );
};

export default PrivateRoute;
