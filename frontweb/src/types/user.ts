export type User = {
  id: number;
  name: string | undefined;
  email: string;
  roles: [
    {
      id: number;
      authority: string;
    }
  ];
};
