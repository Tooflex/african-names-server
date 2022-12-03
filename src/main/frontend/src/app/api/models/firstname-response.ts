/* tslint:disable */
/* eslint-disable */
import { Firstname } from './firstname';
import { Pageable } from './pageable';
import { Sort } from './sort';
export interface FirstnameResponse {
  content?: Array<Firstname>;
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
