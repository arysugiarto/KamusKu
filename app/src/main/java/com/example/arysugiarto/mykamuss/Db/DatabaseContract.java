package com.example.arysugiarto.mykamuss.Db;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static String IND_TO_ENG  = "ind_eng";
    public static String ENG_TO_IND  = "eng_ind";

    public static final class KamusColumns implements BaseColumns {
        public static String F_KATA     = "kata";
        public static String F_ARTI     = "arti";
    }

}
