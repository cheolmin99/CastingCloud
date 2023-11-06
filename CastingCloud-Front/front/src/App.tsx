import './App.css';
import { Route, Routes, useLocation } from 'react-router-dom';
import LandingPageView from './views/LandingPageView';
import { useIActorStore, useIDirectorStore } from './stores/user.store';
import { useCookies } from 'react-cookie';

function App() {

  const path = useLocation();
  const { setActorUser } = useIActorStore();
  const { setDirectorUser } = useIDirectorStore();
  const [cookies] = useCookies();

  return (
    <>
      <Routes>
        <Route path='/' element={(<LandingPageView/>)} />
      </Routes>
    </>
  );
}

export default App;
