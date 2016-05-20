package com.kliuchnik.project.dataaccess.filters;
import javax.persistence.metamodel.SingularAttribute;

import com.kliuchnik.project.datamodel.Role;



public class CustomerFilter extends AbstractFilter{
		
	 	private String name;
	 	private String password;
	 	private Role role;
	 	private String address;
	 	private String bankR;
	 	
		

	    private boolean isFetchUser;
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
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getBankR() {
			return bankR;
		}
		public void setBankR(String bankR) {
			this.bankR = bankR;
		}
		
				public boolean isFetchOrder() {
			return isFetchOrder;
		}
		public void setFetchOrder(boolean isFetchOrder) {
			this.isFetchOrder = isFetchOrder;
		}
		public boolean isFetchUser() {
			return isFetchUser;
		}
		public void setFetchUser(boolean isFetchUser) {
			this.isFetchUser = isFetchUser;
		}

	   
	    
	    
}

		


