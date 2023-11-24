/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author mis12
 */
@ManagedBean
@SessionScoped
public class FileUploadData implements Serializable {

    /**
     * Creates a new instance of FileUploadData
     */
    public FileUploadData() {
    }
    /**
     * @Declare Properties
     *
     */
    private Integer maxFileSize;
    private List<Object[]> uploadedList;

    @ManagedProperty(value = "#{customEntityManagerFactory}")
    private CustomEntityManagerFactory customEntityManagerFactory;

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

    public List<Object[]> getUploadedList() {
        return uploadedList == null ? uploadedList = new ArrayList<>() : uploadedList;
    }

    public void setUploadedList(List<Object[]> uploadedList) {
        this.uploadedList = uploadedList;
    }

    /**
     * @Declare Getters and Setters
     *
     */
    public Integer getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(Integer maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

}
