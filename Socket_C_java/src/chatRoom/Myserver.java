package chatRoom;


import java.io.IOException;  
import java.net.ServerSocket;  
import java.net.Socket;  
import java.util.ArrayList;  

public class Myserver {  
    
  public static ArrayList<ServerThread> list = new ArrayList<ServerThread>();  
    
  public  void initServer() {  
        
      try {  
          //��������������,��ָ���˿ں�  
          ServerSocket server = new ServerSocket(9090);  
          System.out.println("�������Ѿ�����......");  
          //���ϻ�ȡ�ͻ��˵�����  
          while(true){  
              Socket socket =server.accept();  
              System.out.println("�ͻ������ӽ�����......");  
              //���пͻ������ӽ����Ժ󣬿���һ���̣߳����������ÿͻ��˵��߼�,  
              ServerThread st = new ServerThread(socket);  
              st.start();  
              //���Ӹÿͻ��˵�������  
              list.add(st);  
          }  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  

  }  

}  