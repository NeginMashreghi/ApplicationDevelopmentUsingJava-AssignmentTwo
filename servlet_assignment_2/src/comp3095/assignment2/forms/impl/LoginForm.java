package comp3095.assignment2.forms.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comp3095.assignment2.Redirect;
import comp3095.assignment2.forms.Form;
import comp3095.assignment2.forms.FormField;

public class LoginForm extends Form {
	private FormField _username;
	private FormField _password;
	private FormField _rememberMe;

	public FormField getUsername() { return _username; }
	public FormField getPassword() { return _password; }
	public FormField getRememberMe() { return _rememberMe; }

	public LoginForm() {
		super.setAction("login")
			 .setMethod(POST);
		_username = new FormField("username");
		_username.mustBeNonEmpty().mustBeAlphabetic();
		_password = new FormField("password");
		_password.mustBeNonEmpty();
		_rememberMe = new FormField("remember_me");

		addFields(_username, _password, _rememberMe);
	}
	@Override
	protected void onFail(HttpSession session, HttpServletResponse response, String message) throws IOException {
		Redirect.loginPage(response, false);
	}
}
