/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.excelhelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import vn.aptech.warehouse.entity.Customer;

/**
 *
 * @author thien
 */
public class CustomerEHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//    static String[] HEADERs = {"Customer Code", "Name", "Address", "City", "Country", "E-mail", "Phone", "note", "Short Name", "Tax Code"};
    static String SHEET = "Customers";

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Customer> excelToCustomers(InputStream is) {
        try {
            List<Customer> customers;
            Workbook workbook = new XSSFWorkbook(is);
                Sheet sheet = workbook.getSheet(SHEET);
                Iterator<Row> rows = sheet.iterator();
                customers = new ArrayList<Customer>();
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
                    Customer customer = new Customer();
                    int cellIdx = 0;
                    while (cellsInRow.hasNext()) {
                        Cell currentCell = cellsInRow.next();
                        if (currentCell == null || currentCell.getCellType() == CellType.BLANK) {
                            continue;
                        }
                        switch (cellIdx) {
                            case 0:
                                customer.setCust_code(currentCell.getStringCellValue());
                                break;
                            case 1:
                                customer.setCust_name(currentCell.getStringCellValue());
                                break;
                            case 2:
                                customer.setAddress(currentCell.getStringCellValue());
                                break;
                            case 3:
                                customer.setCity(currentCell.getStringCellValue());
                                break;
                            case 4:
                                customer.setCountry(currentCell.getStringCellValue());
                                break;
                            case 5:
                                customer.setEmail(currentCell.getStringCellValue());
                                break;
                            case 6:
                                customer.setPhone(currentCell.getStringCellValue());
                                break;
                            case 7:
                                customer.setNote(currentCell.getStringCellValue());
                                break;
                            case 8:
                                customer.setShort_name(currentCell.getStringCellValue());
                                break;
                            case 9:
                                customer.setTax_code(currentCell.getStringCellValue());
                                break;
                            case 10:
                                customer.setActive(currentCell.getBooleanCellValue());
                                break;
                            default:
                                break;
                        }
                        cellIdx++;
                    }
                    customers.add(customer);
                }
                workbook.close();
        
            return customers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final List<Customer> customers;
    private final String excelFilePath = "Customer.xlsx";
    
    public CustomerEHelper(List<Customer> customers) {
        this.customers = customers;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Customers");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "Customer Code", style);      
        createCell(row, 1, "Name", style);       
        createCell(row, 2, "Address", style);    
        createCell(row, 3, "City", style);
        createCell(row, 4, "Country", style);
        createCell(row, 5, "E-mail", style);
        createCell(row, 6, "Phone", style);
        createCell(row, 7, "Note", style);
        createCell(row, 8, "Short Name", style);
        createCell(row, 9, "Tax Code", style);
        createCell(row, 10, "Active", style);
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
        sheet.autoSizeColumn(columnCount);
    }
     
    private void writeDataLines() throws FileNotFoundException, IOException {
        int rowCount = 8;
        
//        XSSFFont font = workbook.createFont();
//        font.setFontHeight(14);
//        style.setFont(font);
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        workbook = (XSSFWorkbook) WorkbookFactory.create(inputStream);
        
        sheet = workbook.getSheetAt(0);
        XSSFFont font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeight(18);
        
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setFont(font);
        
        for (Customer customer : customers) {
            Row row = sheet.getRow(rowCount++);
            int columnCount = 1;
            int rowNum = 1;
            createCell(row, columnCount++, customer.getCust_name(), style);
            createCell(row, columnCount++, customer.getShort_name(), style);
            createCell(row, columnCount++, customer.getAddress(), style);
            createCell(row, columnCount++, customer.getCity(), style);
            createCell(row, columnCount++, customer.getCountry(), style);
            createCell(row, columnCount++, customer.getEmail(), style);
            createCell(row, columnCount++, customer.getPhone(), style);
            createCell(row, columnCount++, customer.getNote(), style);
            createCell(row, columnCount++, customer.getTax_code(), style);
            rowNum++;
        }
        
        inputStream.close();
        
//        FileOutputStream outputStream = new FileOutputStream("SaleOrder1.xls");
//        workbook.write(outputStream);
//        workbook.close();
//        outputStream.close();
    }
     
    public void export(HttpServletResponse response) throws IOException {
//        writeHeaderLine();
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
