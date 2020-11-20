package com.sec.security;

import java.io.Serializable;

import com.global.message.Message;



public class Response implements Serializable {
	
	private static final long serialVersionUID = 4862364905929104829L;
	
	private String token;
	private Message message;
	
	public Response(String token) {
		this.token = token;
	}
	
	public Response(Message message) {
		this.message = message;
	}

	
}
