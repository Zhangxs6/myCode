package chatRoom;


import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.Socket;  
 
public class ServerThread extends Thread {  
 
   public Socket socket;  
   public InputStream ins;  
   public OutputStream ous;  
 
   public ServerThread(Socket socket) {  
       this.socket = socket;  
   }  
 
   public void run() {  
       try {  
           // ��ȡ���������  
           ins = socket.getInputStream();  
           ous = socket.getOutputStream();  
           // ������Ϣ���ͻ���  
           String msg = "welcome to zhou's server !";  
           sendMsg(ous, msg);  
           // ����Ҫ���¼��Ϣ���ͻ���  
           String userinfo = "please input your name:";  
           sendMsg(ous, userinfo);  
           // ��ȡ�ͻ���������û���  
           String userName = readMsg(ins);  
           // ����Ҫ��������Ϣ���ͻ���  
           String pwd = "please input your password:";  
           sendMsg(ous,  pwd);  
           // ��ȡ�ͻ������������  
           String pass = readMsg(ins);  
           // ��¼��֤  
           boolean falg = loginCheck(userName, pass);  
           // У�鲻ͨ��ʱ��ѭ��У��  
           while (!falg) {  
               msg = "Fail to connect server......";  
               sendMsg(ous, msg);  
               msg = "please check your name and password and login again.....";  
               sendMsg(ous, msg);  
               msg = "please input your name:";  
               sendMsg(ous, msg);  
               // ��ȡ�ͻ���������û���  
               userName = readMsg(ins);  
               // ����Ҫ��������Ϣ���ͻ���  
               msg = "please input your password:";  
               sendMsg(ous, msg);  
               // ��ȡ�ͻ������������  
               pass = readMsg(ins);  
               falg = loginCheck(userName, pass);  
           }  
 
           // У��ɹ��󣺿�ʼ����  
           msg = "successful connected..... you can chat with your friends now ......";  
           sendMsg(ous, msg);  
           // ���촦���߼�  
           //��ȡ�ͻ��˷�������Ϣ  
           msg=readMsg(ins);  
           //����bye��������  
           while(!"bye".equals(msg)){  
               //�������е�ÿ������ת����Ϣ  
               for (int i = 0; i <Myserver.list.size(); i++) {  
                   ServerThread st =Myserver.list.get(i);  
                   //�����Լ�ת����Ϣ  
                   if(st!=this){  
                       sendMsg(st.ous, userName+"  is say:"+msg);  
                   }  
               }  
               //�ȴ���ȡ��һ�ε���Ϣ  
               msg=readMsg(ins);  
           }  
 
       } catch (Exception e) {  
           System.out.println("�ͻ��˲������ر�......");  
//         e.printStackTrace();  
       }  
       //���쳣��ͳһ�����ر�  
       try {  
           ins.close();  
           ous.close();  
           socket.close();  
           //����ǰ�Ѿ��رյĿͻ��˴��������Ƴ�  
           Myserver.list.remove(this);  
       } catch (IOException e) {  
           // TODO Auto-generated catch block  
           e.printStackTrace();  
       }  
   }  
 
   // У��ͻ���������˺ź�����ĺ���,����û�����ݿ⣬��ʱд����  
   public boolean loginCheck(String name, String pwd) {  
       if (name.equals("zhou") && pwd.equals("zhou") || name.equals("user") && pwd.equals("pwd")  
               || name.equals("huaxinjiaoyu") && pwd.equals("huaxinjiaoyu")) {  
 
           return true;  
       }  
       return false;  
   }  
 
   // ������Ϣ�ĺ���  
   public void sendMsg(OutputStream os, String s) throws IOException {  
       // ��ͻ��������Ϣ  
       byte[] bytes = s.getBytes();  
       os.write(bytes);  
       os.write(13);  
       os.write(10);  
       os.flush();  
 
   }  
 
   // ��ȡ�ͻ����������ݵĺ���  
   public String readMsg(InputStream ins) throws Exception {  
       // ��ȡ�ͻ��˵���Ϣ  
       int value = ins.read();  
       // ��ȡ���� ��ȡ���س���13�����У�10��ʱֹͣ��  
       String str = "";  
       while (value != 10) {  
           // ����رտͻ���ʱ�᷵��-1ֵ  
           if (value == -1) {  
               throw new Exception();  
           }  
           str = str + ((char) value);  
           value = ins.read();  
       }  
       str = str.trim();  
       return str;  
   }  
 
}  