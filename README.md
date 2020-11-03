

## ABNSampleProject

 ABNSampleProject is a basic maven java project. 
 
 This project reads an Input.txt file from a resources folder, applies custom logic on data and generates an Output.csv.
 
 users can also pass input.txt file as run-time argument and in this case this application will use user supplied input data.
 
 ABNSampleProject can be configured to run in a multi-threaded or single-threaded env, depending upon size of input file.
 
 It also generates error records for all records which fail to be processed.
 
 Users can also go through Application.log file to check logs, which is also created when this application runs.
 
 Please unzip the folder and read README.md for setting up, running and debugging the application

**Pre-Requisites** 
1. JDK 1.8 is installed  

2. Maven is installed  

Note to check Maven and Java version installed on your machine. please run below command.

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

We can read the input.txt file using single thread or multi thread. Depending upon the size of input file being processed.
If the file size is small then we can use Single threaded reader and for bigger size files we can use multi-threaded Reader.

**Note**
Right now only developers can choose whether to run multi-threaded readers or single-threaded and a number of threads in Thread-pool.
The configuration is by design because multi-threading is a complex topic and 
assumption is made that business users don't understand it.

Go to targets folder or Copy the  executable jar "**ABNSampleProject-1.0.0.jar**" from **target** to any folder.
And run below command.

1. **Multi Thread Run:** Using default input.txt which is present inside jar. 
   
     **java -jar ABNSampleProject-1.0.0.jar**
     
2. **Single Thread Run:** custom input.txt provided by user during run-time. 
   
   **C:\Code\ABNSampleProject>java -jar ABNSampleProject-1.0.0.jar "Input file to be used" "Output CSV Name"**

   This will create 3 files in same folder.
    1. output.csv: Output CSV File
    2. Application.log: Log file for your run
    3. ErrorRecords.txt : List of transaction which were not processed because of any issue with input.txt.

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

**Troubleshooting**

1. Check Application.log file to see any exceptions.
2. Open Error Records text file to check records that were not processed.
3. Correct the ErrorRecords.txt and run the report on these records only.

   > **C:\Code\ABNSampleProject>java -jar ABNSampleProject-1.0.0.jar "%PATH%/ErrorRecords.txt" "ErrorOutput.csv"**

**Steps to configure code on IDE**

1. Open any IDE (Integrated Development Environment) and create new project. 
2. Below steps explain how to import it to IntelliJ IDEA. Same steps can be    done on Eclipse too.
3. Go to
 
   File -> new -> Projects from existing resources
   
   select pom.xml (C:\Code\ABNSampleProject\pom.xml)
   
   click ok
   
   IntelliJ will automatically download all dependencies and create a project for you.
   
**Important Java classes**

    1. AbnMain.java is the main class.
    2. MultiThreadReader.java reads the input.txt file.
    3. Task.java: Callable task to create one row of output data
    3. SingleThreadReader.java reads the input.txt file.
    3. CSVColumnDataWriterUtil.java contains logic for columns data.
    4. CSVOutputResultGenerator.java writes/creates Output.csv and error rcords txt file

**Output.csv**  
  
  The CSV has the following Headers :
   
 1. Client_Information      
 2. Product_Information    
 3. Total_Transaction_Amount
  
**Client_Information** 
This column is a combination of the CLIENT TYPE, CLIENT NUMBER, ACCOUNT NUMBER, SUBACCOUNT NUMBER fields from Input file.
  
**Product_Information** 
This column is a combination of the EXCHANGE CODE, PRODUCT GROUP CODE, SYMBOL, EXPIRATION DATE 

**Total_Transaction_Amount** 
This column is a Net Total of the (QUANTITY LONG - QUANTITY SHORT) values for each client per product.





  




