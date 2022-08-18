///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package vn.aptech.warehouse.service;
//
//
//import java.io.UnsupportedEncodingException;
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
///**
// *
// * @author nhta1
// */
//@Service
//public class EmailSenderService {
//    
//    @Autowired
//    private JavaMailSender mailSender;
//    
//    public void sendImportGoodsRequest(String toEmail,String body) throws MessagingException, UnsupportedEncodingException{
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//       
//        helper.setFrom("sluuthanh.demo.send@gmail.com", "2HAT Warehouse noreply-mail");
//        helper.setTo(toEmail);
//        helper.setSubject("Request import more goods");
//        helper.setText(body);
//        mailSender.send(message);
//    }
//}
