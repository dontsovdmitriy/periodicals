package model.entity.user;

public class User {
	private long id;
	private String name;
	private String surname;
	private String mobilePhone;
	private String email;
	private Type type;
	private String login;
	private String password;

	public enum Type{
		ADMIN, USER;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}
	
	public static class Builder{
		private User user;
		
		public Builder() {
			user = new User();
        }
		public Builder setId(long id) {
			user.setId(id);
			return this;
		}
		public Builder setName(String name) {
			user.setName(name);
			return this;
		}

		public Builder setSurname(String surname) {
			user.setSurname(surname);
			return this;
		}

		public Builder setMobilePhone(String mobilePhone) {
			user.setMobilePhone(mobilePhone);
			return this;
		}

		public Builder setEmail(String email) {
			user.setEmail(email);
			return this;
		}
		public Builder setType(Type type) {
			user.setType(type);
			return this;
		}
		public Builder setLogin(String login) {
			user.setLogin(login);
			return this;
		}
		public Builder setPassword(String password) {
			user.setPassword(password);
			return this;
		}
		
		public User build() {
			return user;
		}
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		 int result = (int) (id ^ (id >>> 32));
	        result = 31 * result + (email != null ? email.hashCode() : 0);
	        result = 31 * result + (login != null ? login.hashCode() : 0);
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

	        User user = (User) obj;

	        if (id != user.id) {
	            return false;
	        }
	        if (email != null ? !email.equals(user.email) : user.email != null) {
	            return false;
	        }
	        if (login != null ? !login.equals(user.login) : user.login != null) {
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

