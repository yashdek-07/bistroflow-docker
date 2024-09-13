export class UserOrderCount {
    userId: number;
    userName: string;
    ordersSold: number;

    constructor(userId: number = 0, userName: string = '', ordersSold: number = 0) {
        this.userId = userId;
        this.userName = userName;
        this.ordersSold = ordersSold;
      }
}