import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    public static final String PRODUCTS_DAT = "products.dat";

    public static void main(String[] args) {
        Product p1 = new Product("1", "Iphone 12 Pro Max", "Apple", 32990000, "128Gb");
        Product p2 = new Product("2", "Galaxy Z Fold3 5G", "Samsung", 41990000, "256Gb");
        Product p3 = new Product("3", "Xiaomi 11T Pro 5G", "Xiaomi", 14990000, "");

        addProductToFile(PRODUCTS_DAT, p1);
        addProductToFile(PRODUCTS_DAT, p2);
        addProductToFile(PRODUCTS_DAT, p3);

        List<Product> products1 = readFromFile(PRODUCTS_DAT);
        for (Product p : products1) {
            System.out.println(p);
        }
        System.out.println();

        String searchName = "iphone";
        System.out.println("Kết quả tìm kiếm từ khóa: "+ searchName);
        List<Product> searchResult = searchProductInFileByName(PRODUCTS_DAT, searchName);
        if (searchResult.size() == 0){
            System.out.println("Không tìm thấy kết quả nào.");
        } else {
            for (Product product : searchResult)
                System.out.println(product);
        }
    }

    public static List<Product> searchProductInFileByName(String fileName, String searchName){
        List<Product> products = readFromFile(fileName);
        return searchProductByName(products, searchName);
    }

    public static List<Product> searchProductByName(List<Product> products, String searchName){
        List<Product> searchResult = new ArrayList<>();
        for (int i = 0; i < products.size(); i++){
            boolean isFound = products.get(i).getName().toLowerCase().contains(searchName.toLowerCase());
            if (isFound){
                searchResult.add(products.get(i));
            }
        }
        return searchResult;
    }

    public static void addProductToFile(String fileName, Product product){
        List<Product> products;

        File file = new File(fileName);
        if (file.exists()){
            products = readFromFile(fileName);
        } else {
            products = new ArrayList<>();
        }

        products.add(product);
        writeToFile(fileName, products);
    }

    public static void writeToFile(String fileName, List<Product> products) {
        try {
            OutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(products);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> readFromFile(String fileName) {
        List<Product> list = null;

        try {
            InputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (List<Product>) ois.readObject();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return list;
    }

}
