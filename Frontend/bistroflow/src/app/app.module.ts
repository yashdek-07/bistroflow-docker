
import { MonthSalesComponent } from './Components/MonthSales.component';
import { DashboardComponent } from './Components/dashboard.component';
import { ServicesService } from './Service/services.service';
import { CategoryComponentsComponent } from './Components/categorycomponents.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BaseChartDirective } from 'ng2-charts';
import { provideCharts, withDefaultRegisterables } from 'ng2-charts';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { OrdercountComponent } from './Components/ordercount.component';
import { CategoryComponent } from './Components/category/category.component';
import { ProductComponent } from './Components/product/product.component';
import { OrderComponent } from './order/order.component';
import { NewSignupComponent } from './new-signup/new-signup.component';
import { NewLoginComponent } from './new-login/new-login.component';
import { AuthInterceptor } from './interceptors/auth.interceptor';
 
const routes: Routes = [
  { path: 'analytics', component:DashboardComponent },
  { path: 'signup', component:NewSignupComponent },
  { path: 'login', component:NewLoginComponent },
  { path: 'categories', component: CategoryComponent },
  { path: 'create-category', component: CategoryComponent },
  { path: 'update-category/:id', component: CategoryComponent },
  { path: 'products', component: ProductComponent },
  { path: 'create-product', component: ProductComponent },
  { path: 'update-product/:id', component: ProductComponent },
  { path: 'order', component: OrderComponent },
  {path : "" , component:NewSignupComponent}
]



@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    OrdercountComponent,
    MonthSalesComponent,
    CategoryComponentsComponent,
    CategoryComponent,
    ProductComponent,
    OrderComponent,
    NewSignupComponent,
    NewLoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    BaseChartDirective,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [provideCharts(withDefaultRegisterables()), {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  bootstrap: [AppComponent,CategoryComponent,ProductComponent,ServicesService]
})
export class AppModule { }
