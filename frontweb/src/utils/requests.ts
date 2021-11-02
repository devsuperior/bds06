import qs from 'qs';
import axios, { AxiosRequestConfig } from 'axios';

/**
 * URL do backend
 */
export const BASE_URL =
   process.env.REACT_APP_BACKEND_URL ?? 'http://localhost:8080';

/**
 * Id da aplicação backend
 */
const CLIENT_ID = process.env.REACT_APP_CLIENT_ID ?? 'myclientid';

/**
 * Senha da aplicação backend
 */
const CLIENT_SECRET = process.env.REACT_APP_CLIENT_SECRET ?? 'myclientsecret';

/**
 * Tipo para armazenar credenciais de login
 */
type LoginData = {
   username: string;
   password: string;
};

/**
 * Cabeçalho "Authorization" necessário para requisição de login
 */
const headerAuthorization = () =>
   'Basic ' + window.btoa(CLIENT_ID + ':' + CLIENT_SECRET);

/**
 * Função para requisitar login ao backend
 */
export const requestLogin = (loginData: LoginData) => {
   /* Desestruturação das credenciais (apenas para maior didática) */
   const { username, password } = loginData;

   /* Cabeçalhos da requisição de login */
   const headers = {
      Authorization: headerAuthorization(),
      'Content-Type': 'application/x-www-form-urlencoded',
   };

   /* Corpo da requisição no formato 'application/x-www-form-urlencoded' */
   const body = qs.stringify({
      username,
      password,
      grant_type: 'password',
   });

   /* Dados necessários para uma requisição de login ser feita usando a biblioteca 'axios' */
   const config: AxiosRequestConfig = {
      method: 'POST',
      baseURL: BASE_URL,
      url: '/oauth/token',
      headers: headers,
      data: body,
   };
   return axios(config);
};
