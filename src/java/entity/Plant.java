/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plant")
public class Plant implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "sm_befor", length = 45, nullable = false)
    private String befor_soil_moisture;
    
    @Column(name = "sm_after", length = 45, nullable = false)
    private String after_soil_moisture;

    @Column(name = "date", nullable = false)
    private Date date;
    
    @Column(name = "start_time", nullable = false)
    private Date start_time;
    

    public Plant(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public String getBefor_soil_moisture() {
        return befor_soil_moisture;
    }

    public void setBefor_soil_moisture(String befor_soil_moisture) {
        this.befor_soil_moisture = befor_soil_moisture;
    }

    public String getAfter_soil_moisture() {
        return after_soil_moisture;
    }

    public void setAfter_soil_moisture(String after_soil_moisture) {
        this.after_soil_moisture = after_soil_moisture;
    }

    
    

}
