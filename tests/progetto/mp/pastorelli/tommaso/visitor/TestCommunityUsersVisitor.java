package progetto.mp.pastorelli.tommaso.visitor;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import progetto.mp.pastorelli.tommaso.SubCommunity;
import progetto.mp.pastorelli.tommaso.TextChat;
import progetto.mp.pastorelli.tommaso.User;
import progetto.mp.pastorelli.tommaso.utils.MockChatEventPrinter;


public class TestCommunityUsersVisitor {


	private CommunityUsersVisitor visitor;
	private User user1;
	private User user2;
	private User user3;
	private TextChat chat1;
	private TextChat chat2;
	
	@Before
	public void before() {
		visitor = new CommunityUsersVisitor();
		user1 =  new User("User", "1", new MockChatEventPrinter());
		user2 =  new User("User", "2", new MockChatEventPrinter());
		user3 =  new User("User", "3", new MockChatEventPrinter());
		chat1 = new TextChat("group1", "this is group1");
		chat2 = new TextChat("group2", "this is group2");
	}
	
	@Test
	public void testTextChatVisitorWithoutUsers() {
		chat1.accept(visitor);
		assertThat(visitor.getUsers())
			.isEmpty();	
	}
	
	@Test
	public void testTextChatUsersVisitorWithUsers() {
		chat1.addUser(user1);
		chat1.addUser(user2);
		chat1.accept(visitor);
		assertEquals(visitor.getUsers().size(), 2);	
		assertThat(visitor.getUsers())
			.containsExactlyInAnyOrder(user1, user2);
	}
	
	@Test
	public void testEmptySubCommunityUsersVisitor() {
		SubCommunity subCommunity = new SubCommunity("Community", "this is a Community");
		subCommunity.accept(visitor);
		assertThat(visitor.getUsers())
			.isEmpty();
	}
	
	@Test
	public void testNotEmptySubCommunityUsersVisitor() {
		SubCommunity subCommunity = new SubCommunity("Community", "this is a Community");
		subCommunity.add(chat1);
		subCommunity.add(chat2);
		chat1.addUser(user1);
		chat1.addUser(user2);
		chat2.addUser(user1);
		chat2.addUser(user3);
		subCommunity.accept(visitor);
		assertEquals(visitor.getUsers().size(), 3);	
		assertThat(visitor.getUsers())
			.containsExactlyInAnyOrder(user1, user2, user3);
	}

}

