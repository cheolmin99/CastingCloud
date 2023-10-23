import { SetStateAction, useState } from "react"
import './index.css';
import ActorLoginView from "./ActorLoginPageView";

export default function LandingPageView() {

    const [ loginView, setLoginView ] = useState<boolean>(true);

    return (
        <>
            <div className="landingPageContainor">
                <div className="left-box">
                    여긴 랜딩페이지에 관한 뭐가 들어가겠지
                </div>
                <div className="right-box">
                    <ActorLoginView setActorLoginView={function (value: SetStateAction<boolean>): void {
                        throw new Error("Function not implemented.");
                    } } />
                </div>
            </div>
        </>
    )
}