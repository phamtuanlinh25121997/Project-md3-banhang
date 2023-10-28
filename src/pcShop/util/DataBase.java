package pcShop.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase<T> {
    public static final String USER_PATH = "C:\\Users\\Asus\\IdeaProjects\\MD3-Project-BanMayTinh\\src\\pcShop\\util\\users.txt";
    public static final String PRODUCT_PATH = "C:\\Users\\Asus\\IdeaProjects\\MD3-Project-BanMayTinh\\src\\pcShop\\util\\products.txt";
    public static final String ORDER_PATH = "C:\\Users\\Asus\\IdeaProjects\\MD3-Project-BanMayTinh\\src\\pcShop\\util\\order.txt";
    public static final String TRADEMARH_PATH = "C:\\Users\\Asus\\IdeaProjects\\MD3-Project-BanMayTinh\\src\\pcShop\\util\\trademarh.txt";

    /**
     * Ghi danh sách đối tượng vào file.
     * @param o    Danh sách đối tượng cần ghi vào file
     * @param path Đường dẫn file
     */
    public void writeToFile(List<T> o, String path) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Đọc danh sách đối tượng từ file.
     * @param path Đường dẫn file
     * @return Danh sách đối tượng được đọc từ file
     */
    public  List<T> readFromFile(String path){
        FileInputStream fis  = null;
        ObjectInputStream ois =null;
        List<T> o = new ArrayList<>();
        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            o = (List<T>) ois.readObject();
        }catch (EOFException xx){

        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if(ois != null){
                    ois.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            try{
                if(fis != null){
                    fis.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return o;
    }
}
