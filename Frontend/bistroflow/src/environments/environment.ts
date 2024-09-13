export const environment = {
    production: false,
    backendBaseUrl:
        window[<any>'env'][<any>'backendBaseUrl'] || 'http://localhost:8080/api/v1/'
};