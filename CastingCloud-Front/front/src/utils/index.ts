export const getExpires = (expriedTime: number) => {
    const now = new Date().getTime();
    const expries = new Date(now + expriedTime);
    return expries;
}