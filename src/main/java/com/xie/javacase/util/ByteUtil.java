package com.xie.javacase.util;

public class ByteUtil {

	/**
	 * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。
	 * @param value 要转换的int值
	 * @return byte数组
	 */
	public static byte[] intToBytes(int value)
	{
		byte[] byte_src = new byte[4];
		byte_src[3] = (byte) ((value & 0xFF000000)>>24);
		byte_src[2] = (byte) ((value & 0x00FF0000)>>16);
		byte_src[1] = (byte) ((value & 0x0000FF00)>>8);
		byte_src[0] = (byte) ((value & 0x000000FF));
		return byte_src;
	}

	/**
	 * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序。
	 * @param ary byte数组
	 * @param offset 从数组的第offset位开始
	 * @return int数值
	 */
	public static int bytesToInt(byte[] ary, int offset) {
		int value;
		value = (int) ((ary[offset]&0xFF)
				| ((ary[offset+1]<<8) & 0xFF00)
				| ((ary[offset+2]<<16)& 0xFF0000)
				| ((ary[offset+3]<<24) & 0xFF000000));
		return value;
	}
}
