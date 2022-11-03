import {Component, OnDestroy, OnInit, ViewEncapsulation} from '@angular/core';
import {FormBuilder, FormGroup, NgForm, Validators} from '@angular/forms';
import { AnimationItem } from 'lottie-web';
import { AnimationOptions } from 'ngx-lottie';
import {SubscribeNewsletterRequest} from "../api/models/subscribe-newsletter-request";
import {MailChimpResourceService} from "../api/services/mail-chimp-resource.service";
import {Subject, takeUntil} from "rxjs";

@Component({
  selector: 'ngx-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class LandingPageComponent implements OnInit, OnDestroy {
  subscribeData: any = <any>{};
  subscribeForm!: FormGroup;

  private destroy$: Subject<void> = new Subject<void>();

  constructor(
    private formBuilder: FormBuilder,
    private subscribeService: MailChimpResourceService
  ) {}

  ngOnInit() { // In the ngOnInit() or in the constructor
    const el = document.getElementById('nb-global-spinner');
    if (el) {
      el.style['display'] = 'none';
    }

    this.subscribeForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email] ],
      firstName: [''],
      lastName: [''],
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
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

  subscribe() {
    if (this.subscribeForm.invalid) {
      return;
    }

    let subscribeRequest: SubscribeNewsletterRequest = {
      email: this.subscribeForm.value.email,
      firstName: this.subscribeForm.value.firstName,
      lastName: this.subscribeForm.value.lastName
    }

    this.subscribeService.subscribeToNewsletter({body: subscribeRequest})
      .pipe(takeUntil(this.destroy$))
      .subscribe( {
        next: (data) => {
          this.subscribeData = data;
          console.log(this.subscribeData);
        },
        error: (error) => {
          console.log(error);
        }
      });
  }
}
