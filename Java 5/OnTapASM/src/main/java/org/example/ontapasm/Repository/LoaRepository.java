package org.example.ontapasm.Repository;

import org.example.ontapasm.Model.Loa;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class LoaRepository {
    private static final List<Loa> DS_Loa = new ArrayList<Loa>();
    static {
        DS_Loa.add(new Loa(1, "Loa JBL", 100.0, "Đen", "2.1"));
        DS_Loa.add(new Loa(2, "Loa Sony", 150.0, "Trắng", "5.1"));
        DS_Loa.add(new Loa(3, "Loa Bose", 200.0, "Xám", "7.1"));
        DS_Loa.add(new Loa(4, "Loa Marshall", 250.0, "Đỏ", "2.1"));
    }

    public List<Loa> findAll() {
        return DS_Loa;
    }

    public List<Loa> findAllByTenLoa(String tuKhoa){
        return DS_Loa.stream().filter(loa -> loa.getTenLoa().contains(tuKhoa)).toList();
    }



}
