import java.net.*;
import java.io.*;

public class client_java {
	static Socket server;
	//byte存储转int类型数据
	public static int[] bytesToInts(byte[] bytes){  
        int bytesLength=bytes.length;  
        int[] ints=new int[bytesLength%4==0? bytesLength/4:bytesLength/4+1];  
        int lengthFlag=4;  
        while (lengthFlag<=bytesLength){  
            ints[lengthFlag/4-1]=(bytes[lengthFlag-4]<<24)|(bytes[lengthFlag-3]&0xff)<<16|  
                    (bytes[lengthFlag-2]&0xff)<<8|(bytes[lengthFlag-1]&0xff);  
            lengthFlag+=4;  
        }  
        for (int i=0;i<bytesLength+4-lengthFlag;i++){  
            if (i==0) ints[lengthFlag/4-1]|=bytes[lengthFlag-4+i]<<8*(bytesLength+4-lengthFlag-i-1);  
            else ints[lengthFlag/4-1]|=(bytes[lengthFlag-4+i]&0xff)<<8*(bytesLength+4-lengthFlag-i-1);  
        }  
        return ints;  
    }  
	//大端转小端
    private static long Long_EndianBigtoLittle(int value)
    {
        byte[] src = new byte[4];
        src[0] = (byte) ((value>>24)&0xFF);
        src[1] = (byte) ((value>>16)&0xFF);
        src[2] = (byte) ((value>>8)&0xFF);
        src[3] = (byte) ((value)&0xFF);
        return (long) ((src[0]&0xFF)|((src[1]&0xFF)<<8)|((src[2]&0xFF)<<16)|((src[3]&0xFF)<<24))&0xFFFFFFFFl;
    }
    //int类型变为byte存储形式
    private static byte[] toLH(int n) {
    	byte[] b = new byte[4];
    	b[0] = (byte) (n & 0xff);
    	b[1] = (byte) (n >> 8 & 0xff);
    	b[2] = (byte) (n >> 16 & 0xff);
    	b[3] = (byte) (n >> 24 & 0xff);
    	return b;
    	}

	public static void main(String[] args) throws Exception {
		server = new Socket("10.108.87.208", 5678);
		//server = new Socket("10.108.86.8", 5678);
		//BufferedReader in = new BufferedReader(new InputStreamReader(
		//		server.getInputStream()));
		DataInputStream in = new DataInputStream(server.getInputStream());//对于byte[]的接收，应该用DataInputStream进行  
		PrintWriter out = new PrintWriter(server.getOutputStream());
		BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.print("输入结果：");
			String str = wt.readLine();
			out.println(str);
			out.flush();
			if (str.equals("end")) {
				break;
			}
			
			
			
			//str = in.readLine();
			//System.out.println("服务器返回："+str);
			int num = 12;
			byte[] a = new byte[num];
			for(int i = 0; i < num; i++)
				a[i] = in.readByte();
			int[] b = bytesToInts(a);
			long[] c = new long[b.length];
			for(int i = 0; i < b.length; i++) {
				c[i] = Long_EndianBigtoLittle(b[i]);
			}
			
			//System.out.println(a);
			//System.out.println(str);
			//byte[] buf = new byte[str.length()*2 + 1];
			//str.getBytes(0, str.length(), buf, 0);
			//int[] a = bytesToInts(buf);
			for(long k : c)
				System.out.print(k+" ");
			System.out.println();
			
		}
		server.close();
	}
}
