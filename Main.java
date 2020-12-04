//talha fareed 105038940
//asssignment 2
package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

abstract class Item {

    // protected to give access to other clases
    protected static int counter=1;
    protected int item_id=0;
    protected double price;
    protected int quantity;
    protected String title;

    public Item() {
        title="Default";
        quantity=0;
        price=0;
    }
    //Overloaded Constructor
    public Item(String title,int quantity,double price) {
        this();
        setTitle(title);
        setQuantity(quantity);
        setPrice(price);
        addId();
    }
    //Setter Methods
    //Sets title only if it is not empty
    public void setTitle(String title) {
        if(title.length()!=0) {
            this.title=title;
        }
    }
    //Sets quantity if it is a positive integer or 0
    public void setQuantity(int num) {
        if(num>=0) {
            this.quantity=num;
        }
    }
    //Sets quantity if it is a positive integer or 0
    public void setPrice(double price) {
        if(price >= 0) {
            this.price=price;
        }
    }
    //sets item_id and updates counter
    public void addId() {
        this.item_id=counter;
        counter++;
    }
    //Getter Methods
    public int getId() {
        return item_id;
    }
    public String getTitle() {
        return title;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }
    //Abstract Methods
    public abstract void Display();

    //Purchase method removes 1 from quantity if the quantity is greater than 0
    public void Purchased() {
        if(quantity>0) {
            this.quantity--;
        }
        else {
            System.out.println("Add another "+title+" to the cart to purchase!");
        }
    }


}

//Derived class of Item
class Book extends Item implements Comparable<Book>{
    private String author;
    private String title;
    private int year;

    //Overloaded Constructor
    public Book(int num, double price, String title, String author, int year) {
        super("Book",num,price);
        this.author = author;
        this.title=title;
        this.year=year;
    }
    //Getter Methods
    public String gettitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getYear() {
        return year;
    }

    //Override methods
    public void Display() {
        System.out.printf("%d     %-10s%-5d\t%-5.2f\t%s written by %s (%d)\n",super.item_id,super.title,super.quantity,super.price,this.title,this.author,this.year);

    }

    //Methods from interface Comparable
    public int compareTo(Book o) {
        return this.author.compareTo(o.author);
    }

}


//Derived class of Item
class GiftCard extends Item implements Comparable<GiftCard>{
    private String label;
    private String manufacturer;

    //Overloaded Constructor
    public GiftCard(int num, double price, String label, String manufacturer) {
        super("GiftCard",num,price);
        this.label=label;
        this.manufacturer=manufacturer;

    }
    //Getter Methods
    public String getLabel() {
        return label;
    }
    public String getManufacturer() {
        return manufacturer;
    }

    //Override methods
    public void Display() {
        System.out.printf("%d     %-10s%-5d\t%-5.2f\tLabel: %s Manufacturer: %s\n",super.item_id,super.title,super.quantity,super.price,this.label,this.manufacturer);
    }

    //Methods from interface Comparable
    public int compareTo(GiftCard o) {
        return this.label.compareTo(o.label);
    }

}


//Derived class of Item
class Shoe extends Item implements Comparable<Shoe>{
    private String colour;
    private double size;
    //Overloaded Constructor
    public Shoe(int num, double price, String colour, double size) {
        super("Shoe",num,price);
        this.colour=colour;
        this.size=size;
    }
    //Getter Methods
    public String getColour() {
        return colour;
    }
    public double getSize() {
        return size;
    }
    //Override Methods
    public void Display() {
        System.out.printf("%d     %-10s%-5d\t%-5.2f\tColour: %s Size:%.1f\n",super.item_id,super.title,super.quantity,super.price,this.colour,this.size);
    }

    //Methods from interface Comparable
    public int compareTo(Shoe o) {
        return Double.compare(o.size, this.size);
    }

}


