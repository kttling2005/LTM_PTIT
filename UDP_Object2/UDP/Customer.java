package UDP;

import java.io.*;

public class Customer implements Serializable {
    private static final long serialVersionUID = 20151107;
    
    private String id;
    private String code;
    private String name;
    private String dayOfBirth;
    private String userName;
    
    public Customer(String id, String code, String name, String dayOfBirth, String userName){
        this.id = id;
        this.code = code;
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.userName = userName;
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
    
    public String getDayOfBirth(){
        return this.dayOfBirth;
    }
    
    public String getUserName(){
        return this.userName;
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
    
    public void setDayOfBirth(String dayOfBirth){
        this.dayOfBirth = dayOfBirth;
    }
    
    public void setUserName(String userName){
        this.userName = userName;
    }
    
    public String show(){
        return (this.id+" "+this.code+" "+this.name+" "+this.dayOfBirth+" "+this.userName);
    }
}
