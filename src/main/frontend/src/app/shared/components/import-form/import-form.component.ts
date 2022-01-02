import {Component, Input} from '@angular/core';
import {Subscription} from 'rxjs';
import {FirstnameResourceService} from '../../../../../build/openapi';

@Component({
  selector: 'ngx-import-form',
  templateUrl: './import-form.component.html',
  styleUrls: ['./import-form.component.scss'],
})
export class ImportFormComponent {


  @Input()
  requiredFileType: string;

  selected: File;
  fileName = '';
  uploadProgress: number;
  uploadSub: Subscription;

  constructor(private service: FirstnameResourceService) {}

  onFileSelected(event) {
    this.selected = event.target.files[0];
    if (this.selected) {
      this.fileName = this.selected.name;
    }
  }

  upload() {
    this.service.uploadCsvFile(this.selected).subscribe();
  }
}
