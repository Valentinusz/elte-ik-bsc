import {BrowserRouter, Route, Routes} from 'react-router-dom';
import {Home} from './Home.jsx';
import {Layout} from './layout/Layout.jsx';
import {Login} from './auth/Login.jsx';
import {Register} from './auth/Register.jsx';
import {Profile} from './Profile.jsx';
import {Surveys} from './survey/Surveys.jsx';
import {SurveyCreator} from './survey/SurveyCreator.jsx';
import {ProtectedRoute} from './routing/ProtectedRoute';
import {IdSurveyLoader} from './survey/loader/IdSurveyLoader.jsx';
import {Results} from './survey/Results';
import {HashSurveyLoader} from './survey/loader/HashSurveyLoader.jsx';

/**
 * Main component of the app. Handles routing.
 * @returns {JSX.Element}
 * @constructor
 */
function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<Layout/>}>
                    <Route path='/' element={<Home/>}/>
                    <Route path='/login' element={<Login/>}/>
                    <Route path='/register' element={<Register/>}/>
                    <Route path='/profile' element={<ProtectedRoute redirectTo='/login'><Profile/></ProtectedRoute>}/>
                    <Route path='/survey/:hash'
                           element={<ProtectedRoute redirectTo='/login'><HashSurveyLoader/></ProtectedRoute>}
                    />
                    <Route path='/surveys'>
                        {/* INDEX */}
                        <Route path='' element={<ProtectedRoute redirectTo='/login'><Surveys/></ProtectedRoute>}/>
                        {/* SHOW */}
                        <Route path=':surveyId'>
                            <Route path=''
                                   element={<ProtectedRoute redirectTo='/login'><IdSurveyLoader/></ProtectedRoute>}
                            />
                            <Route path='results'
                                   element={<ProtectedRoute redirectTo='/login'><Results/></ProtectedRoute>}
                            />
                        </Route>
                        {/* CREATE */}
                        <Route path='create'
                               element={<ProtectedRoute redirectTo='/login'><SurveyCreator/></ProtectedRoute>}
                        />
                    </Route>
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
