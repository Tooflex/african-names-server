/* tslint:disable */
/* eslint-disable */
import { FirstnameTranslation } from './firstname-translation';
import { Pageable } from './pageable';
import { Sort } from './sort';
export interface FirstnameTranslationResponse {
  content?: Array<FirstnameTranslation>;
  empty?: boolean;
  first?: boolean;
  last?: boolean;
  number?: number;
  numberOfElements?: number;
  pageable?: Pageable;
  size?: number;
  sort?: Sort;
  totalElements?: number;
  totalPages?: number;
}
