import './styles.css';

const Catalog = function () {

   const [page, setPage]

   return (
      <div className="catalog-container">
         <h1>Tela listagem de filmes</h1>
         <div className="catalog-movies">
            <a href="#movies1">Acessar /movies/1</a>
            <a href="#movies2">Acessar /movies/2</a>
         </div>
      </div>
   );
};

export default Catalog;
