import java.util.*;
import java.io.*;
class LexicalAnalyzer {
	static ArrayList<ArrayList<String>> tokenSort = new ArrayList<ArrayList<String>>();
	  static ArrayList<String> keyword = new ArrayList<String>();
	    static ArrayList<String> ident = new ArrayList<String>();
	    static ArrayList<String> openBracket = new ArrayList<String>();
	    static ArrayList<String> closeBracket = new ArrayList<String>();
	    static ArrayList<String> openBrace = new ArrayList<String>();
	    static ArrayList<String> closeBrace = new ArrayList<String>();
	    static ArrayList<String> plus_op = new ArrayList<String>();
	    static ArrayList<String> minus_op = new ArrayList<String>();
	    static ArrayList<String> equal = new ArrayList<String>();
	    static ArrayList<String> semicolon = new ArrayList<String>();
	    static ArrayList<String> illegal =  new ArrayList<String>();
	    static HashMap<Integer, String> hm = new HashMap<Integer, String>();
	    static int j = 0;
		static boolean dec = false;
		static String Error = "\nThe first syntax error is ";
	  public static void main(final String[] args) {
	    //make 2d arraylist to hold different token types
		    //index row 0 will be "keyword"
		    //index row 1 will be "ident"
		    //index row 2 will be "open bracket"
	      //index row 3 will be "close bracket"
		    //index row 4 will be "open brace"
		    //index row 5 will be "close brace"
	      //index row 6 will be "plus_op"
		    //index row 7 will be "minus_op"
		    //index row 8 will be "equal"
	      //index row 9 will be "semicolon"
    // index row 10 will be "illegal"

	    //Here I will have the user enter the file name which will be tryprogram1.txt or tryprogram2.txt

    //LEXICAL ANALYZER
    
		  System.out.print("Please enter text file name (tryprogram1.txt or tryprogram2.txt): ");
			final Scanner fileScanner = new Scanner(System.in);
			try {
				tokenSort.add(0, keyword);
	      tokenSort.add(1, ident);
	      tokenSort.add(2, openBracket);
	      tokenSort.add(3, closeBracket);
	      tokenSort.add(4, openBrace);
	      tokenSort.add(5, closeBrace);
	      tokenSort.add(6, plus_op);
	      tokenSort.add(7, minus_op);
	      tokenSort.add(8, equal);
	      tokenSort.add(9, semicolon);
	      tokenSort.add(10, illegal);				
		      final String tryProgram = fileScanner.next();
					final FileInputStream fis = new FileInputStream(tryProgram);
				fileScanner.close();
			    final Scanner s = new Scanner(fis); 
			    String temp = "";
			    String nextlexeme = "";
			    int size = 0;
	        int hmIndex = 0; // creates a hash map to store and keep track of the order of the try program 
					while(s.hasNext()) {
						nextlexeme = s.next();
						size = nextlexeme.length();
						
				        for(int i = 0; i < size; i++) {
				        	
					        temp = temp + nextlexeme.charAt(i);
					        String fix = "";
					        if(i < size-1) {
					        	fix += nextlexeme.charAt( i+1);}
		             //fix is for when a ; or ( or ) is a part of s.next(). This will seperate the letters from theose characters
		              if ((fix.equals(")") && !temp.equals("("))|| (fix.equals(";"))){
		            	  ident.add(temp);
	                  hm.put(hmIndex,temp); hmIndex++;temp = "";
		              }
					        //Keywords
		              if (temp.equals("void")){ 
		  			        keyword.add(temp); 
	                  hm.put(hmIndex,temp); hmIndex++;temp = "";
		  			        }
		              else if (temp.equals("float")){
		  			        keyword.add(temp); 
	                  hm.put(hmIndex,temp); hmIndex++;temp = "";
		  			        }
		              // for the rest of the else if statements basically just copied the way I did the first if statement with the rest of the tokens.
		              //Open Bracket
		              else if (temp.equals("{")){
		                openBracket.add(temp);
	                  hm.put(hmIndex,temp); hmIndex++;temp = "";
		              }
		              //Close Bracket
		              else if(temp.equals("}")){
		                closeBracket.add(temp);
	                  hm.put(hmIndex,temp); hmIndex++;temp = "";
		              }
		              //Open Brace else if goes here
		              else if(temp.equals("(")){
		                openBrace.add(temp);
	                  hm.put(hmIndex,temp); hmIndex++;temp = "";
		              }
		              //Close Brace
		              else if(temp.equals(")")){
		                closeBrace.add(temp); 
	                  hm.put(hmIndex,temp); hmIndex++;temp = "";
		              }
		              //plus_op
		              else if(temp.equals("+")){
		                plus_op.add(temp);
	                  hm.put(hmIndex,temp); hmIndex++;temp = "";
		              }
		              //minus_op
		              else if(temp.equals("-")){
		                minus_op.add(temp);
	                  hm.put(hmIndex,temp); hmIndex++;temp = "";
		              }
                //illegal
		              else if(temp.equals("–")){
			                illegal.add(temp);
	                    hm.put(hmIndex,temp); hmIndex++;temp = "";
			          }
		              //equal
		              else if(temp.equals("=")){
		                equal.add(temp);
	                  hm.put(hmIndex,temp); hmIndex++;temp = "";
		              }
		              //semicolon
		               else if(temp.equals(";")){
		                semicolon.add(temp);
	                   hm.put(hmIndex,temp); hmIndex++;temp = "";
		              }
		              
		              //identifiers are in an else statement because they can be anything
		              
		              else {
		            	  if (size == temp.length()){
		              
		                ident.add(temp);
	                  hm.put(hmIndex,temp); hmIndex++;temp = "";}
		              }
		              fix = "";
			            }		        
				    
			        }
					s.close();
					printTokenSort(tokenSort);
	        
	       
	        
	        isBNF(j);
	        
					
				} catch (final FileNotFoundException e) {
					//using catch if file isn't found
					e.printStackTrace();
				}
						
			} 
	  //Prints out 2D ArrayList
			 public static void printTokenSort(final ArrayList<ArrayList<String>> ts){
			      String result = "";
			      for(int i = 0; i < ts.size(); i++){
			    	    switch(i) {
			    	  case 0 :
			    		  result += "Keyword: "; break;
		          case 1:
		            result += "Identifier: "; break;
		          case 2:
		            result += "Open Bracket: ";break;
		          case 3:
		            result += "Close Bracket: ";break;
		          case 4:
		            result += "Open Brace: ";break;
		          case 5:
		            result += "Close Brace: ";break;
		          case 6:
		            result += "plus_op: ";break;
		          case 7:
		            result += "minus_op: ";break;
		          case 8:
		            result += "equal: ";break;
		          case 9: 
		            result += "semicolon: ";break;
		          case 10:
		        	result += "Illegal Character:";break;
			       }
			    	    	
			          for(int j = 0; j < ts.get(i).size(); j++){
			              result += ts.get(i).get(j);
			              result += " ";
			          }
			          result += "\n";
			      }       
			      System.out.print(result);

	     } 
	        public static void sc(int k) {
	        	if(hm.get(k).equals(";")) {j = k +1; k +=1;}
	        }
	  //checks through identifier for match
			public static boolean iD(int k) {
				
				if(!ident.contains(hm.get(k))){
					
					return false;
				}
				return true;
			}

