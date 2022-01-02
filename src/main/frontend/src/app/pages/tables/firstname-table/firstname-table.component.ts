import { Component } from '@angular/core';
import { LocalDataSource } from 'ng2-smart-table';

import { FirstnameResourceService } from '../../../../../build/openapi';
import {Firstname} from '../../../../../build/openapi';
import GenderEnum = Firstname.GenderEnum;
import SizeEnum = Firstname.SizeEnum;
import {ImportFormComponent} from '../../../shared/components/import-form/import-form.component';
import {Subject} from 'rxjs';
import {takeUntil} from 'rxjs/operators';


@Component({
  selector: 'ngx-firstname-table',
  templateUrl: './firstname-table.component.html',
  styleUrls: ['./firstname-table.component.scss'],
})
export class FirstnameTableComponent {
  private destroy$: Subject<void> = new Subject<void>();

  importForm = ImportFormComponent;

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
      gender: {
        title: 'Gender',
        type: GenderEnum,
      },
      meaning: {
        title: 'Meaning',
        type: 'string',
      },
      meaningMore: {
        title: 'Meaning more',
        type: 'string',
      },
      origins: {
        title: 'Origins',
        type: 'string',
      },
      regions: {
        title: 'Regions',
        type: 'string',
      },
      countries: {
        title: 'Countries',
        type: 'string',
      },
      nearingNames: {
        title: 'Nearing Names',
        type: 'string',
      },
      celebrationDate: {
        title: 'Celebration Date',
        type: 'string',
      },
      celebrities: {
        title: 'Celebrities',
        type: 'string',
      },
      soundURL: {
        title: 'Sound URL',
        type: 'string',
      },
      size: {
        title: 'Size',
        type: SizeEnum,
      },
      createDateTime: {
        title: 'CreatedTime',
        type: 'date',
      },
      updateDateTime: {
        title: 'Update Time',
        type: 'date',
      },
    },
  };

  source: LocalDataSource = new LocalDataSource();

  constructor(private service: FirstnameResourceService) {
    this.service.findFirstnames()
      .pipe(takeUntil(this.destroy$))
      .subscribe(firstnames => {
      this.source.load(firstnames).then();
    });
  }

  onCreateConfirm(event): void {
    let firstnameToCreate: Firstname | undefined;
    firstnameToCreate = event.newData;
    if (firstnameToCreate) {
      this.service.createFirstname(firstnameToCreate)
        .pipe(takeUntil(this.destroy$))
        .subscribe(res => {
        const resFirstname: Firstname | undefined = res;
        if (resFirstname) {
          event.confirm.resolve(resFirstname);
        } else {
          event.confirm.reject();
        }
      });
    } else {
      event.confirm.reject();
    }
  }

  onSaveConfirm(event): void {
    let updatedFirstname: Firstname | undefined;
    updatedFirstname = event.newData;
    if (updatedFirstname) {
      this.service.updateFirstname(updatedFirstname.id, updatedFirstname)
        .pipe(takeUntil(this.destroy$))
        .subscribe(res => {
        const resFirstname: Firstname | undefined = res;
        if (resFirstname) {
          event.confirm.resolve(resFirstname);
        } else {
          event.confirm.reject();
        }
      });
    } else {
      event.confirm.reject();
    }

  }

  onDeleteConfirm(event): void {
    if (window.confirm('Are you sure you want to delete?')) {
      this.service.deleteFirstname(event.data.id).subscribe(res => {
        event.confirm.resolve();
      });
    } else {
      event.confirm.reject();
    }
  }
}
