import './assets/styles/custom.scss'; // estilos customizados do Bootstrap
import './App.css'; // estilos globais

import Navbar from 'components/Navbar';

/**
 * Função principal a ser renderizada.
 */
const App = function () {
   return (
      <>
         <Navbar />
      </>
   );
};

export default App;
