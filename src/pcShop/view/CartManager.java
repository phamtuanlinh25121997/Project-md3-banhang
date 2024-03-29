package pcShop.view;

import pcShop.config.Constants;
import pcShop.config.InputMethods;
import pcShop.controller.CartController;
import pcShop.controller.OrderController;
import pcShop.controller.ProductController;
import pcShop.model.CartItem;
import pcShop.model.Order;
import pcShop.model.Product;
import pcShop.model.User;

public class CartManager {
    private static CartController cartController;
    private ProductController productController;

    public CartManager() {
        productController = new ProductController();
        cartController = new CartController(Navbar.userLogin);
        while (true) {
            Navbar.menuCart();
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    // xem danh sách giỏ hàng
                    showCart();
                    break;
                case 2:
                    // chỉnh số lượng
                    changeQuantity();
                    break;
                case 3:
                    // xóa 1 item
                    deleteItem();
                    break;
                case 4:
                    // xóa hết
                    if (cartController.findAll().size() == 0) {
                        System.err.println("giỏ hàng rỗng");
                        return;
                    }
                    System.out.println("Đã xoá tất cả sản phẩm trong giỏ hàng  ✅");
                    cartController.clearAll();
                    break;
                case 5:
                    // tạo hóa đơn
                    checkout(productController);
                    break;
                case 6:
                    Navbar.menuUser();
                    break;
                default:
                    System.err.println("Vui lòng nhập số từ 0 đến 5");
            }
        }
    }

    public void showCart() {
        User userLogin = Navbar.userLogin;
        if (userLogin.getCart().isEmpty()) {
            System.err.println("Giỏ hàng rỗng");
            return;
        }
        for (CartItem ci : userLogin.getCart()
        ) {
            System.out.println(ci);
        }
    }

    public void changeQuantity() {
        if (cartController.findAll().size()==0){
            System.err.println("giỏ hàng rỗng");
            return;
        }
        System.out.println("Nhập mã sản phẩm  trong giỏ hàng");
        int cartItemID = InputMethods.getInteger();
        CartItem cartItem = cartController.findById(cartItemID);
        if(cartItem ==null){
            System.err.println("không tìm thấy mã giỏ hàng cần thay đổi");
            return;
        }
        System.out.println("Nhập số lượng");
        cartItem.setQuantity(InputMethods.getInteger());
        System.out.println("thay đổi số lượng thành công  ✅");
        cartController.save(cartItem);
    }

    public void deleteItem() {
        if (cartController.findAll().size()==0){
            System.err.println("giỏ hàng rỗng");
            return;
        }
        System.out.println("Nhập mã sản phẩm trong giỏ hàng");
        int cartItemID = InputMethods.getInteger();
        if (cartController.findById(cartItemID) == null) {
            System.err.println("không tìm thấy mã giỏ hàng cần xoá");
            return;
        }
        System.out.println("Đã xoá thành công  ✅");
        cartController.delete(cartItemID);
    }

    public void checkout(ProductController productController) {
        OrderController orderController = new OrderController();
        User userLogin = Navbar.userLogin;
        if(userLogin.getCart().isEmpty()){
            System.err.println("Giỏ hàng rỗng");
            return;
        }
        //  kiểm tra số lượng trong kho
        for (CartItem ci: userLogin.getCart()) {
            Product p = productController.findById(ci.getProduct().getProductId());
            if(ci.getQuantity() >p.getStock() ){
                System.err.println("Sản phẩm "+p.getProductName() + " chỉ còn "+ p.getStock() +" sản phẩm, vui lòng giảm số lượng");
                return;
            }
        }

        Order newOrder = new Order();
        newOrder.setId(orderController.getNewId());
        // coppy sp trong gior hàng sang hóa đơn
        newOrder.setOrderDetail(userLogin.getCart());
        // cập nhật tổng tiền
        double total = 0;
        for (CartItem ci: userLogin.getCart()) {
            total+= ci.getQuantity()*ci.getProduct().getProductPrice();
        }
        newOrder.setTotal(total);

        newOrder.setUserId(userLogin.getId());
        System.out.println("Nhập tên ");
        newOrder.setReceiver(InputMethods.getString());
        System.out.println("Nhập số điện thoại");
        newOrder.setPhoneNumber(InputMethods.getPhoneNumber());
        System.out.println("Nhập email");
        newOrder.setEmail(InputMethods.getEmailAddress());
        System.out.println("Nhập địa chỉ");
        newOrder.setAddress(InputMethods.getString());
        System.out.println("Thanh toán thành công  ✅   cảm ơn quý khách  \uD83D\uDE46");
        orderController.save(newOrder);
        // giảm số lượng đi
        for (CartItem ci: userLogin.getCart()) {
            Product p = productController.findById(ci.getProduct().getProductId());
            p.setStock(p.getStock()-ci.getQuantity());
            productController.save(p);
        }
        cartController.clearAll();
    }

    public static void addToCart() {
        cartController = new CartController(Navbar.userLogin);
        ProductController productController = new ProductController();
        System.out.println("Nhập mã sản phẩm");
        int proId = InputMethods.getInteger();
        for (Product checkproduct: productController.findAll()) {
            if (checkproduct.getStock() == 0 && checkproduct.getProductId() ==proId){
                System.err.println("Sản phẩm đã bán hết");
                return;
            }
        }
        Product pro = productController.findById(proId);
        if (pro == null) {
            System.err.println(Constants.NOT_FOUND);
            return;
        }
        CartItem cartItem = new CartItem();
        cartItem.setId(cartController.getNewId());
        cartItem.setProduct(pro);
        System.out.println("Nhập số lượng");
        cartItem.setQuantity(InputMethods.getPositiveInteger());
        System.out.println("Đã thêm sản phẩm vào giỏ hàng ✅");
        cartController.save(cartItem);
    }
}
