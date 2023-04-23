import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { MinMaxDto } from '../_model/min-max-dto';

@Injectable({
    providedIn: 'root'
})
export class CurrenciesService {
    private backendUrl = environment.backendUrl;

    constructor(private http: HttpClient) { }

    getAverageExchangeRate(date: string, currency: string) {
        let url = `${this.backendUrl}/currency/` + currency + '/average/exchange-rate';
        let queryParams = new HttpParams().set('date', date);
        return this.http.get<number>(url, {params: queryParams});
    }

    getMinMax(range: number, currency: string) {
        let url = `${this.backendUrl}/currency/` + currency + '/top/' + range + '/average/exchange-rate';
        return this.http.get<MinMaxDto>(url);
    }

    getMajorDiffrence(range: number, currency: string) {
        let url = `${this.backendUrl}/currency/` + currency + '/top/' + range + '/exchange-rate';
        return this.http.get<number>(url);
    }
}