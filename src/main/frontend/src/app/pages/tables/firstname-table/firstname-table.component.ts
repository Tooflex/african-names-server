import { Component, OnDestroy, OnInit } from '@angular/core';
import { Firstname } from 'build/openapi/models';
import { FirstnameResourceService } from 'build/openapi/services';
import { LocalDataSource } from 'ng2-smart-table';
import { Subject, takeUntil } from 'rxjs';
import { ImportFormComponent } from 'src/app/shared/import-form/import-form.component';

@Component({
  selector: 'app-firstname-table',
  templateUrl: './firstname-table.component.html',
  styleUrls: ['./firstname-table.component.scss']
})
export class FirstnameTableComponent implements OnInit, OnDestroy {

  private destroy$: Subject<void> = new Subject<void>();

  importForm = ImportFormComponent;

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
      gender: {
        title: 'Gender',
        type: 'string',
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
        type: 'string',
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
  }

  ngOnInit() {
    this.service.findFirstnames()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (firstnames) => {
          this.source.load(firstnames).then();
        },
        error: (err) => {
          console.log(err);
        }
      });
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }

  onCreateConfirm(event: any): void {
    let firstnameToCreate: Firstname | undefined;
    firstnameToCreate = event.newData;
    if (firstnameToCreate) {
      this.service.createFirstname({ body: firstnameToCreate })
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

  onSaveConfirm(event: any): void {
    let updatedFirstname: Firstname | undefined;
    updatedFirstname = event.newData;
    if (updatedFirstname && updatedFirstname.id) {
      this.service.updateFirstname({id: updatedFirstname.id, body: updatedFirstname})
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

  onDeleteConfirm(event: any): void {
    if (window.confirm('Are you sure you want to delete?')) {
      this.service.deleteFirstname(event.data.id).subscribe(res => {
        event.confirm.resolve();
      });
    } else {
      event.confirm.reject();
    }
  }
}

