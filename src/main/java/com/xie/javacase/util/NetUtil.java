package com.xie.javacase.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetUtil {
	public static void getLocalAddress() throws SocketException {
		Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress ip = null;
		while (allNetInterfaces.hasMoreElements())
		{
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
			Enumeration addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements())
			{
				ip = (InetAddress) addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address)
				{
					System.out.println("本机的IP = " + netInterface.getName() + ":" + ip.getHostAddress());
				}
			}
		}
	}
}
