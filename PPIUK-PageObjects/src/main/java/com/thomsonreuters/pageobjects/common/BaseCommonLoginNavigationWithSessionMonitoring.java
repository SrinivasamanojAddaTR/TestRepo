package com.thomsonreuters.pageobjects.common;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.thomsonreuters.pageobjects.rest.service.impl.RestServiceFFHImpl;
import com.thomsonreuters.pageobjects.utils.CobaltUser;

public class BaseCommonLoginNavigationWithSessionMonitoring extends BaseCommonLoginNavigation {

	private RestServiceFFHImpl restServiceFFHImpl = new RestServiceFFHImpl();
	private static Thread receivingSessionIdThread;

	@Override
	protected void loginUser(CobaltUser plPlusUser, boolean isVerifyUserLoggedForSkipLoginCase)
			throws InterruptedException, IOException {
		super.loginUser(plPlusUser, isVerifyUserLoggedForSkipLoginCase);
		if (StringUtils.isBlank(currentUser.getSessionId())) {
			startReceivingSessionIdInSeparateThread();
		}
	}

	private void startReceivingSessionIdInSeparateThread() {
		if (receivingSessionIdThread == null
				|| (receivingSessionIdThread != null && !receivingSessionIdThread.isAlive())) {
			receivingSessionIdThread = new Thread("New Thread") {
				public void run() {
					while (StringUtils.isBlank(currentUser.getSessionId())) {
						String sessionId = restServiceFFHImpl.getCurrentSession();
						currentUser.setSessionId(sessionId);
					}
				}
			};
			receivingSessionIdThread.start();
		}
	}

}
