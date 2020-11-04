
import java.io.*;
import java.util.Scanner;

import client.*;
import common.*;


public class ServerConsole implements ChatIF {
	
	  //Instance variables **********************************************
	  
	  /**
	   * The instance of the client that created this ConsoleChat.
	   */
	  final public static int DEFAULT_PORT = 5555;
	  
	  
	  
	  /**
	   * Scanner to read from the console
	   */
	  private EchoServer sv;
	  
	  /**
	   * Scanner to read from the console
	   */
	  private Scanner fromConsole; 

	  
	  //Constructors ****************************************************

	  /**
	   * Constructs an instance of the ClientConsole UI.
	   *
	   * @param host The host to connect to.
	   * @param port The port to connect on.
	   */
	  public ServerConsole(int port) 
	  {
		sv = new EchoServer(port, this);
	    try 
	    {

	    	sv.listen();  
	    } 
	    catch(Exception e) 
	    {
	      System.out.println("Error- could not listen to clients");
	      System.exit(1);
	    }
	    fromConsole = new Scanner(System.in); 
	  }

	  
	  //Instance methods ************************************************
	  
	  //Instance methods ************************************************
	  
	  /**
	   * This method waits for input from the console.  Once it is 
	   * received, it sends it to the client's message handler.
	   */
	  public void accept() 
	  {
	    try
	    {

	      String message;

	      while (true) 
	      {
	        message = fromConsole.nextLine();
	        sv.handleMessageFromServer(message);
	      }
	    } 
	    catch (Exception ex) 
	    {
	      System.out.println
	        ("Unexpected error while reading from console!");
	    }
	  }
	  
	  /**
	   * This method overrides the method in the ChatIF interface.  It
	   * displays a message onto the screen. 
	   *
	   * @param message The string to be displayed.
	   */
	  public void display(String message) 
	  {
	    System.out.println("SERVER MSG> " + message);
	  }
	  
	  //Class methods ***************************************************
	  
	  /**
	   * This method is responsible for the creation of the Client UI.
	   *
	   * @param args[0] The host to connect to.
	   */
	  public static void main(String[] args) 
	  {
	    int port = 0;

	    try
	    {
	      port = Integer.parseInt(args[0]);
	    }
	    catch(Throwable t)
	    {
	      port = DEFAULT_PORT;
	    }
	    ServerConsole console= new ServerConsole(port);
	    console.accept();  //Wait for console data
	  }
	  
}
