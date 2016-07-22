package com.tq.requisition.presentation.actions.share;

public class ConstValue {
	private final static String POLICY_FILE = "/policy_files";
	private final static String HOUSE_IMG= "/house_imgs";
	private final static String EVIDENCE_FILE = "/evidence_files";
	
	public static String getType(String type){
		switch (type) {
		case "POLICY_FILE":
			return POLICY_FILE;
		case "HOUSE_IMG":
			return HOUSE_IMG;
		case "EVIDENCE_FILE":
			return EVIDENCE_FILE;
		default:
			return "";
		}
	}
}
