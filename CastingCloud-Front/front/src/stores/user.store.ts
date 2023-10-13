import { Actor } from 'src/interfaces';
import { create } from 'zustand';

interface IActorStroe {
    user: Actor | null;
    setUser: (user: Actor) => void;
    resetUser: () => void;
}

const useIActorStore = create<IActorStroe>((set) => ({
    user: null,
    setUser: (user: Actor) => set((state) => ({...state, user})),
    resetUser: () => set((state) => ({...state, user: null}))
}));

export default useIActorStore;