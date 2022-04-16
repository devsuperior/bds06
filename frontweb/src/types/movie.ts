export type Movie = {
  id: number;
  title: string;
  subTitle: string;
  year: number;
  imgUrl: string;
  synopsis: string;
  genre: {
    id: number;
    name: string;
  };
};
