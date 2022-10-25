import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Firstname } from 'src/app/api/models';
import { FirstnameResourceService } from 'src/app/api/services';
import { LocalDataSource } from 'ng2-smart-table';
import { Subject, takeUntil } from 'rxjs';
import { NbPopoverDirective } from '@nebular/theme';
import { ImportFormComponent } from 'src/app/shared/import-form/import-form.component';

@Component({
  selector: 'app-firstname-table',
  templateUrl: './firstname-table.component.html',
  styleUrls: ['./firstname-table.component.scss']
})
export class FirstnameTableComponent implements OnInit, OnDestroy {

  private destroy$: Subject<void> = new Subject<void>();

  importForm = ImportFormComponent;

  @ViewChild(NbPopoverDirective) popover: NbPopoverDirective | undefined;


  genderList = [{ value: 0, title: 'MALE' }, { value: 1, title: 'FEMALE' }, { value: 2, title: 'MIXED' }];

  settings = {
    add: {
      addButtonContent: '+',
      createButtonContent: 'OK',
      cancelButtonContent: 'X',
      confirmCreate: true,
    },
    edit: {
      editButtonContent: 'edit',
      saveButtonContent: 'OK',
      cancelButtonContent: 'X',
      confirmSave: true,
    },
    delete: {
      deleteButtonContent: 'X',
      confirmDelete: true,
    },
    columns: {
      firstname: {
        title: 'First Name',
        type: 'string',
      },
      gender: {
        title: 'Gender',
        type: 'string',
        editor: {
          type: 'list',
          config: {
            list: this.genderList
          }
        },
        valuePrepareFunction: (cell: string) => {
          let res = this.genderList.find(item => item.title === cell);
          return res?.title
        }
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
    this.importForm.type = 'firstname';
  }

  ngOnInit() {
    this.findFirstnames()
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }

  findFirstnames(): void {
    this.service.findFirstnames()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (firstnames) => {
          if (this.popover) {
            this.popover.hide();
          }
          this.source.load(firstnames).then();
        },
        error: (err) => {
          console.log(err);
        }
      });
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
      this.service.updateFirstname({ id: updatedFirstname.id, body: updatedFirstname })
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
      this.service.deleteFirstname(event.data.id).subscribe(_ => {
        event.confirm.resolve();
      });
    } else {
      event.confirm.reject();
    }
  }
}

