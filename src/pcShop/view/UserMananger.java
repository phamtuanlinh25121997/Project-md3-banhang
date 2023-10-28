package pcShop.view;

import pcShop.config.InputMethods;
import pcShop.model.User;
import pcShop.controller.UserController;

public class UserMananger {
    private UserController userController;

    public UserMananger(UserController userController) {
        this.userController = userController;
        while (true) {
            Navbar.menuAccountManager();
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    showAllAccount();
                    break;
                case 2:
                    changeStatus();
                    break;
                case 3:
                    Navbar.menuAdmin();
                    break;
                default:
                    System.err.println("Hãy nhập số từ 1 đến 4");
            }
        }
    }

    public void showAllAccount() {
        for (User u : userController.findAll()) {
            System.out.println("-------------------------------------------");
            System.out.println(u);
        }
    }

    public void changeStatus() {
        // lấy ra userlogin để check quyền xem có được quyền khóa tài khoản kia không
        System.out.println("Nhập mã tài khoản");
        int id = InputMethods.getInteger();
        if (id ==1){
            System.err.println("Mã tài khoản không tồn tại");
            return;
        }
        User user = userController.findById(id);
        if (user == null) {
            System.err.println("Mã tài khoản không tồn tại");
        } else {
            if (user.isStatus() == false){
                System.out.println("Mở khoá thành công \uD83D\uDD13");
                user.setStatus(true);
                userController.save(user);
                return;
            }
            user.setStatus(!user.isStatus());
            System.err.println("Đã khoá tài khoản \uD83D\uDD12");
            userController.save(user);
        }
    }
}
