package dong.nv.utils;

import dong.nv.constant.Constant;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static dong.nv.constant.Constant.UNDER_SCORE;

public class Utils {


    public static String getFileName(String folderName, String prefixName) {
        return folderName
                .concat(prefixName)
                .concat(UNDER_SCORE)
                .concat(
                        new SimpleDateFormat(Constant.SUB_FIX_PATTERN_DATE_TIME).format(
                                Calendar.getInstance().getTime())
                )
                .concat(Constant.EXCEL_FILE_EXTENSION);
    }
}
