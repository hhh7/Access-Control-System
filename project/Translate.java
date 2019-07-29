package project;


public class Translate {
	public static String bytes2HexString(byte[] b) {
		  String ret = "";
		  for (int i = 0; i < b.length; i++) {
		   String hex = Integer.toHexString(b[ i ] & 0xFF);
		   if (hex.length() == 1) {
		    hex = '0' + hex;
		   }
		   ret += hex.toUpperCase();
		  }
		  return ret;
		}
	public static byte[] HexString2Bytes(String src){ 
		byte[] ret = new byte[14]; 
		byte[] tmp = src.getBytes(); 
		for(int i=0; i<14; i++){ 
		ret[i] = uniteBytes(tmp[i*2], tmp[i*2+1]); 
		} 
		return ret; 
		} 
	public static byte uniteBytes(byte src0, byte src1) { 
		byte _b0 = Byte.decode("0x" + new String(new byte[]{src0})).byteValue(); 
		_b0 = (byte)(_b0 << 4); 
		byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue(); 
		byte ret = (byte)(_b0 ^ _b1); 
		return ret; 
		} 

	public static void main(String[] args) {
		

	}

}
