import axios, {AxiosResponse} from "axios";
import {PaginatedList} from "../dto/PaginatedList.model";
import {BaseDto} from "../dto/BaseDto.model";
import {BaseCriteria} from "../criteria/BaseCriteria.model";
import {AuthService} from "../security/Auth.service";

axios.interceptors.request.use(
    async (config) => {
        const authService = new AuthService();
        const token = await authService.getToken();
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);
class AbstractService<T extends BaseDto, C extends BaseCriteria> {
    private _url: string

    constructor(private baseUrl: string, private beanName: string) {
        this._url = baseUrl + beanName;
    }

    getList(): Promise<AxiosResponse<T[]>> {
        return axios.get(this._url);
    }

    save(item: T): Promise<AxiosResponse<T>> {
        return axios.post(this._url, item);
    }

    update(item: T): Promise<AxiosResponse<T>> {
        return axios.put(this._url, item);
    }

    delete(id: number): Promise<AxiosResponse<T>> {
        return axios.delete(this._url + 'id/' + id);
    }


    find(id: number): Promise<AxiosResponse<T>> {
        return axios.get(this._url + 'id/' + id);
    }

    deleteList(items: T[]): Promise<AxiosResponse<string>> {
        return axios.post(this._url + 'multiple', items);
    }

    findPaginatedByCriteria(criteria: C): Promise<AxiosResponse<PaginatedList<T>>> {
        return axios.post<PaginatedList<T>>(this._url + 'find-paginated-by-criteria', criteria);
    }

    findByCriteria(criteria: C): Promise<AxiosResponse<T[]>> {
        return axios.post<T[]>(this._url + 'find-by-criteria', criteria);
    }

}

export default AbstractService;
