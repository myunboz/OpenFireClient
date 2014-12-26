package com.openfire.client;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class Users implements Runnable {
	String username, password, touser, loginurl, toUrl;
	int begin, end, sleeptime;

	public Users(String uname, String pwd, int begin, int end, String touser,
			String loginurl, String toUrl, int sleeptime) {
		this.username = uname;
		this.password = pwd;
		this.begin = begin;
		this.end = end;
		this.touser = touser;
		this.loginurl = loginurl;
		this.toUrl = toUrl;
		this.sleeptime = sleeptime;
	}

	public void run() {
		// XMPPConnection.DEBUG_ENABLED = true;
		SASLAuthentication.supportSASLMechanism("PLAIN", 0);
		ConnectionConfiguration config = new ConnectionConfiguration(loginurl,
				5222);
		Connection connection = new XMPPConnection(config);
		try {
			connection.connect();
			System.out.println("Authenticated = "
					+ connection.isAuthenticated());
			connection.login(username, password);
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Chat chat;
		do {
			if (begin > end) {
				System.out.println("beginNo is:" + begin + "; endNo is:" + end
						+ " ;System exit(0)");
				break;
			} else if (begin == 1 && end == 1) {
				end = 2;
			}
			for (int x = begin; x < end; x++) {
				chat = connection.getChatManager().createChat(
						touser + x + toUrl, new MessageListener() {
							@SuppressWarnings("deprecation")
							public void processMessage(Chat chat,
									Message message) {
								System.out.println(message.getXmlns());
								System.out.println(message.getFrom() + " "
										+ new java.util.Date().toLocaleString()
										+ "get msg:" + message.getBody());
							}

						});
				try {
					Thread.sleep(sleeptime);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String cmd = "this is a test msg";
				try {
					chat.sendMessage(cmd);
					System.out.println("from user " + username + " to user "
							+ touser + x + ";send msg:" + cmd);
				} catch (XMPPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				chat = null;
			}
		} while (true);
	}
}