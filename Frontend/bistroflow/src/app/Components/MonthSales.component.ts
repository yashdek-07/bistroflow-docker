import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../Service/services.service';
import { ChartOptions, ChartType, ChartData } from 'chart.js';
import { format, parseISO } from 'date-fns';


@Component({
  selector: 'app-MonthSalescount',
  templateUrl: './html/MonthSales.component.html',
})
export class MonthSalesComponent implements OnInit {
  isLoading: boolean = true;

  chartOptions: ChartOptions<'line'> = {
    responsive: true,
    scales: {
      x: {
        title: {
          display: true,
          text: 'Date'
        }
      },
      y: {
        beginAtZero: true,
        title: {
          display: true,
          text: 'Total Sales'
        }
      }
    }
  };
  chartLabels: string[] = [];
  chartData: ChartData<'line'> = {
    labels: this.chartLabels,
    datasets: [{
      label: 'Total Sales(RS)',
      data: [],
      fill: true,
      tension: 0.5,
      borderColor: 'black',
      backgroundColor: 'rgba(255,0,0,0.3)'
    }]
  };
  chartType: ChartType = 'line';

  constructor(private servicesService: ServicesService) {}

  ngOnInit(): void {
    this.servicesService.getSalesData().subscribe(
      (data: { date: string, totalSales: number }[]) => {
        if (data.length > 0) {
          this.chartLabels = data.map(item => item.date);
          console.log(this.chartLabels)
          this.chartData = {
            labels: this.chartLabels,
            datasets: [{
              label: 'Total Sales (Rs)',
              data: data.map(item => item.totalSales),
              fill: true,
              tension: 0.5,
              borderColor: 'black',
              backgroundColor: 'rgba(255,0,0,0.3)',      
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
