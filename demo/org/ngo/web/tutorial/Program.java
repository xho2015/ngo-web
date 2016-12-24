package org.ngo.web.tutorial;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		new Program().test();
	}
	
	public void test()
	{
		UserService service = (UserService)ServiceLocator.Instance.get(SimpleUserService.class);
		User a = service.findUser(12);
		System.out.println(a);
		
		service = (UserService)ServiceLocator.Instance.get(SimpleUserService.class);
		User b = service.findUser(13);
		System.out.println(b);	
	}

}
