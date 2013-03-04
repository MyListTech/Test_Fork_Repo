package bridge;

import model.User;

import org.hibernate.search.bridge.StringBridge;


/**
 * 更多：http://docs.jboss.org/hibernate/search/3.3/reference/en-US/html/search-
 * mapping.html#search-mapping-bridge
 */
public class UserBridge implements StringBridge {

	@Override
	public String objectToString(Object object) {
		User user = (User) object;
		if(user == null)
			return null;
		return  "发表人:" + user.getUsername() + ",这定义数据.";
	}

}
