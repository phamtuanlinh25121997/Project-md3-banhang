package pcShop.service;

import pcShop.config.Constants;
import pcShop.model.Product;
import pcShop.model.User;
import pcShop.util.DataBase;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IGenericService<User, Integer> {
    private List<User> users;
    private DataBase<User> userData = new DataBase<>();

    public UserService() {
        List<User> list = userData.readFromFile(DataBase.USER_PATH);
        if (list == null) {
            list = new ArrayList<>();
        }
        this.users = list; // Dữ liệu đọc từ file
    }

    // Trả về danh sách tất cả người dùng.
    @Override
    public List<User> findAll() {
        // Danh sách tất cả người dùng
        return users;
    }

    // Lưu thông tin người dùng.
    @Override
    public void save(User user) {
        if (findById(user.getId()) == null) {
            // Thêm mới người dùng
            users.add(user);
        } else {
            // Cập nhật người dùng
            users.set(users.indexOf(findById(user.getId())), user);
        }
        // Lưu vào file
        userData.writeToFile(users, DataBase.USER_PATH);
    }

    // Xóa người dùng dựa trên ID.
    @Override
    public Product delete(Integer id){
        users.remove(findById(id));
        userData.writeToFile(users,DataBase.USER_PATH);
        return null;
    }

    // Tìm kiếm người dùng dựa trên ID.
    @Override
    public User findById(Integer id) {
        for (User u : users) {
            //id ID của người dùng cần tìm kiếm
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
        //Người dùng tìm được, hoặc null nếu không tìm thấy
    }

    // Thay đổi trạng thái (khoá/mở khoá) của người dùng dựa trên ID.
    public void changeStatus(int idUser) {
        User user = findById(idUser);
        if (user == null) {
            System.err.println(Constants.NOT_FOUND);
            return;
        }
        user.setStatus((!user.isStatus()));
        save(user);
    }

    // Đăng nhập người dùng với tên người dùng và mật khẩu.
    public User login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
        // Người dùng đăng nhập thành công, hoặc null nếu không đăng nhập thành công
    }

    // Trả về ID mới cho người dùng.
    public int getNewId() {
        int max = 0;
        for (User u : users) {
            if (u.getId() > max) {
                max = u.getId();
            }
        }
        return max + 1;
        // ID mới cho người dùng
    }
}
