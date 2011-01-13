package core;

/**
 * 
 */
public class Text implements Categorizable {

	private String text;
	private boolean category;

	public Text(String text) {
		this.text = text;
	}

	public Text(String text, boolean category) {
		this.text = text;
		this.category = category;
	}

	public String getText() {
		return text;
	}

	@Override
	public boolean getCategory() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void setCategory(boolean category) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String toString() {
		return this.text;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Text)) {
			return false;
		}
		Text o = (Text) obj;
		if (this.getText().equals(o.getText())
		    && this.category == o.getCategory()) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 71 * hash + (this.text != null ? this.text.hashCode() : 0);
		hash = 71 * hash + (this.category ? 1 : 0);
		return hash;
	}
}
