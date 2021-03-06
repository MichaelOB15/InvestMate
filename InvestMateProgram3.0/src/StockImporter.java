import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Date;

public class StockImporter {
 
 public static float getCurrentPrice(String symbol){
  //Using a StringBuilder...
  StringBuilder urlBuilder = new StringBuilder();
  //We specify our url...
  urlBuilder.append("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=");
  //We define our stock that we'd like to recieve data for...
  urlBuilder.append(symbol);
  urlBuilder.append("&apikey=7X34UXTUDREKB4IK&datatype=csv");

  try {
   //We try to connect to our url...
   URL url = new URL(urlBuilder.toString());
   URLConnection urlConn = url.openConnection();
   //and instantiate an inputstream to read from the URL...
   InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
   BufferedReader buff = new BufferedReader(inStream);
   buff.readLine();
   String line = buff.readLine();
   while (line != null){
    String [] data = line.split(",");
    return (Float.parseFloat(data[4]));
   }
  }

  catch (MalformedURLException mu){
   System.out.println(mu.getMessage());
   return 0;
  }
  catch (IOException io){
   System.out.println(io.getMessage());
   return 0;
  }
  catch(ArrayIndexOutOfBoundsException ae){
   System.out.println("INVALID STOCK SYMBOL");
   return 0;
  }
  return (0);
 }
 
 public static String[] getStockInfo(String symbol){ 
  // This method can take in any keywords for a stock...(name, symbol, etc) and will return the array of all information
  // about that stock With the format (Symbol, Name, Type, Location, Market Open, Market Close, Time Zone, Currency, Match to keyword)
   
  //Using a StringBuilder...
  StringBuilder urlBuilder = new StringBuilder();
  //We specify our url...
  urlBuilder.append("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=");
  //We define our stock that we'd like to recieve data for...
  urlBuilder.append(symbol);
  urlBuilder.append("&apikey=7X34UXTUDREKB4IK&datatype=csv");

  try {
   //We try to connect to our url...
   URL url = new URL(urlBuilder.toString());
   URLConnection urlConn = url.openConnection();
   //and instantiate an inputstream to read from the URL...
   InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
   BufferedReader buff = new BufferedReader(inStream);
   buff.readLine();
   String line = buff.readLine();
   if (line!=null){
   String [] data = line.split(",");
   return data;
   }
   else{
     return null;
   }
  }

  catch (MalformedURLException mu){ 
   System.out.println(mu.getMessage());
   return null;
  }
  catch (IOException io){
   System.out.println(io.getMessage());
   return null;
  }
  catch(ArrayIndexOutOfBoundsException ae){
   System.out.println("INVALID STOCK SYMBOL");
   return (null);
  }
 }
 
  public static ArrayList<String> getStockList(String symbol){
  // This method takes in a keyword and returns the list of names for the top stocks that match that keyword
  // MSFT returns a list of microsoft stocks for example.
  
  //Using a StringBuilder...
  StringBuilder urlBuilder = new StringBuilder();
  //We specify our url...
  urlBuilder.append("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=");
  //We define our stock that we'd like to recieve data for...
  urlBuilder.append(symbol);
  urlBuilder.append("&apikey=7X34UXTUDREKB4IK&datatype=csv");
  
  ArrayList<String> output = new ArrayList<String>();
  try {
   //We try to connect to our url...
   URL url = new URL(urlBuilder.toString());
   URLConnection urlConn = url.openConnection();
   //and instantiate an inputstream to read from the URL...
   InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
   BufferedReader buff = new BufferedReader(inStream);
   buff.readLine();
   String line = buff.readLine();
   while (line != null){
     String[] data = line.split(",");
     output.add(data[1]);
     line = buff.readLine();
   }
  }
  catch (MalformedURLException mu){ 
   System.out.println(mu.getMessage());
   return null;
  }
  catch (IOException io){
   System.out.println(io.getMessage());
   return null;
  }
  catch(ArrayIndexOutOfBoundsException ae){
   System.out.println("INVALID STOCK SYMBOL");
   return (null);
  }
  return output;
 }
 
 

