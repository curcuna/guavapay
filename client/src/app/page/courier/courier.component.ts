import { Component, OnInit } from '@angular/core';
import {Order} from "../../domain/order";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AdminService} from "../../service/admin.service";
import {AuthenticationService} from "../../service/authentication.service";
import {OrderStatus} from "../../domain/order.status";
import {CargoStatus} from "../../domain/cargo.status";
import {CargoService} from "../../service/cargo.service";

@Component({
  selector: 'app-courier',
  templateUrl: './courier.component.html',
  styleUrls: ['./courier.component.css']
})
export class CourierComponent implements OnInit {

  displayedColumns: string[] = ['id', 'orderId', 'courierId', 'cargoStatus'];
  dataSource: Order[] = [];
  updateCargoForm: FormGroup;
  personId: any;
  cargoStatuses: CargoStatus[] = [CargoStatus.ON_THE_WAY, CargoStatus.DELIVERED ];
  selectedCargo!: any;

  constructor(public fb: FormBuilder,
              public router: Router,
              public activatedRoute: ActivatedRoute,
              public adminService: AdminService,
              public cargoService: CargoService) {
    this.updateCargoForm = this.fb.group({
      cargoStatus: ['']
    });
  }

  ngOnInit(): void {
    this.personId = this.activatedRoute.snapshot.paramMap.get('id');
    this.getAllOrdersAssignedToCourier();
  }

  getAllOrdersAssignedToCourier(): void {
    this.adminService.getAllOrdersAssignedToCourier(this.personId).subscribe((res: any) => {
      this.dataSource = res;
    });
  }

  updateCargo(): void {
    if(this.selectedCargo) {
      this.selectedCargo.cargoStatus = this.updateCargoForm.controls['cargoStatus'].value
      this.cargoService.updateCargoStatus(this.selectedCargo).subscribe((res: any) => {
        this.updateCargoForm.reset();
        this.getAllOrdersAssignedToCourier();
      });
    }
  }
}
