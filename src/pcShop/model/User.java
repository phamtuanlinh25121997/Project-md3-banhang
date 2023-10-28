package pcShop.model;

import pcShop.config.InputMethods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements Serializable {
    private int id; // ID của người dùng
    private String name; // Tên của người dùng
    private String username; // Tên đăng nhập của người dùng
    private String password; // Mật khẩu của người dùng
    private String address; // Địa chỉ của người dùng
    private String phoneNumber; // Số điện thoại của người dùng
    private boolean status = true; // Trạng thái của người dùng
    private Set<RoleName> roles = new HashSet<>(); // Danh sách các quyền của người dùng
    private List<CartItem> cart = new ArrayList<>(); // Giỏ hàng của người dùng

    public User() {
    }

    public User(int id, String name, String username, String password, String address, String phoneNumber, boolean status, Set<RoleName> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.roles = roles;
    }

    // Trả về ID của người dùng.
    public int getId() {
        return id;
    }

    // Thiết lập ID của người dùng.
    public void setId(int id) {
        this.id = id;
    }

    // Trả về tên của người dùng.
    public String getName() {
        return name;
    }

    // Thiết lập tên của người dùng.
    public void setName(String name) {
        this.name = name;
    }

    //Trả về tên đăng nhập của người dùng.
    public String getUsername() {
        return username;
    }

    // Thiết lập tên đăng nhập của người dùng.
    public void setUsername(String username) {
        this.username = username;
    }

    // Trả về mật khẩu của người dùng.
    public String getPassword() {
        return password;
    }

    // Thiết lập mật khẩu của người dùng.
    public void setPassword(String password) {
        this.password = password;
    }

    // Trả về địa chỉ của người dùng.
    public String getAddress() {
        return address;
    }

    // Thiết lập địa chỉ của người dùng.
    public void setAddress(String address) {
        this.address = address;
    }

    // Trả về số điện thoại của người dùng.
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Thiết lập số điện thoại của người dùng.
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Trả về trạng thái của người dùng.
    public boolean isStatus() {
        return status;
    }

    // Thiết lập trạng thái của người dùng.
    public void setStatus(boolean status) {
        this.status = status;
    }

    // Trả về danh sách các quyền của người dùng.
    public Set<RoleName> getRoles() {
        return roles;
    }

    // Thiết lập danh sách các quyền của người dùng.
    public void setRoles(Set<RoleName> roles) {
        this.roles = roles;
    }

    // Trả về giỏ hàng của người dùng.
    public List<CartItem> getCart() {
        return cart;
    }

    // Thiết lập giỏ hàng của người dùng.
    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }
    public void inputUserData(){
        System.out.println("Nhập mật khẩu mới");
        this.password = InputMethods.getpassword();
    }

    @Override
    public String toString() {
        return "Mã người dùng: "+ id + " | Tên : "+ name + "| Tên tài khoản : "+username   + "| Tình trạng : "+(status?"Mở khoá":"Khoá") ;
    }
}
