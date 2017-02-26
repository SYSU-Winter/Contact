package sysu.lwt.contacts;

import java.io.Serializable;

/**
 * Created by 12136 on 2017/2/25.
 */

public class Information implements Serializable{
    private String name, phone_number, attribution, color;

    public Information(String name, String phone_number, String attribution, String color) {
        this.name = name;
        this.attribution = attribution;
        this.color = color;
        this.phone_number = phone_number;
    }

    public String getFirst_name() {
        return name.substring(0, 1);
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public  String getAttribution() {
        return attribution;
    }

    public String getColor() {
        return color;
    }
}
