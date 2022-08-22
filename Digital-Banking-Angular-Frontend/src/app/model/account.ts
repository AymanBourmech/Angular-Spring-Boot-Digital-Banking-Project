import { Customer } from './customer';

export class AccountDetails {
  accountId!: string;
  id!: string;
  balance!: number;
  createdAt!: Date;
  currentPage!: number;
  totalPages!: number;
  pageSize!: number;
  customerDTO!: Customer;
  accountOperationDTO!: AccountOperation[];
}
export class AccountOperation {
  id!: number;
  operationDate!: Date;
  amount!: number;
  type!: string;
  description!: string;
}
