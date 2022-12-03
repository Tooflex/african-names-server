import {Component, OnDestroy, OnInit} from '@angular/core';
import { Subject } from 'rxjs';
import { ServerDataSource} from 'ng2-smart-table';
import { takeUntil } from 'rxjs/operators';
import { ImportFormComponent } from 'src/app/shared/import-form/import-form.component';
import { FirstnameTranslationResourceService } from 'src/app/api/services';
import { environment } from "../../../../environments/environment";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-firstname-translation-table',
  templateUrl: './firstname-translation-table.component.html',
  styleUrls: ['./firstname-translation-table.component.scss'],
})
export class FirstnameTranslationTableComponent implements OnInit, OnDestroy {
  private destroy$: Subject<void> = new Subject<void>();

  importForm = ImportFormComponent;

  langList = [
    { value: 1, title: 'English' },
    { value: 2, title: 'French' },
    { value: 3, title: 'Italian' },
    { value: 4, title: 'German' },
  ];

  settings = {
    actions: {
      edit: true
    },
    add: {
      addButtonContent: '+',
      createButtonContent: 'OK',
      cancelButtonContent: 'X',
      confirmCreate: true,
    },
    edit: {
      editButtonContent: 'fa fa-pencil',
      saveButtonContent: 'fa fa-checkmark',
      cancelButtonContent: 'fa fa-close',
      confirmSave: true,
    },
    delete: {
      deleteButtonContent: '<i class="nb-trash"></i>',
      confirmDelete: true,
    },
    columns: {
      // Firstname value of Firstname object
      firstname: {
        title: 'First Name',
        type: 'string',
        valuePrepareFunction: (cell: any) => {
          return cell.firstname;
        }
      },
      language: {
        title: 'Lang',
        editor:
        {
          type: 'list',
          config: {
            list: this.langList,
          },
        },
        valuePrepareFunction: (cell: any) => {
          return cell.name;
        }
      },
      meaningTranslation: {
        title: 'Meaning translation',
        type: 'string',
      },
      originsTranslation: {
        title: 'Origin translation',
        type: 'string',
      },
    },
  };

  source: ServerDataSource

  constructor(
    private service: FirstnameTranslationResourceService, private http: HttpClient) {
    this.importForm.type = 'firstname_translation';
    this.source = new ServerDataSource(this.http, {
      endPoint: environment.baseApiUrl + FirstnameTranslationResourceService.FindFirstnameTranslationsPath,
      pagerPageKey: 'page',
      pagerLimitKey: 'size',
      dataKey: 'content',
      totalKey: 'totalElements',
    });
  }

  ngOnInit(): void {
    this.findTranslations();
    this.source.onChanged().pipe(takeUntil(this.destroy$)).subscribe((change) => {
      if (change.action === 'page') {
        this.findTranslations(change.paging.page, change.paging.perPage);
      }
    });
    }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  findTranslations(page = 0, size = 1000): void {
    this.service.findFirstnameTranslations({lang: 'en', pageable: {pageNumber: page, pageSize: size}})
      .pipe(takeUntil(this.destroy$))
  }

  onCreateConfirm(event: any): void {
  }

  onSaveConfirm(event: any): void {
  }

  onDeleteConfirm(event: any): void {
  }
}
