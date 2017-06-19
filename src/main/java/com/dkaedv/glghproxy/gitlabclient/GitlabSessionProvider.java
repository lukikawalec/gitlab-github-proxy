package com.dkaedv.glghproxy.gitlabclient;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.TokenType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GitlabSessionProvider {
	private final static Log LOG = LogFactory.getLog(GitlabSessionProvider.class);

	@Value("${gitlabUrl}")
	private String gitlabUrl;

	public GitlabAPI connect(String authorizationHeader) {
		String token = authorizationHeader.replaceAll("token ", "").replaceAll("Bearer ", "");

		GitlabAPI api = GitlabAPI.connect(gitlabUrl, token, TokenType.ACCESS_TOKEN);
		api.ignoreCertificateErrors(true);
		return api;
	}

	@PostConstruct
	public void logUrl() {
		LOG.info("Using Gitlab Base URL: " + gitlabUrl);
	}
}
