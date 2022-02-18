package com.example.f_project_version_1;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@RestController
public class ProductController {
    String Totall[]=new String[1];
    String keywordshere[] =new String[10];

    @Autowired
    private ProductRepository repository1;

    @Autowired
    private BasketRepository repository2;







    @GetMapping("/calculateTotal")
    public String[] getTotal(){

        Integer Totalint2=0;
        for(Basket bas:repository2.findAll()) {
            Totalint2 = Totalint2 + bas.getPrice();
        }
        Totall[0]=Totalint2.toString();

        return Totall;
    }

    @GetMapping("/productlist")
    public Iterable<Product> getProducts() {

        repository1.deleteAll();

        File proionta = new File("products.txt");
        Set<Product> products = new HashSet<>();


        try {
            Scanner scan_input = new Scanner(proionta);


            while (scan_input.hasNextLine()) {
                String line = scan_input.nextLine();
                String[] array_line = line.split(",");
                int i_1 = Integer.parseInt(array_line[2]);
                int i_2 = Integer.parseInt(array_line[4]);
                int i_3 = Integer.parseInt(array_line[5]);
                int i_4 = Integer.parseInt(array_line[6]);

                //System.out.println(i_1+"  "+i_2+"  "+i_3+"   "+i_4);
                products.add(new Product(array_line[0], array_line[1], i_1, array_line[3], i_2, i_3, i_4));


            }
        } catch (
                FileNotFoundException e) {
            System.out.println(" file not found ");
        }

        Product[] products_t = products.stream().toArray(Product[]::new); //kathe cell tou table ->products_t antistixi me object tipou Product
        for (int i = 0; i < products_t.length; i++) {
            repository1.save(products_t[i]);
        }
        return repository1.findAll();
    }
    @Test
    @DisplayName("All positive prices")
    void allPositivePrices( ){
        File proionta = new File("products.txt");
        Set<Product> products = new HashSet<>();


        try {
            Scanner scan_input = new Scanner(proionta);


            while (scan_input.hasNextLine()) {
                String line = scan_input.nextLine();
                String[] array_line = line.split(",");
                int i_1 = Integer.parseInt(array_line[2]);
                int i_2 = Integer.parseInt(array_line[4]);
                int i_3 = Integer.parseInt(array_line[5]);
                int i_4 = Integer.parseInt(array_line[6]);

                //System.out.println(i_1+"  "+i_2+"  "+i_3+"   "+i_4);
                products.add(new Product(array_line[0], array_line[1], i_1, array_line[3], i_2, i_3, i_4));


            }
        } catch (
                FileNotFoundException e) {
            System.out.println(" file not found ");
        }
        int size=0;
        Product[] products_t = products.stream().toArray(Product[]::new);
        for(Product x:products_t){
            if(x.getPrice()>0)
                size++;
            System.out.println(x.getPrice());
        }
        System.out.println("Number of positive prices:"+ size);
        assertTrue(size==16);
    }

    @GetMapping("/addproduct")
    public RedirectView addproduct(@RequestParam final String name, String color, Integer price) {

        Basket b = new Basket(name, color, price);
        repository2.save(b);
        return new RedirectView("shop.html");
    }


    @GetMapping("/addproduct2")
    public RedirectView addproduct2(@RequestParam final String name, String color, Integer price) {

        Basket b = new Basket(name, color, price);
        repository2.save(b);
        return new RedirectView("shop2.html");
    }
    @GetMapping("/deleteproduct")
    public RedirectView deleteProduct(@RequestParam Integer basketID) {
        repository2.deleteById(basketID);
        return new RedirectView("basket.html");

    }

    @GetMapping("/basket")
    public Iterable<Basket> getBasket(){

        return repository2.findAll();
    }

    @GetMapping("/sendEmailcontact")
    public RedirectView sendEmail(@RequestParam String name, String email, String subject, String message){

        SendEmail_Contact emailsender=new SendEmail_Contact();
        String subjectg ="Feedback email from: " + name +" With email: "  + email;
        String messageg="Subject of the email: " + subject + "<br>" + "Email content :" + message;

        messageg=messageg+"<br>";
        try {
            emailsender.createandSendMail(email,subjectg,messageg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return new RedirectView("contact.html");
    }
    @GetMapping("/checkbasket")
    public RedirectView checkBasket(){
        int total=0;
        //System.out.println("GOGO AERA");
        for(Basket x:repository2.findAll()){
            total+=x.getPrice();
            //System.out.println(x.getPrice());
        }
        if(total==0){
            return new RedirectView("/hehe.html");
        }
        else{
            return new RedirectView("/completepage.html");
        }
    }
    @GetMapping("/sendEmailcomplete")
    public RedirectView sendEmail(@RequestParam String client_email, String client_password){
        if(client_password==null){

        }
        SendEmail_Complete emailsender=new SendEmail_Complete();
        String name_t;
        String color_t;
        Integer price_t;
        int items =0;
        String subject="Order Confirmation";
        int id_t;
        String messageg="Thank your for your purchase<br> You Bought the following items:<br><br>";
        for( Basket basket:repository2.findAll()){
            items++;
            id_t=basket.getBasketID();
            name_t=basket.getName();
            color_t=basket.getColor();
            price_t=basket.getPrice();
            String strr="";


            messageg=messageg+"Product: "+name_t +  " - " +    "Color:" + color_t +  "         -         " + "    Price: "+ price_t + " euros"+"<br>";
        }
        messageg=messageg+"Total Items: "+ items +"<br>";
        messageg=messageg+"Total Cost:"+Totall[0]+ " euros"+"<br>";
        messageg=messageg+"<br>";
        try {
            emailsender.createandSendMail(client_email,subject,messageg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        repository2.deleteAll();
        return new RedirectView("");
    }





}





