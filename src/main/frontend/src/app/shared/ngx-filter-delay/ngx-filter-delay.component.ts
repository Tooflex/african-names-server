import {AfterViewInit, Component, OnChanges, SimpleChanges} from '@angular/core';
import {DefaultFilter} from "ng2-smart-table";
import {FormControl} from "@angular/forms";
import {distinctUntilChanged, Observable} from "rxjs";
import {debounceTime} from "rxjs/operators";
import {environment} from "../../../environments/environment";
import {FirstnameResourceService} from "../../api/services/firstname-resource.service";
import {HttpClient} from "@angular/common/http";
import {Firstname} from "../../api/models/firstname";

@Component({
  template: `
    <input
      #input
      [ngClass]="inputClass"
      [formControl]="inputControl"
      class="form-control"
      [placeholder]="column.title">
  `,
})
export class NgxFilterDelayComponent extends DefaultFilter implements OnChanges, AfterViewInit {
  inputControl = new FormControl();
  delayTime = this.delay;

  constructor(private http: HttpClient) {
    super();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['query']) {
      this.query = changes['query'].currentValue;
      //this.inputControl.setValue(this.query);
    }
  }

  ngAfterViewInit() {
    if (this.column && this.column['settings'] && this.column['settings']?.custom?.delayTime) {
      this.delayTime = this.column['settings']?.custom?.delayTime;
    }
    this.inputControl.valueChanges
      .pipe(
        distinctUntilChanged(),
        debounceTime(this.delayTime),
      )
      .subscribe((value: string) => {
        console.log('query');

        this.query = value !== null ? this.inputControl.value.toString() : '';
        this.setFilter();
      });
  }

  override setFilter() {
    //this.searchFirstnames(this.query).subscribe((data: Firstname) => {
      //console.log(data);
    //});
  }

  searchFirstnames(searchStr: string): Observable<Firstname> {
    return this.http.get(environment.baseApiUrl + FirstnameResourceService.SearchFirstnamesPath + '?search=firstname:' + searchStr + "*")
  }
}

