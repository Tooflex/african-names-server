import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { AnimationItem } from 'lottie-web';
import { AnimationOptions } from 'ngx-lottie';

@Component({
  selector: 'ngx-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class LandingPageComponent implements OnInit {

  constructor() {}

  ngOnInit() { // In the ngOnInit() or in the constructor
    const el = document.getElementById('nb-global-spinner');
    if (el) {
      el.style['display'] = 'none';
    }
  }

  optionsSearch: AnimationOptions = {
    path: '/assets/lottie-files/search.json',
  };

  optionsBaby: AnimationOptions = {
    path: '/assets/lottie-files/baby.json',
  };

  animationCreated(animationItem: AnimationItem): void {
    console.log(animationItem);
  }

}
