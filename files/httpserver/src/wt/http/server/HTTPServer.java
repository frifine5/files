package wt.http.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
	
	public static void main(String[] args){
		int port;
		ServerSocket serverSocket;
		try {
			port = Integer.parseInt(args[0]);
		} catch (Exception e) {
			port = 8080;//Ĭ�϶˿�
		}
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("���������ڼ����˿ڣ�"+serverSocket.getLocalPort());
			while(true){
				Socket socket = serverSocket.accept();//����ģʽ
				System.out.println("��������ͻ��˵�һ���µ�TCP���ӣ��ͻ�Դ��ַ��"
						+socket.getRemoteSocketAddress());
				try {
					service(socket);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void service(Socket socket) throws Exception{
		InputStream socketIn = socket.getInputStream();
		Thread.sleep(200);
		int size = socketIn.available();
		System.out.println(size);
		byte[] buf = new byte[size];
		socketIn.read(buf);
		String request = new String(buf, "utf-8");
		System.out.println("rec:\n"+request);
		// get first line of HTTP request
		String firLine = request.substring(0, request.indexOf("\r\n"));
		System.out.println(firLine);
		// analysis the firLine
		String[] parts = firLine.split(" ");
		// uri
		String uri = parts[1];
		// content
		String contentType;
		if(uri.indexOf("html")!=-1||uri.indexOf("htm")!=-1){
			contentType = "text/html";
		}else if(uri.indexOf("jpg")!=-1||uri.indexOf("jpeg")!=-1){
			contentType = "image/jpeg";
		}else if(uri.indexOf("git")!=-1){
			contentType = "image/gif";
		}else{
			contentType = "application/octet-stream";
		}
		
		/*����HTTP��Ӧ���*/
        //HTTP��Ӧ�ĵ�һ��
        String responseFirstLine="HTTP/1.1 200 OK\r\n";
        
        String responseHeader="Content-type:"+contentType+"\r\n\r\n";
        
        InputStream in=HTTPServer.class.getResourceAsStream("/wt/http/server"+uri);
        
        /*����HTTP��Ӧ���*/
        OutputStream socketOut=socket.getOutputStream();
        socketOut.write(responseFirstLine.getBytes());
        socketOut.write(responseHeader.getBytes());
        //��������
        int len=0;
        buf=new byte[128];
        while((len=in.read(buf))!=-1)
            socketOut.write(buf,0,len);
        Thread.sleep(1000);
        socket.close();
		
	}
	

}
