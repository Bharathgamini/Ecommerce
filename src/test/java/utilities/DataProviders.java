package utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	// DataProvider 1: Login Data from Sheet1
	@DataProvider(name="LoginData")
	public String [][] getLoginData() throws IOException
	{
		String path = ".//testdata//Amazon_DDT.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);
		
		List<String[]> dataList = new ArrayList<>();
		
		for(int i=1; i<totalrows; i++) // starting from row 1 (excluding header if present)
		{
			boolean isRowEmpty = true;
			String[] rowData = new String[totalcols];
			
			for(int j=0; j<totalcols; j++)
			{
				String cellData = xlutil.getCellData("Sheet1", i, j);
				rowData[j] = cellData;
				
				if(cellData != null && !cellData.trim().isEmpty()) {
					isRowEmpty = false;
				}
			}
			
			if(!isRowEmpty) {
				dataList.add(rowData);
			}
		}
		
		return dataList.toArray(new String[0][0]);
	}
	
	@DataProvider(name="SearchData")
	public String [][] getesarchdata() throws IOException
	{
		String path = ".//testdata//Amazon_DDT.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrows = xlutil.getRowCount("Sheet2");
		int totalcols = xlutil.getCellCount("Sheet2", 1);
		
		List<String[]> dataList = new ArrayList<>();
		
		for(int i=1; i<totalrows; i++) // starting from row 1 (excluding header if present)
		{
			boolean isRowEmpty = true;
			String[] rowData = new String[totalcols];
			
			for(int j=0; j<totalcols; j++)
			{
				String cellData = xlutil.getCellData("Sheet2", i, j);
				rowData[j] = cellData;
				
				if(cellData != null && !cellData.trim().isEmpty()) {
					isRowEmpty = false;
				}
			}
			
			if(!isRowEmpty) {
				dataList.add(rowData);
			}
		}
		
		return dataList.toArray(new String[0][0]);
	}
}
	