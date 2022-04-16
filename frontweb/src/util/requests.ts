import axios, { AxiosRequestConfig } from 'axios';
import jwtDecode from 'jwt-decode';
import qs from 'qs';
import history from './history';

type LoginResponse = {
  access_token: string;
  token_type: string;
  expires_in: number;
  scope: string;
  userFirstName: string;
  id: number;
};

type Role = 'ROLE_VISITOR' | 'ROLE_MEMBER';

export type TokenData = {
  exp: number;
  user_name: string;
  authorities: Role[];
}

const tokenKey = 'authData';

export const BASE_URL =
  process.env.REACT_APP_BASE_URL ?? 'http://localhost:8080';

const CLIENT_ID = process.env.REACT_APP_CLIENT_ID ?? 'myclientid';
const CLIENT_SECRET = process.env.REACT_APP_CLIENT_SECRET ?? 'myclientsecret';

// poderia usar o FormData? Sim, poderia, mas quis manter certa independência.
type LoginData = {
  username: string;
  password: string;
};

export const RequestBackendLogin = (loginData: LoginData) => {
  const headers = {
    'Content-type': 'application/x-www-form-urlencoded',
    Authorization: 'Basic ' + window.btoa(CLIENT_ID + ':' + CLIENT_SECRET),
  };
  const data = qs.stringify({
    ...loginData,
    grant_type: 'password',
  });

  return axios({
    method: 'POST',
    baseURL: BASE_URL,
    url: '/oauth/token',
    data,
    headers,
  });
};

export const requestBackend = (config: AxiosRequestConfig) => {
  const headers = config.withCredentials
    ? {
        ...config.headers,
        Authorization: 'Bearer ' + getAuthData().access_token,
      }
    : config.headers;

  return axios({ ...config, baseURL: BASE_URL, headers });
};

export const saveAuthData = (obj: LoginResponse) => {
  localStorage.setItem(tokenKey, JSON.stringify(obj));
};

export const getAuthData = () => {
  const str = localStorage.getItem(tokenKey) ?? '{}';
  return JSON.parse(str) as LoginResponse;
};

// Add a request interceptor
axios.interceptors.request.use(function (config) {
  return config;
}, function (error) {
  return Promise.reject(error);
});

// Add a response interceptor
axios.interceptors.response.use(function (response) {
  return response;
}, function (error) {
  if (error.response.status === 401 || error.response.status === 403){
    history.push('/admin/auth');
  }
  return Promise.reject(error);
});

export const getTokenData = () : TokenData | undefined => {
  try {
    return jwtDecode(getAuthData().access_token) as TokenData;
  }
  catch(error){
    return undefined;
  }
}

export const removeTokenData = () => {
  localStorage.removeItem(tokenKey);
}

export const isAuthenticated = () : boolean => {
  const tokenData = getTokenData();
  return (tokenData && tokenData.exp * 1000 > Date.now()) ? true : false;
}

export const hasAnyRole = (roles: Role[]): boolean => {

  const tokenData = getTokenData();

  if (roles.length === 0) {
    return true;
  }

  if (tokenData !== undefined){
    return roles.some(role => tokenData.authorities.includes(role));
  }

  return false;
}