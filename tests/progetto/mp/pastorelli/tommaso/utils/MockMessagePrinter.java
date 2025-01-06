package progetto.mp.pastorelli.tommaso.utils;


public class MockMessagePrinter implements MessagePrinter{
	
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