	  // checks keyword
			public static boolean kW(int k) {
				if(hm.get(k).equals(";")) {j++; k++; k=j;}
				if(keyword.contains(hm.get(k))){
	            
	            return true;
				}
				
				return false;
			}

	  // checks equal
			public static boolean eQ(int k) {
				if(!equal.contains(hm.get(k))){
	            
	            return false;

				}
				return true;
			}

	  // checks open brace
			public static boolean oBrace(int k) {
				if(!openBrace.contains(hm.get(k))){
					
	            
	            return false;
				}
				return true;
			}
	  
	  // checks closed brace
			public static boolean cBrace(int k) {
				if(!closeBrace.contains(hm.get(k))){
				   
	            return false;
				}
				return true;
			}

	  // checks open bracket
			public static boolean oBracket(int k) {
				if(!openBracket.contains(hm.get(k))){
					
	            
	            return false;
				}
				return true;
			}

	  // checks closed bracket
			public static boolean cBracket(int k) {
				if(!closeBracket.contains(hm.get(k))){
					return false;
				}
				return true;
			}

	  // checks operation
			public static boolean oP(int k) {
				if(plus_op.contains(hm.get(k)) || minus_op.contains(hm.get(k))){
					return true;
				}
				
				return false;
			}
			
	  // checks declaration of the try program 
			public static void declares(int k) {
				sc(j);
			    
				if (kW(j)){
					j++;
					
					if(iD(j)){	
						j++;
						
						declares(j);}
					else {System.out.print("");}
					
				}
				else {
					
					return;	
				}
			 
			}

	  // checks through expression toreturn value of error if there is one
			public static void expr(int k) {
				sc(j);
				
				if(iD(j)){j++;
					if (oP(j)) {j++;
					
						expr(j);
						
						}
					else if(hm.get(j).equals("–")) { Error += "\nError at " + hm.get(j) + ", it is an Illegal character and should be an operator";}
					 else {j++; expr(j);}
					   
				}
				
				
			}

	  //checks through assignment amd prints the error if there is one
			public static void assign(int k) {
				
				sc(j);
				
			    //if (hm.get(k+1))
				if (iD(j)){
					j++;

        
					
					if(eQ(j)){	
						j++;
						
						expr(j);}
					else {
						Error += "Error found at " + hm.get(j) + ", needs to be an =";}
							
				}
				else {
					
					return;	
				}}
			 
			

	  // checks entire BNF Grammar
//THIS IS THE PARSER
		public static void isBNF(int k) {
			k = 0;
			//while(hm.get(j) != null) {
				sc(j);
				//if (k > 25) { break;}
				if(kW(j)) {j++; 
					if(iD(j)){j++; isBNF(j); }}
				
				else if(oBrace(j)) {j++; 
					if(kW(j)){j++; 
						if(iD(j+4)){j++; 
							if(cBrace(j)) {j++; isBNF(j);}}}
					
					else{
						if(cBrace(j)) {
							Error += "\nError at " + hm.get(j) + " Braces need a keyword and identifier"; j++; isBNF(j);}
						}
					    
					}
				else if(oBracket(j)) {
					
					j++;
					declares(j);
					
					assign(j);
					if (Error.equals("\nThe first syntax error is ")) {
						System.out.print("This program was generated by the try bnf grammer ");
						
					     
					}
					else {System.out.print("This program was not generated by the try bnf grammer");System.out.println(Error);
				   
					}
				}
				
	}}