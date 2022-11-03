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

import { SubscribeNewsletterRequest } from '../models/subscribe-newsletter-request';

@Injectable({
  providedIn: 'root',
})
export class MailChimpResourceService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation subscribeToNewsletter
   */
  static readonly SubscribeToNewsletterPath = '/api/v1/newsletter/subscribe';

  /**
   * Subscribe to African Name App newsletter.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `subscribeToNewsletter()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  subscribeToNewsletter$Response(params?: {
    body?: SubscribeNewsletterRequest
  }): Observable<StrictHttpResponse<{
}>> {

    const rb = new RequestBuilder(this.rootUrl, MailChimpResourceService.SubscribeToNewsletterPath, 'post');
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
   * Subscribe to African Name App newsletter.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `subscribeToNewsletter$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  subscribeToNewsletter(params?: {
    body?: SubscribeNewsletterRequest
  }): Observable<{
}> {

    return this.subscribeToNewsletter$Response(params).pipe(
      map((r: StrictHttpResponse<{
}>) => r.body as {
})
    );
  }

  /**
   * Path part for operation ping
   */
  static readonly PingPath = '/api/v1/newsletter/ping';

  /**
   * Ping MailChimp API.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `ping()` instead.
   *
   * This method doesn't expect any request body.
   */
  ping$Response(params?: {
  }): Observable<StrictHttpResponse<{
}>> {

    const rb = new RequestBuilder(this.rootUrl, MailChimpResourceService.PingPath, 'get');
    if (params) {
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
   * Ping MailChimp API.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `ping$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  ping(params?: {
  }): Observable<{
}> {

    return this.ping$Response(params).pipe(
      map((r: StrictHttpResponse<{
}>) => r.body as {
})
    );
  }

  /**
   * Path part for operation getSubscribers
   */
  static readonly GetSubscribersPath = '/api/v1/newsletter/members';

  /**
   * Get all members of the newsletter list.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getSubscribers()` instead.
   *
   * This method doesn't expect any request body.
   */
  getSubscribers$Response(params?: {
  }): Observable<StrictHttpResponse<{
}>> {

    const rb = new RequestBuilder(this.rootUrl, MailChimpResourceService.GetSubscribersPath, 'get');
    if (params) {
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
   * Get all members of the newsletter list.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getSubscribers$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getSubscribers(params?: {
  }): Observable<{
}> {

    return this.getSubscribers$Response(params).pipe(
      map((r: StrictHttpResponse<{
}>) => r.body as {
})
    );
  }

}
