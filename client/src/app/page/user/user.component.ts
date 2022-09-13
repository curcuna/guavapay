import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../service/user.service";
import {Order} from "../../domain/order";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  displayedColumns: string[] = ['id', 'orderStatus', 'destination', 'actions'];
  dataSource: Order[] = [];
  createOrderForm: FormGroup;
  personId: any;
  selectedOrder!: any;

  constructor(public fb: FormBuilder,
              public router: Router,
              public activatedRoute: ActivatedRoute,
              public userService: UserService) {
    this.personId = this.activatedRoute.snapshot.paramMap.get('id');
    this.createOrderForm = this.fb.group({
      personId: [''],
      destination: [''],
    });
  }

  ngOnInit(): void {
    this.getOrders(this.personId);
  }

  getOrders(id:number): void {
    this.userService.getOrders(id).subscribe((res: any) => {
      this.dataSource = res;
    });
  }

  createOrder(): void {
    this.createOrderForm.patchValue({
      personId: this.personId
    });
    this.userService.createOrder(this.createOrderForm.value).subscribe((res: any) => {
      this.createOrderForm.reset();
      this.getOrders(this.personId);
    });
  }

  cancelOrder(id:number): void {
    this.userService.cancelOrder(id).subscribe((res: any) => {
      this.getOrders(this.personId);
    });
  }

  changeDestionation(): void {
    if(this.selectedOrder){
      this.selectedOrder.destination = this.createOrderForm.controls['destination'].value
      this.userService.changeDestination(this.selectedOrder).subscribe((res: any) => {
        this.selectedOrder = null;
        this.getOrders(this.personId);
      });
    }
  }


}
