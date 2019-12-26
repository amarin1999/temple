package com.cdgs.temple.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


public class MemberDto implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4601867391972524109L;
	private Long id;
	private String username;
	private String password;
	private String fname;
	private String lname;
	private String address;
	private String tel;
	private String emergencyTel;
	private String email;
	private String img;
	private LocalDateTime registerDate;
	private LocalDateTime lastUpdate;
	
	private Long roleId;
	private String roleName;
	
	private Long titleId;
	private String titleDisplay;
	private String titleName;
	
	private Long genderId;
	private String genderName;

	private String job;
	private String other;
	private String blood;
	
	/* ส่วนที่เพิ่มเติม */
	private String allergyFood;
	private String allergyMedicine;
	private String disease;
	private String emergencyName;
	private String emergencyRelationship;
	private boolean enable = true;
	
	private String idCard;
	private Long age;
	private Long ordianNumber;
	private LocalDateTime ordianDate;
	private String postalCode;
	
	private Long provinceId;
	private String provinceName;
//	private Long regionId;
//	private String regionName;
	private List<HistoryDharmaDto> historyDharma;
	
	
	
	public List<HistoryDharmaDto> getHistoryDharma() {
		return historyDharma;
	}
	public void setHistoryDharma(List<HistoryDharmaDto> historyDharma) {
		this.historyDharma = historyDharma;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public Long getOrdianNumber() {
		return ordianNumber;
	}
	public void setOrdianNumber(Long ordianNumber) {
		this.ordianNumber = ordianNumber;
	}
	public LocalDateTime getOrdianDate() {
		return ordianDate;
	}
	public void setOrdianDate(LocalDateTime ordianDate) {
		this.ordianDate = ordianDate;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public Long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getGenderName() {
		return genderName;
	}
	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}
	public String getTitleDisplay() {
		return titleDisplay;
	}
	public void setTitleDisplay(String titleDisplay) {
		this.titleDisplay = titleDisplay;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmergencyTel() {
		return emergencyTel;
	}
	public void setEmergencyTel(String emergencyTel) {
		this.emergencyTel = emergencyTel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public LocalDateTime getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getTitleId() {
		return titleId;
	}
	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}
	public Long getGenderId() {
		return genderId;
	}
	public void setGenderId(Long genderId) {
		this.genderId = genderId;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getBlood() {
		return blood;
	}
	public void setBlood(String blood) {
		this.blood = blood;
	}
	public String getAllergyFood() {
		return allergyFood;
	}
	public void setAllergyFood(String allergyFood) {
		this.allergyFood = allergyFood;
	}
	public String getAllergyMedicine() {
		return allergyMedicine;
	}
	public void setAllergyMedicine(String allergyMedicine) {
		this.allergyMedicine = allergyMedicine;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getEmergencyName() {
		return emergencyName;
	}
	public void setEmergencyName(String emergencyName) {
		this.emergencyName = emergencyName;
	}
	public String getEmergencyRelationship() {
		return emergencyRelationship;
	}
	public void setEmergencyRelationship(String emergencyRelationship) {
		this.emergencyRelationship = emergencyRelationship;
	}
	public boolean getEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
		
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	
	
	
	
	
	
}
