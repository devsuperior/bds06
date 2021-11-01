import './styles.css'; // estilos locais da barra de navegação

import { Link } from 'react-router-dom';

/**
 * Componente "Barra de navegação".
 */
const Navbar = function () {
   return (
      <div className="bg-primary navbar-container">
         <Link to="/">
            <h1 className="navbar-logo">MovieFlix</h1>
         </Link>
      </div>
   );
};

export default Navbar;
