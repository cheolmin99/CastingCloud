import './App.css';
import { Route, Routes } from 'react-router-dom';
import LandingPageView from './views/LandingPageView';
import ActorSignUpView from './views/LandingPageView/ActorSignUpPageView';

function App() {

  return (
    <>
      <Routes>
        <Route path='/' element={(<LandingPageView/>)} />
      </Routes>
    </>
  );
}

export default App;
