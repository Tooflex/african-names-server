import { Component, Input } from '@angular/core';
import { FirstnameResourceService } from 'src/app/api/services';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-import-form',
  templateUrl: './import-form.component.html',
  styleUrls: ['./import-form.component.scss'],
})
export class ImportFormComponent {
  @Input()
  requiredFileType?: string;

  selected?: File;
  fileName = '';
  uploadProgress?: number;
  uploadSub?: Subscription;

  constructor(private service: FirstnameResourceService) { }

  onFileSelected(event: any) {
    this.selected = event.target.files[0];
    if (this.selected) {
      this.fileName = this.selected.name;
    }
  }

  upload() {
    if (this.selected) {
      this.service
        .uploadCsvFile({
          body: {
            file: this.selected,
          },
        })
        .subscribe();
    }
  }
}
