import './styles.css'; // estilos locais da barra de navegação

/**
 * Componente "Barra de navegação".
 */
const Navbar = function () {
   return (
      <div className="bg-primary navbar-container">
         <h1 className="navbar-logo">MovieFlix</h1>
      </div>
   );
};

export default Navbar;
