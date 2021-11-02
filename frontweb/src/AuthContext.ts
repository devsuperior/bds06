import { createContext } from 'react';
import { TokenData } from 'utils/auth';

/**
 * Tipo de objeto que armazena um estado global
 */
export type AuthContextData = {
   authenticated: boolean;
   tokenData?: TokenData;
};

/**
 * Tipo de objeto que simula o funcionamento de um hook, em que:
 * authContextData = dado
 * setAuthContextData = função que altera o dado
 */
export type AuthContextType = {
   authContextData: AuthContextData;
   setAuthContextData: (authContextData: AuthContextData) => void;
};

/**
 * Função que cria o contexto global com alguns valores padrões que
 * logo serão sobreescritos.
 * Tal componente é invocado da seguinte forma:
 *    <AuthContext.Provider value={authContextType}>
         <Routes />
      </AuthContext.Provider>
 */
export const AuthContext = createContext<AuthContextType>({
   authContextData: {
      authenticated: false,
   },
   setAuthContextData: () => null,
});
