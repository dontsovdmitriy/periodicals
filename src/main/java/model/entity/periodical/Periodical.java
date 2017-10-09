package model.entity.periodical;

public class Periodical {
	private long id;
	private String name;
	private String description;
	private long costPerMonth;
	private Publisher publisher;
	private PeriodicalCategory periodicalCategory;
	private Status status;

	public enum Status{
		ACTIV, INACTIV;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}
	public static class Builder{
		private Periodical periodical;

		public Builder() {
			periodical = new Periodical();
		}

		public Builder setId(long id) {
			periodical.setId(id);
			return this;
		}
		public Builder setName(String name) {
			periodical.setName(name);
			return this;
		}
		public Builder setDescription(String description) {
			periodical.setDescription(description);
			return this;
		}
		public Builder setCostPerMonth(long costPerMonth) {
			periodical.setCostPerMonth(costPerMonth);
			return this;
		}
		public Builder setPublisher(Publisher publisher) {
			periodical.setPublisher(publisher);
			return this;
		}
		public Builder setPeriodicalCategory(PeriodicalCategory periodicalCategory) {
			periodical.setPeriodicalCategory(periodicalCategory);
			return this;
		}	
		public Builder setStatus(Status status) {
			periodical.setStatus(status);
			return this;
		}	
		public Periodical build() {
			return periodical;
		}
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCostPerMonth() {
		return costPerMonth;
	}

	public void setCostPerMonth(long costPerMonth) {
		this.costPerMonth = costPerMonth;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public PeriodicalCategory getPeriodicalCategory() {
		return periodicalCategory;
	}

	public void setPeriodicalCategory(PeriodicalCategory periodicalCategory) {
		this.periodicalCategory = periodicalCategory;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
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

		Periodical periodical = (Periodical) obj;

		if (id != periodical.id) {
			return false;
		}
		if (name != null ? !name.equals(periodical.name) : periodical.name != null) {
			return false;
		}
		if (publisher != null ? !publisher.equals(periodical.publisher) : periodical.publisher != null) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		 return String.format("Periodical{id=%d,name='%s' description='%s', costPerMonth=%d, " +
	                "publisher='%s', periodicalCategory='%s', status='%s'}",
	                id, name, description, costPerMonth, publisher, periodicalCategory, status);
	}		
}
