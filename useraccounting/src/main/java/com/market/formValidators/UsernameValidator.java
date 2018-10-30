package com.market.formValidators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class UsernameValidator {

	public int ValidateUsername(final String uname) {

		if (NullOrEmpty(uname)) {
			return 1;
		} else if (ViolatedPattern(uname)) {
			return 2;
		} else if (TooShort(uname)) {
			return 3;
		} else if (TooLong(uname)) {
			return 4;
		}
		return 0;
	}

	private boolean NullOrEmpty(final String uname) {

		if (uname == null) {
			return true;
		} else if (uname.equals("")) {
			return true;
		} else if (uname.length() < 1) {
			return true;
		} else {
			return false;
		}
	}

	private boolean ViolatedPattern(final String uname) {

		Pattern pattern = Pattern.compile("^[a-zA-Z](([a-zA-Z0-9])|[a-zA-Z0-9])*[a-z0-9]$");
		Matcher matcher = pattern.matcher(uname);

		if (!matcher.matches()) {
			return true;
		}
		return false;
	}

	private boolean TooShort(final String uname) {
		if (uname.length() <= 3) {
			return true;
		}
		return false;
	}

	private boolean TooLong(final String uname) {
		if (uname.length() > 20) {
			return true;
		}
		return false;
	}
}
