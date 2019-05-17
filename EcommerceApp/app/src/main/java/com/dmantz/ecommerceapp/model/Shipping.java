package com.dmantz.ecommerceapp.model;

// model class for shipping address
public class Shipping {

    private String area;
    private String city;
    private String customerId ;
    private String firstName;
    private String flatNo;
    private Integer id;
    private String landmark;
    private String lastFlg;
    private String lastName;
    private String middleName;
    private long mobileNo;
    private Integer pincode;
    private String primaryFlg;
    private String state;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getLastFlg() {
        return lastFlg;
    }

    public void setLastFlg(String lastFlg) {
        this.lastFlg = lastFlg;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getPrimaryFlg() {
        return primaryFlg;
    }

    public void setPrimaryFlg(String primaryFlg) {
        this.primaryFlg = primaryFlg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", customerId='" + customerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", flatNo='" + flatNo + '\'' +
                ", id=" + id +
                ", landmark='" + landmark + '\'' +
                ", lastFlg='" + lastFlg + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", mobileNo=" + mobileNo +
                ", pincode=" + pincode +
                ", primaryFlg='" + primaryFlg + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}