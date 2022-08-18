package vn.aptech.warehouse.excelhelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.aptech.warehouse.entity.Supplier;

public class SupplierEHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"Sup_code", "Name", "Address", "Email", "City", "Tax Code", "Active"};
    static String SHEET = "Suppliers";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Supplier> excelToSuppliers(InputStream is) {
        try {
            List<Supplier> suppliers;
            Workbook workbook = new XSSFWorkbook(is);
                Sheet sheet = workbook.getSheet(SHEET);
                Iterator<Row> rows = sheet.iterator();
                suppliers = new ArrayList<Supplier>();
                int rowNumber = 0;
                while (rows.hasNext()) {
                    Row currentRow = rows.next();
                    // skip header
                    if (rowNumber == 0) {
                        rowNumber++;
                        continue;
                    }
                    if (isRowEmpty(currentRow)) {
                        continue;
                    }
                    Iterator<Cell> cellsInRow = currentRow.iterator();
                    Supplier supplier = new Supplier();
                    int cellIdx = 0;
                    while (cellsInRow.hasNext()) {
                        Cell currentCell = cellsInRow.next();
                        switch (cellIdx) {
                            case 0:
                                supplier.setSup_code(currentCell.getStringCellValue());
                                break;
                            case 1:
                                supplier.setSup_name(currentCell.getStringCellValue());
                                break;
                            case 2:
                                supplier.setSup_address(currentCell.getStringCellValue());
                                break;
                            case 3:
                                supplier.setSup_email(currentCell.getStringCellValue());
                                break;
                            case 4:
                                supplier.setCity(currentCell.getStringCellValue());
                                break;
                            case 5:
                                supplier.setTax_code(currentCell.getStringCellValue());
                                break;
                            case 6:
                                supplier.setActive(currentCell.getBooleanCellValue());
                                break;
                            default:
                                break;
                        }
                        cellIdx++;
                    }
                    suppliers.add(supplier);
                }
                workbook.close();
        
            return suppliers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
//    public static ByteArrayInputStream suppliersToExcel(List<Supplier> suppliers) {
//        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
//            Sheet sheet = workbook.createSheet(SHEET);
//            // Header
//            Row headrow = sheet.createRow(0);
//            for (int col = 0; col < HEADERs.length; col++) {
//                Cell cell = headrow.createCell(col);
//                cell.setCellValue(HEADERs[col]);
//            }
//            int rowIdx = 1;
//            for (Supplier supplier: suppliers) {
//                Row row = sheet.createRow(rowIdx++);
//                row.createCell(0).setCellValue(supplier.getSup_code());
//                row.createCell(1).setCellValue(supplier.getSup_name());
//                row.createCell(2).setCellValue(supplier.getSup_address());
//                row.createCell(3).setCellValue(supplier.getSup_email());
//                row.createCell(4).setCellValue(supplier.getCity());
//                row.createCell(5).setCellValue(supplier.getTax_code());
//                row.createCell(6).setCellValue(supplier.getActive());
//            }
//            workbook.write(out);
//            return new ByteArrayInputStream(out.toByteArray());
//        } catch (IOException e) {
//            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
//        }
//    }

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Supplier> suppliers;
     
    public SupplierEHelper(List<Supplier> suppliers) {
        this.suppliers = suppliers;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Suppliers");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "Sup_code", style);      
        createCell(row, 1, "Name", style);       
        createCell(row, 2, "Address", style);    
        createCell(row, 3, "Email", style);
        createCell(row, 4, "City", style);
        createCell(row, 5, "Tax Code", style);
        createCell(row, 6, "Active", style);
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Supplier supplier : suppliers) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, supplier.getSup_code(), style);
            createCell(row, columnCount++, supplier.getSup_name(), style);
            createCell(row, columnCount++, supplier.getSup_address(), style);
            createCell(row, columnCount++, supplier.getSup_email(), style);
            createCell(row, columnCount++, supplier.getCity(), style);
            createCell(row, columnCount++, supplier.getTax_code(), style);
            if (supplier.getActive()==true) {
                createCell(row, columnCount++, "Active", style);
            } else {
                createCell(row, columnCount++, "Inactive", style);
            }
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }

    public static boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK)
                return false;
        }
        return true;
    }
}
