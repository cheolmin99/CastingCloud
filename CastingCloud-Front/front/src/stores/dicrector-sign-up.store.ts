import { create } from "zustand";

interface IDirectorSginUpStore {
    directorEmail: string;
    directorPassword: string;
    directorPasswordCheck: string;
    directorPhoneNumber: string;
    directorName: string;
    directorCompany: string;
    directorProfile: string;
    setDirectorEmail: (str: string) => void;
    setDirectorPassword: (str: string) => void;
    setDirectorPasswordCheck: (str: string) => void;
    setDirectorPhoneNumber: (str: string) => void;
    setDirectorName: (str: string) => void;
    setDirectorCompany: (str: string) => void;
    setDirectorProfile: (str: string) => void;

    directorSignUpError: boolean | null;
    setDirectorSignUpError: (directorSignUpError: boolean) => void;

    directorEmailValidate: boolean | null;
    setDirectorEmailValidate: (directorEmailValidate: boolean) => void;

    directorPasswordPatternCheck: boolean | null;
    setDirectorPasswordPatternCheck: (directorPasswordPatternCheck: boolean) => void;
    directorPasswordValidate: boolean | null;
    setDirectorPasswordValidate: (directorPasswordValidate : boolean) => void;

    directorNameValidate: boolean | null;
    setDirectorNameValidate: (directorNameValidate: boolean) => void;
}

const useDirectorStore = create<IDirectorSginUpStore>((set) => ({
    directorEmail: '',
    directorPassword: '',
    directorPasswordCheck: '',
    directorPhoneNumber: '',
    directorName: '',
    directorCompany: '',
    directorProfile: '',
    setDirectorEmail: (directorEmail) => {
        const directorEmailValidate = /^[A-Za-z0-9]*@[A-Za-z0-9]([-.]?[A-Za-z0-9])*\.[A-Za-z0-9]{2,3}$/;
        const isMatched = directorEmailValidate.test(directorEmail);
        const directorEmailErrorMessage = isMatched ? '' : '이메일 형식이 아닙니다.';
        set((state) => ({...state, directorEmail, directorEmailErrorMessage}))
    },
    setDirectorPassword: (direcotrPassword) => set((state) => ({...state, direcotrPassword})),
    setDirectorPasswordCheck: (directorPasswordCheck) => set((state) => ({...state, directorPasswordCheck})),
    setDirectorPhoneNumber: (directorPhoneNumber) => set((state) => ({...state, directorPhoneNumber})),
    setDirectorName: (directorName) => set((state) => ({...state, directorName})),
    setDirectorCompany: (directorCompany) => set((state) => ({...state, directorCompany})),
    setDirectorProfile: (directorProfile) => set((state) => ({...state, directorProfile})),

    directorSignUpError: false,
    setDirectorSignUpError: (directorSignUpError: boolean) => set((state) => ({...state, directorSignUpError})),

    directorEmailValidate: null,
    setDirectorEmailValidate: (directorEmailValidate: boolean) => set((state) => ({...state, directorEmailValidate})),

    directorPasswordPatternCheck: null,
    setDirectorPasswordPatternCheck: (directorPasswordPatternCheck: boolean) => set((state) => ({...state, directorPasswordPatternCheck})),
    directorPasswordValidate: null,
    setDirectorPasswordValidate: (directorPasswordValidate: boolean) => set((state) => ({...state, directorPasswordValidate})),

    directorNameValidate: null,
    setDirectorNameValidate: (directorNameValidate: boolean) => set((state) => ({...state, directorNameValidate}))
    
}))

export default useDirectorStore;