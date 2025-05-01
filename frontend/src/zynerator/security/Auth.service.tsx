import axios, { AxiosResponse } from "axios";
import { ADMIN_AUTH_URL, ADMIN_REGISTER_URL, BASE_URL, } from "../../../config/AppConfig";
import AsyncStorage from '@react-native-async-storage/async-storage';
import { UserDto } from "../dto/UserDto.model";
import { ActivationDto } from "../dto/ActivationDto.model";

export class AuthService {

    async signIn(username: string, password: string): Promise<AxiosResponse<any>> {
        return axios.post(ADMIN_AUTH_URL, { username, password });
    }

    async signOut() {
        await this.removeToken();
    }

    async getRoleConnectedUser(): Promise<string[]> {
        const decodedJwt = await this.decodeJWT();
        return decodedJwt ? decodedJwt['roles'] : [];
    }

    async saveToken(token: string) {
        try {
            await AsyncStorage.setItem('token', token);
        } catch (error) {
            console.error('Error saving token:', error);
        }
    }

    async removeToken() {
        try {
            await AsyncStorage.removeItem('token');
        } catch (error) {
            console.error('Error removing token:', error);
        }
    }

    async getToken(): Promise<string | null> {
        try {
            return await AsyncStorage.getItem('token');
        } catch (error) {
            console.error('Error getting token:', error);
            return null;
        }
    }

    async decodeJWT() {
        const token = await this.getToken();

        if (token) {
            try {
                const base64Url = token.split('.')[1]; // Get the payload part of the JWT
                const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
                const jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
                    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
                }).join(''));

                return JSON.parse(jsonPayload);
            } catch (error) {
                console.error('Error decoding JWT:', error);
                return null;
            }
        }
        return null;
    }

    async getUsername(): Promise<string | null> {
        const tokenDecoded = await this.decodeJWT();
        return tokenDecoded ? tokenDecoded.sub : null;
    }

    async getExpirationFromToken(): Promise<number> {
        const tokenDecoded = await this.decodeJWT();
        return tokenDecoded ? tokenDecoded.exp : 0;
    }

    async isTokenValid(): Promise<boolean> {
        const exp = await this.getExpirationFromToken();
        const now = Date.now();
        return exp * 1000 > now;
    }

    async register(user: UserDto): Promise<AxiosResponse<any>> {
        console.log(user);
        return axios.post(ADMIN_REGISTER_URL + "/admin", user);
    }

    async activateAccount(activationDto: ActivationDto): Promise<AxiosResponse<any>> {
        return axios.post(BASE_URL + 'activateAccount', activationDto);
    }
    
}
