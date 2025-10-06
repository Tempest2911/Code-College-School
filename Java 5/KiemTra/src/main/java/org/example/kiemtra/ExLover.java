package org.example.kiemtra;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExLover {
    private int id;
    private String ten;
    private LocalDateTime ngayCT;
    private String lyDoCT;
    private int mucDoDrama;

    public ExLover() {
    }

    public ExLover(int id, String ten, LocalDateTime ngayCT, String lyDoCT, int mucDoDrama) {
        this.id = id;
        this.ten = ten;
        this.ngayCT = ngayCT;
        this.lyDoCT = lyDoCT;
        this.mucDoDrama = mucDoDrama;
    }
}
