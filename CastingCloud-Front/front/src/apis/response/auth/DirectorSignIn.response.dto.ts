interface Dto {
    directorEmail: string;
    directorPhoneNumber: string;
    directorName: string;
    directorCompany: string;
    directorProfile: string | null;
    
    expiredTime: number;
    token: string;
}

export default Dto;