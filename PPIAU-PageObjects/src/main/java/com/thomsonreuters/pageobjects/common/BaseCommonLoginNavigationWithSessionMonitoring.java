package com.thomsonreuters.pageobjects.common;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.thomsonreuters.pageobjects.rest.service.impl.RestServiceFFHImpl;
import com.thomsonreuters.pageobjects.utils.CobaltUser;

public class BaseCommonLoginNavigationWithSessionMonitoring extends BaseCommonLoginNavigation {

	private RestServiceFFHImpl restServiceFFHImpl = new RestServiceFFHImpl();
	private static ThreadLocal<Thread> receivingSessionIdThread = new ThreadLocal<>();

	@Override
	protected void loginUser(CobaltUser plPlusUser, boolean isVerifyUserLoggedForSkipLoginCase) throws IOException {
		super.loginUser(plPlusUser, isVerifyUserLoggedForSkipLoginCase);
		if (StringUtils.isBlank(currentUser.getSessionId())) {
			startReceivingSessionIdInSeparateThread();
		}
	}

	private void startReceivingSessionIdInSeparateThread() {
		if (receivingSessionIdThread.get() == null
				|| (receivingSessionIdThread.get() != null && !receivingSessionIdThread.get().isAlive())) {
			receivingSessionIdThread.set(new Thread("New Thread") {
				@Override
				public void run() {
					while (StringUtils.isBlank(currentUser.getSessionId())) {
						String sessionId = restServiceFFHImpl.getCurrentSession();
						currentUser.setSessionId(sessionId);
					}
				}
			});
			receivingSessionIdThread.get().start();
		}
	}

	public void removeReceivingSessionIdThread()
	{
		receivingSessionIdThread.remove();
	}

}
