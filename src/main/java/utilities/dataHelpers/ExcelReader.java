package utilities.dataHelpers;

import exceptions.ExcelReaderException;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private static Logger log = LogManager.getLogger();

    /**
     *
     * @param filePtah
     * @param sheetName
     */

    public  ExcelReader(String filePtah,String sheetName) {
        try {
            workbook=new XSSFWorkbook(filePtah);
            sheet=workbook.getSheet(sheetName);
        }catch (Exception e){
            log.error("there is an issue occurred during initializing Excel reader");
            e.fillInStackTrace();
        }
    }

    /**
     *
     * @return int number of the available rows at Excel sheet
     */
    public int getRowCount(){
        int row=sheet.getPhysicalNumberOfRows();
        return row;
    }

    /**
     *
     * @return int number of the available columns at Excel sheet
     */
    public int getColCount(){
        int col = sheet.getRow(0).getPhysicalNumberOfCells();
        return col;
    }

    /**
     *
     * @param row int row index
     * @param col int column index
     * @return Object
     */
    @SneakyThrows
    public Object getCellData(int row, int col){
        Cell cell=sheet.getRow(row).getCell(col);
        CellType cellType=cell.getCellType();
        switch (cellType){
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case ERROR:
                return cell.getErrorCellValue();
            default:
                log.error("no supported input");
                throw new ExcelReaderException("no supported input");
        }
    }

    /**
     *
     * @param row int row index
     * @param col int column index
     * @return 2-D array with its headers (e.g. username , password ..)
     */
    public Object [][] getData(int row,int col){
        Object[][] array=new Object[row][col];
        for (var i=0;i<row;i++){
            for (var j=0;j<col;j++){
                array[i][j]=getCellData(i,j);
            }
        }
        return array;
    }

    public static void main(String[] args) {

    }


}
