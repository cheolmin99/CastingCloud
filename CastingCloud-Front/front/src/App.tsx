import './App.css';
import { Route, Routes } from 'react-router-dom';
import LandingPageView from './views/LandingPageView';

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
