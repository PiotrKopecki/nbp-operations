import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MinMaxDto } from 'src/app/_model/min-max-dto';
import { CurrenciesService } from 'src/app/_service/currencies.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  chosenDate: string = "";
  currencyCodes = ["AUD","THB","BRL","BGN","CAD","CLP","CZK","DKK","EUR","HUF","HKD","UAH","ISK","INR","MYR","MXN","ILS",
  "NZD","NOK","PHP","GBP","ZAR","RON","IDR","SGD","SEK","CHF","TRY","USD","KRW","JPY","CNY","XDR"];
  selectedCurrency: string = "";
  task1Result: string = "";
  chosenRange: number = 0;
  chosenRange2: number = 0;
  task2Result: string = "";
  task3Result: string = "";

  constructor (
    private fb: FormBuilder,
    private datePipe: DatePipe,
    private currenciesService: CurrenciesService
    ) {}

  getAverageExchangeRate() {
    this.currenciesService.getAverageExchangeRate(this.chosenDate, this.selectedCurrency).subscribe(result => {
      this.task1Result = (result == -1) ? "brak danych" : result.toString();
    })
  }
  onDateChange(event: any) {
    this.chosenDate = this.datePipe.transform(event, 'yyyy-MM-dd')!;
  }
  isTask1Disabled() {
    return this.chosenDate === "" || this.selectedCurrency === ""
  }
  changeRange(event: any) {
    this.chosenRange = event.target.value;
  }
  changeRange2(event: any) {
    this.chosenRange2 = event.target.value;
  }
  getMinMax() {
    this.currenciesService.getMinMax(this.chosenRange, this.selectedCurrency).subscribe(result => {
      let minMaxDto: MinMaxDto = result;
      this.task2Result = (minMaxDto == null) ? "brak danych" : "max: " + minMaxDto.max + "  min: " + minMaxDto.min;
    })
  }
  isTask2Disabled() {
    return this.chosenRange === 0 || this.selectedCurrency === "";
  }
  isTask3Disabled() {
    return this.chosenRange2 === 0 || this.selectedCurrency === "";
  }
  getMajorDiffrence() {
    this.currenciesService.getMajorDiffrence(this.chosenRange2, this.selectedCurrency).subscribe(result => {
      this.task3Result = (result == -1) ?  "brak danych" : result.toFixed(10).toString();
    })
  }
}
