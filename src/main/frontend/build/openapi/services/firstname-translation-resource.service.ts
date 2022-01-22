/* tslint:disable */
/* eslint-disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { FirstnameTranslation } from '../models/firstname-translation';

@Injectable({
  providedIn: 'root',
})
export class FirstnameTranslationResourceService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation findFirstnameTranslations
   */
  static readonly FindFirstnameTranslationsPath = '/api/v1/translations';

  /**
   * Get firstname translations.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findFirstnameTranslations()` instead.
   *
   * This method doesn't expect any request body.
   */
  findFirstnameTranslations$Response(params?: {
    lang?: string;
  }): Observable<StrictHttpResponse<Array<FirstnameTranslation>>> {

    const rb = new RequestBuilder(this.rootUrl, FirstnameTranslationResourceService.FindFirstnameTranslationsPath, 'get');
    if (params) {
      rb.query('lang', params.lang, {});
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Array<FirstnameTranslation>>;
      })
    );
  }

  /**
   * Get firstname translations.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `findFirstnameTranslations$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findFirstnameTranslations(params?: {
    lang?: string;
  }): Observable<Array<FirstnameTranslation>> {

    return this.findFirstnameTranslations$Response(params).pipe(
      map((r: StrictHttpResponse<Array<FirstnameTranslation>>) => r.body as Array<FirstnameTranslation>)
    );
  }

  /**
   * Path part for operation findFirstnameTranslationsByFirstname
   */
  static readonly FindFirstnameTranslationsByFirstnamePath = '/api/v1/translations/firstnames/{id}';

  /**
   * Get firstname translations.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findFirstnameTranslationsByFirstname()` instead.
   *
   * This method doesn't expect any request body.
   */
  findFirstnameTranslationsByFirstname$Response(params: {
    id: number;
  }): Observable<StrictHttpResponse<Array<FirstnameTranslation>>> {

    const rb = new RequestBuilder(this.rootUrl, FirstnameTranslationResourceService.FindFirstnameTranslationsByFirstnamePath, 'get');
    if (params) {
      rb.path('id', params.id, {});
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Array<FirstnameTranslation>>;
      })
    );
  }

  /**
   * Get firstname translations.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `findFirstnameTranslationsByFirstname$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findFirstnameTranslationsByFirstname(params: {
    id: number;
  }): Observable<Array<FirstnameTranslation>> {

    return this.findFirstnameTranslationsByFirstname$Response(params).pipe(
      map((r: StrictHttpResponse<Array<FirstnameTranslation>>) => r.body as Array<FirstnameTranslation>)
    );
  }

  /**
   * Path part for operation findFirstnameTranslationByFirstnameAndLang
   */
  static readonly FindFirstnameTranslationByFirstnameAndLangPath = '/api/v1/translations/firstnames/{firstnameId}/lang/{langId}';

  /**
   * Get firstname translations.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findFirstnameTranslationByFirstnameAndLang()` instead.
   *
   * This method doesn't expect any request body.
   */
  findFirstnameTranslationByFirstnameAndLang$Response(params: {
    firstnameId: number;
    langId: string;
  }): Observable<StrictHttpResponse<Array<FirstnameTranslation>>> {

    const rb = new RequestBuilder(this.rootUrl, FirstnameTranslationResourceService.FindFirstnameTranslationByFirstnameAndLangPath, 'get');
    if (params) {
      rb.path('firstnameId', params.firstnameId, {});
      rb.path('langId', params.langId, {});
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Array<FirstnameTranslation>>;
      })
    );
  }

  /**
   * Get firstname translations.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `findFirstnameTranslationByFirstnameAndLang$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findFirstnameTranslationByFirstnameAndLang(params: {
    firstnameId: number;
    langId: string;
  }): Observable<Array<FirstnameTranslation>> {

    return this.findFirstnameTranslationByFirstnameAndLang$Response(params).pipe(
      map((r: StrictHttpResponse<Array<FirstnameTranslation>>) => r.body as Array<FirstnameTranslation>)
    );
  }

}
