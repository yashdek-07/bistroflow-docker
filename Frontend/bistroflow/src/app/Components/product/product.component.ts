

import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../services/product.service';

import { CategoryService } from '../../services/category.service';
import { Product } from '../../Models/product';
import { switchMap } from 'rxjs';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms'; 
import { Category } from '../../Models/category';

@Component({
  selector: 'app-product',
  
  //imports: [CommonModule , HttpClientModule,FormsModule,RouterModule],
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
  providers : [ProductService,CategoryService]
})
export class ProductComponent implements OnInit {
  products: Product[] = [];
  categories: Category[] = [];
  newProduct: Product = { productId: 0, productName: '', productDescription: '', productPrice: 0, category: null };
  isEdit: boolean = false;
  currentProductId: number = 0;

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.loadProducts();
    this.loadCategories();
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.isEdit = true;
        this.productService.getProductById(+id).subscribe(
          product => {
            this.newProduct = product;
          },
          error => console.error('Error loading product', error)
        );
      }
    });
  }

  loadProducts(): void {
    this.productService.getAllProducts().subscribe(
      (data: Product[]) => {this.products = data
        console.log(data);
      },
      
      (error: any) => console.error(error)
    );
  }

  loadCategories(): void {
    this.categoryService.getAllCategories().subscribe(
      (data: Category[]) => (this.categories = data),
      (error: any) => console.error(error)
    );
  }

  createProduct(): void {
    this.productService.createProduct(this.newProduct).subscribe(
      () => this.router.navigate(['/products']),
      (error: any) => console.error(error)
    );
  }

  updateProduct(): void {
    this.isEdit = true;
    if (this.newProduct.productId) {
      this.productService.updateProduct(this.newProduct.productId, this.newProduct).subscribe(
        () => this.router.navigate(['/products']),
        (error: any) => console.error(error)
      );
    }
  }

  deleteProduct(id: number): void {
    if (confirm('Are you sure you want to delete this product?')) {
      this.productService.deleteProduct(id).subscribe(
        () => this.loadProducts(),
        (error: any) => console.error(error)
      );
    }
  }

  selectCategory(category: Category): void {
    this.newProduct.category = category;
  }
}