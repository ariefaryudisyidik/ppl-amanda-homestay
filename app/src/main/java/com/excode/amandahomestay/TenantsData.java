package com.excode.amandahomestay;

import java.util.ArrayList;

public class TenantsData {
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

    static ArrayList<Tenant> getListData() {
        ArrayList<Tenant> list = new ArrayList<>();
        for (int position = 0; position < tenantNames.length; position++) {
            Tenant tenant = new Tenant();
            tenant.setName(tenantNames[position]);
            tenant.setDetail(tenantDetails[position]);
            tenant.setPhoto(tenantImages[position]);
            list.add(tenant);
        }
        return list;
    }
}
