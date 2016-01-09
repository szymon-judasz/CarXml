package tools;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class HashMaker {
	public String getFileHash(String filename) throws FileNotFoundException
	{
		MessageDigest md;
		try {
			md =  MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return this.toString() + " algorithm not found";
		} 
		FileInputStream istream = new FileInputStream(filename);
		int character;
		try {
			while((character = istream.read()) != -1)
			{
				md.update((byte) character);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DatatypeConverter.printHexBinary(md.digest());
	}
	
	

}
