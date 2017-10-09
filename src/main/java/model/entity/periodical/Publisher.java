package model.entity.periodical;

public class Publisher {
	private long id;
	private String publisher;
	
	public static class Builder{
		private Publisher publisher;
		
		public Builder() {
			publisher = new Publisher();
        }
		public Builder setId(long id) {
			publisher.setId(id);
			return this;
		}		
		public Builder setPublisher(String publisherName) {
			publisher.setPublisher(publisherName);
			return this;
		}		
		public Publisher build() {
			return publisher;
		}
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Publisher publisher = (Publisher) obj;

		if (id != publisher.id) {
			return false;
		}
		if (publisher != null ? !publisher.equals(publisher.publisher) : publisher.publisher != null) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
	    return String.format("Publisher{id=%d, publisher='%s'}",
        id, publisher);
	}	
}
