package Ecommerce_APi;

import java.util.List;

public class Order {
	private List<Orderdetails>orders;

	public List<Orderdetails> getOrders() {
		return orders;
	}

	public void setOrders(List<Orderdetails> orders) {
		this.orders = orders;
	}

}
