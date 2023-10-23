import axios, { AxiosResponse } from 'axios';
import { Dispatch, KeyboardEvent, SetStateAction, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { ActorSignInResponseDto } from 'src/apis/response/auth';
import ResponseDto from 'src/apis/response';
import { ActorSignInDto } from 'src/apis/request/auth';
import { ACTOR_SIGN_IN_URL } from 'src/constants/api';
import useIActorStore from 'src/stores/user.store';
import { getExpires } from 'src/utils';
import { useCookies } from 'react-cookie';

interface Props {
    setActorLoginView: Dispatch<SetStateAction<boolean>>;
}

export default function ActorLoginView({ setActorLoginView }: Props) {

    const navigator = useNavigate();
    const ActorPasswordRef = useRef<HTMLInputElement | null>(null);

    const { setUser } = useIActorStore();
    
    const [cookies, setCookie] = useCookies();
    const [actorEmail, setActorEmail] = useState<string>("");
    const [actorPassword, setActorPassword] = useState<string>("");
    const [showActorPassword, setShowActorpassword] = useState<boolean>(false);
    const [actorLoginError, setActorLoginError] = useState<boolean>(false);

    const onActorEmailKeyPressHandler = (event: KeyboardEvent<HTMLInputElement>) => {
        if (event.key !== 'Enter') return;
        if (!ActorPasswordRef.current) return;
        (ActorPasswordRef as any).current?.lastChild?.firstChild?.focus();
    }

    const onActorPasswordKeyPressHandler = (event: KeyboardEvent<HTMLInputElement>) => {
        if(!event.key || !actorPassword) return;
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
        setUser(user);
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
                            <input className="password" type={showActorPassword ? 'text' : 'password'}
                            onChange={(event) => setActorPassword(event.target.value)}
                            onKeyPress={(event) => onActorPasswordKeyPressHandler(event)} />
                        </div>
                        <div className="button-box">
                            <button className="login-button" type="button">로그인</button>
                        </div>
                        <div className="auth-navi">
                            <div className="actor-sign-in">
                                회원가입
                            </div>
                            <div className="director-auth">
                                디랙터 로그인
                            </div>
                        </div>
                    </div>
        </>
    )
}