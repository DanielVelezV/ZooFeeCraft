package org.example.elwarriorcito.zoofee.Utils;

import java.util.Vector;

public class ZooMobsSerializer {

    String EntityUUID;
    String Name;
    String Age;
    String Sex;
    Vector Location;

    public ZooMobsSerializer(String entityUUID, String name, String age, String sex) {
        EntityUUID = entityUUID;
        Name = name;
        Age = age;
        Sex = sex;
    }
}
