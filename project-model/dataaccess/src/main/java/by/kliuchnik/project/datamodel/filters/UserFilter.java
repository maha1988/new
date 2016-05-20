package by.kliuchnik.project.datamodel.filters;
import javax.persistence.metamodel.SingularAttribute;


public class UserFilter {
		
	    private String userName;
	    private SingularAttribute sortProperty;
	    private boolean sortOrder;
	    private Integer offset;
	    private Integer limit;

	    private boolean isFetchUser;

	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public SingularAttribute getSortProperty() {
	        return sortProperty;
	    }

	    public void setSortProperty(SingularAttribute sortProperty) {
	        this.sortProperty = sortProperty;
	    }

	    public boolean isSortOrder() {
	        return sortOrder;
	    }

	    public void setSortOrder(boolean sortOrder) {
	        this.sortOrder = sortOrder;
	    }

	    public Integer getOffset() {
	        return offset;
	    }

	    public void setOffset(Integer offset) {
	        this.offset = offset;
	    }

	    public Integer getLimit() {
	        return limit;
	    }

	    public void setLimit(Integer limit) {
	        this.limit = limit;
	    }

	    public boolean isFetchUser() {
	        return isFetchUser;
	    }

	    public void setFetchUser(boolean isFetchUser) {
	        this.isFetchUser = isFetchUser;
	    }

	}


