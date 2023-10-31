import { ChangeEvent, Dispatch, SetStateAction, useState } from 'react'
import './index.css'
import { useActorStore, useDirectorStore } from 'src/stores'
import axios, { AxiosResponse } from 'axios';
import ResponseDto from 'src/apis/response';
import { ValidateEmailResponseDto } from 'src/apis/response/user';
import { ValidateEmailDto } from 'src/apis/request/user';
import { USER_VALIDATE_EMAIL } from 'src/constants/api';

interface Props {
    setActorSignUpView: Dispatch<SetStateAction<boolean>>
}

export default function ActorSignUpView({ setActorSignUpView }: Props) {
    const { actorEmail, actorPassword, actorEmailPatternCheck, actorPasswordCheck} = useActorStore();
    const { setActorEmail, setActorPassword, setActorEmailPatternCheck, setActorPasswordCheck } = useActorStore();
    const { actorEmailValidate, actorPasswordValidate, actorPasswordPatternCheck } = useActorStore();
    const { setActorEmailValidate, setActorPasswordValidate, setActorPasswordPatternCheck } = useActorStore();
    const { directorEmail, setDirectorEmail, setDirectorEmailValidate } = useDirectorStore();

    const [ showPassword, setShowPassword ] = useState<boolean>(false);
    const [showPasswordCheck, setShowPasswordCheck] = useState<boolean>(false);

    const emailValidator = /^[A-Za-z0-9]*@[A-Za-z0-9]([-.]?[A-Za-z0-9])*\.[A-Za-z0-9]{2,3}$/;
    const passwordValidator = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!?_]).{8,20}$/;

    const onActorEmailChangeHandler = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
        const value = event.target.value;
        const isMatched = emailValidator.test(value);
        setActorEmailPatternCheck(isMatched);
        setActorEmail(value);
    }
    
    const onEmailValidateButtonHandler = () => {
        if (!actorEmailPatternCheck) return;

        const data: ValidateEmailDto = {
            actorEmail,
            directorEmail : actorEmail // actorEmail 값을 directorEmail에 복사
        };
    
        axios.post(USER_VALIDATE_EMAIL, data)
            .then((response) => actorEmailValidateResponseHandler(response))
            .catch((error) => actorValidateEmailErrorHandler(error));
            console.log(actorEmailPatternCheck)
    };

    const onActorPasswordChangeHandler = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
        const value = event.target.value;
        const isMatched = passwordValidator.test(value);
        setActorPasswordPatternCheck(isMatched);
        setActorPassword(value);
    }

    const onActorPasswordCheckChangeHandler = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
        const value = event.target.value;
        const isMatched = actorPassword === value;
        setActorPasswordValidate(isMatched);
        setActorPasswordCheck(value);
    }

    const actorEmailValidateResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<ValidateEmailResponseDto>;
        if (!result || !data) {
            alert(message);
            return;
        }
        setActorEmailValidate(data.result);
        setDirectorEmailValidate(data.result);
    };

    const actorValidateEmailErrorHandler = (error: any) => {
        console.log(error.message);
    }

    return (
        <>
            <div className='actorSignUpContainor'>
                <div className='actorSignUp-box'>
                    회원가입
                    <br />
                    <br />
                    이메일
                    <div className='actor-email'>
                        <input type='text' id='email' value={actorEmail} onChange={(event) => onActorEmailChangeHandler(event)} />
                        <input type='hidden' id='email-hidden' value={directorEmail} onChange={(event) => onActorEmailChangeHandler(event)}/>
                        {/* <div className="option" typeof="option">
                            @
                                <select className="option-box" name="order">
                                    <option value="naver">naver.com</option>
                                    <option value="google">google.com</option>
                                    <option value="kakao">kakao.com</option>
                                    <option value="daum">daum.net</option>
                                </select>
                        </div> */}
                        <button type='button' className='email-check-button' onClick={() => onEmailValidateButtonHandler()} >이메일 중복 확인</button>
                        { actorEmailPatternCheck === null ? (<div></div>) :
                        !actorEmailPatternCheck ? (<div style={{color: 'red'}}>이메일 형식이 맞지 않습니다.</div>) : 
                        actorEmailValidate === null ? (<div style={{color: 'orange'}}>이메일 중복 체크를 해주세요.</div>) :
                        !actorEmailValidate ? (<div style={{color: 'red'}}>사용할 수 없는 이메일 입니다.</div>) : (<div style={{color: 'green'}}>사용 가능한 이메일 입니다.</div>)}
                    </div>
                    비밀번호
                    <div className='password-box'>
                        <input type={showPassword ? 'text' : 'password'} className='password' value={actorPassword} onChange={(event) => onActorPasswordChangeHandler(event)}/>
                        <button type='button' className='password-icon' onClick={() => setShowPassword(!showPassword)}>!</button>
                        { actorPasswordPatternCheck === false ? (<div style={{color: 'red'}}>{'영대문자 + 영소문자 + 숫자 + 특수문자(!?_)를 포함한 8-20자를 입력해주세요.'}</div>) : (<></>) }
                    </div>
                    비밀번호 확인
                    <div className='password-check-box'>
                        <input type={showPasswordCheck ? 'text' : 'password'} className='password-check' value={actorPasswordCheck} onChange={(event) => onActorPasswordCheckChangeHandler(event)} />
                        <button type='button' className='password-icon' onClick={() => setShowPasswordCheck(!showPasswordCheck)}>!</button>
                        { actorPasswordValidate === false ? (<div style={{color: 'red'}}>{'비밀번호가 겹치지 않음'}</div>) : (<></>) }
                    </div>
                    <button type='button' className='signup-button'>회원가입</button>
                </div>
            </div>
        </>
    )
}