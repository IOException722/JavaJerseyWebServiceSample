package myfirstjerseyservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;
@Path("/jsondata")
public class JsonData {
	
	@GET
	@Produces("text/html")
	public Response getJsonData()
	{
		String output = "<h1>Hello World!<h1>" +
				"<p>RESTful Service is running ... <br>Ping @ " + new Date().toString() + "</p<br>";
	
		   JSONObject jsonString = new JSONObject();

		   try{
		    	jsonString.put("name","Abhay Kumar");
		        jsonString.put("company","Quantum Four");
		        jsonString.put("location", "Mumbai");
		    }
		    catch (Exception e) {
				// TODO: handle exception
			}
		    
			int[] ids = { 100, 200, 300 };
//		    JSONObject mainObject = new JSONObject();
		    JSONArray recipients = new JSONArray();
		    int i = 0;
		    try{
		    	 for (int id : ids) {
		 	        JSONObject idsJsonObject = new JSONObject();
		 	        String key = "num"+i++ ;
		 	     
		 	        idsJsonObject.put(key, id);
		 	        idsJsonObject.put(key+10, id*10);
		 	        recipients.put(idsJsonObject);
		 	    }
		 	    jsonString.put("recipients", recipients);
		    }
		   catch(Exception e){
		    	
		    }

			// Get the printwriter object from response to write the required json object to the output stream      
//			PrintWriter out = response.getWriter();
//			// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
//			out.print(jsonString);
//			out.flush();
			
		   // MySql Connection
		    try{  
		    	Class.forName("com.mysql.jdbc.Driver");  
		    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quantumfour","root","quantumfour");  
		    	//here sonoo is database name, root is username and password  
		    	Statement stmt=con.createStatement();  
		    	ResultSet rs=stmt.executeQuery("select * from services");  
		    	while(rs.next())  {
			        jsonString.put(rs.getString(2), rs.getString(3));

		    	System.out.println(rs.getString(2)+"  "+rs.getString(3));  
		    	}
		    	con.close();  
		    	}catch(Exception e){ System.out.println(e);}  
		    
	
		return Response.status(200).entity(jsonString.toString()).build();
	}
	

}
