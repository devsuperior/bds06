import { AxiosRequestConfig } from 'axios';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Movie } from 'types/movie';
import { Review } from 'types/review';
import MovieDetailsInfo from './MovieDetailsInfo';
import IconUserReview from 'assets/images/icon-user-review.png';
import ButtonIcon from 'components/ButtonIcon';
import { useForm } from 'react-hook-form';
import { requestBackend } from 'util/requests';
import './style.css';

type UrlParams = {
  movieId: string;
};

const MovieDetails = () => {
  const { movieId } = useParams<UrlParams>();
  const [movie, setMovie] = useState<Movie>();
  const [reviews, setReview] = useState<Review[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const {register, handleSubmit, formState:{errors}, resetField} = useForm<Review>();

  const onSubmit = (formData: Review) => {
    formData.movieId = movie?.id;
    const paramsReview: AxiosRequestConfig = {
      withCredentials: true,
      method: 'POST',
      data: formData,
      url: `/reviews`,
    };  
    requestBackend(paramsReview).then((response) => {
      setReview([...reviews, response.data]);
      resetField('text');
    }
    ).catch(error => {
      console.log('ERRO', error);
    });
  }
  
  
  useEffect(() => {
    const paramsMovie: AxiosRequestConfig = {
      withCredentials: true,
      method: 'GET',
      url: `/movies/${movieId}`,
    };
    const paramsReviews: AxiosRequestConfig = {
      withCredentials: true,
      method: 'GET',
      url: `/reviews/${movieId}`,
    };
    setIsLoading(true);
    requestBackend(paramsMovie)
      .then((response) => {
        setMovie(response.data);
      });
    requestBackend(paramsReviews)
      .then((response) => {
        setReview(response.data);
      })
      .finally(() => {
        setIsLoading(false);
      });
  }, [movieId]);

  return (
    <div className="movie-details-container">
      <form name="formReview" onSubmit={handleSubmit(onSubmit)}>
        <div className="movie-details-card">
          <h1>Tela detalhes do filme</h1>
          <h2>Id:{movie?.id}</h2>
          <div className="review-push">
            <input 
              {...register("text", {required: 'Campo obrigatório'})}
              type="text"
              name="text"
              placeholder={errors.text?.message || "Digite sua avaliação aqui"}
            />
            <div className="review-push-btn">
              <ButtonIcon text="SALVAR AVALIAÇÃO" />
            </div>
          </div>
          {isLoading ? (
            <MovieDetailsInfo />
          ) : (
            <div className="row review-card">
              {reviews &&
              reviews?.map((review) => (
                <div className="col-sm-12 col-lg-12 col-xl-12" key={review.id}>
                  <div className="review-card-user">
                    <img src={IconUserReview} alt="" />
                    <h3>{review?.user.name}</h3>
                  </div>
                  <div className="review-card-text">
                    <h4>{review?.text}</h4>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>
      </form>
    </div>
  );
};

export default MovieDetails;
