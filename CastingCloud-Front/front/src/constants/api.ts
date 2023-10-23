export const authorizationHeader = (accessToken: string) => {
    return { headers: { Authorization: `Bearer ${accessToken}` }};
}

export const multipartHeader = () => {
    return { headers: { 'Content-Type': 'multipart/form-data' }}
}

const HOST = 'http://localhost:4040/'

export const ACTOR_SIGN_IN_URL = `${HOST}auth/actor-sign-in`;
export const ACTOR_SIGN_UP_URL = `${HOST}auth/actor-sign-up`;

export const DIRECTOR_SIGN_IN_URL = `${HOST}auth/director-sign-in`;
export const DIRECTOR_SIGN_UP_URL = `${HOST}auth/director-sign-up`;

export const USER_VALIDATE_EMAIL = `${HOST}user/validate/email`;
export const USER_VALIDAtE_NICK_NAME = `${HOST}user/validate/name`;