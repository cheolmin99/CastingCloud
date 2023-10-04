import { create } from "zustand";

interface IActorSignUpStore {
    actorEmail: string;
    actorPassword: string;
    actorPasswordCheck: string;
    actorNickName: string;
    actorProfile: string;
    setActorEmail: (str: string) => void;
    setActorPassword: (str: string) => void;
    setActorPasswordCheck: (str: string) => void;
    setActorNickName: (str: string) => void;
    setActorProfile: (str: string) => void;

    actorSignUpError: boolean;
    setActorSignUpError: (actorSignUpError: boolean) => void;

    actorEmailPatternCheck: boolean | null;
    setActorEmailPatternCheck: (actorEmailPatternCheck: boolean) => void;
    actorEmailValidate: boolean | null;
    setActorEmailValidate: (actorEmailValidate: boolean) => void;

    actorPasswordPatternCheck: boolean | null;
    setActorPasswordPatternCheck: (actorPasswordPatternCheck: boolean) => void;
    actorPasswordValidate: boolean | null;
    setActorPasswordValidate: (actorPasswordValidate: boolean) => void;

    actorNickNameValidate: boolean | null;
    setActorNickNameValidate: (actorNickNameValidate: boolean) => void;
}

const useActorStore = create<IActorSignUpStore>((set) => ({
    actorEmail: '',
    actorPassword: '',
    actorPasswordCheck: '',
    actorNickName: '',
    actorProfile: '',
    setActorEmail: (actorEmail) => {
        const actorEmailValidate = /^[A-Za-z0-9]*@[A-Za-z0-9]([-.]?[A-Za-z0-9])*\.[A-Za-z0-9]{2,3}$/;
        const isMatched = actorEmailValidate.test(actorEmail);
        const emailMessage = isMatched ? '' : '이메일 형태가 아닙니다.';
        set((state) => ({...state, actorEmail, emailMessage}))
    },
    setActorPassword: (actorPassword) => set((state) => ({...state, actorPassword})),
    setActorPasswordCheck: (actorPasswordCheck) => set((state) => ({...state, actorPasswordCheck})),
    setActorNickName: (actorNickName) => set((state) => ({...state, actorNickName})),
    setActorProfile: (actorProfile) => set((state) => ({...state, actorProfile})),

    actorSignUpError: false,
    setActorSignUpError: (actorSignUpError: boolean) => set((state) => ({...state, actorSignUpError})),

    actorEmailPatternCheck: null,
    setActorEmailPatternCheck: (actorEmailPatternCheck: boolean) => set((state) => ({...state, actorEmailPatternCheck})),
    actorEmailValidate: null,
    setActorEmailValidate: (actorEmailValidate: boolean) => set((state) => ({...state, actorEmailValidate})),

    actorPasswordPatternCheck: null,
    setActorPasswordPatternCheck: (actorPasswordPatternCheck: boolean) => set((state) => ({...state, actorPasswordPatternCheck})),
    actorPasswordValidate: null,
    setActorPasswordValidate: (actorPasswordValidate: boolean) => set((state) => ({...state, actorPasswordValidate})),

    actorNickNameValidate: null,
    setActorNickNameValidate: (actorNickNameValidate : boolean) => set((state) => ({...state, actorNickNameValidate})),

}))

export default useActorStore;