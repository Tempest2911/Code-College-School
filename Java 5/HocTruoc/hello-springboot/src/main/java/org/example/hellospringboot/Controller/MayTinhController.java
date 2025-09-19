package org.example.hellospringboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MayTinhController {
    @GetMapping("may-tinh/cong")
    @ResponseBody
        public String mayTinh1(@RequestParam(name = "soThuNhat") int a, @RequestParam(name = "soThuHai") int b) {
            int c = a+b;
            return "ketqua = " +c;
        }

    @GetMapping("may-tinh/tru")
    @ResponseBody
    public String mayTinh2(@RequestParam(name = "soThuNhat") int a, @RequestParam(name = "soThuHai") int b) {
        int c = a-b;
        return "ketqua = " +c;
    }

    @GetMapping("may-tinh/nhan")
    @ResponseBody
    public String mayTinh3(@RequestParam(name = "soThuNhat") int a, @RequestParam(name = "soThuHai") int b) {

        int c = a*b;
        return "ketqua = " +c;
    }

    @GetMapping("may-tinh/chia")
    @ResponseBody
    public String mayTinh4(@RequestParam(name = "soThuNhat") double a, @RequestParam(name = "soThuHai") double b) {

        double c = a/b;
        if (b==0) {
            return "khong the chia het";
        }else{
            return "ketqua = " +c;
        }

    }

}
