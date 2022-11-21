package project;

public class proj {
		public int call(String umove,String cmove) {
			if(umove.equals(cmove)) {
				return 0;
			}
			else
				if(umove.equals("r")) {          
				if(cmove.equalsIgnoreCase("p"))                 //  string handling function
					return -1;
				else
					return 1;		}
				else
					if(umove.equalsIgnoreCase("p")) {          // string handling function
					if(cmove.equals("s"))
						return -1;
					else
						return 1;
				}
					else
						if(umove.equals("s")) {
						if(cmove.equals("r"))
							return -1;
						else
							return 1;
					}
			return -2;
		}	
	}

