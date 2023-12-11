package com.djiteye.ablo.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageResponse {
	public String message;
	
	public MessageResponse(String msg) {
		this.message = msg;
	}

}
