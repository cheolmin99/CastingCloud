import { Actor, Director } from 'src/interfaces';
import { create } from 'zustand';

interface IActorStroe {
    user: Actor | null;
    setUser: (user: Actor) => void;
    resetUser: () => void;
}

interface IDirectorStroe {
    user: Director | null;
    setUser: (user:Director) => void;
    resetUser: () => void;
}

const useIActorStore = create<IActorStroe>((set) => ({
    user: null,
    setUser: (user: Actor) => set((state) => ({...state, user})),
    resetUser: () => set((state) => ({...state, user: null}))
}));

const useIDirectorStore = create<IDirectorStroe>((set) => ({
    user: null,
    setUser: (user: Director) => set((state) => ({...state, user})),
    resetUser: () => set((state) => ({...state, user: null}))
}));

export {useIActorStore, useIDirectorStore};