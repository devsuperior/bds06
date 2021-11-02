import './styles.css'; // estilos locais da barra de navegação

import { Link } from 'react-router-dom';
import { useContext, useEffect } from 'react';
import { AuthContext } from 'AuthContext';
import { removeAuthData } from 'utils/storage';
import { getTokenData, isAuthenticated } from 'utils/auth';
import history from 'utils/history';

/**
 * Componente "Barra de navegação".
 */
const Navbar = function () {
   /**
    * Hook para monitorar e alterar o estado global
    */
   const { authContextData, setAuthContextData } = useContext(AuthContext);

   useEffect(() => {
      if (isAuthenticated()) {
         // Se existe um token válido armazenado no localStorage
         setAuthContextData({ authenticated: true, tokenData: getTokenData() });
      } else {
         setAuthContextData({ authenticated: false });
      }
   }, [setAuthContextData]);

   const handleClickLogout = function (event: React.MouseEvent) {
      event.preventDefault();
      removeAuthData();
      setAuthContextData({ authenticated: false });
      history.replace('/');
   };

   return (
      <div className="bg-primary navbar-container">
         <Link to="/">
            <h1 className="navbar-logo">MovieFlix</h1>
         </Link>
         {authContextData.authenticated && (
            <div className="navbar-login-logout">
               <a href="#logout" onClick={handleClickLogout}>
                  Sair
               </a>
            </div>
         )}
      </div>
   );
};

export default Navbar;
