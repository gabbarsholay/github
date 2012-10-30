package com.decisionhub.common.helper;

import java.util.List;

import com.decisionhub.common.data.User;
import com.decisionhub.service.controller.ISocialNetworkService;
import com.decisionhub.service.controller.UserService;
import com.decisionhub.web.beans.UserBean;

public class UserHelper {
		
	/** log user activity **/
	public static void logUserActivity(UserBean userBean,
			ISocialNetworkService faceBookService,
			UserService userService) {
		
		// calculate total number of friends 
		User user = userBean.getUser();
		List<com.decisionhub.common.data.User> listFriends = faceBookService
				.loadFriends(user, user.getSocialNetworkId() + "");

		user.setListOfFriends(listFriends);
		
		Long size = new Long(0);
		if (listFriends != null && listFriends.size() > 0) {

			size = new Long(listFriends.size()); 
		}			
		user.setTotalFriends(size);
		
		userBean.setUser(user);
		
		// insert into login history and update user for total no. of friends
		userService.insertLoginHistoryAndUpdateUserAttr(user);
	}
}
changed on server.