import { useState } from "react"
import './index.css';

export default function LandingPageView() {

    const [ loginView, setLoginView ] = useState<boolean>(true);

    return (
        <>
            <div className="landingPageContainor">
                <div className="left-box">
                    여긴 랜딩페이지에 관한 뭐가 들어가겠지
                </div>
                <div className="right-box">
                    <div className="login-box">
                        <div className="login">

                        </div>
                        <input className="email" type="email" />
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
                </div>
            </div>
        </>
    )
}