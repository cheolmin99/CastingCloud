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
        </>
    )
}