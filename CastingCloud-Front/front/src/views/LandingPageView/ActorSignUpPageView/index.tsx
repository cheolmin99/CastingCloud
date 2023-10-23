import { Dispatch, SetStateAction } from 'react'
import './index.css'
import { useActorStore, useDirectorStore } from 'src/stores'

interface Props {
    setActorSignUpView: Dispatch<SetStateAction<boolean>>
}

export default function ActorSignUpView({ setActorSignUpView }: Props) {
    const { actorEmail, actorPassword, actorPasswordCheck} = useActorStore();
    const { setActorEmail, setActorPassword, setActorPasswordCheck } = useActorStore();
    const { actorEmailValidate, actorNickNameValidate } = useActorStore();
    const { directorEmailValidate, directorNameValidate } =useDirectorStore();

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