class eStore {
    public static void main(String[]arg) {
        //Inventory using linked list
        LinkedList<Item> list = new LinkedList<Item>();

        int input = 0;

        while(input!=8) {
            DisplayMenu();

            Scanner sc = new Scanner(System.in);
            input = sc.nextInt();

            switch (input){
                case 1:

                    int type;
                    double price;
                    int num;
                    //Displays option of products
                    System.out.println("What item would you like to your cart?");
                    System.out.println("Enter [1] to add a book.");
                    System.out.println("Enter [2] to add a giftcard.");
                    System.out.println("Enter [3] to add a shoe.");

                    type=sc.nextInt();
                    System.out.printf("How many would you like to add?\n",type);
                    num=sc.nextInt();
                    System.out.println("What is the price?");
                    price=sc.nextDouble();

                    if(type==1){
                        addBook(list,num,price);
                    }
                    else if(type==2) {
                        addGiftCard(list,num,price);
                    }
                    else if(type==3) {
                        addShoe(list,num,price);
                    }
                    break;
                //Displays the inventory
                case 2:
                    displayCart(list);
                    break;
                //sorting
                case 3:
                    ArrayList<Book> books =new ArrayList<Book>();
                    for(Item l:list) {
                        if(l.getTitle().equals("Book")) {
                            books.add((Book) l);
                        }
                    }
                    Collections.sort(books);
                    for(Book b:books) {
                        b.Display();
                    }
                    break;
                //to sort the giftcards
                case 4:
                    ArrayList<GiftCard> cards =new ArrayList<GiftCard>();
                    for(Item l:list) {
                        if(l.getTitle().equals("Giftcard")) {
                            cards.add((GiftCard)l);
                        }
                    }
                    Collections.sort(cards);

                    for(GiftCard g:cards) {
                        g.Display();
                    }

                    break;
                case 5:
                    ArrayList<Shoe> shoes =new ArrayList<Shoe>();
                    for(Item l:list) {
                        if(l.getTitle().equals("Shoe")) {
                            shoes.add((Shoe)l);
                        }
                    }
                    Collections.sort(shoes);

                    for(Shoe s:shoes) {
                        s.Display();
                    }
                    break;
                case 6:
                    displayCart(list);
                    System.out.println("Enter item ID that you would like to remove from cart:");
                    Scanner s = new Scanner(System.in);
                    int id = s.nextInt()-1;
                    if(id>=0 && id<list.size()) {
                        list.remove(id);
                        System.out.println("Item removed!\n");
                    }
                    else {
                        System.out.println("Not in inventory!\n");
                    }
                    break;
                case 7:
                    displayCart(list);
                    System.out.println("What item would you like to purchase?");
                    Scanner S = new Scanner(System.in);
                    int ID = S.nextInt();
                    for(Item l:list) {
                        if(l.getId()==ID) {
                            System.out.println("How many would you like to purchase?");
                            int n = S.nextInt();

                            if(n<=l.getQuantity() && n>0) {
                                System.out.printf("%d %s have been purchased!\n",n,l.getTitle());
                            }
                            else if(n>l.getQuantity()) {
                                System.out.printf("%d %s have been purchased!\n",l.getQuantity(),l.getTitle());
                                System.out.printf("%d %s has to be added to the cart to purchase!\n",n-l.getQuantity(),l.getTitle());
                                n=l.getQuantity();
                            }
                            //if negative input
                            else{
                                System.out.println("Invalid input!");
                            }
                            for(int i=0;i<n;i++) {
                                l.Purchased();
                            }
                        }
                        else if(list.getLast()==l){
                            System.out.println("Not in inventory!\n");
                        }
                    }


                    break;
                case 8:

                    break;
                default:
                    System.out.println("Invalid input, please try again.\n");
                    break;

            }

        }

    }
    //Displays Menu options
    public static void DisplayMenu() {
        System.out.println("\n\nWelcome to the Online Store!");
        System.out.println("Enter [1] to add an item.");
        System.out.println("Enter [2] to display all items.");
        System.out.println("Enter [3] to display books sorted by author name.");
        System.out.println("Enter [4] to display giftcards sorted  by label.");
        System.out.println("Enter [5] to display shoes sorted by size");
        System.out.println("Enter [6] Delete an item by item id");
        System.out.println("Enter [7] to purchase an item from cart.");
        System.out.println("Enter [8] to exit.");
    }
    //Displays cart
    public static void displayCart(LinkedList<Item> list) {
        System.out.println("\nID   TITLE     QTY       PRICE  DESCRIPTION");
        System.out.println("----------------------------------------------");
        for(Item l:list) {
            l.Display();
        }
    }
    //Adds book to inventory and prompts user
    public static void addBook(LinkedList<Item> list,int num,double price) {
        String title;
        String author;
        int year;
        Scanner s = new Scanner(System.in);

        System.out.println("Enter Title: ");
        title=s.nextLine();
        System.out.println("Enter Author: ");
        author=s.nextLine();
        System.out.println("Enter Year Published: ");
        year=s.nextInt();

        list.add(new Book(num,price,title,author,year));
        System.out.println("Book added to the cart!");
    }
    //Adds Giftcard to inventory and prompts user
    public static void addGiftCard(LinkedList<Item> list,int num,double price) {
        String label;
        String manu;

        Scanner S = new Scanner(System.in);
        System.out.println("Enter Label:");
        label=S.nextLine();
        System.out.println("Enter Manufacturer:");
        manu=S.nextLine();

        list.add(new GiftCard(num,price,label,manu));
        System.out.println("Giftcard added to the cart!");
    }
    //Adds shoe to inventory and prompts user
    public static void addShoe(LinkedList<Item> list,int num,double price) {
        int colour;
        double size;

        Scanner SC = new Scanner(System.in);
        System.out.println("Enter Size: ");
        size = SC.nextDouble();

        System.out.println("Enter Colour: ");
        System.out.println("[1] WHITE");
        System.out.println("[2] SILVER");
        System.out.println("[3] RED");
        System.out.println("[4] BEIGE");
        System.out.println("[5] BROWN");
        System.out.println("[6] BLUE");
        System.out.println("[7] BLACK");
        System.out.println("[8] PINK");
        colour =SC.nextInt();

        switch(colour) {

            case 1:
                list.add(new Shoe(num,price, "White",size));
                break;
            case 2:
                list.add(new Shoe(num,price, "Silver",size));
                break;
            case 3:
                list.add(new Shoe(num,price, "Red",size));
                break;
            case 4:
                list.add(new Shoe(num,price, "Beige",size));
                break;
            case 5:
                list.add(new Shoe(num,price, "Brown",size));
                break;
            case 6:
                list.add(new Shoe(num,price, "Blue",size));
                break;
            case 7:
                list.add(new Shoe(num,price, "Black",size));
                break;
            case 8:
                list.add(new Shoe(num,price, "Pink",size));
                break;
        }
        System.out.println("Shoe added to the cart!");

    }

}

