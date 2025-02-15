import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAuthorFormComponent } from './edit-author-form.component';

describe('EditAuthorFormComponent', () => {
  let component: EditAuthorFormComponent;
  let fixture: ComponentFixture<EditAuthorFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditAuthorFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditAuthorFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
