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

import { Firstname } from '../models/firstname';
import { SpecificationFirstname } from '../models/specification-firstname';

@Injectable({
  providedIn: 'root',
})
export class FirstnameResourceService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation updateFirstname
   */
  static readonly UpdateFirstnamePath = '/api/v1/firstnames/{id}';

  /**
   * Update a firstname.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateFirstname()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateFirstname$Response(params: {
    id: number;
    body: Firstname
  }): Observable<StrictHttpResponse<Firstname>> {

    const rb = new RequestBuilder(this.rootUrl, FirstnameResourceService.UpdateFirstnamePath, 'put');
    if (params) {
      rb.path('id', params.id, {});
      rb.body(params.body, 'application/json');
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Firstname>;
      })
    );
  }

  /**
   * Update a firstname.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `updateFirstname$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateFirstname(params: {
    id: number;
    body: Firstname
  }): Observable<Firstname> {

    return this.updateFirstname$Response(params).pipe(
      map((r: StrictHttpResponse<Firstname>) => r.body as Firstname)
    );
  }

  /**
   * Path part for operation deleteFirstname
   */
  static readonly DeleteFirstnamePath = '/api/v1/firstnames/{id}';

  /**
   * Update a firstname.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteFirstname()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteFirstname$Response(params: {
    id: number;
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, FirstnameResourceService.DeleteFirstnamePath, 'delete');
    if (params) {
      rb.path('id', params.id, {});
    }

    return this.http.request(rb.build({
      responseType: 'text',
      accept: '*/*'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return (r as HttpResponse<any>).clone({ body: undefined }) as StrictHttpResponse<void>;
      })
    );
  }

  /**
   * Update a firstname.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `deleteFirstname$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteFirstname(params: {
    id: number;
  }): Observable<void> {

    return this.deleteFirstname$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation uploadCsvFile
   */
  static readonly UploadCsvFilePath = '/api/v1/firstnames/import';

  /**
   * Import firstnames via .csv file.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `uploadCsvFile()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadCsvFile$Response(params?: {
    body?: {
'file': Blob;
}
  }): Observable<StrictHttpResponse<Array<Firstname>>> {

    const rb = new RequestBuilder(this.rootUrl, FirstnameResourceService.UploadCsvFilePath, 'post');
    if (params) {
      rb.body(params.body, 'multipart/form-data');
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Array<Firstname>>;
      })
    );
  }

  /**
   * Import firstnames via .csv file.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `uploadCsvFile$Response()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadCsvFile(params?: {
    body?: {
'file': Blob;
}
  }): Observable<Array<Firstname>> {

    return this.uploadCsvFile$Response(params).pipe(
      map((r: StrictHttpResponse<Array<Firstname>>) => r.body as Array<Firstname>)
    );
  }

  /**
   * Path part for operation createFirstname
   */
  static readonly CreateFirstnamePath = '/api/v1/firstnames/';

  /**
   * Create a firstname.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `createFirstname()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createFirstname$Response(params: {
    body: Firstname
  }): Observable<StrictHttpResponse<Firstname>> {

    const rb = new RequestBuilder(this.rootUrl, FirstnameResourceService.CreateFirstnamePath, 'post');
    if (params) {
      rb.body(params.body, 'application/json');
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Firstname>;
      })
    );
  }

  /**
   * Create a firstname.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `createFirstname$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createFirstname(params: {
    body: Firstname
  }): Observable<Firstname> {

    return this.createFirstname$Response(params).pipe(
      map((r: StrictHttpResponse<Firstname>) => r.body as Firstname)
    );
  }

  /**
   * Path part for operation findFirstnames
   */
  static readonly FindFirstnamesPath = '/api/v1/firstnames';

  /**
   * Get firstnames.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findFirstnames()` instead.
   *
   * This method doesn't expect any request body.
   */
  findFirstnames$Response(params?: {
    lang?: string;
  }): Observable<StrictHttpResponse<Array<Firstname>>> {

    const rb = new RequestBuilder(this.rootUrl, FirstnameResourceService.FindFirstnamesPath, 'get');
    if (params) {
      rb.query('lang', params.lang, {});
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Array<Firstname>>;
      })
    );
  }

  /**
   * Get firstnames.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `findFirstnames$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findFirstnames(params?: {
    lang?: string;
  }): Observable<Array<Firstname>> {

    return this.findFirstnames$Response(params).pipe(
      map((r: StrictHttpResponse<Array<Firstname>>) => r.body as Array<Firstname>)
    );
  }

  /**
   * Path part for operation searchFirstnames
   */
  static readonly SearchFirstnamesPath = '/api/v1/firstnames/search';

  /**
   * Search firstnames.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `searchFirstnames()` instead.
   *
   * This method doesn't expect any request body.
   */
  searchFirstnames$Response(params?: {
    specs?: SpecificationFirstname;
  }): Observable<StrictHttpResponse<Array<Firstname>>> {

    const rb = new RequestBuilder(this.rootUrl, FirstnameResourceService.SearchFirstnamesPath, 'get');
    if (params) {
      rb.query('specs', params.specs, {});
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Array<Firstname>>;
      })
    );
  }

  /**
   * Search firstnames.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `searchFirstnames$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  searchFirstnames(params?: {
    specs?: SpecificationFirstname;
  }): Observable<Array<Firstname>> {

    return this.searchFirstnames$Response(params).pipe(
      map((r: StrictHttpResponse<Array<Firstname>>) => r.body as Array<Firstname>)
    );
  }

  /**
   * Path part for operation findPrenomsAlea
   */
  static readonly FindPrenomsAleaPath = '/api/v1/firstnames/random';

  /**
   * Get random list of firstnames.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findPrenomsAlea()` instead.
   *
   * This method doesn't expect any request body.
   */
  findPrenomsAlea$Response(params?: {
    lang?: string;
  }): Observable<StrictHttpResponse<Array<Firstname>>> {

    const rb = new RequestBuilder(this.rootUrl, FirstnameResourceService.FindPrenomsAleaPath, 'get');
    if (params) {
      rb.query('lang', params.lang, {});
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Array<Firstname>>;
      })
    );
  }

  /**
   * Get random list of firstnames.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `findPrenomsAlea$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findPrenomsAlea(params?: {
    lang?: string;
  }): Observable<Array<Firstname>> {

    return this.findPrenomsAlea$Response(params).pipe(
      map((r: StrictHttpResponse<Array<Firstname>>) => r.body as Array<Firstname>)
    );
  }

}
