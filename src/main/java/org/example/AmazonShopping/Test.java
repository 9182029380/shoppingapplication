package org.example.AmazonShopping;

import com.google.gson.Gson;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        Map<String, Product> products = new HashMap<>();
        initializeProducts(products);
        registerUser(users, "user1", "password1", "user1@gamil.com", "1234567890");
        registerUser(users, "user2", "password2", "user2@gamil.com", "0987654321");
        registerUser(users, "user3", "password3", "user3@gamil.com", "1111111111");
        registerUser(users, "user4", "password4", "user4@gamil.com", "2222222222");
        Scanner scanner = new Scanner(System.in);
        boolean continueShopping = true;
        while (continueShopping) {
            System.out.println("Enter username or phonenumber:");
            String inputUsernameorPhonumber = scanner.nextLine();
            System.out.println("Enter password:");
            String inputPassword = scanner.nextLine();
            boolean loggedIn = false;
            try {
                User currentuser = loginUser(users, inputUsernameorPhonumber, inputPassword);
                if (currentuser != null) {
                    System.out.println("Login successful");
                    ArrayList<CartItem> cart = new ArrayList<CartItem>();
                    addToCart(scanner, products, cart);
                    System.out.println("user information ");
                    System.out.println(userToJson(currentuser));
                    System.out.println("cart information ");
                    System.out.println(cartToJson(cart));
                    System.out.println("Doyou want to continue shopping (yes/no): ");
                    String continueShoppingInput = scanner.nextLine().toLowerCase();
                    if (!continueShoppingInput.equals("yes")) {
                        continueShopping = false;
                    }
                } else {
                    System.out.println("invalid username or password login failed");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("thanks you shopping with us");
    }

    private static void registerUser(List<User> users, String username, String password, String email, String
            phonenumber) {
        users.add(new User(username, password, email, phonenumber));
    }

    private static void initializeProducts(Map<String, Product> products) {
        products.put("product1", new Product("product1", 300, 100));
        products.put("product2", new Product("product2", 400, 200));
        products.put("product3", new Product("product3", 60, 300));
        products.put("product4", new Product("product4", 70, 400));
    }


    private static String cartToJson(ArrayList<CartItem> cart) {
        Gson gson = new Gson();
        return  gson.toJson(cart);


    }

    private static String userToJson(User currentuser) {
        Gson gson = new Gson();
        return  gson.toJson(currentuser);
    }

    private static void addToCart(Scanner scanner, Map<String, Product> products, ArrayList<CartItem> cart) {
        boolean continueAdding=true;
        while (continueAdding) {
            System.out.println("Available  products:");
            for(Map.Entry<String,Product> entry:products.entrySet()){
                System.out.println(entry.getKey()+" "+entry.getValue().getPrice());
            }
            System.out.println("Enter product name to add to the cart:");
            String productName = scanner.nextLine();
            if (products.containsKey(productName)) {
                System.out.println("Enter quantity:");
                int quantity = Integer.parseInt(scanner.nextLine());
                cart.add(new CartItem(products.get(productName), quantity));
                //System.out.println("Product added to cart successfully");
            } else {
                System.out.println(" product was not found ");
            }
            System.out.println("Do you want to continue adding to the cart (yes/no):");
            String addMore = scanner.nextLine().toLowerCase();
            if(!addMore.equals("yes")){
                continueAdding=false;
            }
            //(continueAdding

        }
    }

    private static User loginUser(List<User> users, String inputUsernameorPhonumber, String inputPassword) {
        for (User user : users) {
            if ((user.getUsername().equals(inputUsernameorPhonumber) || user.getPhoneNumber().equals(inputUsernameorPhonumber))
                    && user.getPassword().equals(inputPassword)) {
                return user;
            }
        }
        return null;
    }
}


