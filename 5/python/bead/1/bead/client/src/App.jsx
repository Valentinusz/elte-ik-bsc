import React from 'react';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import {Devices} from './views/Devices';
import {Layout} from './views/Layout/Layout';
import {Provider} from 'react-redux';
import {store} from './state/store';
import {createRoot} from 'react-dom/client';
import { Discover } from './views/Discover/Discover';
import { DeviceSettings } from './views/Device/DeviceSettings';
import './style.css';

createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <Provider store={store}>
            <BrowserRouter>
                <Routes>
                    <Route path='/' Component={Layout}>
                        <Route exact path='/' Component={Devices}/>
                        <Route exact path='discover' Component={Discover}/>
                        <Route exact path='device/:id' Component={DeviceSettings}/>
                    </Route>
                </Routes>
            </BrowserRouter>
        </Provider>
    </React.StrictMode>
);
