package progetto.mp.pastorelli.tommaso.utils;


public class MockChatEventPrinter implements ChatEventPrinter{

	private StringBuilder builder = new StringBuilder();
	
	@Override
	public void print(String text) {
		builder.append(text);
    }

    @Override
    public String toString() {
        return builder.toString();
    }

}
