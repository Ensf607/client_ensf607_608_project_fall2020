 package client;

import client.controller.Client;
import client.controller.ModelController;
import client.controller.ViewController;

 /**
  * The type Main.
  */
 public class mainLocal {
     /**
      * The entry point of application.
      *
      * @param args the input arguments
      */
     public static void main(String[] args) {
         System.out.println("connecting to the backed server hosted at aws ... ");
         Client c =new Client("localhost", 9090);
         ModelController mc =new ModelController(c);
         ViewController vc=new ViewController(mc);
     }
 }
