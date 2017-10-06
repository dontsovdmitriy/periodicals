package model.entity.subscription;

import java.time.LocalDate;

import model.entity.periodical.Periodical;
import model.entity.periodical.Publisher;
import model.entity.user.User;

public class Subscription {
	private long id;
	private User user;
	private Periodical periodical;
	private LocalDate startDate;
	private int numberMonth;
	private String address;
		
	public static class Builder{
		private Subscription subscription;
		
		public Builder() {
			subscription = new Subscription();
        }
		
		public Builder setId(long id) {
			subscription.setId(id);
			return this;
		}
		public Builder setUser(User user) {
			subscription.setUser(user);
			return this;
		}
		public Builder setPeriodical(Periodical periodical) {
			subscription.setPeriodical(periodical);
			return this;
		}
		public Builder setStartDate(LocalDate startDate) {
			subscription.setStartDate(startDate);
			return this;
		}
		public Builder setNumberMonth(int numberMonth) {
			subscription.setNumberMonth(numberMonth);
			return this;
		}
		public Builder setAddress(String address) {
			subscription.setAddress(address);
			return this;
		}
				
		public Subscription build() {
			return subscription;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Periodical getPeriodical() {
		return periodical;
	}

	public void setPeriodical(Periodical periodical) {
		this.periodical = periodical;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getNumberMonth() {
		return numberMonth;
	}

	public void setNumberMonth(int numberMonth) {
		this.numberMonth = numberMonth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (user != null ? user.hashCode() : 0);
		result = 31 * result + (periodical != null ? periodical.hashCode() : 0);
		result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
		result = 31 * result + numberMonth;
		result = 31 * result + (address != null ? address.hashCode() : 0);

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

		Subscription subscription = (Subscription) obj;
		
		if (id != subscription.id) {
			return false;
		}
		if (user != null ? !user.equals(subscription.user) : subscription.user != null) {
			return false;
		}
		if (periodical != null ? !user.equals(subscription.periodical) : subscription.periodical != null) {
			return false;
		}
		if (startDate != null ? !user.equals(subscription.startDate) : subscription.startDate != null) {
			return false;
		}
		if (numberMonth != subscription.numberMonth) {
			return false;
		}
		if (address != null ? !address.equals(subscription.address) : subscription.address != null) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}	
}
