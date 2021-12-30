import { Component, Input } from '@angular/core';
import {Subject} from 'rxjs';
import {ImportFormComponent} from '../../../shared/components/import-form/import-form.component';
import {LocalDataSource} from 'ng2-smart-table';
import {
  FirstnameTranslationResourceService,
} from '../../../../../build/openapi';
import {takeUntil} from 'rxjs/operators';

interface TreeNode<T> {
  data: T;
  children?: TreeNode<T>[];
  expanded?: boolean;
}

interface FSEntry {
  name: string;
  size: string;
  kind: string;
  items?: number;
}

@Component({
  selector: 'ngx-firstname-translation-table',
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
      addButtonContent: '<i class="nb-plus"></i>',
      createButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
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
    this.service.findFirstnameTranslations('en')
      .pipe(takeUntil(this.destroy$))
      .subscribe(firstnameTranslations => {
      this.source.load(firstnameTranslations).then();
    });
  }

  onCreateConfirm(event): void {
  }

  onSaveConfirm(event): void {
  }

  onDeleteConfirm(event): void {
  }
}
