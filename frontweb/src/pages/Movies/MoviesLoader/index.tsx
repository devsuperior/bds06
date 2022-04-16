import ContentLoader from 'react-content-loader';

const MoviesLoader = () => {
  return (
    <ContentLoader viewBox="0 0 320 200" height={280} width={320}>
      <rect x="0" y="0" rx="10" width="280" height="180" />
    </ContentLoader>
  );
};

MoviesLoader.metadata = {
  name: 'RJavlonbek',
  github: 'RJavlonbek',
  description: 'Blog item',
  filename: 'BlogItem',
};

export default MoviesLoader;
