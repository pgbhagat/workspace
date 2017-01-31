Class with main method and to be executed/run as application is 'com.stock.main.StockManager'.

1. Extract the given ZIP at any location
2. Unzip it.
3. Locate all source code under root folder 'StockManager'
4. Open eclipse
5. Import 'Existing projects into workspace'
6. Navigate to 'StockManager' and import StockManager project.
7. Copy required library to folder 'StockManager\libs' and have these included in build path, build the full project
8. Run main class 'com.stock.main.StockManager' as Java application
9. Look out at Eclipse console for user interaction options e.g. get current stock price, get year high, get year low
10. Exit the code using appropriate choice on Eclipse console.

Notes-
1.The stock details CSV will be dumped at 'StockManager\result\stock_details.txt'
2. Stock options to be crawled should be provided in 'StockManager\resource\Stocks.txt'

3rh Party Libraries-
1. This project needs JUnit4 library which normally JRE supplies.
2. This project also need fasterxml library, one will need to download them and include them in build path.
	2.a com.fasterxml.jackson.annotations.jar
	2.b com.fasterxml.jackson.core.jar
	2.c.com.fasterxml.jackson.databind.jar

	Download these fasterxml jars from internet and Copy them to 'StockManager\libs' folder


