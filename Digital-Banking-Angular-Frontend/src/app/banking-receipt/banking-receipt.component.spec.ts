import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankingReceiptComponent } from './banking-receipt.component';

describe('BankingReceiptComponent', () => {
  let component: BankingReceiptComponent;
  let fixture: ComponentFixture<BankingReceiptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BankingReceiptComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BankingReceiptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
