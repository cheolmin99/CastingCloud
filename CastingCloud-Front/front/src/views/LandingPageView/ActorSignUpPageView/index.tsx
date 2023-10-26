import { ChangeEvent, Dispatch, SetStateAction, useState } from 'react'
import './index.css'
import { useActorStore, useDirectorStore } from 'src/stores'

interface Props {
    setActorSignUpView: Dispatch<SetStateAction<boolean>>
}

export default function ActorSignUpView({ setActorSignUpView }: Props) {
    const { actorEmail, actorPassword, actorPasswordCheck} = useActorStore();
    const { setActorEmail, setActorPassword, setActorEmailPatternCheck, setActorPasswordCheck } = useActorStore();
    const { actorEmailValidate, actorNickNameValidate, actorPasswordValidate, actorPasswordPatternCheck } = useActorStore();
    const { setActorEmailValidate, setActorNickNameValidate, setActorPasswordValidate, setActorPasswordPatternCheck } = useActorStore();
    const { directorEmailValidate, directorNameValidate } = useDirectorStore();

    const [showPasswordCheck, setShowPasswordCheck] = useState<boolean>(false);

    const emailValidator = /^[A-Za-z0-9]*@[A-Za-z0-9]([-.]?[A-Za-z0-9])*\.[A-Za-z0-9]{2,3}$/;
    const passwordValidator = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!?_]).{8,20}$/;

    const onActorEmailChangeHandler = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
        const value = event.target.value;
        const isMatched = emailValidator.test(value);
        setActorEmailPatternCheck(isMatched);
        setActorEmail(value);
    }

    const onActorPasswordCheckChangeHandler = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
        const value = event.target.value;
        const isMatched = actorPassword === value;
        setActorPasswordValidate(isMatched);
        setActorPassword(value);
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
                        <input className='email'/>
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
                        <button type='button' className='email-check-button'>이메일 중복 확인</button>
                    비밀번호
                    <div className='password-box'>
                        <input type='password' className='password' />
                    </div>
                    비밀번호 확인
                    <div className='password-check-box'>
                        <input type='password' className='password-check' />
                    </div>
                    <button type='button' className='signup-button'>회원가입</button>
                </div>
            </div>
        </>
    )
}