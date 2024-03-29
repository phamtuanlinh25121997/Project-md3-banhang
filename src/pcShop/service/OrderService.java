package pcShop.service;

import pcShop.model.Order;
import pcShop.util.DataBase;
import pcShop.view.Navbar;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private DataBase<Order> orderData;
    private List<Order> orders;

    public OrderService() {
        orderData = new DataBase<>();
        orders = orderData.readFromFile(DataBase.ORDER_PATH);
    }

    public List<Order> findAll() {
        return orders;
    }

    public void save(Order o) {
        if (findById(o.getId()) == null) {
            // add
            orders.add(o);
        } else {
            // update
            orders.set(orders.indexOf(findById(o.getId())), o);
        }
        // Lưu vào file
        orderData.writeToFile(orders, DataBase.ORDER_PATH);
    }

    public Order findById(int id) {
        for (Order o : findOrderByUserId()) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }

    public Order findALlById(int id) {
        for (Order allOrder : orders) {
            if (allOrder.getId()==id) {
                return allOrder;
            }
        }
        return null;
    }
    public List<Order> findOrderByUserId(){
        List<Order> findList = new ArrayList<>();
        for (Order o : orders){
            if (o.getUserId()== Navbar.userLogin.getId()){
                findList.add(o);
            }
        }
        return findList;
    }
    public int getNewId() {
        int max = 0;
        for (Order o : orders) {
            if (o.getId() > max) {
                max = o.getId();
            }
        }
        return max + 1;
    }
}
