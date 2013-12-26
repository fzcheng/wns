package admins.ben;

/**
 * 渠道channel
 */
public class SectionVO {


	/* 分区id */
	int sectionId;
 
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
 
	public String getDsc() {
		return dsc;
	}
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	/* 渠道名称  */
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	String sectionName;
	String dsc;
}
