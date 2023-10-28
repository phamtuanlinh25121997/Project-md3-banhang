package pcShop.model;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int id;
    private Product product;
    private int quantity;

    public CartItem() {
    }

    public CartItem(int id, Product product, int quatity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    @Override
//    public String toString() {
//        return "Mã giỏ hàng : "+id + "| Tên sản phẩm : " +product.getProductName()+ " | Giá : "+product.getProductPrice() +" VND "+ "| Số lượng : "+quantity + " Máy ";
//    }
@Override
public String toString() {
    return "| Mã giỏ hàng | Tên sản phẩm | Giá (VND) | Số lượng |\n"
            + "| --- | --- | --- | --- |\n"
            + "| " + id + " | " + product.getProductName() + " | " + product.getProductPrice() + " | " + quantity + " máy |\n";
}

}
