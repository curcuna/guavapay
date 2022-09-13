import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Order} from "../../domain/order";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AdminService} from "../../service/admin.service";
import {OrderStatus} from "../../domain/order.status";
import {Cargo} from "../../domain/cargo";
import {AuthenticationService} from "../../service/authentication.service";
import {Person} from "../../domain/person";


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  displayedColumns: string[] = ['id', 'orderStatus', 'destination'];
  dataSource: Order[] = [];
  updateOrderForm: FormGroup;
  personId: any;
  selectedOrder!: any;
  assignCargoForm: FormGroup;


  courierDisplayedColumns: string[] = ['id', 'username'];
  courierDataSource: Person[] = [];
  orderStatuses: OrderStatus[] = [OrderStatus.COMPLETED, OrderStatus.CANCELED ];
  createCourierForm: FormGroup;

  constructor(public fb: FormBuilder,
              public router: Router,
              public activatedRoute: ActivatedRoute,
              public adminService: AdminService,
              public authenticationService: AuthenticationService) {
    this.personId = this.activatedRoute.snapshot.paramMap.get('id');
    this.updateOrderForm = this.fb.group({
      orderStatus: ['']
    });
    this.assignCargoForm = this.fb.group({
      courier: ['']
    });
    this.createCourierForm = this.fb.group({
    username: [''],
    password: [''],
  });
  }

  ngOnInit(): void {
    this.getAllOrders();
    this.getAllCouriers();
  }

  getAllOrders(): void {
    this.adminService.getAllOrders().subscribe((res: any) => {
      this.dataSource = res;
    });
  }

  updateOrder(): void {
    if(this.selectedOrder) {
      this.selectedOrder.orderStatus = this.updateOrderForm.controls['orderStatus'].value
      this.adminService.updateOrder(this.selectedOrder).subscribe((res: any) => {
        this.updateOrderForm.reset();
        this.getAllOrders();
      });
    }
  }

  assignCargo(): void {
    if(this.selectedOrder) {
      let cargo = new Cargo();
      cargo.orderId = this.selectedOrder.id;
      cargo.courierId = this.assignCargoForm.controls['courier'].value
      this.adminService.assignCourier(cargo).subscribe((res: any) => {
        this.assignCargoForm.reset();
        this.getAllOrders();
      });
    }
  }

  getAllCouriers(): void {
    this.authenticationService.getAllCouriers().subscribe((res: any) => {
      this.courierDataSource = res;
    });
  }

  createCourier() {
    this.authenticationService.createCourier(this.createCourierForm.value).subscribe((res) => {
      this.createCourierForm.reset();
      this.getAllCouriers();
    });
  }
}

