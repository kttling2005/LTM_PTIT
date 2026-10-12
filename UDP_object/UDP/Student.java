
package UDP;

import java.io.*;

public class Student implements Serializable{
    private static final long serialVersionUID = 20171107;
    //khai bao qua trinh chuyen doi 1 doi tuong Java trong bo nho thanh 1 chuoi byte de ghi xuong o cung 
    private String id;
    private String code;
    private String name;
    private String email;
    
    public Student(String id, String code, String name, String email){
        this.id = id;
        this.code = code;
        this.name = name;
        this.email = email;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getCode(){
        return this.code;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String show(){
        return (this.id +" " +this.name+" "+ this.email+" "+this.code);
    }
}
