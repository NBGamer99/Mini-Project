package me.ynabouzi.miniproject.controllers.error;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
@Named(value = "ErrorMessageController")
@SessionScoped
public class ErrorMessageController implements Serializable {
	private String errorMessage;
	public String getErrorMessage() {
		String msg = errorMessage;
		errorMessage = null;
		return msg;
	}
}
