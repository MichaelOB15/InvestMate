import java.util.*;
import java.io.*;

/**
 * THis class keeps track of all things related to a user in the application
 * @author davidcasente
 *
 */
public class User{
  
  private String name;
  private int age;
  private Portfolio portfolio;
  private String password;
  
  /**
   * creates a user class
   * @param name
   * @param age
   * @param password
   */
  public User(String name, int age, String password){
    this.name = name;
    this.age = age;
    this.password = password;
  }
  
  /**
   * 
   * @return the password of a user
   */
  public String getPassword() {
	  return password;
  }
  
  /**
   * 
   * @param temp
   * set the password
   */
  public void setPassword(String temp) {
	  this.password = temp;
  }
  

  /**
   * 
   * @return the name of the user
   */
  public String getName(){
    return name;
  }
  
  /**
   * sets the name
   * @param name
   */
  public void setName(String name){
   this.name = name;
  }
  /**
   * 
   * @return the age
   */
  public int getAge(){
   return age; 
  }
  
  /**
   * sets the age
   * @param age
   */
  public void setAge(int age){
   this.age = age;
  }
  
  /**
   * 
   * @return the portfolio of the user
   */
  public Portfolio getPortfolio(){
   return portfolio; 
  }
  
  /**
   * sets the ortolfio
   * @param portfolio
   */
  public void setPortfolio(Portfolio portfolio){
    this.portfolio = portfolio; 
  }
  
  /**
   * creates a portfolio and adds to user based on arraylist of owned stocks
   * @param input
   */
  public Portfolio createPortfolio(ArrayList<OwnedStock> input){
   Portfolio temp = new Portfolio(input);
   this.portfolio = temp; 
   return temp;
  }
  
  
  
  
} 