package com.openfire.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Env {
	String userName, passWord, loginUrl, loginPort, userNo, toUserNo, toUsers,
			toUrl, sleepTime;

	public int getSleepTime() {
		String st = map.get("sleepTime");
		if (st != null) {
			int rtp = Integer.valueOf(st);
			if (rtp > 0) {
				return rtp;
			} else {
				System.out.println("sleepTime is Undefined, use default 0");
				return 0;
			}
		} else {
			System.out.println("sleepTime is Undefined, use default 0");
			return 0;
		}
	}

	public void setSleepTime(String sleepTime) {
		this.sleepTime = sleepTime;
	}

	Map<String, String> map = new HashMap<String, String>();

	public Env() {
		this.setMap(map);
	}

	public int getLoginPort() {
		String st = map.get("loginPort");
		if (st != null) {
			int rtp = Integer.valueOf(st);
			if (rtp > 0) {
				return rtp;
			} else {
				System.out.println("loginPort is Undefined, use default 5222");
				return 5222;
			}
		} else {
			System.out.println("loginPort is Undefined, use default 5222");
			return 5222;
		}
	}

	public void setLoginPort(String loginPort) {
		this.loginPort = loginPort;
	}

	public String getUserName() {
		String st = map.get("userName");
		if (st == null) {
			System.out.println("userName is Undefined");
			System.exit(1);
		}
		return st;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		String st = map.get("passWord");
		if (st == null) {
			System.out.println("passWord is Undefined");
			System.exit(1);
		}
		return st;
	}

	public void setpassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getLoginUrl() {
		String st = map.get("loginUrl");
		if (st == null) {
			System.out.println("loginUrl is Undefined");
			System.exit(1);
		}
		return st;
	}

	public void setloginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public int getUserNo() {
		String st = map.get("userNo");
		if (st != null) {
			int rtp = Integer.valueOf(st);
			if (rtp > 0) {
				return rtp;
			} else {
				System.out.println("userNo is Undefined, use default 1");
				return 2;
			}
		} else {
			System.out.println("userNo is Undefined, use default 1");
			return 2;
		}
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public int getToUserNo() {
		String st = map.get("toUserNo");
		if (st != null) {
			int rtp = Integer.valueOf(st);
			if (rtp > 0) {
				return rtp;
			} else {
				System.out.println("toUserNo is Undefined, use default 1");
				return 2;
			}
		} else {
			System.out.println("toUserNo is Undefined, use default 1");
			return 2;
		}
	}

	public void setToUserNo(String toUserNo) {
		this.toUserNo = toUserNo;
	}

	public String getToUsers() {
		String st = map.get("toUsers");
		if (st == null) {
			System.out.println("toUsers is Undefined");
			System.exit(1);
		}
		return st;
	}

	public void setToUsers(String toUsers) {
		this.toUsers = toUsers;
	}

	public String getToUrl() {
		String st = map.get("toUrl");
		if (st == null) {
			System.out.println("toUrl is Undefined");
			System.exit(1);
		}
		return "@" + st;
	}

	public void setToUrl(String toUrl) {
		this.toUrl = toUrl;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		Properties pps = new Properties();
		try {
			pps.load(new FileInputStream("lib/config.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Enumeration<?> enum0 = pps.propertyNames();// 得到配置文件的名字
		while (enum0.hasMoreElements()) {
			String strKey = (String) enum0.nextElement();
			String strValue = pps.getProperty(strKey);
			map.put(strKey, strValue);
		}
	}

}
