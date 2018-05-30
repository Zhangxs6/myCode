

import java.net.*;
import java.lang.reflect.*;
import java.io.*;
 
public class GreetingServer extends Thread
{
   private ServerSocket serverSocket;
   
   public GreetingServer(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(10000);
   }
 
   public void run()
   {
      while(true)
      {
         try
         {
            System.out.println("�ȴ�Զ�����ӣ��˿ں�Ϊ��" + serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Զ��������ַ��" + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());
            System.out.println(in.readUTF());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("лл�����ң�" + server.getLocalSocketAddress() + "\nGoodbye!");
            server.close();
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }
   public static void main(String [] args)
   {
      int port = Integer.parseInt("6066");
      Class a = GreetingServer.class;
     // Method[] methods = a.getMethods();
      String s = a.getSimpleName();
      System.out.println(s);
      Class ss = a.getSuperclass();
      System.out.println(ss.getName());
      Field[] methods = a.getFields();
      for(Field method : methods){
          System.out.println("method = " + method.getName());
      }
      try
      {
         Thread t1 = new GreetingServer(port);
         t1.run();
         //Thread t2 = new GreetingServer(port);
        // t2.run();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}