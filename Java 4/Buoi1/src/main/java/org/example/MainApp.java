package org.example;
import java.util.List;
public class MainApp {
    public static void main(String[] args) {
        NhanVienRepository repo = new NhanVienRepository();
        List<SimpleUser> dssu = repo.getAllSimple();
        List<Contact> ct = repo.getAllContact();
        List<Product> pd = repo.getAllProduct();
        List<Task> t = repo.getAllTask();
        List<NewsletterSubscriber> nls = repo.getAllNewsletterSubscribers();


        System.out.println("---------------------------------------");
        for (SimpleUser nv : dssu) {
            System.out.println("ID: " + nv.getId() + " | "
                    + "Username: " + nv.getUsername() + " | "
                    + "Email: " + nv.getEmail() + " | ");
        }

        System.out.println("---------------------------------------");
        for (Contact nv : ct) {

            System.out.println("ID: " + nv.getId() + " | "
                    + "Username: " + nv.getFull_name() + " | "
                    + "Email: " + nv.getEmail() + " | "
                    + "Birth_Date: " + nv.getBirth_date() + " | ");
        }

        System.out.println("---------------------------------------");
        for (Product nv : pd) {

            System.out.println("ID: " + nv.getId() + " | "
                    + "Name: " + nv.getName() + " | "
                    + "Price: " + nv.getprice() + " | "
                    + "In_stock: " + nv.getIn_stock() + " | ");
        }

        System.out.println("---------------------------------------");
        for (Task nv : t) {

            System.out.println("ID: " + nv.getId() + " | "
                    + "Title: " + nv.getTitle() + " | "
                    + "Description: " + nv.getDescription() + " | "
                    + "Status: " + nv.getStatus() + " | "
                    + "Due_date: " + nv.getDue_date() + " | ");
        }

        System.out.println("---------------------------------------");
        for (NewsletterSubscriber nv : nls) {
            System.out.println("ID: " + nv.getId() + " | "
                    + "Email: " + nv.getEmail() + " | "
                    + "Subscribed: " + nv.getSubscribed() + " | "
                    + "Subscribed_at: " + nv.getSubscribed_at() + " | ");
        }
    }
}
