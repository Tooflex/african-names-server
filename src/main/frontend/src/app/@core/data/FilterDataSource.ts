/*
 * Copyright (c) 2022.
 * @Author Otourou Da Costa
 */

import {ServerDataSource} from "ng2-smart-table";
import {HttpParams} from "@angular/common/http";

export class FilterServerDataSource extends ServerDataSource {

  override addFilterRequestParams(httpParams: HttpParams): HttpParams {
    return httpParams;
  }


  protected override createRequesParams(): HttpParams {
    let httpParams = new HttpParams();
    return httpParams;
  }

}
