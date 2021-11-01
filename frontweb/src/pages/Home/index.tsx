import './styles.css';

import { ReactComponent as BannerImg } from 'assets/images/banner-img.svg';

/**
 * Componente "Página inicial".
 */
const Home = function () {
   return (
      <div className="home">
         <div className="home-container">
            <div className="banner">
               <div className="banner-content">
                  <h1>Avalie Filmes</h1>
                  <p>Diga o que você achou do seu filme favorito</p>
               </div>
               <div className="banner-img">
                  <BannerImg />
               </div>
            </div>

            <div>
               Card de login
            </div>
         </div>
      </div>
   );
};

export default Home;
