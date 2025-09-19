package org.example.lab2;

public class Love {
    private String nickName;
    private int namSinh;

    public Love() {

    }

    public Love(String nickName, int namSinh) {
        this.nickName = nickName;
        this.namSinh = namSinh;
    }
    public String getNickName() {
        return nickName;
    }
    public int getNamSinh() {
        return namSinh;
    }
}
