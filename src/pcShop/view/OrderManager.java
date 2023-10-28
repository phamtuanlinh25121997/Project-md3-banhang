package pcShop.view;

import pcShop.config.InputMethods;
import pcShop.controller.OrderController;
import pcShop.controller.ProductController;
import pcShop.model.CartItem;
import pcShop.model.Order;
import pcShop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private OrderController orderController;


    public OrderManager() {
        orderController = new OrderController();
        while (true) {
            System.out.println("╔═════════════════════════════════════════════════╗");
            System.out.println("║                                                 ║");
            System.out.println("║                Lịch sử Đơn hàng                 ║");
            System.out.println("║                                                 ║");
            System.out.println("╠═══════╦═════════════════════════════════════════╣");
            System.out.println("║       │                                         ║");
            System.out.println("║   1   │   Hiển thị tất cả Đơn hàng              ║");
            System.out.println("║   2   │   Hiển thị Đơn hàng chờ xác nhận        ║");
            System.out.println("║   3   │   Hiển thị Đơn hàng đã chấp nhận        ║");
            System.out.println("║   4   │   Hiển thị Đơn hàng đã hủy              ║");
            System.out.println("║   5   │   Hiển thị Chi tiết Đơn hàng            ║");
            System.out.println("║   6   │   Quay lại                              ║");
            System.out.println("║       │                                         ║");
            System.out.println("╚═══════╩═════════════════════════════════════════╝");
            System.out.println("Nhập lựa chọn của bạn");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    // hiển thị tất cả
                    showAllOrder();
                    break;
                case 2:
                    // chờ xác nhận
                    showOrderByCode((byte) 0);
                    break;
                case 3:
                    showOrderByCode((byte) 1);
                    break;
                case 4:
                    showOrderByCode((byte) 2);
                    break;
                case 5:
                    // chi tiết hóa đơn
                    showOrderDetail();
                    break;
                case 6:
                    break;
                default:
                    System.err.println("Vui lòng nhập số từ 0 đến 5");
            }
            if (choice == 0) {
                break;
            }
        }
    }

    public OrderManager(OrderController orderController) {
        this.orderController = orderController;
        while (true) {
            Navbar.menuOrderConfirmManager();
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    // hiển thị tất cả các đơn đặt hàng của khách hàng
                    showAllOrderAllUser();
                    break;
                case 2:
                    // xác nhận trạng thái đơn hàng
                    OrderComfirm();
                    break;
                case 3:
                    //quay lại
                    Navbar.menuAdmin();
                    break;
                default:
                    System.err.println("Nhập lựa chọn từ 1 đến 3");
            }

        }
    }
    public  void  showAllOrderAllUser(){
        for (Order od:  orderController.findAll()
        ) {
            System.out.println("\u001B[34m ————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————— ");
            System.out.println(od);
            System.out.println("\u001B[34m ————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————— ");
        }
    }

    public  void OrderComfirm(){
        System.out.println("Nhâp mã đơn hàng");
        int ip=InputMethods.getInteger();
        Order comfirmOrder = orderController.findALlById(ip) ;
        if (comfirmOrder == null){
            System.err.println("không tìm thấy mã đơn hàng");

        } else if (comfirmOrder.getStatus()==0) {
            System.out.println("Bạn muốn xác nhận đơn hàng này chứ ?");
            System.out.println("1. Có ✅");
            System.out.println("2. Không  ❌");
            int choice =InputMethods.getInteger();
            if (choice==1){
                System.out.println("Đã xác nhận ✅");
                comfirmOrder.setStatus((byte) 1);
                orderController.save(comfirmOrder);
            }
        }

    }

    public void showAllOrder() {
        List<Order> list = orderController.findOrderByUserId();

        if (list.isEmpty()) {
            System.err.println("Lịch sử đơn hàng trống");
            return;
        }
        for (Order o : list) {
            System.out.println(o);
        }
    }
    public void showOrderByCode(byte code) {
        List<Order> orders = orderController.findOrderByUserId();
        List<Order> filter = new ArrayList<>();
        for (Order o : orders) {
            if (o.getStatus() == code) {
                filter.add(o);
            }
        }
        if (filter.isEmpty()) {
            System.err.println("Đơn hàng trống");
            return;
        }
        for (Order o : filter) {
            System.out.println(o);
        }
    }

    public void showOrderDetail() {
        ProductController productController = new ProductController();
        System.out.println("Nhập mã đơn hàng");
        int orderId = InputMethods.getInteger();
        Order order = orderController.findById(orderId);
        if (order == null) {
            System.err.println("không tìm thấy mã đơn hàng");
            return;
        }

        // in ra chi tiết hóa đơn
        System.out.printf("———————————————————————————————Chi tiết Đơn hàng————————————————————————————\n");
        System.out.printf("                               Mã đơn hàng : %3d \n", order.getId());
        System.out.println("———————————————————————————————————Thông tin————————————————————————————————");
        System.out.print("Người nhận : " + order.getReceiver() + " | Điện thoại : " + order.getPhoneNumber() + "\n");
        System.out.println("Email : " +order.getEmail() + " Địa chỉ : " + order.getAddress());
        System.out.println("———————————————————————————————————Chi tiết—————————————————————————————————");
        for (CartItem ci : order.getOrderDetail()) {
            System.out.println(ci);
        }
        System.out.println("Tổng cộng : " + order.getTotal());
        System.out.println("———————————————————————————————————Kết thúc—————————————————————————————————");
        if (order.getStatus() == 0) {

            System.out.println("Bạn chắc chắn muốn hủy đơn hàng này?");
            System.out.println("1. Có ✅");
            System.out.println("2. Không ❌");
            System.out.println("Nhập lựa chọn của bạn");

            int choice = InputMethods.getInteger();
            if (choice == 1) {
                System.out.println("Đơn hàng đã bị huỷ");
                for (CartItem cartItem : order.getOrderDetail()) {
                    Product product = productController.findById(cartItem.getProduct().getProductId());
                    product.setStock(product.getStock() + cartItem.getQuantity());
                    productController.save(product);
                }
                order.setStatus((byte) 2);
                orderController.save(order);
            }
        }
    }
}