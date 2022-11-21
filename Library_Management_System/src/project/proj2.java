package project;

public class proj2 {                                    //class in different package
          public int a;
          public void right(int a) {
        	  if(a==-2) {
      			try {
      				throw new Exception("Out Of Boundary");             //custom exception
      			}
      			catch(Exception e) {
      				System.out.println(e);
      			}
      		}
        	  
          }
}
