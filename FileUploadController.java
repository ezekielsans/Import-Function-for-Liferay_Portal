/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import java.io.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.primefaces.model.DefaultUploadedFile;

/**
 *
 * @author mis12
 */
@ManagedBean

@RequestScoped
public class FileUploadController implements Serializable {

    /**
     * @Declare Properties
     *
     */
    @ManagedProperty(value = "#{customEntityManagerFactory}")
    private CustomEntityManagerFactory customEntityManagerFactory;
    @ManagedProperty(value = "#{fileUploadData}")
    private FileUploadData fileUploadData;
    @ManagedProperty(value = "#{dbConnection}")
    private DbConnection dbConnection;
    @ManagedProperty(value = "#{portalData}")
    private PortalData portalData;

    /**
     * @Declare Variables
     *
     */
    private UploadedFile simpleFileUpload;

    /**
     * @Declare List
     *
     */
    public CustomEntityManagerFactory getCustomEntityManagerFactory() {
        return customEntityManagerFactory == null ? customEntityManagerFactory = new CustomEntityManagerFactory() : customEntityManagerFactory;
    }

    public void setCustomEntityManagerFactory(CustomEntityManagerFactory customEntityManagerFactory) {
        this.customEntityManagerFactory = customEntityManagerFactory;

    }

    public FileUploadData getFileUploadData() {
        return fileUploadData == null ? fileUploadData = new FileUploadData() : fileUploadData;
    }

    public void setFileUploadData(FileUploadData fileUploadData) {
        this.fileUploadData = fileUploadData;
    }

    public DbConnection getDbConnection() {
        return dbConnection == null ? dbConnection = new DbConnection() : dbConnection;
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public PortalData getPortalData() {
        return portalData == null ? portalData = new PortalData() : portalData;
    }

    public void setPortalData(PortalData portalData) {
        this.portalData = portalData;
    }

    /**
     * @Declare Getters and Setters
     *
     */
    public UploadedFile getSimpleFileUpload() {
        return simpleFileUpload;
    }

    public void setSimpleFileUpload(UploadedFile simpleFileUpload) {
        this.simpleFileUpload = simpleFileUpload;
    }

    /**
     * @param event
     * @Declare Methods
     *
     */
    public void upload(FileUploadEvent event) throws IOException {

        UploadedFile uploadedFile = event.getFile();
        System.out.println("UPLOADED FILE " + uploadedFile.getFileName());

        try (InputStream fileContent = uploadedFile.getInputstream()) {

            System.out.println("May laman " + fileContent);
            XSSFWorkbook workbook = new XSSFWorkbook(fileContent);

            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getPhysicalNumberOfRows();

            System.out.println("No of rows: " + rowCount);
//= sheet.getRow(0);
            int i = 0;
            XSSFRow row;
            while ((row = sheet.getRow(i)) != null) {

                getFileUploadData().getUploadedList().add(new Object[]{
                    row.getCell(0).getStringCellValue(),
                    row.getCell(1).getStringCellValue(),
                    row.getCell(2).getStringCellValue(),
                    row.getCell(3).getStringCellValue(),
                    row.getCell(4).getStringCellValue()
                });
                System.out.println("Content of file " + row.getCell(0).getStringCellValue());
                System.out.println("Content of file " + row.getCell(1).getStringCellValue());
                System.out.println("Content of file " + row.getCell(2).getStringCellValue());
                System.out.println("Content of file " + row.getCell(3).getStringCellValue());
                System.out.println("Content of file " + row.getCell(4).getStringCellValue());
                i++;

            }
        } catch (IOException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to process the uploaded file."));

        }

    }

    public void init() {
        if (FacesContext.getCurrentInstance().isPostback() == false) {

        }
        // initExcel();
    }
    /*
    
     WORKING STATIC FILE READING

     public void initExcel() {
     try {

     System.out.println("pumasok dito");
     // String projectDirectory = System.getProperty(uploadedFileName);
     String fileName = "sample_2.xlsx";
     String filePath = "/home/mis/Projects/Projects/zoom101/web/fileUpload";

     File file = new File(filePath, fileName);
     System.out.println("May laman " + file);
     XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));

     XSSFSheet sheet = workbook.getSheetAt(0);

     int rowCount = sheet.getPhysicalNumberOfRows();

     System.out.println("No of rows: " + rowCount);

     XSSFRow row = sheet.getRow(0);
     int i = 0;

     while ((row = sheet.getRow(i)) != null) {
     System.out.println("This is the first row of file " + row.getCell(0).getStringCellValue() + "This is the 2nd row" + row.getCell(1).getStringCellValue() + "Content of the 3rd row" + row.getCell(2).getStringCellValue() + "Content of the 4th row" + row.getCell(3).getStringCellValue() + "Content of the 5th row" + row.getCell(4).getStringCellValue());

     System.out.println("Content of file " + row.getCell(1).getStringCellValue());
     System.out.println("Content of file " + row.getCell(2).getStringCellValue());
     System.out.println("Content of file " + row.getCell(3).getStringCellValue());
     System.out.println("Content of file " + row.getCell(4).getStringCellValue());
     i++;
     }
     } catch (IOException e) {
     e.printStackTrace();

     System.out.println("May error ka ");
     }
     }
     */

}
