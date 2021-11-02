import './assets/styles/custom.scss'; // estilos customizados do Bootstrap
import './App.css'; // estilos globais

import Routes from 'Routes';
import { useState } from 'react';
import { AuthContext, AuthContextData } from 'AuthContext';

/**
 * Função principal a ser renderizada.
 */
const App = function () {
   
   const [authContextData, setAuthContextData] = useState<AuthContextData>({
      authenticated: false,
   });

   const authContextType = {authContextData, setAuthContextData};

   return (
      <AuthContext.Provider value={authContextType}>
         <Routes />
      </AuthContext.Provider>
   );
};

export default App;
