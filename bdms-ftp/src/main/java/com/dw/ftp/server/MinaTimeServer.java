package com.dw.ftp.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;


public class MinaTimeServer {

	/**
	 * description:网络传输服务，用户数据传输
	 * 
	 * 规定：服务器端口说明
	 *
	 *
	 *
	 *
	 * @param args[0]    监听的端口号
	 * void 2016-4-27 上午10:38:27 by chenfeng
	 */

	public static void main(String[] args) throws IOException {
		System.out.println("spark sokect 启动中....  ");
		int port=-1;
		if (args.length > 0) {
			System.out.println("你输入的端口是：" + args[0]);
			port = Integer.parseInt(args[0]);
		} else {
			System.out.println("请输入端口!!!");
		}

		IoAcceptor acceptor = new NioSocketAcceptor(); // socket 服务器
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());

		// 设定这个过滤器将一行一行(/r/n)的读取数据
		acceptor.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		
		acceptor.setHandler(new Message2SparkHandler(acceptor));
		acceptor.getSessionConfig().setReadBufferSize(20);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		acceptor.bind(new InetSocketAddress(port));
		System.out.println("spark 服务端启动成功...");

	}

}
