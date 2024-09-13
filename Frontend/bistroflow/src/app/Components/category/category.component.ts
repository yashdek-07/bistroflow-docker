
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CategoryService } from '../../services/category.service';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from '../../Models/category';


@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
  //imports: [CommonModule, HttpClientModule, FormsModule,RouterModule],
  providers:[CategoryService]
})
export class CategoryComponent implements OnInit {
  categories: Category[] = [];
  newCategory: Category = { id: 0, categoryName: '' };
  isEdit: boolean = false;

  constructor(
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadCategories();
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.isEdit = true;
        this.categoryService.getCategoryById(+id).subscribe(
          category => {
            this.newCategory = category;
          },
          error => console.error('Error loading category', error)
        );
      }
    });
  
  }

  loadCategories(): void {
    this.categoryService.getAllCategories().subscribe(
      (data: Category[]) => {
        // this.categories = data
        // console.log(data);
        // console.log("in class",this.categories);
        this.categories = data;
      },
      (error: any) => console.error(error)
    );
  }

  createCategory(): void {
    this.categoryService.createCategory(this.newCategory).subscribe(
      () => {
        this.loadCategories();
        this.newCategory = { id: 0, categoryName: '' };
      },
      (error: any) => console.error('Error creating category', error)
    );
  }
  updateCategory(): void {
    if (this.newCategory.id) {
      this.categoryService.updateCategory(this.newCategory.id, this.newCategory).subscribe(
        () => {
          this.loadCategories();
          this.newCategory = { id: 0, categoryName: '' };
          this.router.navigate(['/categories']);
        },
        (error: any) => console.error('Error updating category', error)
      );
    } else {
      console.error('Category ID is required for updating');
    }
  }
  
  deleteCategory(id: number): void {
    if (id) {
      this.categoryService.deleteCategory(id).subscribe(
        () => {
          this.loadCategories();
        },
        (error: any) => console.error('Error deleting category', error)
      );
    } else {
      console.error('Category ID is undefined or invalid');
    }
  }
  navigateToUpdate(id: number): void {
  console.log('Attempting to navigate with Category ID:', id);
  this.isEdit = true;
    this.router.navigate(['/update-category', id]);
}
}