 public static String getStockSymbol(String keyword){ //Returns the symbol of the stock most closely matching the keyword (can be symbol or name)
   String[] output = getStockInfo(keyword);
   if (output!=null){
     return getStockInfo(keyword)[0];
   }else{
     return null;
   } 
 }

 public static String getStockName(String keyword){ //Returns the name of the stock most closely matching the keyword
   String[] output = getStockInfo(keyword);
   if (output!=null){
     return getStockInfo(keyword)[1];
   }else{
     return null;
   } 
 }
 
 public static String getStockType(String keyword){ //Returns the type of the stock most closely matching the keyword (can be symbol or name)
   String[] output = getStockInfo(keyword);
   if (output!=null){
     return getStockInfo(keyword)[2];
   }else{
     return null;
   } 
 }
 
 public static String getStockLocale(String keyword){ //Returns the location of the stock most closely matching the keyword (can be symbol or name)
   String[] output = getStockInfo(keyword);
   if (output!=null){
     return getStockInfo(keyword)[3];
   }else{
     return null;
   } 
 }
 
 public static String getStockCurrency(String keyword){ //Returns the currency ues at the location of the stock most closely matching the keyword (can be symbol or name)
   String[] output = getStockInfo(keyword);
   if (output!=null){
     return getStockInfo(keyword)[7];
   }else{
     return null;
   } 
 }
 
 public static ArrayList<Float> timeSeriesDaily(String symbol){
   //Using a StringBuilder...
   StringBuilder urlBuilder = new StringBuilder();
   ArrayList<Float> close = new ArrayList<Float>();
   
  //We specify our url...
  urlBuilder.append("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=");
  //We define our stock that we'd like to recieve data for...
  urlBuilder.append(symbol);
  urlBuilder.append("&outputsize=full&apikey=7X34UXTUDREKB4IK&datatype=csv");

  try {
   //We try to connect to our url...
   URL url = new URL(urlBuilder.toString());
   URLConnection urlConn = url.openConnection();
   //and instantiate an inputstream to read from the URL...
   InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
   BufferedReader buff = new BufferedReader(inStream);
   buff.readLine();
   String line = buff.readLine();
   int i  = 0;
   while (line != null && i < 365){
    String [] data = line.split(",");
    close.add(Float.parseFloat(data[4]));
    line = buff.readLine();
    i++;
   }
   return close;
  }

  catch (MalformedURLException mu){
   System.out.println(mu.getMessage());
   return new ArrayList<Float>();
  }
  catch (IOException io){
   System.out.println(io.getMessage());
   return new ArrayList<Float>();
  }
  catch(ArrayIndexOutOfBoundsException ae){
   System.out.println("INVALID STOCK SYMBOL");
   return new ArrayList<Float>();
  }
 }
 
  public static Object[] timeSeriesWeekly(String symbol){
   //Using a StringBuilder...
   StringBuilder urlBuilder = new StringBuilder();
   
   ArrayList<String> date = new ArrayList<String>();
   ArrayList<Float> open = new ArrayList<Float>();
   ArrayList<Float> high = new ArrayList<Float>();
   ArrayList<Float> low = new ArrayList<Float>();
   ArrayList<Float> close = new ArrayList<Float>();
   ArrayList<Float> volume = new ArrayList<Float>();
   
  //We specify our url...
  urlBuilder.append("https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=");
  //We define our stock that we'd like to recieve data for...
  urlBuilder.append(symbol);
  urlBuilder.append("&outputsize=full&apikey=7X34UXTUDREKB4IK&datatype=csv");

  try {
   //We try to connect to our url...
   URL url = new URL(urlBuilder.toString());
   URLConnection urlConn = url.openConnection();
   //and instantiate an inputstream to read from the URL...
   InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
   BufferedReader buff = new BufferedReader(inStream);
   buff.readLine();
   String line = buff.readLine();
   int i  = 0;
   while (line != null){
    String [] data = line.split(",");
    date.add(data[0]);
    open.add(Float.parseFloat(data[1]));
    high.add(Float.parseFloat(data[2]));
    low.add(Float.parseFloat(data[3]));
    close.add(Float.parseFloat(data[4]));
    volume.add(Float.parseFloat(data[5]));
    line = buff.readLine();
   }
   return new Object[]{date, open, high, low, close, volume};
  }

  catch (MalformedURLException mu){
   System.out.println(mu.getMessage());
   return null;
  }
  catch (IOException io){
   System.out.println(io.getMessage());
   return null;
  }
  catch(ArrayIndexOutOfBoundsException ae){
   System.out.println("INVALID STOCK SYMBOL");
   return null;
  }
 }
  
