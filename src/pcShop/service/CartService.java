package pcShop.service;

import pcShop.model.CartItem;
import pcShop.model.Product;
import pcShop.model.User;

import java.util.ArrayList;
import java.util.List;

public class CartService implements IGenericService<CartItem, Integer> {
    private User userLogin;
    private UserService userService;

    public CartService(User userLogin) {
        this.userLogin = userLogin;
        userService = new UserService();
    }
    /**
     * Trả về danh sách các CartItem trong giỏ hàng của người dùng đang đăng nhập.
     *
     * @return Danh sách CartItem trong giỏ hàng
     */
    @Override
    public List<CartItem> findAll() {
        return userLogin.getCart();
    }
    /**
     * Lưu một CartItem vào giỏ hàng của người dùng đang đăng nhập.
     *
     * @param cartItem CartItem cần lưu
     */
    @Override
    public void save(CartItem cartItem) {
        List<CartItem> cart = userLogin.getCart();
        if (findById(cartItem.getId()) == null) {
            // cartitem thêm mới
            CartItem ci = findByProductId(cartItem.getProduct().getProductId());
            if (ci != null) {
                // đã có sp này trong giỏ hàng
                ci.setQuantity(ci.getQuantity() + cartItem.getQuantity());
            } else {
                // chưa có sp trong giỏ hàng
                cart.add(cartItem);
            }
            // lưu vào DB
            userService.save(userLogin);
        }
    }
    /**
     * Xóa một CartItem khỏi giỏ hàng của người dùng đang đăng nhập.
     *
     * @param id ID của CartItem cần xóa
     * @return
     */
    @Override
    public Product delete(Integer id) {
        userLogin.getCart().remove(findById(id));
        userService.save(userLogin);
        return null;
    }
    /**
     * Tìm kiếm một CartItem theo ID.
     *
     * @param id ID của CartItem cần tìm kiếm
     * @return CartItem tìm được, hoặc null nếu không tìm thấy
     */
    @Override
    public CartItem findById(Integer id) {
        for (CartItem ci : userLogin.getCart()) {
            if (ci.getId() == id) {
                return ci;
            }
        }
        return null;
    }
    /**
     * Tìm kiếm một CartItem theo ID sản phẩm.
     *
     * @param productId ID sản phẩm của CartItem cần tìm kiếm
     * @return CartItem tìm được, hoặc null nếu không tìm thấy
     */
    public CartItem findByProductId(int productId) {
        for (CartItem ci : userLogin.getCart()) {
            if (ci.getProduct().getProductId() == productId) {
                return ci;
            }
        }
        return null;
    }
    /**
     * Trả về ID mới cho CartItem.
     *
     * @return ID mới cho CartItem
     */
    public int getNewId() {
        int max = 0;
        for (CartItem ci : userLogin.getCart()) {
            if (ci.getId() > max){
                max = ci.getId();
            }
        }
        return max+1;
    }
    /**
     * Xóa tất cả CartItem trong giỏ hàng của người dùng đang đăng nhập.
     */
    public void clearAll(){
    userLogin.setCart(new ArrayList<>());
    userService.save(userLogin);
    }
}
