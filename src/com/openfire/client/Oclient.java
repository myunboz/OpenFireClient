package com.openfire.client;

public class Oclient {
	static Env envs = new Env();
	static String username = envs.getUserName(), password = envs.getPassWord(),
			loginurl = envs.getLoginUrl(), toUsers = envs.getToUsers(),
			toUrl = envs.getToUrl();
	static int userNo = envs.getUserNo(), toUserNo = envs.getToUserNo(),
			sleepTime = envs.getSleepTime();

	public static void main(String[] args) throws Exception {
		String[] tous = toUsers.split(",");
		int clientNo, avg;
		if (toUserNo >= userNo) {
			clientNo = toUserNo / userNo;
		} else if (toUserNo < userNo && toUserNo >= tous.length) {
			clientNo = 1;
		} else {
			clientNo = 0;
		}
		if (userNo > tous.length) {
			avg = userNo / tous.length;
		} else {
			avg = 1;
		}
		for (int y = 0; y < tous.length; y++) {
			for (int x = 1; x < userNo + 1; x++) {
				if (avg * y < x && x < avg * (y + 1) + 1) {
					Users us = new Users(username + x, "1" + x, (x - 1)
							* clientNo + 1, x * clientNo + 1, tous[y],
							loginurl, toUrl, sleepTime);
					new Thread(us).start();
					System.out.println("toUser " + tous[y] + " thread start "
							+ x);
				}
			}
		}
	}
}