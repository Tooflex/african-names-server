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

import { JwtResponse } from '../models/jwt-response';
import { LoginRequest } from '../models/login-request';
import { SignupRequest } from '../models/signup-request';

@Injectable({
  providedIn: 'root',
})
export class AuthResourceService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation registerUser
   */
  static readonly RegisterUserPath = '/api/v1/auth/signup';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `registerUser()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  registerUser$Response(params?: {
    body?: SignupRequest
  }): Observable<StrictHttpResponse<{
}>> {

    const rb = new RequestBuilder(this.rootUrl, AuthResourceService.RegisterUserPath, 'post');
    if (params) {
      rb.body(params.body, 'application/json');
    }

    return this.http.request(rb.build({
      responseType: 'blob',
      accept: '*/*'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<{
        }>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `registerUser$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  registerUser(params?: {
    body?: SignupRequest
  }): Observable<{
}> {

    return this.registerUser$Response(params).pipe(
      map((r: StrictHttpResponse<{
}>) => r.body as {
})
    );
  }

  /**
   * Path part for operation authenticateUser
   */
  static readonly AuthenticateUserPath = '/api/v1/auth/login';

  /**
   * Authenticate user.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `authenticateUser()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  authenticateUser$Response(params?: {
    body?: LoginRequest
  }): Observable<StrictHttpResponse<JwtResponse>> {

    const rb = new RequestBuilder(this.rootUrl, AuthResourceService.AuthenticateUserPath, 'post');
    if (params) {
      rb.body(params.body, 'application/json');
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<JwtResponse>;
      })
    );
  }

  /**
   * Authenticate user.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `authenticateUser$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  authenticateUser(params?: {
    body?: LoginRequest
  }): Observable<JwtResponse> {

    return this.authenticateUser$Response(params).pipe(
      map((r: StrictHttpResponse<JwtResponse>) => r.body as JwtResponse)
    );
  }

}
