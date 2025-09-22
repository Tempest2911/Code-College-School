package org.example.buoi4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DangKyController {

    @GetMapping("/dang-ky")
    public String formDangKy(){

        return "dang-ky";
    }

    @PostMapping("/dang-ky")
    @ResponseBody
    public String functionDangKy(@RequestParam(name = "studentCode") String mssv,
                                 @RequestParam(name = "subject") String maMonHoc){

        return mssv + " đã đăng ký học lại môn " + maMonHoc;
    }

}
