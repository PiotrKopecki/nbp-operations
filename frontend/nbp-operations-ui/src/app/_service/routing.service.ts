import { Router } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})

export class RoutingService {
    
    constructor(private router: Router) {}

    openHomePage() {
        this.router.navigate(['/home'])
    }
}