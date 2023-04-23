import { Component } from '@angular/core';
import { RoutingService } from 'src/app/_service/routing.service';

@Component({
  selector: 'app-home-layout',
  templateUrl: './home-layout.component.html',
  styleUrls: ['./home-layout.component.scss']
})
export class HomeLayoutComponent {

  constructor (private routingService: RoutingService) {}

  ngOnInit(): void {
    this.routingService.openHomePage();
  }
}
