import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FirstnameResourceService, FirstnameTranslationResourceService } from 'src/app/api/services';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-import-form',
  templateUrl: './import-form.component.html',
  styleUrls: ['./import-form.component.scss'],
})
export class ImportFormComponent {
  @Input()
  requiredFileType?: string;

  @Output()
  uploadFinished: EventEmitter<boolean> = new EventEmitter<boolean>();


  selected?: File;
  fileName = '';
  uploadProgress?: number;
  uploadSub?: Subscription;

  static type: string = 'firstname'

  constructor(private service: FirstnameResourceService, private translationService: FirstnameTranslationResourceService) { }

  onFileSelected(event: any) {
    this.selected = event.target.files[0];
    if (this.selected) {
      this.fileName = this.selected.name;
    }
  }

  upload() {
    if (this.selected) {
      if (ImportFormComponent.type === 'firstname') {
        this.service
          .uploadCsvFile1({
            body: {
              file: this.selected,
            },
          })
          .subscribe(
            () => {
              this.uploadFinished.emit(true);
            }
          );
      }
      else if (ImportFormComponent.type === 'firstname_translation') {
        this.translationService
          .uploadCsvFile({
            body: {
              file: this.selected,
            },
          })
          .subscribe(
            () => {
              this.uploadFinished.emit(true);
            }
          );
      }
    }
  }
}
