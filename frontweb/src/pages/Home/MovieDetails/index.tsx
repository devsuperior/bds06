import './styles.css';

import { AxiosRequestConfig } from 'axios';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { Review } from 'types/review';
import { requestBackend } from 'utils/requests';
import Star from 'assets/images/star-img.png';

type UrlParams = {
   movieId: string;
};

const MovieDetails = function () {
   const { movieId } = useParams<UrlParams>();

   const [reviews, setReviews] = useState<Review[]>([]);

   useEffect(() => {
      /* Parâmetros usados pela biblioteca 'axios' para fazer requisições */
      const config: AxiosRequestConfig = {
         method: 'GET',
         url: `/movies/${movieId}/reviews`,
         withCredentials: true, // requisição autorizada
      };

      requestBackend(config).then((response) => {
         setReviews(response.data);
      });
   }, [movieId]);

   return (
      <div className="movie-reviews">
         <div className="reviews-container">
            <h1>Tela detalhes do filme id: {movieId}</h1>

            {reviews.length > 0 &&
               <div className="reviews-card">
                  {reviews?.map((review) => {
                     return (
                        <div className="review" key={review.id}>
                           <div className="review-header">
                              <img src={Star} alt="star-img" />
                              <h1>{review.user.name}</h1>
                           </div>
                           <p>{review?.text}</p>
                        </div>
                     );
                  })}
               </div>
            }
         </div>
      </div>
   );
};

export default MovieDetails;
