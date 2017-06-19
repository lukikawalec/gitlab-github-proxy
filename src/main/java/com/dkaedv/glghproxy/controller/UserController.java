package com.dkaedv.glghproxy.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.egit.github.core.Repository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.beans.factory.annotation.Autowired;
import com.dkaedv.glghproxy.gitlabclient.GitlabSessionProvider;
import org.gitlab.api.GitlabAPI;
import org.eclipse.egit.github.core.User;
import org.gitlab.api.models.GitlabUser;
import com.dkaedv.glghproxy.converter.GitlabToGithubConverter;

@Controller
@RequestMapping("/api/v3/user")
public class UserController {
	private static final Log LOG = LogFactory.getLog(UserController.class);

	@Autowired
	private GitlabSessionProvider gitlab;

	@RequestMapping("")
	@ResponseBody
	public User getUser(@RequestHeader("Authorization") String authorization) throws IOException {
		LOG.info("Authorization" + authorization);
		GitlabAPI api = gitlab.connect(authorization);
		GitlabUser user = api.getUser();
		return GitlabToGithubConverter.convertUser(user);
	}

	@RequestMapping("/repos")
	@ResponseBody
	public List<Repository> getReposForCurrentUser(
			@RequestParam String per_page,
			@RequestParam String page,
			@RequestHeader("Authorization") String authorization) throws IOException {

		return Collections.emptyList();
	}

}
