package fr.eni.ecole.jbabinot.android.tp.lokacar.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jbabinot2015 on 29/06/2017.
 */

public class PhotoManager {


    public static File saveImage(Context context, Bitmap image, String fileName, String path){

        if(path == null){
            path = context.getExternalFilesDir(null).toString();
        }
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(path + "/photos");
        if(!myDir.exists()) {
            myDir.mkdirs();
        }

        File file = new File(myDir, fileName);

        if (file.exists()) {
            file.delete();
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            if(!image.compress(Bitmap.CompressFormat.JPEG, 80, out)){
               throw new Exception("Erreur lors de l'enregistrement de l'image.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
