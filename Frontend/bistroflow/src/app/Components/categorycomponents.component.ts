import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../Service/services.service';
import { ChartOptions, ChartDataset } from 'chart.js';

@Component({
  selector: 'app-category-components',
  templateUrl: './html/categorycomponents.component.html',
  styleUrls: ['./css/categorycomponents.component.css']
})
export class CategoryComponentsComponent implements OnInit {
  public pieChartOptions: ChartOptions<'pie'> = {
    responsive: false,
  };

  public pieChartLabels: string[] = [];
  public pieChartDatasets: ChartDataset<'pie', number[]>[] = [];
  public pieChartType: string = 'pie';

  public pieChartLegend = true;
  public pieChartPlugins = [];

  constructor(private servicesService: ServicesService) { }

  ngOnInit(): void {
    this.servicesService.getCategorySalesData().subscribe(data => {
      this.pieChartLabels = data.map(item => item.category_name);
      this.pieChartDatasets = [{
        data: data.map(item => item.totalSales),
        label: 'Sales'
      }];
    });
  }
}
