package org.example.templatesof3012.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/dai-cao-binh-ngo")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().println("<h1>Bình Ngô Đại Cáo</h1>");
        resp.getWriter().println("Đại thiên hành hoá hoàng thượng nhược viết:<br>" +
                "<br>" +
                "Cái văn:<br>" +
                "Nhân nghĩa chi cử, yếu tại an dân,<br>" +
                "Điếu phạt chi sư, mạc tiên khử bạo.<br>" +
                "Duy, ngã Đại Việt chi quốc,<br>" +
                "Thực vi văn hiến chi bang.<br>" +
                "Sơn xuyên chi phong vực ký thù,<br>" +
                "Nam bắc chi phong tục diệc dị.<br>" +
                "Tự Triệu, Đinh, Lý, Trần chi triệu tạo ngã quốc,<br>" +
                "Dữ Hán, Đường, Tống, Nguyên nhi các đế nhất phương.<br>" +
                "Tuy cường nhược thì hữu bất đồng,<br>" +
                "Nhi hào kiệt thế vị thường phạp.<br>" +
                "Cố Lưu Cung tham công dĩ thủ bại,<br>" +
                "Nhi Triệu Tiết hiếu đại dĩ xúc vong.<br>" +
                "Toa Đô ký cầm ư Hàm Tử quan,<br>" +
                "Ô Mã hựu ế ư Bạch Đằng hải.<br>" +
                "Kê chư vãng cổ,<br>" +
                "Quyết hữu minh trưng.<br>" +
                "Khoảnh nhân Hồ chính chi phiền hà.<br>" +
                "Chí sử nhân tâm chi oán bạn.<br>" +
                "Cuồng Minh tứ khích, nhân dĩ độc ngã dân;<br>" +
                "Ác đảng hoài gian, cánh dĩ mại ngã quốc.<br>" +
                "Hân thương sinh ư ngược diễm,<br>" +
                "Hãm xích tử ư hoạ khanh.<br>" +
                "Khi thiên võng dân, quỷ kế cái thiên vạn trạng;<br>" +
                "Liên binh kết hấn nẫm ác đãi nhị thập niên.<br>" +
                "Bại nghĩa thương nhân, càn khôn kỷ hồ dục tức;<br>" +
                "Trọng khoa hậu liễm, sơn trạch mi hữu kiết di.<br>" +
                "Khai kim trường tái mạo lam chướng nhi phủ sơn đào sa,<br>" +
                "Thái minh châu tắc xúc giao long nhi căng yêu thộn hải.<br>" +
                "Nhiễu dân thiết huyền lộc chi hãm tịnh,<br>" +
                "Điển vật chức thuý cầm chi võng la.<br>" +
                "Côn trùng thảo mộc giai bất đắc dĩ toại kỳ sinh,<br>" +
                "Quan quả điên liên câu bất hoạch dĩ an kỳ sở.<br>" +
                "Tuấn sinh linh chi huyết dĩ nhuận kiệt hiệt chi vẫn nha;<br>" +
                "Cực thổ mộc chi công dĩ sùng công tư chi giải vũ.<br>" +
                "Châu lý chi chinh dao trọng khốn,<br>" +
                "Lư diêm chi trữ trục giai không.<br>" +
                "Quyết Đông Hải chi thuỷ bất túc dĩ trạc kỳ ô,<br>" +
                "Khánh Nam Sơn chi trúc bất túc dĩ thư kỳ ác.<br>" +
                "Thần dân chi sở cộng phẫn,<br>" +
                "Thiên địa chi sở bất dung.<br>" +
                "<br>" +
                "Dư:<br>" +
                "Phấn tích Lam Sơn,<br>" +
                "Thê thân hoang dã.<br>" +
                "Niệm thế thù khởi khả cộng đới,<br>" +
                "Thệ nghịch tặc nan dữ câu sinh.<br>" +
                "Thống tâm tật thủ giả thuỳ thập dư niên,<br>" +
                "Thường đảm ngoạ tân giả cái phi nhất nhật.<br>" +
                "Phát phẫn vong thực, mỗi nghiên đàm thao lược chi thư,<br>" +
                "Tức cổ nghiệm kim, tế suy cứu hưng vong chi lý.<br>" +
                "Đồ hồi chi chí,<br>" +
                "Ngộ mị bất vong.<br>" +
                "Đương nghĩa kỳ sơ khởi chi thì,<br>" +
                "Chính tặc thế phương trương chi nhật.<br>" +
                "<br>" +
                "Nại dĩ:<br>" +
                "Nhân tài thu diệp,<br>" +
                "Tuấn kiệt thần tinh.<br>" +
                "Bôn tẩu tiên hậu giả ký phạp kỳ nhân,<br>" +
                "Mưu mô duy ác giả hựu quả kỳ trợ.<br>" +
                "Đặc dĩ cứu dân chi niệm, mỗi uất uất nhi dục đông;<br>" +
                "Cố ư đãi hiền chi xa, thường cấp cấp dĩ hư tả.<br>" +
                "<br>" +
                "Nhiên kỳ:<br>" +
                "Đắc nhân chi hiệu mang nhược vọng dương,<br>" +
                "Do kỷ chi thành thậm ư chửng nịch.<br>" +
                "Phẫn hung đồ chi vị diệt,<br>" +
                "Niệm quốc bộ chi tao truân.<br>" +
                "Linh Sơn chi thực tận kiêm tuần,<br>" +
                "Khôi Huyện chi chúng vô nhất lữ.<br>" +
                "Cái thiên dục khốn ngã dĩ giáng quyết nhiệm,<br>" +
                "Cố dữ ích lệ chí dĩ tế vu nan.<br>" +
                "Yết can vi kỳ, manh lệ chi đồ tứ tập;<br>" +
                "Đầu giao hưởng sĩ, phụ tử chi binh nhất tâm.<br>" +
                "Dĩ nhược chế cường, hoặc công nhân chi bất bị;<br>" +
                "Dĩ quả địch chúng thường thiết phục dĩ xuất kỳ.<br>" +
                "<br>" +
                "Tốt năng:<br>" +
                "Dĩ đại nghĩa nhi thắng hung tàn,<br>" +
                "Dĩ chí nhân nhi dị cường bạo.<br>" +
                "Bồ Đằng chi đình khu điện xế,<br>" +
                "Trà Lân chi trúc phá hôi phi.<br>" +
                "Sĩ khí dĩ chi ích tăng,<br>" +
                "Quân thanh dĩ chi đại chấn.<br>" +
                "Trần Trí, Sơn Thọ văn phong nhi sỉ phách,<br>" +
                "Lý An, Phương Chính giả tức dĩ thâu sinh.<br>" +
                "Thừa thắng trường khu, Tây Kinh ký vị ngã hữu;<br>" +
                "Tuyển binh tiến thủ, Đông Đô tận phục cựu cương.<br>" +
                "Ninh Kiều chi huyết thành xuyên, lưu tinh vạn lý;<br>" +
                "Tốt Động chi thi tích dã, di xú thiên niên.<br>" +
                "Trần Hiệp tặc chi phúc tâm, ký kiêu kỳ thủ;<br>" +
                "Lý Lượng tặc chi gian đố, hựu bạo quyết thi.<br>" +
                "Vương Thông lý loạn nhi phần giả ích phần,<br>" +
                "Mã Anh cứu đấu nhi nộ giả ích nộ.<br>" +
                "Bỉ trí cùng nhi lực tận, thúc thủ đãi vong;<br>" +
                "Ngã mưu phạt nhi tâm công, bất chiến tự khuất.<br>" +
                "Vị bỉ tất dị tâm nhi cải lự,<br>" +
                "Khởi ý phục tác nghiệt dĩ tốc cô.<br>" +
                "Chấp nhất kỷ chi kiến dĩ giá hoạ ư tha nhân,<br>" +
                "Tham nhất thì chi công dĩ di tiếu ư thiên hạ.<br>" +
                "Toại linh Tuyên Đức chi giảo đồng, độc binh vô yếm;<br>" +
                "Nhưng mệnh Thạnh Thăng chi noạ tướng, dĩ du cứu phần.<br>" +
                "Đinh vị cửu nguyệt Liễu Thăng toại dẫn binh do Khâu Ôn nhi tiến,<br>" +
                "Bản niên thập nguyệt Mộc Thạnh hựu phân đồ tự Vân Nam nhi lai.<br>" +
                "Dư tiền ký tuyển binh tái hiểm dĩ tồi kỳ phong,<br>" +
                "Dư hậu tái điều binh tiệt lộ dĩ đoạn kỳ thực.<br>" +
                "Bản nguyệt thập bát nhật Liễu Thăng vị ngã quân sở công, kế truỵ ư Chi Lăng chi dã;<br>" +
                "Bản nguyệt nhị thập nhật Liễu Thăng hựu vị ngã quân sở bại, thân tử ư Mã An chi sơn.<br>" +
                "Nhị thập ngũ nhật Bảo Định bá Lương Minh trận hãm nhi táng khu,<br>" +
                "Nhị thập bát nhật Thượng thư Lý Khánh kế cùng nhi vẫn thủ.<br>" +
                "Ngã toại nghênh nhận nhi giải,<br>" +
                "Bỉ tự đảo qua tương công.<br>" +
                "Kế nhi tứ diện thiêm binh dĩ bao vi,<br>" +
                "Kỳ dĩ thập nguyệt trung tuần nhi điễn diệt.<br>" +
                "Viên tuyển tì hưu chi sĩ,<br>" +
                "Thân mệnh trảo nha chi thần.<br>" +
                "Ẩm tượng nhi hà thuỷ càn,<br>" +
                "Ma đao nhi sơn thạch quyết.<br>" +
                "Nhất cổ nhi kình khô ngạc đoạn,<br>" +
                "Tái cổ nhi điểu tán quân kinh.<br>" +
                "Quyết hội nghĩ ư băng đê,<br>" +
                "Chấn cương phong ư cảo diệp.<br>" +
                "Đô đốc Thôi Tụ tất hành nhi tống khoản,<br>" +
                "Thượng thư Hoàng Phúc diện phọc dĩ tựu cầm.<br>" +
                "Cương thi tái Lượng Giang, Lượng Sơn chi đồ,<br>" +
                "Chiến huyết xích Xương Giang, Bình Than chi thuỷ.<br>" +
                "Phong vân vị chi biến sắc,<br>" +
                "Nhật nguyệt thảm dĩ vô quang.<br>" +
                "Kỳ Vân Nam binh vị ngã quân sở ách ư Lê Hoa, tự đỗng nghi hư hạt nhi tiên dĩ phá phủ;<br>" +
                "Kỳ Mộc Thạnh chúng văn Thăng quân sở bại ư Cần Trạm, toại lận tạ bôn hội nhi cận đắc thoát thân.<br>" +
                "Lãnh Câu chi huyết chử phiếu, giang thuỷ vị chi ô yết;<br>" +
                "Đan Xá chi thi sơn tích, dã thảo vị chi ân hồng.<br>" +
                "Lưỡng lộ cứu binh ký bất toàn chủng nhi câu bại,<br>" +
                "Các thành cùng khấu diệc tướng giải giáp dĩ xuất hàng.<br>" +
                "Tặc thủ thành cầm, bỉ ký trạo ngạ hổ khất liên chi vĩ;<br>" +
                "Thần võ bất sát, dư diệc thể thượng đế hiếu sinh chi tâm.<br>" +
                "Tham tướng Phương Chính, Nội quan Mã Kỳ, tiên cấp hạm ngũ bách dư sưu, ký độ hải nhi do thả hồn phi phách tán;<br>" +
                "Tổng binh Vương Thông, Tham chính Mã Anh, hựu cấp mã sổ thiên dư thất, dĩ hoàn quốc nhi ích tự cổ lật tâm kinh.<br>" +
                "Bỉ ký uý tử tham sinh, nhi tu hảo hữu thành;<br>" +
                "Dư dĩ toàn quân vi thượng, nhi dục dân chi đắc tức.<br>" +
                "Phi duy mưu kế chi cực kỳ thâm viễn,<br>" +
                "Cái diệc cổ kim chi sở vị kiến văn.<br>" +
                "Xã tắc dĩ chi điện an,<br>" +
                "Sơn xuyên dĩ chi cải quan.<br>" +
                "Càn khôn ký bĩ nhi phục thái,<br>" +
                "Nhật nguyệt ký hối nhi phục minh.<br>" +
                "Vu dĩ khai vạn thế thái bình chi cơ,<br>" +
                "Vu dĩ tuyết thiên địa vô cùng chi sỉ.<br>" +
                "Thị do thiên địa tổ tông chi linh hữu,<br>" +
                "Dĩ mặc tương âm hữu nhi trí nhiên dã.<br>" +
                "<br>" +
                "Ô hô!<br>" +
                "Nhất nhung đại định, hất thành vô cạnh chi công;<br>" +
                "Tứ hải vĩnh thanh, đản bố duy tân chi cáo.<br>" +
                "<br>" +
                "Bá cáo hà nhĩ,<br>" +
                "Hàm sử văn tri.");
    }
}