/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.MODEL;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author duck
 */
public class GuiMail {
private static String otpCode;

    public static boolean sendOTP(String toEmail) {
    final String fromEmail = "truonglelamquyet12@gmail.com"; // Thay bằng email của bạn
    final String password = "hzvz kmrw gpub tfkm";     // Mật khẩu ứng dụng từ Gmail

    // Tạo OTP
    otpCode = String.format("%06d", new Random().nextInt(999999));

    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(fromEmail, password);
        }
    });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Mã OTP xác thực");
        message.setText("Mã OTP của bạn là: " + otpCode);

        Transport.send(message);
        return true;
    } catch (MessagingException e) {
        e.printStackTrace();
        return false;
    }
}

    public static String getOTPCode() {
        return otpCode;
    }

}
