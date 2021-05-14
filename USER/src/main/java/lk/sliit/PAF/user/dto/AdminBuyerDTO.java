package lk.sliit.PAF.user.dto;

public class AdminBuyerDTO {
    private int id;
    private String fName;
    private String lName;
    private String email;
    private String contactNo;
    private String address;
    private String zipCode;
    private String password;

    public AdminBuyerDTO() {
    }

    public AdminBuyerDTO(int id, String fName, String lName, String email, String contactNo, String address, String zipCode, String password) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.contactNo = contactNo;
        this.address = address;
        this.zipCode = zipCode;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminBuyerDTO{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AdminBuyerDTO other = (AdminBuyerDTO) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
