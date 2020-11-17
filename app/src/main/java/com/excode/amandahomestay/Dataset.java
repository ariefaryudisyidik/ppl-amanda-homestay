package com.excode.amandahomestay;

import java.util.ArrayList;

public class Dataset {
    private static String[] tenantNames = {
            "Asep Ramadhan",
            "Mujiyanto"
    };

    private static String[] tenantDetails = {
            "Nomor Kamar\t: A1" +
                    "\nNomor Telepon\t: 082161916114" +
                    "\nJenis Kelamin: laki - laki" +
                    "\nAlamat Penyewa: Bandar Lampung" +
                    "\nEmail Penyewa: asepramadhan@gmail.com",
            "Nomor Kamar\t: B2" +
                    "\nNomor Telepon\t: 085609209492" +
                    "\nJenis Kelamin: laki - laki" +
                    "\nAlamat Penyewa: Bandar Lampung" +
                    "\nEmail Penyewa: mujiyanto@gmail.com"
    };

    private static int[] tenantImages = {
            R.drawable.ic_tenant_data,
            R.drawable.ic_tenant_data
    };

    private static String[] roomNumbers = {
            "A1",
            "A2",
            "A3",
            "A4",
            "A5",
            "B1",
            "B2",
            "B3",
            "B4",
            "B5",
            "C1",
            "C2",
            "C3",
            "C4",
            "C5",
            "D1",
            "D2",
            "D3",
            "D4",
            "D5",
    };

    static ArrayList<GetterSetter> getListDataTenant() {
        ArrayList<GetterSetter> list = new ArrayList<>();
        for (int position = 0; position < tenantNames.length; position++) {
            GetterSetter getterSetter = new GetterSetter();
            getterSetter.setTenantName(tenantNames[position]);
            getterSetter.setTenantDetail(tenantDetails[position]);
            getterSetter.setTenantPhoto(tenantImages[position]);
            list.add(getterSetter);
        }
        return list;
    }

    static ArrayList<GetterSetter> getListDataRoomStatus() {
        ArrayList<GetterSetter> list = new ArrayList<>();
        for (int position = 0; position < roomNumbers.length; position++) {
            GetterSetter getterSetter = new GetterSetter();
            getterSetter.setRoomNumber(roomNumbers[position]);
            list.add(getterSetter);
        }
        return list;
    }
}
