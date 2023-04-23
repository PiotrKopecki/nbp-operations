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
      if (result == -1) {
        this.task1Result = "brak danych";
      }
      else {
        this.task1Result = result.toString();
      }
    })
  }
  onDateChange(event: any) {
    this.chosenDate = this.datePipe.transform(event, 'yyyy-MM-dd')!;
  }
  isTask1Disabled() {
    if (this.chosenDate != "" && this.selectedCurrency != "") {
      return false;
    }
    return true;
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
      if (minMaxDto == null) {
        this.task2Result = "brak danych";
      }
      else {
        this.task2Result = "max: " + minMaxDto.max + "  min: " + minMaxDto.min;
      }
    })
  }
  isTask2Disabled() {
    if (this.chosenRange != 0 && this.selectedCurrency != "") {
      return false;
    }
    return true;
  }
  isTask3Disabled() {
    if (this.chosenRange2 != 0 && this.selectedCurrency != "") {
      return false;
    }
    return true;
  }
  getMajorDiffrence() {
    this.currenciesService.getMajorDiffrence(this.chosenRange2, this.selectedCurrency).subscribe(result => {
      if (result == -1) {
        this.task3Result = "brak danych";
      }
      else {
        this.task3Result = result.toFixed(10).toString();
      }
    })
  }
}
