import './App.css';
import { Route, Routes, useLocation } from 'react-router-dom';
import LandingPageView from './views/LandingPageView';
import { useIActorStore, useIDirectorStore } from './stores/user.store';
import { useCookies } from 'react-cookie';
import axios, { AxiosResponse } from 'axios';
import { GET_ACTOR_URL, GET_DIRECTOR_URL, authorizationHeader } from './constants/api';
import ResponseDto from './apis/response';
import { GetActorResponseDto, GetDirectorResponseDto } from './apis/response/user';
import { useEffect } from 'react';
import MainPageView from './views/MainView';

function App() {

  const path = useLocation();
  const { setActorUser } = useIActorStore();
  const { setDirectorUser } = useIDirectorStore();
  const [cookies] = useCookies();

  const getActor = (accessToken: string) => {
    axios.get(GET_ACTOR_URL, authorizationHeader(accessToken))
    .then((response) => getActorResponseHandler(response))
    .catch((error) => getActorErrorHandler(error));
  }

  const getActorResponseHandler = (response: AxiosResponse<any, any>) => {
    const { result, message, data } = response.data as ResponseDto<any>;
    if (!result || !data) {
      return
    }
    const actor = data as GetActorResponseDto;
    setActorUser(actor);
  }

  const getActorErrorHandler = (error: any) => {
    console.log(error.message);
  }

  useEffect(() => {
    const accessToken = cookies.accessToken;
    if (accessToken) getActor(accessToken);
  }, [path]);


  
  const getDirector = (accessToken: string) => {
    axios.get(GET_DIRECTOR_URL, authorizationHeader(accessToken))
    .then((response) => getDirectorResponseHandler(response))
    .catch((error) => getDirectorErrorHandler(error));
  }

  const getDirectorResponseHandler = (response: AxiosResponse<any, any>) => {
    const { result, message, data } = response.data as ResponseDto<any>;
    if (!result || !data) {
      return
    }
    const director = data as GetDirectorResponseDto;
    setDirectorUser(director);
  }

  const getDirectorErrorHandler = (error: any) => {
    console.log(error.message);
  }

  useEffect(() => {
    const accessToken = cookies.accessToken;
    if (accessToken) getDirector(accessToken);
  }, [path]);

  return (
    <>
      <Routes>
        <Route path='/' element={(<LandingPageView/>)} />
        <Route path='/main' element={(<MainPageView />)} />
      </Routes>
    </>
  );
}

export default App;
