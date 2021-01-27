package com.xiaohan.seven.cide.tools;


import android.app.Activity;

import com.pgyersdk.update.UpdateManagerListener;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * Created by Administrator on 2017/1/8.
 */

public class Email {
    public static String getUrl(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        InputStream is = conn.getInputStream();
        byte[] data = readIS(is);
        String html = new String(data,"GBK");
        return html;
    }
    public static String getUrl(String path,int o) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        InputStream is = conn.getInputStream();
        byte[] data = readIS(is);
        String html = new String(data,"UTF-8");
        return html;
    }
    static byte[] readIS(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len = is.read(buffer))!=-1){
            bos.write(buffer,0,len);
        }
        is.close();
        return  bos.toByteArray();
    }
    public static void DownTask(Activity activity,String url){
        UpdateManagerListener.startDownloadTask(activity,url);
    }

    private static int port1 = 25;  //smtp协议使用的是25号端口
    private static String server1 = "smtp.qq.com"; // 发件人邮件服务器,这是163服务器
    private static String user1 = "2632588830@qq.com";   // 使用者账号
    private static String password1 = "nil"; //使用者密码/授权码

    //构造发送邮件帐户的服务器，端口，帐户，密码
    public static  void MainUtils(String server, int port, String user, String passwd) {
        port1 = port;
        user1 = user;
        password1 = passwd;
        server1 = server;
    }

    public static void sendEmail(String email, String subject, String body,
								 List<String> paths) throws MessagingException, UnsupportedEncodingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", server1);//服务器
        props.put("mail.smtp.port", String.valueOf(port1));//端口号
        props.put("mail.smtp.auth", "true");//不知
        Transport transport = null;
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage msg = new MimeMessage(session);
		transport = session.getTransport("smtp");
		transport.connect(server1, user1, password1);    //建立与服务器连接
		msg.setSentDate(new Date());
		InternetAddress fromAddress = null;
		fromAddress = new InternetAddress(user1);
		msg.setFrom(fromAddress);
		InternetAddress[] toAddress = new InternetAddress[1];
		toAddress[0] = new InternetAddress(email);
		msg.setRecipients(Message.RecipientType.TO, toAddress);
		msg.setSubject(subject, "UTF-8");            //设置邮件标题
		MimeMultipart multi = new MimeMultipart();   //代表整个邮件邮件
		BodyPart textBodyPart = new MimeBodyPart();  //设置正文对象
		textBodyPart.setText(body);                  //设置正文
		multi.addBodyPart(textBodyPart);//添加正文到邮件
        if(paths!=null) {
            for (String path : paths) {
                FileDataSource fds = new FileDataSource(path);   //获取磁盘文件
                BodyPart fileBodyPart = new MimeBodyPart();                       //创建BodyPart
                fileBodyPart.setDataHandler(new DataHandler(fds));           //将文件信息封装至BodyPart对象
                String fileNameNew = MimeUtility.encodeText(fds.getName(),
															"utf-8", null);      //设置文件名称显示编码，解决乱码问题
                fileBodyPart.setFileName(fileNameNew);  //设置邮件中显示的附件文件名
                multi.addBodyPart(fileBodyPart);        //将附件添加到邮件中
            }
        }
		msg.setContent(multi);                      //将整个邮件添加到message中
		msg.saveChanges();
		transport.sendMessage(msg, msg.getAllRecipients());  //发送邮件
		transport.close();
    }
}

