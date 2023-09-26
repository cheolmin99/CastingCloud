import Actor from './Actor.interface';
import Director from './Director.interface';

export interface IActor {
    actorEmail: string;
    actorNickName: string;
    actorPassword: string;
    actorProfile?: string | null;
}

export interface IDirector {
    directorEmail: string;
    directorPassword: string;
    directorPhoneNumber: string;
    directorName: string;
    directorCompany: string;
    directorProfile?: string | null;
}

export type { Actor, Director } 