package com.service.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.springframework.stereotype.Service;
import com.service.socketServerService;

@Service("socketServerService")
public class socketServerImpl implements socketServerService {

	@SuppressWarnings("resource")
	public void socketServer() {
		try {
			System.out.println("Server Socket 생성");
			ServerSocket serverSocket = new ServerSocket(6080); // 현재 아이피로 8981포트를 사용하여 서버 오픈
			while (true) {
				System.out.println("*******접속 대기중*******");
				Socket clientSocket = serverSocket.accept();// 계속 기다리고 있다가 클라이언트가 접속하면 통신할 수 있는 소켓 반환
				System.out.println("-------Server Socket 연결 && New Socket 생성-------");
				
				// 입력 스트림
				InputStream is = clientSocket.getInputStream();
				System.out.println("New Socket : InputStream");
				byte[] bytes = new byte[1024];
				int readByteCount = is.read(bytes);
				if (readByteCount > 0) {

					/*
					//TODO: 새로운 소켓으로 들어온 입력값을 출력하기위함 => 작동안됨
					String data = "";
					BufferedReader reader = new BufferedReader(new InputStreamReader(is));
					while (!(data = reader.readLine()).equals(null)) {
						System.out.println("from Client>" + data);
					}
					*/
					
					// 출력 스트림
					OutputStream os = clientSocket.getOutputStream();
					System.out.println("New Socket : OutputStream");
					os.write(bytes);
					os.flush();
				}
				clientSocket.close();
				System.out.println("-----------------New Socket 닫힘------------------");
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/*
//Client Socket 소스
public LuluResult socketClient() throws Exception {
		LuluResult result = new LuluResult();
		String data = null;
	
		String st = "Hellow World";
		Socket socket = new Socket(); // 소켓 생성
		SocketAddress address = new InetSocketAddress("127.0.0.1", 6080); //주소 생성
		System.out.println("*******Client Socket 생성*******");

		try {
			socket.connect(address); //소캣연결
			System.out.println("-------Client Socket Connect-------");
			
			OutputStream os = socket.getOutputStream(); //출력 스트림
			os.write(st.getBytes());
			os.flush();
			System.out.println("Client Socket : OutputStream");

			InputStream input = socket.getInputStream(); //입력 스트림
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			data = reader.readLine();
			System.out.println("Client Socket : InputStream = " + data);
			
			socket.close(); // 소캣 닫기
			System.out.println("--------Client Socket Close--------");
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("******* Fail *******");
		} 

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("data", data);
		result.setResultData(map);
		return result;
	}
*/