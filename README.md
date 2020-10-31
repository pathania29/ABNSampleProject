
## ABNSampleProject

 ABNSampleProject is an basic maven java project . The project simply reads an Input.txt file from resources folder , applies custom logic on data and generates an Output.csv

**Input.txt**
  System A has produced the file Input.txt, which is a Fixed Width text file that contains the   Future Transactions done by client 1234

**Output.csv**  
  The CSV has the following Headers  :
   
 1. Client_Information      
 2. Product_Information    
 3. Total_Transaction_Amount
  
**Client_Information** 
This column is a combination of the CLIENT TYPE, CLIENT NUMBER, ACCOUNT NUMBER, SUBACCOUNT NUMBER fields from Input file.
  
**Product_Information** 
This column is a combination of the EXCHANGE CODE, PRODUCT GROUP CODE, SYMBOL, EXPIRATION DATE 

**Total_Transaction_Amount** 
This column is a Net Total of the (QUANTITY LONG - QUANTITY SHORT) values for each client per product

**prerequisites** 
1. JDK 1.8 is installed
2. Maven is installed

How to build Project

mvn clean install

How to run Project

java -jar ABNSampleProject-1.0.0.jar