package com.eshopweb.domain.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public class Authority implements GrantedAuthority{
	private final String authority;

	public Authority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}
}
