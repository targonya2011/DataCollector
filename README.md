# DataCollector
The program that collects data from different sources and writes two JSON files. Parsing of different data takes place in different classes.
Web page parsing class. What happens in it: 

getting the HTML code of the page "List of Moscow Metro stations" using the jsoup library; 

parsing the received page and getting the following data from it: 

Moscow metro lines (name and line number, no color needed); 

Moscow metro stations (station name and line number). 
 

A class for searching files in folders. In the methods of this class, traversal of folders lying in the archive is implemented. Unzipping and code that will crawl all subfolders and search for JSON and CSV files in them (with extensions *.json and *.csv). The method for traversing folders takes the path to the folder in which to search. 
 

A class for parsing JSON format files. Classes have been created to store data from these files. Implemented a code that will accept JSON data and output a list of objects corresponding to them. 
 

A class for parsing CSV files. Classes have been created to store data from these files. Implemented a code that will accept CSV data and issue a list

{
    "stations": [
          {
                "name": "Название станции",
                "line": "Название линии",
                "date": "Дата открытия в формате 19.01.2005",
                "depth": "Глубина станции в виде числа",
                "hasConnection": "Есть ли на станции переход"
          },
          …
    ]
}![image](https://user-images.githubusercontent.com/59205706/236648657-184c2080-7720-4a28-b5c0-1484558dbfdb.png)
