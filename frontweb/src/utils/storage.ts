/**
 * Tipo de objetos que armazenam respostas (bem sucedidas)
 * à requisição de login
 */
type LoginResponse = { // AuthData
   access_token: string;
   token_type: string;
   expires_in: number;
   scope: string;
   userId: number;
   userName: string;
};

/**
 * Chave do objeto armazenado no 'localStorage' que contém 
 * a resposta da requisição de login, em caso de sucesso.
 */
const tokenKey = 'authData';

/**
 * Função que armazena uma resposta de login (dados de autenticação) 
 * no `localStorage`
 */
export const saveAuthData = (response: LoginResponse) => {
   localStorage.setItem(tokenKey, JSON.stringify(response))
}

/**
 * Função que retorna a resposta de login armazenada no `localStorage`
 */
export const getAuthData = () => {
   const text = localStorage.getItem(tokenKey) ?? '{}';
   const authData = JSON.parse(text);
   return authData as LoginResponse;
}