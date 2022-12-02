package com.sparta.jn;

import java.util.ArrayList;

public class TestSample {

    public static void main(String[] args) {
        DataDriven dataDriven = new DataDriven();
        ArrayList data = dataDriven.getData("Add Profile");
        System.out.println(data);
    }
}
