package progetto.mp.pastorelli.tommaso;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import progetto.mp.pastorelli.tommaso.utils.ChatEventPrinter;
import progetto.mp.pastorelli.tommaso.utils.MockChatEventPrinter;


public class TestSubCommunity {

	private ChatEventPrinter chatPrinter;
	private TextChat chat1;
	private TextChat chat2;
	private User user1;
	private User user2;
	private SubCommunity subCommunity1;
	private SubCommunity subCommunity2;
	
	@Before
	public void before() {
		chatPrinter = new MockChatEventPrinter();
		chat1 = new TextChat("group1", "this is group1");
		chat2 = new TextChat("group2", "this is group2");
		user1 = new User("User", "1", chatPrinter);
		user2 = new User("User", "2", chatPrinter);
		subCommunity1 = new SubCommunity("Community1", "this is Community1");
		subCommunity2 = new SubCommunity("Community2", "this is Community2");
	}
	
	@Test
	public void testSubCommunityAdd() {
		subCommunity1.getCommunities().add(chat1);
		assertEquals(subCommunity1.getCommunities().size(), 1);
		assertThat(subCommunity1.getCommunities())
			.contains(chat1);
	}
	
	@Test
	public void testSubCommunityRemove() {
		subCommunity1.getCommunities().add(chat1);
		subCommunity1.getCommunities().add(chat2);
		subCommunity1.getCommunities().remove(chat1);
		assertEquals(subCommunity1.getCommunities().size(), 1);
		assertThat(subCommunity1.getCommunities())
			.contains(chat2);
		assertThat(subCommunity1.getCommunities())
			.doesNotContain(chat1);
	}
	
	@Test
	public void testSubCommunityRename() {
		subCommunity1.rename("New Community");
		assertEquals(subCommunity1.getName(), "New Community");
	}
	 
	@Test
	public void testSubCommunityChangeDescription() {
		subCommunity1.changeDescription("New Description");
		assertEquals(subCommunity1.getDescription(), "New Description");
	}
	
	@Test
	public void testSubCommunityIterator() {
		subCommunity1.getCommunities().add(subCommunity2);
		subCommunity1.getCommunities().add(chat1);
		assertThat(subCommunity1.iterator())
			.toIterable()
				.containsExactlyInAnyOrder(chat1,subCommunity2);
	}
	
	@Test
	public void testAddMessageToSubCommunity() {
		chat1.addUser(user1);
		chat2.addUser(user1);
		chat2.addUser(user2);
		subCommunity2.getCommunities().add(chat2);
		subCommunity1.getCommunities().add(subCommunity2);
		subCommunity1.getCommunities().add(chat1);
		Message msg1 = user1
						.sendMessage(subCommunity1, "Hello");
		Message msg2 = user2
						.sendMessage(subCommunity1, "Hi");
		assertThat(chat1.getMessages())
			.contains(msg1);
		assertThat(chat2.getMessages())
			.contains(msg1);
		assertThat(chat1.getMessages())
			.doesNotContain(msg2);
		assertThat(chat2.getMessages())
			.contains(msg2);
	}

}
