package model;

import android.graphics.Bitmap;

public class profilePhoto {

    private int id_user;
    private String fileData;

    public profilePhoto(int id_user, String fileData) {
        this.id_user = id_user;
        this.fileData = fileData;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

    @Override
    public String toString() {
        return "profilePhoto{" +
                "id_user=" + id_user +
                ", fileData='" + fileData + '\'' +
                '}';
    }
}
