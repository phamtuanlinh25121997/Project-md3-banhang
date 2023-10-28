package pcShop.controller;

import pcShop.model.User;
import pcShop.service.UserService;

import java.util.List;

public class UserController {
    private UserService userService;

    //Khởi tạo một đối tượng UserService.
    public UserController() {
        userService = new UserService();
    }

    // Trả về danh sách tất cả các User có trong hệ thống.
    public List<User> findAll() {
        // danh sách User
        return userService.findAll();
    }

    // Lưu một User vào hệ thống.
    public void save(User user) {
        // user User cần được lưu
        userService.save(user);
    }

    // Xóa một User dựa trên ID.
    public void delete(Integer id) {
        // id ID của User cần xóa
        userService.delete(id);
    }

    // Tìm kiếm một User dựa trên ID.
    public User findById(Integer id) {
        //id ID của User cần tìm
        //User tìm thấy, hoặc null nếu không tìm thấy
        return userService.findById(id);
    }
    // Thay đổi trạng thái của một User dựa trên ID.
    public void changeStatus(int idUser) {
        // idUser ID của User cần thay đổi trạng thái
        userService.changeStatus(idUser);
    }


    // Đăng nhập User với username và password.
    public User login(String username, String password) {
        // User tìm thấy dựa trên thông tin đăng nhập, hoặc null nếu không tìm thấy
        // username username của User
        // password password của User
        return userService.login(username, password);
    }

    // Trả về ID mới nhất để sử dụng cho một User mới.
    public int getNewId() {
        // ID mới nhất
        return userService.getNewId();
    }
}
