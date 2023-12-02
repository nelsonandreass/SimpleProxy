package com.proxy.proxy.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.util.Base64Utils;

public class Encoder {
	

	public String encoder64(String original) {
		byte[] encodedBytes = Base64Utils.encode(original.getBytes());
		System.out.println(new String(encodedBytes));
		return new String(encodedBytes);
	}
	
	public static String hashString(String input) {
		try {
			// Create an instance of the SHA-256 algorithm
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

	        // Get the byte array of the input string
	        byte[] encodedhash = digest.digest(input.getBytes());

	        // Convert the byte array to a hexadecimal string
	        StringBuilder hexString = new StringBuilder();
	        for (byte b : encodedhash) {
	        	String hex = Integer.toHexString(0xff & b);
	        	if (hex.length() == 1) {
	        		hexString.append('0');
	             }
	             hexString.append(hex);
	         }

	         return hexString.toString();

	     } catch (NoSuchAlgorithmException e) {
	            // Handle the exception (e.g., log it or throw a custom exception)
	            e.printStackTrace();
	            return null;
	     }
	}
}
