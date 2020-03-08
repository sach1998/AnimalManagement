package kiran;
import java.awt.Desktop;
import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.swing.text.StyledEditorKit.FontFamilyAction;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

//The PhoneBookEntry class is a template for every phonebook entry object
//that will be saved in a file called phonebook.bin
//Each entry has name and phone number
//The PhoneBookEntry must implement the Serializable interface since its object
//will be added to the Hashtable for saving in the file

class Animaldetailsentry1 implements Serializable{
 
 private String Name;
 private String place;
 private String gender;
 private String available;
 
 
 
 //constructor
Animaldetailsentry1(String name, String place,String gender,String available){
  
  this.Name=name;
  this.place=place;
  this.gender=gender;
  this.available=available;
  
  
 }
 public void AnimaldetailsEntry(String name2, String place2,String gender2,String available2) {
	// TODO Auto-generated constructor stub
}
//get name
 public String getName(){
  return Name;
 }
 
 
 //get phone number
 public String getPlace(){
  return place;
 }
 public String getGender(){
	  return gender;
	 }
 
 public String getAvailable(){
	  return available;
	 }
 //print entry information
 public void printInfo(){
  System.out.println(Name+"\t\t"+place+"\t\t"+gender+"\t\t"+available);
 }
}


public class animaldetails{
 static Hashtable<String,Animaldetailsentry1> animaldetails; 
   public static void main(String[] args){
	   System.out.println("*******ANIMALS DETAILS*********");
    animaldetails=readList(); //read phonebook
    int ch;
    char con='y';
    Scanner sc=new Scanner(System.in); //create scanner object to receive choice input
    
    while(con=='y'){
     showMenu(); //show menu
     System.out.println("Enter your choice:");
     ch=sc.nextInt();    
     switch(ch){
      case 1:viewAll();break;
      case 2:addToanimaldet();break;
      case 3:deleteFromanimaldet();break;
      case 4:searchByName();break;
      
      case 5:createHTML();break;
      case 6:System.exit(0);break;
      default: System.out.println("Invalid choice");
      }
    
     try{
      //prompt for continuing the program
      InputStreamReader isr=new InputStreamReader(System.in);
      System.out.println("Press y to continue:");
      con=(char)isr.read();
     }catch(IOException ie){}
     
     
    }
    
    
   }
   //The viewAll method displays all entries in the phonebook
   public static void viewAll(){
	   System.out.println("NAME\t\tPLACE\t\tGENDER\t\tAVAILABLE");
    
    if(animaldetails!=null){
     
     for(Enumeration<String> e=animaldetails.keys(); e.hasMoreElements();){
      Animaldetailsentry1 entry=animaldetails.get(e.nextElement());
      entry.printInfo();
     }

    }

     
    
   }
   
   
   
   public static void addToanimaldet(){
   
    if(animaldetails==null) animaldetails=new Hashtable<String,Animaldetailsentry1>();
    try{
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter name:");
    String name=br.readLine();    
    System.out.println("Enter place:");
    String place=br.readLine(); 
    System.out.println("enter Gender");
    String gender=br.readLine();
    System.out.println("enter Available");
    String available=br.readLine();
	Animaldetailsentry1 st=new Animaldetailsentry1(name,place,gender,available);
    animaldetails.put(name,st); 
    writeIt(animaldetails); 
    }catch(IOException e){}
   }
   
   
   public static void deleteFromanimaldet(){
    if(animaldetails!=null){
     int si=animaldetails.size(); 
     try{
     BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     System.out.println("Name:");
     String key=br.readLine();
     animaldetails.remove(key); 
     if(animaldetails.size()<si){
      writeIt(animaldetails);
      System.out.println("The entry has been deleted.");
     }
     else
      System.out.println("Wrong name");
     }catch(IOException ie){}
     
     
    }
  }
  
  
   public static void searchByName(){
    if(animaldetails!=null){
     try{
     BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     System.out.println("Search by name:");
     String key=br.readLine();
     Animaldetailsentry1 cu=animaldetails.get(key);
     if(cu!=null)
     {
    	 System.out.println("NAME\t\tPLACE\t\tGENDER\t\tAVAILABLE");
     
      cu.printInfo();
     } 
     else
      System.out.println("Not found");
     }catch(IOException ie){}
     
     
    }
   }
   
   
   public static void writeIt(Hashtable<String,Animaldetailsentry1> obj){
    try{
    FileOutputStream fos=new FileOutputStream("animaldetails.txt");
    ObjectOutputStream oos=new ObjectOutputStream(fos);
    oos.writeObject(obj);
    oos.flush();
    oos.close();
    }catch(IOException ie){}
    
   }
   
 
   
   public static Hashtable<String,Animaldetailsentry1> readList(){

    Hashtable<String,Animaldetailsentry1> animal=null;
    try{
    FileInputStream fis=new FileInputStream("animaldetails.txt");
    ObjectInputStream ois=new ObjectInputStream(fis);
    animal=(Hashtable<String,Animaldetailsentry1>)ois.readObject();
    ois.close();
   
    }catch(Exception ie){}
    return animal;
    
   }
  
 
   
   
   public static void createHTML(){

    try{
    FileWriter fw=new FileWriter("animaldetails.html");
    BufferedWriter bw=new BufferedWriter(fw);
    bw.write("<!Doctype html>");
    bw.newLine();
    bw.write("<html><head><title>ANIMAL DETAILS</title></head>");
    bw.newLine();
    bw.write("<body style='padding-left:50px'>");
    bw.newLine();
    bw.write("<p style='color:#9900ff; font-size:16pt'>ANIMAL DETAILS</p>");
    bw.write("<table>");
    bw.newLine();
    bw.write("<tr style='background-color:#00aaaa'><th>Name</th><th>Place</th><th>gender</th><th>available</th>");
    if(animaldetails!=null){
     Set<String> set=animaldetails.keySet();
     Iterator<String> iterator=set.iterator();
     while(iterator.hasNext()){
      Animaldetailsentry1 cu=animaldetails.get(iterator.next());
      bw.write("<tr><td>"+cu.getName()+"</td><td>"+cu.getPlace()+"</td><td>"+cu.getGender()+"</td><td>"+cu.getAvailable()+"</td><tr>");
      bw.newLine();
      }
     
     }
    bw.write("</table>");
    bw.newLine();
    bw.write("</body></html>");
    bw.flush();
    bw.close();
    displayReport("animaldetails.html");
    }catch(IOException ie){ie.printStackTrace();}
    
   }
   
   public static void displayReport(String src){
	   
    try{
   
     if(Desktop.isDesktopSupported()){
      File f=new File(src);
      Desktop.getDesktop().open(f);
   
     }
    }catch(IOException ie){}
   }
   
   public static void showMenu(){
    System.out.println("1. View all Animal entries");
    System.out.println("2. Add to Animal Details");
    System.out.println("3. Remove from Animal Details");
    System.out.println("4. Find an entry");
    System.out.println("5. Create Animaldetails html file");    
    System.out.println("6. Exit");
   }
   
   

}