  public static Object[] timeSeriesMonthly(String symbol){
   //Using a StringBuilder...
   StringBuilder urlBuilder = new StringBuilder();
   
   ArrayList<String> date = new ArrayList<String>();
   ArrayList<Float> open = new ArrayList<Float>();
   ArrayList<Float> high = new ArrayList<Float>();
   ArrayList<Float> low = new ArrayList<Float>();
   ArrayList<Float> close = new ArrayList<Float>();
   ArrayList<Float> volume = new ArrayList<Float>();
   
  //We specify our url...
  urlBuilder.append("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=");
  //We define our stock that we'd like to recieve data for...
  urlBuilder.append(symbol);
  urlBuilder.append("&outputsize=full&apikey=7X34UXTUDREKB4IK&datatype=csv");

  try {
   //We try to connect to our url...
   URL url = new URL(urlBuilder.toString());
   URLConnection urlConn = url.openConnection();
   //and instantiate an inputstream to read from the URL...
   InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
   BufferedReader buff = new BufferedReader(inStream);
   buff.readLine();
   String line = buff.readLine();
   while (line != null){
    String [] data = line.split(",");
    date.add(data[0]);
    open.add(Float.parseFloat(data[1]));
    high.add(Float.parseFloat(data[2]));
    low.add(Float.parseFloat(data[3]));
    close.add(Float.parseFloat(data[4]));
    volume.add(Float.parseFloat(data[5]));
    line = buff.readLine();
   }
   return new Object[]{date, open, high, low, close, volume};
  }

  catch (MalformedURLException mu){
   System.out.println(mu.getMessage());
   return null;
  }
  catch (IOException io){
   System.out.println(io.getMessage());
   return null;
  }
  catch(ArrayIndexOutOfBoundsException ae){
   System.out.println("INVALID STOCK SYMBOL");
   return null;
  }
 }
  
  public static Float getLastDayClose(String symbol){
    Float close = new Float(0);
  //Using a StringBuilder...
  StringBuilder urlBuilder = new StringBuilder();
  //We specify our url...
  urlBuilder.append("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=");
  //We define our stock that we'd like to recieve data for...
  urlBuilder.append(symbol);
  urlBuilder.append("&outputsize=full&apikey=7X34UXTUDREKB4IK&datatype=csv");

  try {
   //We try to connect to our url...
   URL url = new URL(urlBuilder.toString());
   URLConnection urlConn = url.openConnection();
   //and instantiate an inputstream to read from the URL...
   InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
   BufferedReader buff = new BufferedReader(inStream);
   buff.readLine();
   String line = buff.readLine();
   String[] data = line.split(",");
   close=(Float.parseFloat(data[4]));
          
   return close;
  }

  catch (MalformedURLException mu){
   System.out.println(mu.getMessage());
   return null;
  }
  catch (IOException io){
   System.out.println(io.getMessage());
   return null;
  }
  catch(ArrayIndexOutOfBoundsException ae){
   System.out.println("INVALID STOCK SYMBOL");
   return null;
  }
 }
  
