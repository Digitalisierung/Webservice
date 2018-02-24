package model.dto;

import java.io.Serializable;

public class ApprovalDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean approval;
	
	public ApprovalDTO() {
		
	}

	public Boolean getApproval() {
		return approval;
	}

	public void setApproval(Boolean approval) {
		this.approval = approval;
	}

}
