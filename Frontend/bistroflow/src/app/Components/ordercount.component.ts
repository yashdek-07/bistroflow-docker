import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../Service/services.service'; // Update the import path as needed
import { UserOrderCount } from '../Models/userordercount'; // Update the import path as needed
import { ChartOptions, ChartType, ChartData } from 'chart.js';

@Component({
  selector: 'app-ordercount',
  templateUrl: './html/ordercount.component.html',
  styleUrls: ['./css/ordercount.component.css']
})
export class OrdercountComponent implements OnInit {
  userOrderCounts: UserOrderCount[] = [];
  userNames: string[] = [];
  ordersSold: number[] = [];
  isLoading: boolean = true;

  chartOptions: ChartOptions<'bar'> = {
    responsive: true,
    scales: {
      x: {
        beginAtZero: true,
        title: {
          display: true,
          text: 'User Name'
        }
      },
      y: {
        beginAtZero: true,
        title: {
          display: true,
          text: 'Orders Sold'
        }
      }
    }
  };
  chartLabels: string[] = []; 
  chartData: ChartData<'bar'> = {
    labels: this.chartLabels,
    datasets: [{
      label: 'Orders Sold',
      data: this.ordersSold,
      backgroundColor: 'rgba(63, 81, 181, 0.5)',
    }]
  };
  chartType: ChartType = 'bar';

  constructor(private servicesService: ServicesService) {}

  ngOnInit(): void {
    this.servicesService.getUserOrderCount().subscribe(
      (data: UserOrderCount[]) => {
        if (data.length > 0) {
          this.userOrderCounts = data;
          this.chartLabels = data.map(user => user.userName);
          this.chartData = {
            labels: this.chartLabels,
            datasets: [{
              label: 'Orders Sold',
              data: data.map(user => user.ordersSold),
              backgroundColor: 'rgba(63, 81, 181, 0.5)',
            }]
          };
        }
        this.isLoading = false;
      },
      error => {
        console.error('Error fetching data', error);
        this.isLoading = false;
      }
    );
  }
}
