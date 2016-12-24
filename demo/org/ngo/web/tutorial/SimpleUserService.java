package org.ngo.web.tutorial;

@RemoteService
public class SimpleUserService implements UserService {

	@Override
	public User findUser(long userId) {
		System.out.println("local service invoke.");
		return new User(userId,"LocalUser",30);
	}

}
