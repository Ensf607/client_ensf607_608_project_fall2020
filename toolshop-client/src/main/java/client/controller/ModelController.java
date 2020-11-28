package client.controller;

import java.io.IOException;


/**
 * The type Model controller.
 */
public class ModelController implements Observer{
	private Client c;

    /**
     * Instantiates a new Model controller.
     *
     * @param c the c
     */
    public ModelController(Client c) {
		this.c=c;
	
	}
	/**
	 * implement the method in Observer interface
	 */
	public String request(String json) {
		
		System.out.println("GOT A MESSAGE FROM GUI EVENT");
		
		try {
			String response =c.send(json);
			System.out.println("Got response from server !!");
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

    /**
     * The entry point of application.
     *
     * @param arg the input arguments
     */
    public static void main (String [] arg) {
	Client c =new Client("localhost", 9090);
	ModelController mc =new ModelController(c);
	ViewController vc=new ViewController(mc);
}


}
