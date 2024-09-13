import { Component, OnInit } from '@angular/core';
import { OrderService } from '../services/order.service';
import jsPDF from 'jspdf';
import { OrderDetails } from '../orderDetails';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  userId: number = 1;
  customerName: string = '';
  customerEmail: string = '';
  productCategory: string = '';
  productName: string = '';
  productId: number = 0; 
  quantity: number = 0;
  price: number = 0;
  totalAmount: number = 0;
  orderItems: any[] = [];
  categories: any[] = [];
  products: any[] = [];
  orderSubmitted: boolean = false;

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.orderService.getCategories().subscribe((data: any[]) => {
      this.categories = data;
    });
  }

  onCategoryChange(): void {
    if (this.productCategory) {
      this.orderService.getProductsByCategoryId(this.productCategory).subscribe((data: any[]) => {
        this.products = data;
        this.productName = '';
        this.price = 0;
      });
    } else {
      this.products = [];
    }
  }

  onProductChange(): void {
    const selectedProduct = this.products.find(product => product.productName === this.productName);
    if (selectedProduct) {
      this.productId = selectedProduct.productId; 
      this.price = selectedProduct.productPrice;
      this.updateTotal();  
    } else {
      this.price = 0;
      this.updateTotal();  
    }
  }

  onQuantityChange(): void {
    this.updateTotal(); 
  }

  updateTotal() {
    this.totalAmount = parseFloat(
      this.orderItems.reduce((sum, item) => sum + item.total, 0).toFixed(2)
    );
  }

  addItem() {
    if (this.quantity <= 0) {
      alert('Quantity must be greater than 0');
      return;
    }
    const item = {
      category: this.productCategory,
      product: this.productName,
      productId: this.productId, 
      price: parseFloat(this.price.toFixed(2)),
      quantity: this.quantity,
      total: parseFloat((this.price * this.quantity).toFixed(2))
    };
    this.orderItems.push(item);
    this.totalAmount += item.total;
    this.totalAmount = parseFloat(this.totalAmount.toFixed(2)); 
    this.resetForm();
  }

  removeItem(index: number) {
    this.totalAmount -= this.orderItems[index].total;
    this.totalAmount = parseFloat(this.totalAmount.toFixed(2)); 
    this.orderItems.splice(index, 1);
  }

  submitOrder() {
    this.submitOrderDetails();
  }

  resetForm() {
    this.productCategory = '';
    this.productName = '';
    this.productId = 0;
    this.quantity = 0;
    this.price = 0;
    this.products = [];
    this.updateTotal();  
  }

  resetOrderSummary() {
    this.customerName = '';
    this.customerEmail = '';
    this.orderItems = [];
    this.totalAmount = 0;
  }

  submitOrderDetails() {
    if (this.orderItems.length > 0) {
      const orderRequest = {
        order: {
          userId: this.userId,  // Assuming you have a userId available or retrieve it from somewhere
          customerName: this.customerName,
          customerContact: this.customerEmail,
          totalAmount: this.totalAmount,
          date: new Date().toISOString().split('T')[0] 
        },
        orderDetails: this.orderItems.map(item => ({
          productId: item.productId,
          quantity: item.quantity,
          individualAmount: item.total
        }))
      };

      console.log(orderRequest);
      this.orderService.saveOrder(orderRequest).subscribe(
        response => {
          console.log('Order details submitted successfully:', response);
          this.generatePDF(); 
          this.resetOrderSummary();
        },
        error => {
          console.error('Error submitting order details:', error);
        }
      );
    } else {
      alert('Please add items to the order before submitting.');
    }
  }

  generatePDF() {
    if (this.orderItems.length > 0) {
      const doc = new jsPDF();

      doc.text('Bill', 10, 10);
      doc.text(`Name: ${this.customerName}`, 10, 20);
      doc.text(`Email: ${this.customerEmail}`, 10, 30);

      let y = 50;
      doc.text('Order Summary', 10, y);
      y += 10;
      this.orderItems.forEach(item => {
        doc.text(`Product: ${item.product}`, 10, y + 10);
        doc.text(`Price: ${item.price.toFixed(2)}`, 10, y + 20);
        doc.text(`Quantity: ${item.quantity}`, 10, y + 30);
        doc.text(`Total: ${item.total.toFixed(2)}`, 10, y + 40);
        y += 50;
      });
      y += 10;
      doc.text(`Total Amount: ${this.totalAmount.toFixed(2)}`, 10, y);

      doc.save('bill.pdf');
    } else {
      console.log('Please add items to the order before submitting.');
    }
  }
}
