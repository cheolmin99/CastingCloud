import axios, { AxiosResponse } from 'axios';
import { Dispatch, KeyboardEvent, SetStateAction, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { ActorSignInResponseDto, DirectorSignInResponseDto } from 'src/apis/response/auth';
import ResponseDto from 'src/apis/response';
import { ActorSignInDto, DirectorSignInDto } from 'src/apis/request/auth';
import { ACTOR_SIGN_IN_URL, DIRECTOR_SIGN_IN_URL } from 'src/constants/api';
import { useIActorStore, useIDirectorStore } from 'src/stores/user.store';
import { getExpires } from 'src/utils';
import { useCookies } from 'react-cookie';
import './index.css';

function FirstPage() {
    const navigator = useNavigate();
    const actorPasswordRef = useRef<HTMLInputElement | null>(null);

    const { setActorUser } = useIActorStore();
    
    const [cookies, setCookie] = useCookies();
    const [actorEmail, setActorEmail] = useState<string>("");
    const [actorPassword, setActorPassword] = useState<string>("");
    const [showActorPassword, setShowActorPassword] = useState<boolean>(false);
    const [actorLoginError, setActorLoginError] = useState<boolean>(false);

    const onActorEmailKeyPressHandler = (event: KeyboardEvent<HTMLInputElement>) => {
        if (event.key !== 'Enter') return;
        if (!actorPasswordRef.current) return;
        (actorPasswordRef as any).current?.lastChild?.firstChild?.focus();
    }

    const onActorPasswordKeyPressHandler = (event: KeyboardEvent<HTMLInputElement>) => {
        if(event.key !== 'Enter') return;
        onActorLoginHandler();
    }

    const onActorLoginHandler = () => {
        if (!actorEmail.trim() || !actorPassword) {
            alert('아이디, 비밀번호를 모두 입력해주세요.');
            return;
        }
        const data: ActorSignInDto = { actorEmail, actorPassword };

        axios.post(ACTOR_SIGN_IN_URL, data)
            .then((response) => actorSignInResponseHandler(response))
            .catch((error) => actorSignInErrorHandler(error));
    }
    
    const actorSignInResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<ActorSignInResponseDto>;
        if (!result || !data) {
            setActorLoginError(true);
            return;
        }
        const { token, expiredTime, ...user } = data;
        const expires = getExpires(expiredTime);
        setCookie('accessToken', token, { expires, path: '/' /* 메인으로 바꿀것 */});
        setActorUser(user);
        navigator('/' /* 마찬가지 */);
    }
    
    const actorSignInErrorHandler = (error: any) => {
        console.log(error.message);
    }

    return(
        <>
            <div className="login-box">
                        <div className="login">
                        <input className="email" 
                                onChange={(event) => setActorEmail(event.target.value)}
                                onKeyPress={(event) => onActorEmailKeyPressHandler(event)}/>
                            {/* <div className="option" typeof="option">
                                @
                                    <select className="option-box" name="order">
                                        <option value="naver">naver.com</option>
                                        <option value="google">google.com</option>
                                        <option value="kakao">kakao.com</option>
                                        <option value="daum">daum.net</option>
                                    </select>
                            </div> */}
                        </div>
                        <div className="password-box">
                            <input className="password" type={showActorPassword ? 'text' : 'password'}
                            onChange={(event) => setActorPassword(event.target.value)}
                            onKeyPress={(event) => onActorPasswordKeyPressHandler(event)} />
                            <button type='button' className='password-button' onClick={() => setShowActorPassword(!showActorPassword)}>!</button>
                        </div>
                        <div className="button-box">
                            <button className="login-button" type="button" onClick={onActorLoginHandler}>로그인</button>
                        </div>
                        {actorLoginError && (<div style={{ fontSize: '12px', color: 'red', opacity: '0.7' }}>이메일 주소 또는 비밀번호를 잘못 입력하였습니다.</div>)}
                    </div>
        </>
    )
}

