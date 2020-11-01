

## ABNSampleProject

 ABNSampleProject is an basic maven java project . The project reads an Input.txt file from resources folder , applies custom logic on data and generates an Output.csv

**Pre-Requisites** 
1. JDK 1.8 is installed  

2. Maven is installed  
        
> C:\Code\ABNSampleProject>mvn --version
>
>      Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
>
>      Maven home: C:\maven\apache-maven-3.6.3\bin\..
>
>      Java version: 1.8.0_65, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk1.8.0_65\jre
>
>      Default locale: en_US, platform encoding: Cp1252
>
>      OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"

 
**How to build the software**  

1. Copy to zip file to your local directory.
     e.g. C:\Code\ABNSampleProject.rar
     
2. Unzip the file to that folder. 
   
   This step copies the sample project to your local machine.
     
      e.g: C:\Code\ABNSampleProject

>     Directory: C:\Code\ABNSampleProject
> 
>     d-----         30-Oct-20   2:48 PM                src
>
>     d-----         01-Nov-20   1:23 PM                target
>
>     -a----         30-Oct-20   2:48 PM             81 ABNSampleProject.iml
>
>     -a----         31-Oct-20  11:19 PM             44 CHANGELOG.md
>
>     -a----         01-Nov-20   1:23 PM           2132 dependency-reduced-pom.xml
>
>     -a----         01-Nov-20   1:58 AM           3902 pom.xml
>
>     -a----         01-Nov-20  12:41 PM           1397 README.md
   
3. Go to your cmd prompt and run:

>  C:\Code\ABNSampleProject = **mvn clean install**

   This command will create an executable jar "**ABNSampleProject-1.0.0.jar**" inside **target** folder.  

> Example:
>
>      C:\Code\ABNSampleProject>mvn clean install
>
>      [INFO] Scanning for projects...
>
>      [INFO]
>      [INFO] -----------------< ABNSampleProject:ABNSampleProject >------------------
>      [INFO] Building ABNSampleProject 1.0.0
>      [INFO] --------------------------------[ jar ]---------------------------------
>      [INFO]
>      [INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ ABNSampleProject ---
>
>      .
>      .
>      .
> 
>      INFO] ------------------------------------------------------------------------
>      [INFO] BUILD SUCCESS
>      [INFO] ------------------------------------------------------------------------
>      [INFO] Total time:  11.320 s
>      [INFO] Finished at: 2020-11-01T13:23:56+11:00
>      [INFO] ------------------------------------------------------------------------

**How to run Project**  

Go to targets folder or Copy the  executable jar "**ABNSampleProject-1.0.0.jar**" from **target** to any folder.
And run below command.
   
     **java -jar ABNSampleProject-1.0.0.jar**

This will create Output.csv and Application.log files in same folder.

>  C:\Temp> ls
>
>  Mode                 LastWriteTime         Length Name
>
>  -a----         01-Nov-20   1:23 PM       12391804 ABNSampleProject-1.0.0.jar
>
>  C:\Temp> java -jar ABNSampleProject-1.0.0.jar
>
>  2020-11-01 13:37:48 INFO  AbnMain:19 - Starting main method
>
>  2020-11-01 13:37:48 INFO  CsvOutputMappingBuilder:28 -  Started reading Input.txt
>
>  2020-11-01 13:37:48 INFO  CsvOutputMappingBuilder:39 - Input.txt read.
>
>  2020-11-01 13:37:48 INFO  CSVOutputResultGenerator:27 - Generating... output.csv
>
>  2020-11-01 13:37:48 INFO  CSVOutputResultGenerator:33 - output.csv generated !!!
>
>  C:\Temp> ls
>
>  Mode                 LastWriteTime         Length Name
>
>  -a----         01-Nov-20   1:23 PM       12391804 ABNSampleProject-1.0.0.jar
>
>  -a----         01-Nov-20   1:37 PM            381 Application.log
>
>  -a----         01-Nov-20   1:37 PM         117739 output.csv 
>
>  C:\Temp>

**How to run Junit test cases**

Go to root folder and run **mvn clean test**

> `C:\Code\ABNSampleProject = mvn clean test`

**Troubleshooting And Important Java classes**

1. Check Application.log file to see any exceptions.
2. Open any IDE (Integrated Development Environment) and create new project. 
3. Below steps explain how to import it to IntelliJ IDEA. Same steps can be done on Eclipse too.
4. Go to 
   File -> new -> Projects from existing resources
   select pom.xml (C:\Code\ABNSampleProject\pom.xml)
   click ok
   IntelliJ will automatically download all dependencies and create a project for you.
5. AbnMain.java is the main class.
6. CsvOutputMappingBuilder.java is class which reads the input.txt file.
7. CSVColumnDataExtractorUtil.java is the class that contains methods to create column data.
8. CSVOutputResultGenerator.java is the class that writes/creates Output.csv

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
This column is a Net Total of the (QUANTITY LONG - QUANTITY SHORT) values for each client per product.





  




