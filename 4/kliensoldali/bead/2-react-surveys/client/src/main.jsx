import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './views/App.jsx';
import './views/style.css';
import {store} from './state/store.js';
import {Provider} from 'react-redux';

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <Provider store={store}>
            <App/>
        </Provider>
    </React.StrictMode>,
);
