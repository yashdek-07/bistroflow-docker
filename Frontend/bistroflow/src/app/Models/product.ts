// import { Category } from "./category";

import { Category } from "./category";


export interface Product {
  productId: number;
  productName: string;
  productDescription: string;
  productPrice: number;
  category : Category | null;
}
