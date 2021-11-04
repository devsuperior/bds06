import { Redirect, Route } from 'react-router-dom';
import { isAuthenticated, Role } from 'utils/auth';

type Props = {
   path: string;
   exact?: boolean;
   children: React.ReactNode;
   roles?: Role[];
};

const PrivateRoute = (props: Props) => {
   const { path, children, roles = [] } = props;

   return (
      <Route
         path={path}
         exact
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
