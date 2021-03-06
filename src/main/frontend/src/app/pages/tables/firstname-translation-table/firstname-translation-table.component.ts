import { Component } from '@angular/core';
import {Subject} from 'rxjs';
import {LocalDataSource} from 'ng2-smart-table';
import {takeUntil} from 'rxjs/operators';
import { ImportFormComponent } from 'src/app/shared/import-form/import-form.component';
import { FirstnameTranslationResourceService } from 'src/app/api/services';

@Component({
  selector: 'app-firstname-translation-table',
  templateUrl: './firstname-translation-table.component.html',
  styleUrls: ['./firstname-translation-table.component.scss'],
})
export class FirstnameTranslationTableComponent {
  private destroy$: Subject<void> = new Subject<void>();

  importForm = ImportFormComponent;

  langList = [
    {value: 1, title: 'English'},
    {value: 2, title: 'French'},
    {value: 3, title: 'Italian'},
    {value: 4, title: 'German'},
  ];

  settings = {
    add: {
      addButtonContent: '+',
      createButtonContent: 'OK',
      cancelButtonContent: 'X',
      confirmCreate: true,
    },
    edit: {
      editButtonContent: '<i class="nb-edit"></i>',
      saveButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
      confirmSave: true,
    },
    delete: {
      deleteButtonContent: '<i class="nb-trash"></i>',
      confirmDelete: true,
    },
    columns: {
      id: {
        title: 'ID',
        type: 'number',
      },
      firstname: {
        title: 'First Name',
        type: 'string',
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

  source: LocalDataSource = new LocalDataSource();

  constructor(private service: FirstnameTranslationResourceService) {
    this.service.findFirstnameTranslations({lang: 'en'})
      .pipe(takeUntil(this.destroy$))
      .subscribe(firstnameTranslations => {
      this.source.load(firstnameTranslations).then();
    });
  }

  onCreateConfirm(event: any): void {
  }

  onSaveConfirm(event: any): void {
  }

  onDeleteConfirm(event: any): void {
  }
}
