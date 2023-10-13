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

    const onActorEmailKeyPressHandler = (event: KeyboardEvent<HTMLDivElement>) => {
        if (event.key !== 'Enter') return;
        if (!ActorPasswordRef.current) return;
        (ActorPasswordRef as any).current?.lastChild?.firstChild?.focus();
    }

    const onActorPasswordKeyPressHandler = (event: KeyboardEvent<HTMLDivElement>) => {
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
            <div className="container">
                <div className="actor-login-box">
                    로그인
                    <input type="text" className="actor-input-login" />
                </div>
                <div className="actor-password-box">
                    비밀번호
                    <input type="email" className="actor-input-password" />
                </div>
            </div>
        </>
    )
}