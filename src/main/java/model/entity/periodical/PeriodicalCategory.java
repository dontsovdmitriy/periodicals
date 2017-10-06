package model.entity.periodical;

public class PeriodicalCategory {
	private long id;
	private String categoryName;
	
	public static class Builder{
		private PeriodicalCategory periodicalCategory;
		
		public Builder() {
			periodicalCategory = new PeriodicalCategory();
        }
		public Builder setId(long id) {
			periodicalCategory.setId(id);
			return this;
		}
		public Builder setCategoryName(String categoryName) {
			periodicalCategory.setCategoryName(categoryName);
			return this;
		}		
		public PeriodicalCategory build() {
			return periodicalCategory;
		}
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
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

		 PeriodicalCategory periodicalCategory = (PeriodicalCategory) obj;

		if (id != periodicalCategory.id) {
			return false;
		}
		if (categoryName != null ? !categoryName.equals(periodicalCategory.categoryName) : periodicalCategory.categoryName != null) {
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