function SecondPage() {

    const navigator = useNavigate();
    const directorPasswordRef = useRef<HTMLInputElement | null>(null);

    const { setDirectorUser } = useIDirectorStore();
    
    const [cookies, setCookie] = useCookies();
    const [directorEmail, setDirectorEmail] = useState<string>("");
    const [directorPassword, setDirectorPassword] = useState<string>("");
    const [showDirectorPassword, setShowDirectorPassword] = useState<boolean>(false);
    const [directorLoginError, setDirectorLoginError] = useState<boolean>(false);

    const onDirectorEmailKeyPressHandler = (event: KeyboardEvent<HTMLInputElement>) => {
        if (event.key !== 'Enter') return;
        if (!directorPasswordRef.current) return;
        (directorPasswordRef as any).current?.lastChild?.firstChild?.focus();
    }

    const onDirectorPasswordKeyPressHandler = (event: KeyboardEvent<HTMLInputElement>) => {
        if(event.key !== 'Enter') return;
        onDirectorLoginHandler();
    }

    const onDirectorLoginHandler = () => {
        if (!directorEmail.trim() || !directorPassword) {
            alert('아이디, 비밀번호를 모두 입력해주세요.');
            return;
        }
        const data: DirectorSignInDto = { directorEmail, directorPassword };

        axios.post(DIRECTOR_SIGN_IN_URL, data)
            .then((response) => directorSignInResponseHandler(response))
            .catch((error) => directorSignInErrorHandler(error));
    }
    
    const directorSignInResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<DirectorSignInResponseDto>;
        if (!result || !data) {
            setDirectorLoginError(true);
            return;
        }
        const { token, expiredTime, ...user } = data;
        const expires = getExpires(expiredTime);
        setCookie('accessToken', token, { expires, path: '/' /* 메인으로 바꿀것 */});
        setDirectorUser(user);
        navigator('/' /* 마찬가지 */);
    }
    
    const directorSignInErrorHandler = (error: any) => {
        alert("로그인 실패");
        console.log(error.message);
    }

    return(
        <>
            <div className="login-box">
                        <div className="login">
                        <input className="email" 
                                onChange={(event) => setDirectorEmail(event.target.value)}
                                onKeyPress={(event) => onDirectorEmailKeyPressHandler(event)}/>
                            <div className="option" typeof="option">
                                @
                                    <select className="option-box" name="order">
                                        <option value="naver">naver.com</option>
                                        <option value="google">google.com</option>
                                        <option value="kakao">kakao.com</option>
                                        <option value="daum">daum.net</option>
                                    </select>
                            </div>
                        </div>
                        <div className="password-box">
                            <input className="password" type={showDirectorPassword ? 'text' : 'password'}
                            onChange={(event) => setDirectorPassword(event.target.value)}
                            onKeyPress={(event) => onDirectorPasswordKeyPressHandler(event)} />
                            <button type='button' className='password-button' onClick={() => setShowDirectorPassword(!showDirectorPassword)}>!</button>
                        </div>
                        <div className="button-box">
                            <button className="login-button" type="button" onClick={onDirectorLoginHandler}>로그인</button>
                        </div>
                        
                    </div>
        </>
    )
}

interface Props {
    setActorLoginView: Dispatch<SetStateAction<boolean>>;
}

export default function ActorLoginView({ setActorLoginView }: Props) {

    const [page, setPage] = useState<number>(1);


    const onNextButtonHandler = () => {
    setPage(2);
    };

    return(
        <>  
            <div className='login-view-cont'>
                <div>
                    {page === 1 ? <FirstPage /> : <SecondPage />}
                </div>
                <div className="auth-navi">
                    {page === 1 && (
                        <>
                            <div className="actor-sign-in" onClick={() => setActorLoginView(false)}>
                            회원가입
                            </div>
                            <div className="director-auth" onClick={onNextButtonHandler}>
                            디랙터 로그인
                            </div>
                        </>
                    )}
                        
                </div>
            </div>    
        </>
    )
}