package org.example.buoi5;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NguoiYeu {

    private String nickname;
    private boolean gioiTinh;
    private Integer namSinh;
    private double chieuCao;

    public NguoiYeu() {
    }

    public NguoiYeu(String nickname, boolean gioiTinh, Integer namSinh, double chieuCao) {
        this.nickname = nickname;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.chieuCao = chieuCao;
    }
}