  public static Object[] getSectorInfo(){
    //Returns an array of arraylists of information about different sectors
    ArrayList<Float> energy = new ArrayList<Float>();
    ArrayList<Float> financials = new ArrayList<Float>();
    ArrayList<Float> healthcare = new ArrayList<Float>();
    ArrayList<Float> utilities = new ArrayList<Float>();
    ArrayList<Float> consumerStaples = new ArrayList<Float>();
    ArrayList<Float> communications = new ArrayList<Float>();
    ArrayList<Float> realEstate = new ArrayList<Float>();
    ArrayList<Float> consumerDisc = new ArrayList<Float>();
    ArrayList<Float> informationTech = new ArrayList<Float>();
    ArrayList<Float> industries = new ArrayList<Float>();
    ArrayList<Float> materials = new ArrayList<Float>();
    
    Float close = new Float(0);
    
    //Using a StringBuilder...
    StringBuilder urlBuilder = new StringBuilder();
    //We specify our url...
    urlBuilder.append("https://www.alphavantage.co/query?function=SECTOR&apikey=7X34UXTUDREKB4IK");
    
    try {
      //We try to connect to our url...
      URL url = new URL(urlBuilder.toString());
      URLConnection urlConn = url.openConnection();
      //and instantiate an inputstream to read from the URL...
      InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
      BufferedReader buff = new BufferedReader(inStream);
      buff.readLine();
      buff.readLine();
      buff.readLine();
      buff.readLine();
      String line = buff.readLine();
      int i = 0;
      String pruned = new String();
      while (line != null){
        if (line.contains("Energy")){
          pruned = line.replaceAll("[^0-9.-]", "");
          energy.add(Float.parseFloat(pruned));
        }
        if (line.contains("Financials")){
          pruned = line.replaceAll("[^0-9.-]", "");
          financials.add(Float.parseFloat(pruned));
        }
        if (line.contains("Health")){
          pruned = line.replaceAll("[^0-9.-]", "");
          healthcare.add(Float.parseFloat(pruned));
        }
        if (line.contains("Industrials")){
          pruned = line.replaceAll("[^0-9.-]", "");
          industries.add(Float.parseFloat(pruned));
        }
        if (line.contains("Information")){
          pruned = line.replaceAll("[^0-9.-]", "");
          informationTech.add(Float.parseFloat(pruned));
        }
        if (line.contains("Materials")){
          pruned = line.replaceAll("[^0-9.-]", "");
          materials.add(Float.parseFloat(pruned));
        }
        if (line.contains("Consumer")){
          pruned = line.replaceAll("[^0-9.-]", "");
          consumerDisc.add(Float.parseFloat(pruned));
        }
        if (line.contains("Communication")){
          pruned = line.replaceAll("[^0-9.-]", "");
          communications.add(Float.parseFloat(pruned));
        }
        if (line.contains("Consumer")){
          pruned = line.replaceAll("[^0-9.-]", "");
          consumerStaples.add(Float.parseFloat(pruned));
        }
        if (line.contains("Real Estate")){
          pruned = line.replaceAll("[^0-9.-]", "");
          realEstate.add(Float.parseFloat(pruned));
        }
        if (line.contains("Utilities")){
          pruned = line.replaceAll("[^0-9.-]", "");
          utilities.add(Float.parseFloat(pruned));
        }
        line = buff.readLine();
      }
      return new Object[]{"Communication Services",communications,"Consumer Discretionary",consumerDisc,"Consumer Staples",consumerStaples,"Energy",energy,"Financials",financials,"Industrials",industries,"Information Technology",informationTech,"Health Care",healthcare,"Materials",materials,"Real Estate",realEstate,"Utilities",utilities};
    }
    
    catch (MalformedURLException mu){
      System.out.println(mu.getMessage());
      return null;
    }
    catch (IOException io){
      System.out.println(io.getMessage());
      return null;
    }
    catch(ArrayIndexOutOfBoundsException ae){
      System.out.println("INVALID STOCK SYMBOL");
      return null;
    }
  }
  
  
  public static boolean isStockLive(String symbol){
    float a = StockImporter.getCurrentPrice(symbol);
    if (a != 0){
      return true;
    }
    else{
      return false;
    }
  }
  
}