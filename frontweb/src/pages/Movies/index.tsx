import { Link } from 'react-router-dom';
import './style.css';

const Movies = () => {
  return (
    <div className="movies-container">
      <h1>Tela listagem de filmes</h1>
      <div className="movies-card">
          <Link to="/movies/1"><h2>Acessar /movies/1</h2></Link>
          <Link to="/movies/2"><h2>Acessar /movies/2</h2></Link>
      </div>
    </div>
  );
};

export default Movies;
