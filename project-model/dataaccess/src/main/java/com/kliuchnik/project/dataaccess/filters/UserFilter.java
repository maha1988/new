package com.kliuchnik.project.dataaccess.filters;

import com.kliuchnik.project.datamodel.Role;

public class UserFilter  extends AbstractFilter{
		
	 	private String name;
	 	private String password;
	 	private Role role;
	 	
	
	    private boolean isFetchCustomer;
	    private boolean isFetchOrder;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Role getRole() {
			return role;
		}
		public void setRole(Role role) {
			this.role = role;
		}
		
				public boolean isFetchOrder() {
			return isFetchOrder;
		}
		public void setFetchOrder(boolean isFetchOrder) {
			this.isFetchOrder = isFetchOrder;
		}
		public boolean isFetchCustomer() {
			return isFetchCustomer;
		}
		public void setFetchCustomer(boolean isFetchCustomer) {
			this.isFetchCustomer = isFetchCustomer;
		}
		
	    
	    
}

		


