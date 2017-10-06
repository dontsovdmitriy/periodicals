package model.entity.invoice;

import java.time.LocalDate;

import model.entity.subscription.Subscription;

//TODO нужно ли делать сущности Serialasible?
public class Invoice {
	private long id;
	private long cost;
	private LocalDate creationDate;
	private LocalDate paymentDate;
	private Subscription subscription;
	private Status status;

	public enum Status{
		PAID, UNPAID;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}

	public static class Builder{
		private Invoice invoice;

		public Builder() {
			invoice = new Invoice();
		}

		public Builder setId(long id) {
			invoice.setId(id);
			return this;
		}

		public Builder setCost(long cost) {
			invoice.setCost(cost);
			return this;
		}

		public Builder setCreationDate(LocalDate creationDate) {
			invoice.setCreationDate(creationDate);
			return this;
		}

		public Builder setPaymentDate(LocalDate paymentDate) {
			invoice.setPaymentDate(paymentDate);
			return this;
		}

		public Builder setSubscription(Subscription subscription) {
			invoice.setSubscription(subscription);
			return this;
		}

		public Builder setStatus(Status status) {
			invoice.setStatus(status);
			return this;
		}
		public Invoice build() {
			return invoice;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (subscription != null ? subscription.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        
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

	        Invoice invoice = (Invoice) obj;

	        if (id != invoice.id) {
	            return false;
	        }
	        if (subscription != null ? !subscription.equals(invoice.subscription) : invoice.subscription != null) {
	            return false;
	        }
	        if (creationDate != null ? !creationDate.equals(invoice.creationDate) : invoice.creationDate != null) {
	            return false;
	        }
	        if (paymentDate != null ? !paymentDate.equals(invoice.paymentDate) : invoice.paymentDate != null) {
	            return false;
	        }
	        if (status != null ? !status.equals(invoice.status) : invoice.status != null) {
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
