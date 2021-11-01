import React from 'react';
import ReactDOM from 'react-dom';
import App from './App'; // importação do componente raiz

/**
 * Função renderizadora: "injeta" o componente principal App.tsx
 * na página index.html.
 */
ReactDOM.render(
   <React.StrictMode>
      <App />
   </React.StrictMode>,
   document.getElementById('root')
);
