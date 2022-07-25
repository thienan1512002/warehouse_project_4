package vn.aptech.warehouse.excelhelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
            try (Workbook workbook = new XSSFWorkbook(is)) {
                Sheet sheet = workbook.getSheet(SHEET);
                Iterator<Row> rows = sheet.iterator();
                suppliers = new ArrayList<>();
                int rowNumber = 0;
                while (rows.hasNext()) {
                    Row currentRow = rows.next();
                    // skip header
                    if (rowNumber == 0) {
                        rowNumber++;
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
            }
            return suppliers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
