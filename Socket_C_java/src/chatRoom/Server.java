package chatRoom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

class Server {
	private ServerSocket socket_server;
	private int port;
	private Map<String, Socket> map_client_socket;
	
	public static void main(String args[]){
		try {
			new Server(Setting.SERVER_PORT).start();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Server(int _port) throws IOException {
		this.port = _port;
		map_client_socket = new HashMap<String, Socket>();
	}
	
	public void start() throws IOException {
		try {
		    socket_server = new ServerSocket(port);
		    while(true) {
		    	Socket socket_connection = socket_server.accept();
		    	new ListenService(socket_connection).start();
		    }
		} catch (IOException e) {
			System.out.println("初始化错误");
			throw e;
		}
	}
	
	class ListenService extends Thread {
		private Socket socket;
		private String user_name;
		private boolean logined = false;
		private BufferedReader in = null;
		
		public ListenService(Socket _socket) {
			try {
				socket = _socket;
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			try {
				String command = "";
				while(true) {
					if((command = in.readLine()) != null) {
						if(!logined) {
							if(command.equals(Setting.COMMAND_LOGIN)) {
								user_name = in.readLine();	//读取用户名
								
								//如果用户名存在则发送无法登陆消息并关闭连接
								if(map_client_socket.containsKey(user_name)) {
									sendMsg(socket, Setting.COMMAND_LOGIN_FAILED);
									in.close();
									socket.close();
									break;
								}
								
								//将socket放入map中
								map_client_socket.put(user_name, socket);
								
								String msg = Setting.COMMAND_LOGIN_SUC + "\n" + user_name;
								sendMsgToAll(msg);
								updateUserList();
								logined = true;
							}
						} else {
						 	if(command.equals(Setting.COMMAND_LOGOUT)) {
						 		in.close();
						 		socket.close();
						 		map_client_socket.remove(user_name);
						 		
						 		String msg = Setting.COMMAND_LOGOUT + "\n" + user_name;
						 		sendMsgToAll(msg);
						 		updateUserList();
						 		break;
						 	} else if(command.equals(Setting.COMMAND_SENDMSG)) {
						 		String dest_username = in.readLine();	//读取目标用户名
						 		String msg = Setting.COMMAND_MSG + "\n" 
						 				+ user_name + "\n" //将发送者的用户名发过去
						 				+ in.readLine();
							
						 		if(dest_username.equals("所有人")) {
						 			sendMsgToAll(msg);
						 		} else {
						 			sendMsg(dest_username, msg);
						 		}
						 	}
						}
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
				map_client_socket.remove(user_name);
		 		updateUserList();
			}
		}
		
		public void sendMsg(Socket dest_socket, String message) {
			try {
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(dest_socket.getOutputStream())),true);
				out.println(message);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void sendMsg(String dest_username, String message) {
			Socket dest_socket = map_client_socket.get(dest_username);
			sendMsg(dest_socket, message);
		}
		
		public void sendMsgToAll(String message) {
			try {
				for(Iterator<Entry<String, Socket>> itor = map_client_socket.entrySet().iterator(); itor.hasNext();) {
					Entry<String, Socket> entry = (Entry<String, Socket>)itor.next();
					
					Socket dest_socket = entry.getValue();
					PrintWriter out = new PrintWriter(new BufferedWriter(
							new OutputStreamWriter(dest_socket.getOutputStream())),true);
					out.println(message);
					out.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void updateUserList() {
			String msg = Setting.COMMAND_USERLIST + "\n";
			
			for(Iterator<Entry<String, Socket>> itor = map_client_socket.entrySet().iterator(); itor.hasNext();) {
				Entry<String, Socket> entry = (Entry<String, Socket>)itor.next();
				msg += entry.getKey() + "|";
			}
			
			sendMsgToAll(msg);
		}
	}
}
