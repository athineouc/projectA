How to run::

1. Download and extract the project

2. gradle wrapper

3. ./gradlew build

4. Copy your csv file or Create one with CreateCsvFile.py script
    4.1 How to create csv file:
    for small size(2mb) :: python3 CreateCsvFile.py small
    for medium size(500mb) :: python3 CreateCsvFile.py medium
    for large size(2gb) :: python3 CreateCsvFile.py large (it take some time...)

5. java -Xmx{size}m -jar build/libs/projectA.jar -fileName={fileName}
    
    Arguments::
    {size} = the heap memory of the application (m :: in megabytes)
    {fileName} = the name of the file you want to process
    
    example:
    java -Xmx100m -jar build/libs/projectA.jar -fileName=small.csv
    size = 100mb
    fileName = small.csv



Solution::

For the solution of this tast i am using Spring Batch, a framework for batch processing.
In a few words Spring Batch provides you the functionality to configure jobs to procees 
a big mount of data from many resourse e.g. relation databases, csv files...
Every job is consisting of some steps, and every step is consisting of 3 items:
ItemReader : to read the data and mapping them to java objects
ItemProcessor : to process the data (bussines logic) 
ItemWriter : to write the data to the destination source

In my solution i have configure the ItemReader to read from a csv file given by the
user as argument and mapping the rows to Person objects with 3 attributes
(mail, firstName, lastName). In case of a valid row i am handling thrown by 
spring batch and continue to the next line.

For the ItemProcessor i have override the process method to just validate if the email
of a person is valid to sent an email. In case of valid email return the person object
that it will be added to a list that takes the itemWritter as argument from sping batch,
otherwise return null.

For ItemWritter i have override the write method to take a processed list of Persons,
filter the null values, sleepping 0.5 seconds to emulate the process of sending email.

About memory::

In Spring Batch is very easy to configure the chunk size of every step.
chunk size is the number of rows every time you load in memory as person objects to process.
I have configured the chunk size to 100.000(can found it in Constans.java file)
We can't know exactly the size of a Person object because it has 3 string attributes 
A simple solution is to consider that the compination of these 3 are ~ 150bytes.
150bytes * 100.000entries = 15000000bytes ~ 14,5mb
but again we can't be sure of this size, so a very safe solution is to provide 100mb heap
memory to application

