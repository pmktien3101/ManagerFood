package model;

import java.util.ArrayList;

public class ShoppingCart extends ArrayList<Cart> {
    private double total;

    public double getTotal() {
        return total;
    }

    @Override
    public boolean add(Cart e) {
        total += e.getSubtotal();
        return super.add(e);
    }

    @Override
    public Cart set(int index, Cart element) {
        Cart old = this.get(index);
        total -= old.getSubtotal();
        total += element.getSubtotal();
        return super.set(index, element);
    }

    @Override
    public Cart remove(int index) {
        Cart removed = super.remove(index);
        total -= removed.getSubtotal();
        return removed;
    }

    public ShoppingCart() {
        total = 0;
    }

    public void recalculateTotal() {
        total = 0;
        for (Cart cart : this) {
            total += cart.getSubtotal();
        }
    }
}
